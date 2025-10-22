package scoreboard;

public class Football extends Sport {
    
    public Football() {
        super("Football");
    }
    
    @Override
    public void displayRules() {
        System.out.println("Football Rules:");
        System.out.println("- 2 teams, 11 players each");
        System.out.println("- Score by getting ball into opponent's goal");
        System.out.println("- Team with most goals wins");
    }
    
    @Override
    public String getSportType() {
        return "Football";
    }
}