package scoreboard;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Live Scoreboard System...\n");
        
        ScoreboardGUI scoreboard = new ScoreboardGUI();
        scoreboard.run();
    }
}