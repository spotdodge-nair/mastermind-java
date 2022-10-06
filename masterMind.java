import java.util.*;

public class masterMind {
    private static char[] answer;
    private static char[] colors = { 'r', 'g', 'y', 'o', 'p', 'k' };
    private static ArrayList<guess> listOfGuesses = new ArrayList<>();
    private static int maxGuesses = 12; // if maxGuesses = 12, the game will be in Standard Mode, if it's -1, the game
                                        // will be in Infinite Mode, any other number means the game will be in
                                        // Challenge Mode.
    private static Random r = new Random();

    /*
     * Because this is an utility class,
     * there should be a constructor (preferably private) to hide the implicit
     * public constructor.
     * This way, an object of type masterMind will never be created.
     */
    private masterMind() {
        throw new IllegalStateException("Utility class. Should not have an object of type masterMind.");
    }

    public static void getMenu() {
        System.out.println("Welcome to MasterMind! Select from the options below: ");
        System.out.println("__________________________________________");
        System.out.println();
        System.out.println("(1)  How do I play this game?");
        System.out.println();
        System.out.println("(2)  Play Standard Mode");
        System.out.println();
        System.out.println("(3)  Play Challenge Mode");
        System.out.println();
        System.out.println("(4)  Play Infinite Mode");
        System.out.println();
        System.out.println("(5)  Quit");
    }

    public static void getIntroduction() {
        System.out.println("MasterMind Introduction: ");
        System.out.println(
                "MasterMind is a game where one person, known as the codemaker (the computer in this case) comes up with a 4 color code. \n You, the code-breaker, has to guess what that 4 color code the codemaker came up with by coming up with your own 4 color code.");
        System.out.println("After guessing, the codemaker will give you several hints: ");
        System.out.println(
                "If you guessed the color in the right position, the codemaker will put a \"B\" (Black) in that position.");
        System.out.println(
                "If you guessed the color, but the color is in the wrong position, the codemaker will put a \"W\" (White) in that position.");
        System.out.println(
                "If you didnt guess the color and it's in the wrong position, the codemaker will put a \"X\" in that position.");
        System.out.println(
                "__________________________________________");
        System.out.println();
    }

    public static void randomColorGenerator(int pegs) {
        answer = new char[pegs];
        for (int a = 0; a < answer.length; a++) {
            answer[a] = colors[r.nextInt(6)];
        }
    }

    /*
     * Calculates guess for the user to see. B stands for black peg, W stands for
     * white peg, X stands for wrong peg + wrong position
     */
    public static void calculateGuess(guess g) {

        char[] checker = new char[answer.length];

        /*
         * Sets every element in the guess array and the checker array to X's
         */
        for (int a = 0; a < answer.length; a++) {
            g.setGuessHint('X', a);
            checker[a] = 'X';
        }

        /*
         * Sets blacks and whites.
         * If the character at index b are the same for both the guess object and the
         * answer array,
         * then it will set it to black
         * Otherwise, it will be white
         * (The 2nd for loop must test for whether or not the character at index c is
         * the same for both
         * the answer array and the guess object because of the edge case where someone
         * guessed the character correctly on the right index and
         * the index before it)
         */
        for (int b = 0; b < answer.length; b++) {
            if (g.getChar(b) == answer[b]) {
                checker[b] = 'B';
                g.setGuessHint('B', b);
            } else {
                for (int c = 0; c < answer.length; c++) {
                    if (g.getChar(b) == answer[c] && b != c && checker[c] == 'X' && g.getChar(c) != answer[c]) {
                        checker[c] = 'W';
                        g.setGuessHint('W', b);
                    }
                }
            }
        }
    }

    public static boolean checkWin(guess g) {
        for (int a = 0; a < g.getGuess().length; a++) {
            if (answer[a] != g.getChar(a)) {
                return false;
            }
        }
        return true;
    }

    public static void guessVisualizationGenerator() {
        int number = 1;
        System.out.println("_____________________________________");
        System.out.println();
        for (int a = 0; a < listOfGuesses.size(); a++) {
            System.out.println("Guess #" + number + ": ");
            System.out.println("_____________________________________");
            System.out.println();
            System.out.print("|     ");
            System.out.print(listOfGuesses.get(a).toString());
            System.out.println("     |");
            System.out.println("_____________________________________");
        }
        System.out.println();
        System.out.println("gaming");
        System.out.println();
        System.out.println("_____________________________________");
        System.out.println();

    }

    public static void setMaxGuesses(int maxNumberGuesses) {
        maxGuesses = maxNumberGuesses;
    }

    public static void startGame(int guesses, int choice, int pegs) {
        Scanner console = new Scanner(System.in);
        char guessChar = ' ';

        maxGuesses = guesses;

        int guessNumber = 0;

        guess g;

        randomColorGenerator(pegs);

        boolean win = false;

        System.out.println(Arrays.toString(answer));

        do {
            try {
                g = new guess(pegs);
                guessNumber++;
                System.out.println("Round #" + guessNumber + ": ");
                for (int a = 1; a <= answer.length; a++) {
                    System.out.println("Guess #" + a + ": ");
                    guessChar = console.next().charAt(0);
                    while (!(contains(guessChar))) {
                        System.out.println("Not a valid color! Try again!");
                        guessChar = console.next().charAt(0);
                    }
                    g.setGuess(guessChar, a - 1);
                }
                listOfGuesses.add(g);
                System.out.println();
                calculateGuess(g);
                guessVisualizationGenerator();
                win = checkWin(g);
            } catch (InputMismatchException exception) {
                System.out.println("Not a color! Try again!");
                console.next();
            }
        } while ((!win) && (guessNumber <= maxGuesses));

        /*
         * Displays win/lose message depending on whether or not you guessed the code
         */
        if (win) {
            switch (choice) {
                case 2:
                    System.out.println("MasterMind in Standard Mode");
                    winMessage(guessNumber);
                    break;
                case 3:
                    System.out.println("MasterMind in Challenge Mode");
                    winMessage(guessNumber);
                    break;
                default:
                    System.out.println("MasterMind in Infinite Mode");
                    winMessage(guessNumber);
                    break;
            }
        } else {
            System.out.println("An unfortunate loss. Try again next time!");
            System.out.print("The code the computer came up with: ");
            System.out.print(Arrays.toString(answer));
            System.out.println("_____________________________________");
        }
    }

    public static void winMessage(int guesses) {
        if (guesses == 1) {
            System.out.println("bro's actually cheating. You beat MasterMind in 1 turn");
            System.out.println();
        } else if (guesses <= 3) {
            System.out.println("bro's actually cheating. You beat MasterMind in " + guesses + " turns");
            System.out.println();
        } else if ((guesses > 3) && guesses < maxGuesses) {
            System.out.println("Congrats! You beat MasterMind in " + guesses + " turns!");
            System.out.println();
        } else {
            System.out.println("Damn that was a close call. You beat MasterMind in " + maxGuesses + " turns!");
            System.out.println();
        }
    }

    public static boolean contains(char c) {
        for (int a = 0; a < colors.length; a++) {
            if (colors[a] == c) {
                return true;
            }
        }
        return false;
    }
}