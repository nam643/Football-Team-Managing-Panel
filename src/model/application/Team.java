package model.application;

public class Team {
    public static int REQUIRED_TEAM_SIZE = 5;

    private final String localName;
    private final String teamName;
    private Manager manager;
    private final Players allPlayers;
    private final Player[] currentTeam;

    public Team(String localName, String teamName, Manager manager, Players allPlayers) {
        this.localName = localName;
        this.teamName = teamName;
        this.manager = manager;
        this.allPlayers = allPlayers;
        this.currentTeam = new Player[REQUIRED_TEAM_SIZE];
    }

    public String getTeamName() {
        return this.teamName;
    }

    public Manager getManager() {
        return this.manager;
    }

    public Players getAllPlayers() { return this.allPlayers; }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return this.localName + " " + this.teamName;
    }
}
