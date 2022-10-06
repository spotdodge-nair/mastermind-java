import java.util.*;

public class masterMindClient {
    public static void main(String[] args) {
        int choice = 0;
        Scanner console = new Scanner(System.in);

        do {
            try {
                masterMind.getMenu();

                if (console.hasNextInt()) {
                    choice = console.nextInt();
                }

                switch (choice) {
                    case 1:
                        masterMind.getIntroduction();
                        break;
                    case 2:
                        System.out.println("Loading Standard Mode...");
                        masterMind.startGame(12, 2, 4);
                        break;
                    case 3:
                        System.out.print(
                                "In Challenge Mode, \n you get to decide how many turns you want to play to guess the code. \n Type in a number for the number of turns: ");
                        int numberOfGuesses = console.nextInt();
                        System.out.println();
                        System.out.print("Type in the amount of pegs you want to guess: ");
                        int pegs = console.nextInt();
                        System.out.println("Loading Challenge Mode...");
                        masterMind.startGame(numberOfGuesses, 3, pegs);
                        break;
                    case 4:
                        System.out.println("Loading Infinite Mode...");
                        masterMind.startGame(-1, 4, 4);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid input. Type an integer between 1-5!");
                        System.out.println();
                        break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Not an integer! Try again!");
                System.out.println("__________________________________________");
                console.next(); // This advances the scanner so that it can wait for user input
            }
        } while (choice != 5);

        console.close();
        System.out.println("Have a nice day!");
    }
}
