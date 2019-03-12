import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.HashMap;

public class Viewer {
    private HashMap<Integer, Floor> floors = new HashMap(2);
    private Box robot;
    private Group actualFloor;
    private GridPane pane;
    private int rot = 0;
    private int floorId = 0;

    private EventHandler<KeyEvent> keytype = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case LEFT:
                    rot -= 90;
                    changeFloor(floorId);
                    break;
                case RIGHT:
                    rot += 90;
                    changeFloor(floorId);
                    break;
            }
        }
    };

    Viewer(GridPane p) {
        p.getScene().setOnKeyPressed(keytype);
        pane = p;
        p.setAlignment(Pos.CENTER);
        floors.put(0, new Floor(new Canvas(), 50));
        robot = new Box(60, 80, 10);
        PhongMaterial tint = new PhongMaterial();
        tint.setDiffuseColor(Color.rgb(28, 234, 189));
        tint.setSpecularColor(Color.rgb(31, 196, 160));
        robot.setMaterial(tint);
        changeFloor(0);
    }

    public void changeFloor(int f) {
        pane.getChildren().remove(actualFloor);
        actualFloor = new Group(robot, floors.get(f).getCanvas());
        pane.getChildren().add(actualFloor);
        actualFloor.setRotate(rot);
        rot = (int) actualFloor.getRotate();
    }
}
