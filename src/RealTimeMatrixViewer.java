import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
        //stage.setAlwaysOnTop(true);
        Scene scene = new Scene(new Group());
        scene.setFill(Color.WHEAT);
        stage.setScene(scene);
        stage.show();
        viewer = new Viewer(scene);
        //new Thread(() -> recv()).start();
    }

    public void recv() {
        try {
            for (int i = -3; i <= 3 && !Thread.interrupted(); i++) {
                for (int j = -3; j <= 3; j++) {
                    viewer.moveRobot(i, j);
                    Thread.sleep(1500);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
