package scoreboard;

public class Match {
    private int matchId;
    private Team team1;
    private Team team2;
    private Sport sport;
    private String status;
    
    public Match(int matchId, String team1Name, String team2Name, int team1Score, int team2Score, String sportType) {
        this.matchId = matchId;
        this.team1 = new Team(team1Name, team1Score);
        this.team2 = new Team(team2Name, team2Score);
        
        if (sportType.equalsIgnoreCase("Cricket")) {
            this.sport = new Cricket();
        } else if (sportType.equalsIgnoreCase("Football")) {
            this.sport = new Football();
        }
        
        this.status = "LIVE";
    }
    
    public int getMatchId() {
        return matchId;
    }
    
    public Team getTeam1() {
        return team1;
    }
    
    public Team getTeam2() {
        return team2;
    }
    
    public Sport getSport() {
        return sport;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void updateScore(int team1Score, int team2Score) {
        team1.setScore(team1Score);
        team2.setScore(team2Score);
    }
    
    public void displayMatch() {
        System.out.println("\n==== MATCH " + matchId + " ====");
        System.out.println("Sport: " + sport.getSportType());
        System.out.println(team1.getTeamName() + " vs " + team2.getTeamName());
        System.out.println("Score: " + team1.getScore() + " - " + team2.getScore());
        System.out.println("Status: " + status);
        System.out.println("==================");
    }
}