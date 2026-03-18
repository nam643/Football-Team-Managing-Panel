package model.application;

import javafx.beans.property.*;
import model.enums.Position;

public class Player {
    private final String firstName;
    private final String lastName;
    private Team team;
    private final Position position;
    private final StringProperty fullNameProperty;
    private final StringProperty positionProperty;

    public Player(String firstName, String lastName, Team team, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.position = position;
        this.fullNameProperty = new SimpleStringProperty(firstName + " " + lastName);
        this.positionProperty = new SimpleStringProperty(position.toString());
    }
    public String getFullName() {
        return fullNameProperty.get();
    }
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) { this.team = team; }

    public ReadOnlyStringProperty fullNameProperty() {
        return fullNameProperty;
    }

    public ReadOnlyStringProperty positionProperty() {
        return positionProperty;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + position + ")";
    }
}
