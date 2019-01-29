import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameRank {

    public static void main(String[] args) {
        GameRank gameRank = new GameRank();
        gameRank.gameRank();
    }

    private Scanner scanner = new Scanner(System.in);

    private int rank = 25;
    private int currentStars = 0;
    private int consecutiveWins = 0;
    private int starsNeededToRankUp = 0;
    private String resultFromGame = scanner.nextLine();

    public void gameRank () {
        List<String> listOfResults = Arrays.asList(resultFromGame.split(""));

        for (String s : listOfResults) {
            if (s.equals("W")) { // Hva gjør vi hvis spilleren vinner en kamp?
                consecutiveWins++;
                if (allowExtraStar()) {
                    currentStars += 2;
                } else {
                    currentStars += 1;
                }
                if (currentStars > checkRankUp()) {
                    rank--;
                    currentStars -= starsNeededToRankUp;
                }

            } else {
                consecutiveWins = 0;
                if (rank == 20) {
                    if (currentStars > 0) {
                        currentStars--;
                    } else {
                        currentStars = 0;
                    }
                }
                if (rank < 20) {
                    if (currentStars == 0) {
                        rank++;
                        currentStars = checkRankUp() - 1;
                    } else if (currentStars > 0) {
                        currentStars--;
                    } else currentStars = 0;
                }
            }
            if (rank == 0) {
                System.out.println("Legend");
                break;
            }
        }
        if (rank != 0) {
            System.out.println(rank);
        }
    }

    public int checkRankUp () {
        if (rank > 20) {
            starsNeededToRankUp = 2;
            return starsNeededToRankUp;
        } else if (rank > 15) {
            starsNeededToRankUp = 3;
            return starsNeededToRankUp;
        } else if (rank > 10) {
            starsNeededToRankUp = 4;
            return starsNeededToRankUp;
        } else if (rank > 0) {
            starsNeededToRankUp = 5;
            return starsNeededToRankUp;
        }
        return 0;
    }

    public boolean allowExtraStar() {
        if (rank >= 6 && consecutiveWins >= 3) {
            return true;
        } else return false;
    }
}
