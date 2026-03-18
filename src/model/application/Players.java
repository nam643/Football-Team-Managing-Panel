package model.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Players {
    private final ObservableList<Player> players;

    public Players(List<Player> players) {
        this.players = FXCollections.observableList(players);
    }

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public void add(Player player) {
        this.players.add(player);
    }

    public void remove(Player player) {
        this.players.remove(player);
    }

    /**
     * The lookup pattern on the player list
     * @param name The full name of the player to search for
     * @return The player object with the corresponding the name, or null if not found
     */
    public Player player(String name) {
        for (Player player : players) {
            if (player.getFullName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append(player.getFullName()).append("\n");
        }
        return sb.toString();
    }
}
