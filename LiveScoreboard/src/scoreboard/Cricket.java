package scoreboard;

public class Cricket extends Sport {
    
    public Cricket() {
        super("Cricket");
    }
    
    @Override
    public void displayRules() {
        System.out.println("Cricket Rules:");
        System.out.println("- 2 teams, 11 players each");
        System.out.println("- Score runs by hitting the ball");
        System.out.println("- Team with most runs wins");
    }
    
    @Override
    public String getSportType() {
        return "Cricket";
    }
}