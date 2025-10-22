package scoreboard;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreboardGUI {
    private DatabaseManager dbManager;
    private ArrayList<Match> matches;
    private Scanner scanner;
    
    public ScoreboardGUI() {
        dbManager = new DatabaseManager();
        matches = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadMatches();
    }
    
    private void loadMatches() {
        try {
            ResultSet rs = dbManager.getAllMatches();
            matches.clear();
            
            while (rs.next()) {
                int id = rs.getInt("id");                 // corrected column
                String team1 = rs.getString("team1");     // corrected column
                String team2 = rs.getString("team2");     // corrected column
                int score1 = rs.getInt("team1_score");
                int score2 = rs.getInt("team2_score");
                String sport = rs.getString("sport");     // corrected column
                
                Match match = new Match(id, team1, team2, score1, score2, sport);
                matches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void displayScoreboard() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       LIVE SCOREBOARD SYSTEM           ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        for (Match match : matches) {
            match.displayMatch();
        }
    }
    
    public void updateScoreInterface() {
        displayScoreboard();
        
        System.out.println("\n--- Update Score ---");
        System.out.print("Enter Match ID to update: ");
        int matchId = scanner.nextInt();
        
        System.out.print("Enter Team 1 new score: ");
        int score1 = scanner.nextInt();
        
        System.out.print("Enter Team 2 new score: ");
        int score2 = scanner.nextInt();
        
        dbManager.updateScore(matchId, score1, score2);
        
        // Update local match object
        for (Match match : matches) {
            if (match.getMatchId() == matchId) {
                match.updateScore(score1, score2);
            }
        }
        
        System.out.println("\n✓ Score updated successfully!");
        displayScoreboard();
    }
    
    public void run() {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. View Scoreboard");
            System.out.println("2. Update Score");
            System.out.println("3. Refresh from Database");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    displayScoreboard();
                    break;
                case 2:
                    updateScoreInterface();
                    break;
                case 3:
                    loadMatches();
                    System.out.println("✓ Data refreshed!");
                    displayScoreboard();
                    break;
                case 4:
                    dbManager.closeConnection();
                    System.out.println("Thank you! Goodbye.");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
