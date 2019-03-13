import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.HashMap;

public class Viewer implements ChangeListener<Number> {
    private HashMap<Integer, Floor> floors = new HashMap(2);
    private Box robot;
    private int floorId = 0;
    private Scene scene;
    private Floor floor;

    Viewer(Scene scene) {
        this.scene = scene;
        scene.widthProperty().addListener(this);
        scene.heightProperty().addListener(this);
        robot = new Box(60, 80, 10);
        PhongMaterial tint = new PhongMaterial();
        tint.setDiffuseColor(Color.rgb(28, 234, 189));
        tint.setSpecularColor(Color.rgb(31, 196, 160));
        robot.setMaterial(tint);

        floors.put(0, new Floor(robot, 100));
        changeFloor(0);
    }

    public void changeFloor(int f) {
        floor = floors.get(f);
        scene.setRoot(floor);
        floor.place(scene.getHeight(), scene.getWidth());
    }

    public void moveRobot(int x, int y) {
        floors.get(0).moveTo(x, y);
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        changeFloor(floorId);
    }
}
