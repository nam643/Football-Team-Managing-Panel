import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import model.application.League;
import model.utils.SeedData;

public class ManagerPanelApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SeedData seededData = new SeedData();
        seededData.seed();
        League.initialize(seededData.teams(), seededData.players(), seededData.managers());
        ViewLoader.showStage(League.getInstance(), "/view/LoginView.fxml", "Login", new Stage());
    }
}
