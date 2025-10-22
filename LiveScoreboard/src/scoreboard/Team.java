package scoreboard;

public class Team {
    private String teamName;
    private int score;
    
    public Team(String teamName, int score) {
        this.teamName = teamName;
        this.score = score;
    }
    
    public String getTeamName() {
        return teamName;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void incrementScore(int points) {
        this.score += points;
    }
    
    @Override
    public String toString() {
        return teamName + ": " + score;
    }
}

