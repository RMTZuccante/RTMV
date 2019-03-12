import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RealTimeMatrixViewer extends Application {

    private static final String TITLE = "Prova";
    private Viewer viewer;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);
        stage.setFullScreenExitHint("Press ESC to exit fullscreen");
        //stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        GridPane root = new GridPane();
        stage.setScene(new Scene(root));
        stage.show();
        viewer = new Viewer(root);
    }
}
