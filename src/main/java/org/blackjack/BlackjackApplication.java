package org.blackjack;

import org.blackjack.constants.BlackjackConstants;
import org.blackjack.service.BlackjackService;
import org.blackjack.utilities.CLIOutputUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
			System.out.println("To start a game type 'Play' or 'P'");
			System.out.println("To exit, type 'Exit' or 'E'");
			userInput = scanner.nextLine();

			if (userInput.equalsIgnoreCase("p") || userInput.equalsIgnoreCase("Play")) {
				System.out.println("Starting...");
				blackjackService.startGame();

				do {
					System.out.println(blackjackService.generatePlayerAndDealerCardAndScoreSummary());
					System.out.print("Type '(H)it', '(S)tand', or '(E)xit': ");

					userInput = scanner.nextLine();

					if (userInput.equalsIgnoreCase("h") || userInput.equalsIgnoreCase("Hit")) {
						if (blackjackService.playerHit()) {
							System.out.println(CLIOutputUtils.generateOutputString("You busted."
									, BlackjackConstants.CLI_LINE_SEPARATOR));
							break;
						}
						blackjackService.generatePlayerAndDealerCardAndScoreSummary();
					} else if (userInput.equalsIgnoreCase("s") || userInput.equalsIgnoreCase("Stand")) {
						blackjackService.simulateDealerHand();
						System.out.println(blackjackService.checkForWinner());
						break;
					}

				} while (userInput.equalsIgnoreCase("h") || userInput.equalsIgnoreCase("Hit"));
			}

			System.out.println(blackjackService.endGame());

		} while (!userInput.equalsIgnoreCase("e") && !userInput.equalsIgnoreCase("Exit"));

		System.out.println("Exiting");
	}
}
