//Task 1 : Number Guessing Game


import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean retry = true;
        while (retry) {
            playGame(sc);
            System.out.println("");
            System.out.println("");
            System.out.println("Do you want to play again?(y/n) : ");
            String response = sc.nextLine().trim().toLowerCase();
            if (!response.equals("y")) {
                retry = false;
            }

        }
        System.out.println("Thanks for playing....!");
        sc.close();
    }

    private static void playGame(Scanner sc) {
        Random randomNum = new Random();
        int numGuess = randomNum.nextInt(100) + 1;
        int maxTry = 10;
        int attempts = 0;
        boolean correctGuess = false;

        System.out.println("");
        System.out.println("");
        System.out.println("You have to guess number between 1 & 100.Will you guess it correctly?");
        System.out.println("You have " + maxTry + " attempts to guess correct number");

        while (attempts < maxTry && !correctGuess) {
            System.out.println("");
            System.out.print("Enter your guess : ");
            int guessByUser = sc.nextInt();
            sc.nextLine();
            attempts += 1;

            if (guessByUser == numGuess) {
                correctGuess = true;
                System.out.println("Congrats!!!  you guessed correct number in " + attempts + " attempts");

            } else if (guessByUser < numGuess) {
                System.out.println("Too low ...");

                System.out.println("");
                System.out.println("");
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");
                System.out.println("");
                System.out.println("");
            } else {
                System.out.println("Too high...");
                System.out.println("");
                System.out.println("");
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------");
                System.out.println("");
                System.out.println("");
            }

        }
        if (!correctGuess) {
            System.out.println("");
            System.out.println("");
            System.out.println("Better Luck Next Time ! You have used " + maxTry
                    + " attempts. The correct number was " + numGuess + ".");
        }
    }

}