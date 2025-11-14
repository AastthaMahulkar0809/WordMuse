import java.util.*;

public class Leaderboard {
    private static Map<String, Integer> scores = new LinkedHashMap<>();

    public static void addScore(String playerName, int score) {
        if (scores.containsKey(playerName)) {
            int prev = scores.get(playerName);
            if (score > prev) {
                scores.put(playerName, score);
            }
        } else {
            scores.put(playerName, score);
        }
    }

    public static void showLeaderboard() {
        System.out.println("\n🏆 LEADERBOARD 🏆");
        System.out.println("-------------------------");

        if (scores.isEmpty()) {
            System.out.println("No players yet!");
            System.out.println("-------------------------");
            return;
        }

        scores.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .forEach(entry -> System.out.printf("%-15s : %d%n", entry.getKey(), entry.getValue()));

        System.out.println("-------------------------\n");
    }
}
