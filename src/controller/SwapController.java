package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.application.League;
import model.application.Manager;
import model.application.Team;
import model.application.Teams;

public class SwapController extends Controller<League> {
    @FXML private ListView<Team> managableTeamLv;
    @FXML private Button swapBtn;
    private ObservableList<Team> tmp = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        Bindings.bindContent(tmp,League.getInstance().getManageableTeams().getTeams());
        managableTeamLv.setItems(tmp);
        swapBtn.disableProperty().bind(
                managableTeamLv.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    public void handleClose(ActionEvent actionEvent) {
        stage.close();
    }

    private Team getSelectedTeam(){return managableTeamLv.getSelectionModel().getSelectedItem();}

    public void handleSwap(ActionEvent actionEvent) {
        Team team = getSelectedTeam();
        Manager loginManager = League.getInstance().getLoggedInManager();
        League.getInstance().setManagerForTeam(loginManager,team);
    }
}
