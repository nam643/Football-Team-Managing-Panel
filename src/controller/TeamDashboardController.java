package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.application.League;
import model.application.Player;
import model.application.Team;
import model.enums.Position;
import model.exception.FillException;
import model.exception.InvalidSigningException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TeamDashboardController extends Controller<League> {
    @FXML private TableView<Player> playerTv;
    @FXML private TableColumn<Player, String> nameCol;
    @FXML private TableColumn<Player, String> positionCol;
    @FXML private Label teamNameLbl;
    @FXML private Button unsignBtn;
    @FXML private Button signBtn;
    @FXML private TextField signPlayerTf;

    @FXML private GridPane jerseyGd;
    private List<ImageView> jerseyImg;

    private final ObservableList<Player> playersList = FXCollections.observableArrayList();
    private final StringProperty teamNameBinding = new SimpleStringProperty();

    private final ObservableList<Player> activePlayer = FXCollections.observableArrayList(
            new Player("Empty", "Slot", null, Position.Forward),
            new Player("Empty", "Slot", null, Position.Forward),
            new Player("Empty", "Slot", null, Position.Forward),
            new Player("Empty", "Slot", null, Position.Forward),
            new Player("Empty", "Slot", null, Position.Forward)
    );
    @FXML
    private void initialize() {
        //implement enter from keyboard
        signPlayerTf.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                signBtn.fire();
            }
        });

        //binding team name
        teamNameLbl.textProperty().bind(League.getInstance().getLoggedInManager().teamProperty().asString());

        //binding table view to display name + position
        Bindings.bindContent(playersList, League.getInstance().getLoggedInManager().getTeam().getAllPlayers().getPlayers());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        positionCol.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        playerTv.setItems(playersList);

        //disable unsignButton when nothing is selected
        unsignBtn.disableProperty().bind(
                playerTv.getSelectionModel().selectedItemProperty().isNull()
        );

        //disable sign button when nothing inside the text field
        signBtn.disableProperty().bind(signPlayerTf.textProperty().isEmpty());

        //handle click on images
        jerseyImg = jerseyGd.getChildren().stream().filter(node -> node instanceof ImageView).map(node -> (ImageView) node).collect(Collectors.toList());
        for(ImageView img:jerseyImg){
            Tooltip tooltip = new Tooltip("Unallocated");
            Tooltip.install(img,tooltip);
        }
        for (ImageView img : jerseyImg) {
            img.setOnMouseClicked(e -> {
                if(getSelectedPlayer()!=null){
                    try{
                        if(!isInTeam(activePlayer, getSelectedPlayer())){
                            Tooltip tooltip = new Tooltip(getSelectedPlayer().toString());
                            Tooltip.install(img,tooltip);
                            activePlayer.set(jerseyImg.indexOf(img),getSelectedPlayer());
                            img.setImage(new Image("/view/image/" + League.getInstance().getLoggedInManager().getTeam().getTeamName().toLowerCase() + ".png"));
                        }else{
                            throw new FillException(getSelectedPlayer().getFullName() + " is already in the active playing team");
                        }
                    }catch (Exception ex){
                        ViewLoader.showStage(ex,"/view/ErrorView.fxml","Error", new Stage());
                    }

                }
                else{
                    activePlayer.set(jerseyImg.indexOf(img), new Player("Empty", "Slot", null, Position.Forward));
                    Tooltip.install(img,new Tooltip("Unallocated"));
                    img.setImage(new Image("/view/image/" + "none" + ".png"));
                }
            });
        }
        /***********************************************************************/

    }

    public League getLeague(){return model;}

    public void handleClose(ActionEvent actionEvent) {
        stage.close();
    }

    //click and change the jersey + the player selected
    public Player getSelectedPlayer(){
        return playerTv.getSelectionModel().getSelectedItem();
    }

    public boolean isInTeam(ObservableList<Player> pList, Player track){
        for(Player p:pList){
            if(p.fullNameProperty().equals(track.fullNameProperty())) return true;
        }
        return false;
    }

    //sign button
    public void handleSign(ActionEvent actionEvent) {
        try{
            String newPlayerFullName = signPlayerTf.getText();
            Player newPlayer = League.getInstance().getPlayers().player(newPlayerFullName);
            if(newPlayer==null){
                throw new InvalidSigningException("Player does not exist within the league");
            }
            else if(League.getInstance().getLoggedInManager().getTeam().getAllPlayers().player(newPlayerFullName) != null){
                throw new InvalidSigningException(newPlayerFullName + " is already signed to your team");
            }
            else if(checkAllTeam(newPlayerFullName,League.getInstance().getAllTeams().getTeams())){
                throw new InvalidSigningException("Cannot sign " + newPlayerFullName + ", player is already signed to " + getTeamOfInvalidPlayer(newPlayerFullName,League.getInstance().getAllTeams().getTeams()));
            }
            else{
                League.getInstance().getLoggedInManager().getTeam().getAllPlayers().add(newPlayer);
            }
        } catch (InvalidSigningException e) {
            ViewLoader.showStage(e,"/view/ErrorView.fxml","Error",new Stage());
        }
    }
    public boolean checkAllTeam(String fullName, ObservableList<Team> teams){
        for(Team t:teams){
            if(t.getAllPlayers().player(fullName) != null) return true;
        }
        return false;
    }

    public Team getTeamOfInvalidPlayer(String fullName, ObservableList<Team> teams){
        for(Team t:teams){
            if(t.getAllPlayers().player(fullName) != null) return t;
        }
        return null;
    }


    //unsign button
    public void handleUnsign(ActionEvent actionEvent){
        try{
            if(isInTeam(activePlayer,getSelectedPlayer())){
                throw new InvalidSigningException("Cannot remove " + getSelectedPlayer().getFullName() + ", player is in the active team");
            }
            else{
                League.getInstance().getLoggedInManager().getTeam().getAllPlayers().remove(getSelectedPlayer());
            }
        }
        catch (Exception ex){
            ViewLoader.showStage(ex,"/view/ErrorView.fxml","Error",new Stage());
        }
    }
}
