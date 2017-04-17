package ohtu;

public class TennisGame {

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String player1Name;
    private String player2Name;
    String[] individualScore = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(this.player1Name)) {
            scorePlayer1++;
        } else {
            scorePlayer2++;
        }
    }

    public String evenScore() {
        if (scorePlayer1 < 4) {
            return individualScore[scorePlayer1] + "-All";
        } else {
            return "Deuce";
        }
    }

    public String winOrAdvantageScore() {
        int minusResult = scorePlayer1 - scorePlayer2;
        if (minusResult == 1) {
            return "Advantage " + player1Name;
        } else if (minusResult == -1) {
            return "Advantage " + player2Name;
        } else if (minusResult >= 2) {
            return "Win for " + player1Name;
        } else {
            return "Win for " + player2Name;
        }
    }

    public String normalScore() {
        return individualScore[scorePlayer1] + "-" + individualScore[scorePlayer2];
    }

    public String getScore() {
        if (scorePlayer1 == scorePlayer2) {
            return evenScore();
        }
        if (scorePlayer1 >= 4 || scorePlayer2 >= 4) {
            return winOrAdvantageScore();
        } else {
            return normalScore();
        }
    }
}
