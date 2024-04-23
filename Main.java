import java.util.Scanner;

// Define a class to generate random numbers
class NumberGenerator {

    // Method to generate a random number within a range
    public int generate(int max, int min){
        return (int) (Math.random() * (max - min + 1) + min);
    }
}

public class Main {

    public static void main(String[] args) {

        // Create a Scanner object to get user input
        Scanner scanner = new Scanner(System.in);

        // Create an instance of the NumberGenerator class
        NumberGenerator generator = new NumberGenerator();

        // Initialize variables to track total attempts and wins
        int totalAttempts = 0;
        int wins = 0;

        while (true) {
            System.out.println("Enter the maximum number:");
            int max = scanner.nextInt();

            System.out.println("Enter the minimum number:");
            int min = scanner.nextInt();
            scanner.nextLine();

            // Generate a random number within the given range
            int correctNumber = generator.generate(max, min);

            int attempts = 0;

            while (true) {
                System.out.println("Guess a number between " + min + " and " + max + ":");
                int guessedNumber = scanner.nextInt();
                attempts++;

                if (guessedNumber > correctNumber) {
                    System.out.println("It's greater.");
                } else if (guessedNumber < correctNumber) {
                    System.out.println("It's lower.");
                } else {
                    System.out.println("Correct guess!");
                    wins++;
                    break;
                }
            }

            // Update total attempts
            totalAttempts += attempts;

            // Display attempts and wins
            System.out.println("Attempts: " + attempts);
            System.out.println("Wins: " + wins);

            // Calculate and display win rate
            double winRate = (double) wins / totalAttempts * 100;
            System.out.printf("Your win rate is %.2f%%\n", winRate);

            // Ask the user if they want to play again
            System.out.println("Do you want to play again? (yes/no)");
            String playAgain = scanner.next();

            // Close the scanner
            scanner.nextLine();

            if (!playAgain.equalsIgnoreCase("yes")) {
                System.exit(0);
            }
        }
    }
}
