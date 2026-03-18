package model.application;

import model.exception.UnauthorisedAccessException;

import java.util.List;
import java.util.stream.Collectors;

public class League {
    private static League instance;

    private final Teams teams;
    private final Teams manageableTeams;
    private final List<Manager> managers;
    private final Players players;
    private Manager loggedInManager;

    private League(Teams seededTeams, Players seededPlayers, List<Manager> seededManagers) {
        this.teams = seededTeams;
        this.manageableTeams = new Teams(seededTeams.getTeams().stream().filter(team -> team.getManager() == null).collect(Collectors.toList()));
        this.players = seededPlayers;
        this.managers = seededManagers;
    }

    public static void initialize(Teams seededTeams, Players seededPlayers, List<Manager> seededManagers) {
        if (instance == null) {
            instance = new League(seededTeams, seededPlayers, seededManagers);
        }
        else {
            throw new IllegalStateException("League has already been initialized");
        }
    }

    public static League getInstance() {
        if (instance == null) {
            throw new IllegalStateException("League has not been initialized");
        }
        return instance;
    }

    public Teams getManageableTeams() {
        return manageableTeams;
    }

    public Teams getAllTeams(){
        return teams;
    }

    public Players getPlayers() { return players; }

    public Manager getLoggedInManager() {
        return loggedInManager;
    }

    public void setLoggedInManager(Manager manager) {
        this.loggedInManager = manager;
    }

    /**
     * Assigns a manager to a new team
     *
     * <p>This method performs multiple operations.
     * It first checks if the manager is already assigned to a team. If so, that teams manager field is set to null, and
     * the old team is added to the {@link League#manageableTeams} list.
     * It then assigns the team to the manager, assigns the manager to the team, and removes the team from the {@link League#manageableTeams} list</p>
     *
     * @param manager The manager to be withdrawn
     * @param team The team to set the manager for
     */
    public void setManagerForTeam(Manager manager, Team team){
        if (team == null || manager == null) {
            throw new IllegalArgumentException("Team and Manager cannot be null");
        }
        if (team.getManager() != null) {
            throw new IllegalArgumentException("Team already has a Manager. You should only be calling this method on a Team that is in the manageableTeams list");
        }
        if (manager.getTeam() != null) {
            Team oldTeam = manager.getTeam();
            oldTeam.setManager(null);
            manageableTeams.add(oldTeam);
        }
        manager.assignTeam(team);
        team.setManager(manager);
        manageableTeams.remove(team);
    }

    /**
     * Withdraws a given Manager from the team they are currently assigned to
     *
     * @param manager The manager to be withdrawn
     */
    public void withdrawManagerFromTeam(Manager manager){
        if (manager == null) {
            throw new IllegalArgumentException("Manager cannot be null");
        }
        if (manager.getTeam() == null) {
            throw new IllegalArgumentException("Manager does not have a team to withdraw from");
        }
        manageableTeams.add(manager.getTeam());
        manager.getTeam().setManager(null);
        manager.assignTeam(null);
    }

    /**
     * Confirms that the id is a valid manager id
     *
     * @param id The integer id to compare with
     * @return The Manager object with that id
     * @throws UnauthorisedAccessException when there is no manager in the League with the provided id
     */
    public Manager validateManager(int id) throws UnauthorisedAccessException {
        for (Manager manager : this.managers) {
            if (manager.hasId(id)) {
                return manager;
            }
        }
        throw new UnauthorisedAccessException("Invalid login credentials");
    }
}

