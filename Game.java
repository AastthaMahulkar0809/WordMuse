import java.util.*;

public class Game {
    // 🎨 ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";  // Correct letter, correct position
    private static final String YELLOW = "\u001B[33m"; // Correct letter, wrong position
    private static final String RED = "\u001B[31m";    // Letter not in the word

    private String chosenWord;
    private String hint;
    private Scanner sc = new Scanner(System.in);

    public Game(String chosenWord, String hint) {
        this.chosenWord = chosenWord;
        this.hint = hint;
    }

    public int play() {
        int tries = 0;
        boolean guessed = false;

        System.out.println("\nA 5-letter word has been chosen. Start guessing!");

        while (tries < 15 && !guessed) {
            tries++;
            System.out.print("Your guess (" + tries + "): ");
            String guess = sc.nextLine().toLowerCase();

            if (guess.equals(chosenWord)) {
                guessed = true;
                System.out.println(GREEN + "✅ Correct! You guessed the word in " + tries + " tries!" + RESET);
                break;
            }

            showFeedback(guess, chosenWord);

            if (tries == 11) {
                System.out.println("💡 Hint: " + hint);
            }
        }

        if (!guessed) {
            System.out.println(RED + "❌ You ran out of tries. The word was: " + chosenWord + RESET);
        }

        return calculateScore(tries, guessed);
    }

    // 🧩 Shows color-coded feedback
    private void showFeedback(String guess, String chosenWord) {
        StringBuilder feedback = new StringBuilder();

        for (int i = 0; i < guess.length(); i++) {
            char g = guess.charAt(i);
            if (g == chosenWord.charAt(i)) {
                feedback.append(GREEN).append(g).append(RESET);
            } else if (chosenWord.contains(String.valueOf(g))) {
                feedback.append(YELLOW).append(g).append(RESET);
            } else {
                feedback.append(RED).append(g).append(RESET);
            }
        }

        System.out.println("Feedback: " + feedback);
    }

    private int calculateScore(int tries, boolean guessed) {
        if (!guessed) return 0;
        return Math.max(0, 100 - (tries - 1) * 5); // Fewer tries = higher score
    }
}
