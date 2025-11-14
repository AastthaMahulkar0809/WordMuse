import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎯 Welcome to WordSeek!");
        boolean playMore = true;

        // Get the word map once
        Map<String, String> wordMap = WordList.getWordMap();
        List<String> words = new ArrayList<>(wordMap.keySet());
        Random random = new Random();

        while (playMore) {
            System.out.print("\nEnter player name: ");
            String playerName = sc.nextLine();

            // Pick a random word
            String word = words.get(random.nextInt(words.size()));
            String hint = wordMap.get(word);

            Game game = new Game(word, hint);
            int score = game.play();

            Leaderboard.addScore(playerName, score);

            System.out.print("Do you want another player to play? (yes/no): ");
            playMore = sc.nextLine().equalsIgnoreCase("yes");
        }

        // ✅ Show leaderboard once all players finish
        Leaderboard.showLeaderboard();

        System.out.println("🎮 Game session ended!");
    }
}
