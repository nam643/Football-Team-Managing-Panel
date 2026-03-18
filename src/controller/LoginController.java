package controller;

import au.edu.uts.ap.javafx.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.application.*;
import model.exception.InvalidSigningException;
import model.exception.UnauthorisedAccessException;

import javax.security.auth.login.LoginException;
import java.io.IOException;


public class LoginController extends Controller<League> {

    @FXML private TextField managerIdTf;
    @FXML private Button loginBtn;
    @FXML private Button exitBtn;


    @FXML
    private void initialize() {
        managerIdTf.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                loginBtn.fire();
            }
        });
        loginBtn.disableProperty().bind(managerIdTf.textProperty().isEmpty());
    }

    public String getManagerIdTf(){return managerIdTf.getText();}


    public void handleLogin(ActionEvent e) throws IOException {
        try{
            Manager manager = League.getInstance().validateManager(Integer.parseInt(getManagerIdTf()));
            League.getInstance().setLoggedInManager(manager);
            stage.close();
            ViewLoader.showStage(manager, "/view/ManagerDashboardView.fxml", "Manager Dashboard", new Stage());

        } catch (UnauthorisedAccessException ex) {
            ViewLoader.showStage(ex, "/view/ErrorView.fxml","Error",new Stage());
        } catch (NumberFormatException ex) {
            NumberFormatException numberFormatException = new NumberFormatException("Incorrect format for manager id");
            ViewLoader.showStage(numberFormatException, "/view/ErrorView.fxml", "Error", new Stage());
        }
    }


    public void handleExit(ActionEvent e) {
        stage.close();
    }
}
