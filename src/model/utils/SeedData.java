package model.utils;

import java.util.*;
import model.application.*;
import model.enums.*;

//Do not alter the seed data class
public class SeedData {

    private final List<Player> players;
    private final List<Team> teams;
    private final List<Manager> managers;

    public SeedData() {
        this.players = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.managers = new ArrayList<>();
    }

    public void seed() {
        populatePlayers();
        populateTeams();
        populateManagers();
        assignTeamManagers();
    }

    private void populatePlayers() {
        this.players.addAll(Arrays.asList(
                new Player("Ethan", "Lockyer", null, Position.Fullback),
                new Player("Lucas", "Inglis", null, Position.Wing),
                new Player("Max", "Thurston", null, Position.Centre),
                new Player("Noah", "Smith", null, Position.Halfback),
                new Player("Kai", "Fittler", null, Position.Forward),
                new Player("Leo", "Tallis", null, Position.Wing),
                new Player("Isaac", "Slater", null, Position.Centre),
                new Player("Owen", "Minichiello", null, Position.Fullback),
                new Player("Liam", "Hodges", null, Position.Forward),
                new Player("Caleb", "Hayne", null, Position.Halfback),
                new Player("Ryan", "Burgess", null, Position.Centre),
                new Player("Mason", "Carney", null, Position.Wing),
                new Player("Zac", "Marshall", null, Position.Forward),
                new Player("Nathan", "Gasnier", null, Position.Fullback),
                new Player("Finn", "Gallen", null, Position.Wing),
                new Player("Jake", "Civoniceva", null, Position.Centre),
                new Player("Connor", "Watmough", null, Position.Forward),
                new Player("Daniel", "Kennedy", null, Position.Halfback),
                new Player("Aiden", "Meninga", null, Position.Wing),
                new Player("Xavier", "Webcke", null, Position.Forward),
                new Player("Elijah", "Pearce", null, Position.Centre),
                new Player("Arlo", "Williams", null, Position.Fullback),
                new Player("Hunter", "Johns", null, Position.Halfback),
                new Player("Thomas", "Lewis", null, Position.Forward),
                new Player("Jayden", "Chambers", null, Position.Wing),
                new Player("Blake", "Walker", null, Position.Centre),
                new Player("Joshua", "Stewart", null, Position.Forward),
                new Player("Cooper", "Morris", null, Position.Wing),
                new Player("Eli", "Lane", null, Position.Halfback),
                new Player("Jordan", "Jennings", null, Position.Centre),
                new Player("Tyler", "Barrett", null, Position.Fullback),
                new Player("Jaxon", "Boyd", null, Position.Wing),
                new Player("Luca", "Douglas", null, Position.Forward),
                new Player("Riley", "Martin", null, Position.Centre),
                new Player("Nathaniel", "Reynolds", null, Position.Fullback),
                new Player("Charlie", "Morgan", null, Position.Wing),
                new Player("Isaiah", "Baker", null, Position.Halfback),
                new Player("Miles", "Ferguson", null, Position.Forward),
                new Player("Zane", "Bryant", null, Position.Centre),
                new Player("Archie", "Wade", null, Position.Wing),
                new Player("Sebastian", "Day", null, Position.Halfback),
                new Player("Grayson", "Hancock", null, Position.Forward),
                new Player("Leon", "Preston", null, Position.Centre),
                new Player("Ezekiel", "Rowe", null, Position.Fullback),
                new Player("Jude", "Doyle", null, Position.Wing),
                new Player("Asher", "Fleming", null, Position.Centre),
                new Player("Carter", "O'Connor", null, Position.Forward),
                new Player("Anthony", "Hughes", null, Position.Halfback),
                new Player("Joel", "King", null, Position.Fullback),
                new Player("Micah", "Sutton", null, Position.Wing),
                new Player("Reuben", "Armstrong", null, Position.Forward)
        ));
    }

    private void populateTeams() {
        this.teams.addAll(Arrays.asList(
                new Team("Ultimo", "Eels", null, new Players(new LinkedList<Player>(Arrays.asList(
                        this.players.get(0), this.players.get(1), this.players.get(2), this.players.get(3), this.players.get(4), this.players.get(5), this.players.get(6)
                )))),
                new Team("Chippendale", "Panthers", null, new Players(new LinkedList<Player>(Arrays.asList(
                        this.players.get(7), this.players.get(8), this.players.get(9), this.players.get(10), this.players.get(11), this.players.get(12)
                )))),
                new Team("Haymarket", "Storm", null, new Players(new LinkedList<Player>(Arrays.asList(
                        this.players.get(13), this.players.get(14), this.players.get(15), this.players.get(16), this.players.get(17), this.players.get(18), this.players.get(19)
                )))),
                new Team("Town Hall", "Titans", null, new Players(new LinkedList<Player>(Arrays.asList(
                        this.players.get(20), this.players.get(21), this.players.get(22), this.players.get(23), this.players.get(24), this.players.get(25), this.players.get(26), this.players.get(27)
                )))),
                new Team("Surry Hills", "Sharks", null, new Players(new LinkedList<Player>(Arrays.asList(
                        this.players.get(28), this.players.get(29), this.players.get(30), this.players.get(31), this.players.get(32)
                )))),
                new Team("Broadway", "Bulldogs", null, new Players(new LinkedList<Player>(Arrays.asList(
                        this.players.get(33), this.players.get(34), this.players.get(35), this.players.get(36), this.players.get(37)
                )))),
                new Team("Wynyard", "Warriors", null, new Players(new LinkedList<Player>(Arrays.asList(
                        this.players.get(38),  this.players.get(41), this.players.get(43), this.players.get(44), this.players.get(45), this.players.get(46), this.players.get(47), this.players.get(48), this.players.get(49)
                ))))
        ));
        for (Team team : this.teams) {
            for (Player player : team.getAllPlayers().getPlayers()) {
                player.setTeam(team);
            }
        }
    }

    private void populateManagers() {
        this.managers.addAll(Arrays.asList(
                new Manager(12345, "Davey", "Dyer", null),
                new Manager(1, "Aziz", "Shavershian", null),
                new Manager(34896, "Head", "Hunterz", null),
                new Manager(678, "Lee", "Yeoreum", null),
                new Manager(912, "Dahyun", "Kim", null)
        ));
    }

    private void assignTeamManagers() {

        List<Map.Entry<Integer, Integer>> indexPairs = new ArrayList<>(Arrays.asList(
                new AbstractMap.SimpleEntry<>(0, 0),
                new AbstractMap.SimpleEntry<>(1, null),
                new AbstractMap.SimpleEntry<>(2, null),
                new AbstractMap.SimpleEntry<>(3, 1),
                new AbstractMap.SimpleEntry<>(4, 2),
                new AbstractMap.SimpleEntry<>(5, null),
                new AbstractMap.SimpleEntry<>(6, 3)
        ));
        for (Map.Entry<Integer, Integer> indexPair : indexPairs) {
            Team team = this.teams.get(indexPair.getKey());
            if (indexPair.getValue() == null) {continue;}

            Manager manager = this.managers.get(indexPair.getValue());
            if (manager != null) {
                team.setManager(manager);
                manager.assignTeam(team);
            }
        }
    }

    public Players players(){
        return new Players(this.players);
    }
    public Teams teams(){
        return new Teams(this.teams);
    }
    public List<Manager> managers(){
        return this.managers;
    }
}