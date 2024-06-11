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

            System.out.println("round " + rounds + ":");
            System.out.println("you have 5 attempts to guess the generated number");

            while (attempts < totalAttempts) {
                System.out.print("attempt " + (attempts + 1) + ": enter your guess: ");
                int userGuess = input.nextInt();
                attempts++;

                if (userGuess < numGenerated) {
                    System.out.println("too low");
                } else if (userGuess > numGenerated) {
                    System.out.println("too high");
                } else {
                    System.out.println("guessed the right number");
                    userScore += (totalAttempts - attempts + 1);
                    break;
                }
            }

            if (attempts == totalAttempts) {
                System.out.println("you have used all your" + totalAttempts + " attempts");
                System.out.println("the generated number was " + numGenerated);
            }

            System.out.print("do you want to play another round (yes/no): ");
            String newGame = input.next();

            if (!newGame.equalsIgnoreCase("yes")) {
                break;
            }

            rounds++;
        }

        System.out.println("game over! your final score is: " + userScore);
        input.close();
    }
}
