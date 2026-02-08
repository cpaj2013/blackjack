package org.blackjack;

import org.blackjack.constants.BlackjackConstants;
import org.blackjack.enums.Command;
import org.blackjack.service.BlackjackService;
import org.blackjack.utilities.CLIOutputUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class BlackjackApplication implements CommandLineRunner {

	BlackjackService blackjackService;



	@Autowired
	public BlackjackApplication(BlackjackService blackjackService) {
		this.blackjackService = blackjackService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Blackjack!");

		String userInput = "";

		do {
			// Menu level
			System.out.println("To start a game type 'Play' or 'P'");
			System.out.println("To exit, type 'Exit' or 'E'");
			userInput = scanner.nextLine();

			//  Check valid input
			if (!Command.isValidInput(userInput, Command.CommandType.MENU)) {
				System.out.println(CLIOutputUtils.generateOutputString("Invalid input value: " + userInput
						, BlackjackConstants.CLI_LINE_SEPARATOR));
			} else if (Objects.equals(Command.fromInput(userInput, Command.CommandType.MENU), Command.PLAY)) {
				// Gameplay Level
				System.out.println("Starting...");
				blackjackService.startGame();

				do {
					System.out.println(blackjackService.generatePlayerAndDealerCardAndScoreSummary());
					System.out.print("Type '(H)it', '(X) Stand', '(S)plit', or '(E)xit': ");

					userInput = scanner.nextLine();

					if (!Command.isValidInput(userInput, Command.CommandType.GAMEPLAY)) {
						System.out.println(CLIOutputUtils.generateOutputString("Invalid input value: " + userInput
								, BlackjackConstants.CLI_LINE_SEPARATOR));
					} else if (Objects.equals(Command.fromInput(userInput, Command.CommandType.GAMEPLAY), Command.HIT)) {
						if (!blackjackService.playerHit()) {
							break;
						}
						blackjackService.generatePlayerAndDealerCardAndScoreSummary();
					} else if (Objects.equals(Command.fromInput(userInput, Command.CommandType.GAMEPLAY), Command.STAND)) {
						if (!blackjackService.playerStand()) {
							blackjackService.simulateDealerHand();
							break;
						}
						break;
					} else if (Objects.equals(Command.fromInput(userInput, Command.CommandType.GAMEPLAY), Command.SPLIT)) {
						if (!blackjackService.playerSplit()) {
							System.out.println(CLIOutputUtils.generateOutputString("Cannot split current hand."
									, BlackjackConstants.CLI_LINE_SEPARATOR));
						}
					}

				} while (Objects.equals(Command.fromInput(userInput, Command.CommandType.GAMEPLAY), Command.HIT)
				|| Objects.equals(Command.fromInput(userInput, Command.CommandType.GAMEPLAY), Command.SPLIT)
				|| !Command.isValidInput(userInput, Command.CommandType.GAMEPLAY));

				// TODO maybe this check isn't necessary, because game should always be active at this point
				if (blackjackService.isGameActive()) {
					System.out.println(blackjackService.endGame());
				}
			}

		} while (!Objects.equals(Command.fromInput(userInput, Command.CommandType.MENU), Command.EXIT_MENU));

		System.out.println("Exiting");
	}
}
