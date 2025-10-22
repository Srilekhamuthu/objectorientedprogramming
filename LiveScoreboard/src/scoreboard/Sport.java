package scoreboard;

public abstract class Sport {
    protected String sportName;
    
    public Sport(String sportName) {
        this.sportName = sportName;
    }
    
    public abstract void displayRules();
    public abstract String getSportType();
    
    public String getSportName() {
        return sportName;
    }
}