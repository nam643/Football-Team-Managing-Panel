package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.application.League;

public class ErrorController extends Controller<Exception> {
    @FXML private Label messageLbl;
    @FXML private Label errorTypeLbl;

    @FXML
    private void initialize() {
        messageLbl.setText(model.getMessage());
        errorTypeLbl.setText(model.getClass().getSimpleName());
    }

    public void handleClose(ActionEvent actionEvent) {
        stage.close();
    }
}
