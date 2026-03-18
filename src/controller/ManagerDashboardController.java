package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.application.League;
import model.application.Manager;

import java.util.Objects;

public class ManagerDashboardController extends Controller<Manager> {

    @FXML private Label nameLbl;
    @FXML private Button manageBtn;
    @FXML private Button withdrawBtn;

    @FXML
    private ImageView imageView;
    private final StringProperty teamNameBinding = new SimpleStringProperty();


    @FXML
    private void initialize() {
        teamNameBinding.bind(
                Bindings.createStringBinding(() -> {
                    if (getManager().getTeam() == null) {
                        return "No team";
                    } else {
                        return getManager().getTeam().toString();
                    }
                }, getManager().teamProperty())
        );
        nameLbl.textProperty().bind(teamNameBinding);

        imageView.imageProperty().bind(
                Bindings.createObjectBinding(
                        () -> new Image("/view/image/"
                                + (getManager().getTeam() == null ? "none" : getManager().getTeam().getTeamName().toLowerCase())
                                + ".png"),
                        teamNameBinding
                )
        );

        manageBtn.disableProperty().bind(getManager().teamProperty().isNull());
        withdrawBtn.disableProperty().bind(getManager().teamProperty().isNull());
    }

    public ReadOnlyStringProperty getTeamNameBindingProperty() {
        return teamNameBinding;
    }

    public String getTeamNameBinding() {
        return teamNameBinding.get();
    }

    public void setTeamNameBinding(String s) {
        teamNameBinding.set(s);
    }

    public Manager getManager() {
        return model;
    }

    public void handleSwapTeam(ActionEvent actionEvent) {
        ViewLoader.showStage(League.getInstance().getManageableTeams(), "/view/SwapView.fxml", "Swap", new Stage());
    }


    public void handleWithdraw(ActionEvent actionEvent) {
        Manager loginManager = League.getInstance().getLoggedInManager();
        League.getInstance().withdrawManagerFromTeam(loginManager);
    }

    public void handleClose(ActionEvent actionEvent) {
        stage.close();
    }

    public void handleManage(ActionEvent actionEvent) {
        stage.close();
        ViewLoader.showStage(getManager().getTeam(), "/view/TeamDashboardView.fxml", "Team Dashboard", new Stage());
    }
}
