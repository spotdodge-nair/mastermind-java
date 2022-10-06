import java.util.*;

public class guess {
    private char[] guessColors;
    private char[] guessHints;

    public guess(int pegs) {
        guessColors = new char[pegs];
        guessHints = new char[pegs];
    }

    public char getChar(int index) {
        return guessColors[index];
    }

    public char getGuessHint(int a) {
        return guessHints[a];
    }

    public void setGuess(char c, int a) {
        guessColors[a] = c;
    }

    public void setGuessHint(char c, int a) {
        guessHints[a] = c;
    }

    public String toString() {
        return Arrays.toString(guessColors) + " " + Arrays.toString(guessHints);
    }

    public char[] getGuess() {
        return guessColors;
    }

}
