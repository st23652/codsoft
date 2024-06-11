import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random randomCheck = new Random();
        int userScore = 0;
        int rounds = 1;

        while (true) {
            int numGenerated = randomCheck.nextInt(100) + 1;
            int attempts = 0;
            int totalAttempts = 5;

            System.out.println("/nRound " + rounds + ":");
            System.out.println("You have 5 attempts to guess the generated number.");

            while (attempts < totalAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
                int userGuess = input.nextInt();
                attempts++;

                if (userGuess < numGenerated) {
                    System.out.println("Too Low");
                } else if (userGuess > numGenerated) {
                    System.out.println("Too High");
                } else {
                    System.out.println("Guessed the right number.");
                    userScore += (totalAttempts - attempts + 1);
                    break;
                }
            }

            if (attempts == totalAttempts) {
                System.out.println("You've used all " + totalAttempts + " attempts.");
                System.out.println("The generated number was " + numGenerated + ".");
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = input.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }

            rounds++;
        }

        System.out.println("/nGame over! Your final score is: " + userScore);
        input.close();
    }
}
