
public abstract class Player {
    
    // player's name
    private String name;
    
    // This method will return a string variable to show what this player
    public abstract String performMove();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
