import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Shape3D;
import javafx.util.Duration;

import java.util.HashMap;

public class Floor extends Group {
    TranslateTransition robotTransition;
    TranslateTransition floorTransition;
    Pair<Integer, Integer> pos = new Pair<>(0, 0); // (x, y) of the robot
    private Shape3D robot;
    private ObservableList<Node> child;
    private int size; //Size of a cell
    private HashMap<Pair<Integer, Integer>, Cell> cells = new HashMap<>(20);

    Floor(Shape3D robot, int u) {
        size = u;
        robotTransition = new TranslateTransition(new Duration(1000), robot);
        floorTransition = new TranslateTransition(new Duration(500), this);
        setScaleY(-1);
        child = getChildren();
        this.robot = robot;
        child.add(robot);
        addCell(0, 0);
        addCell(0, 1);
        addCell(0, 2);
        cells.get(pos).setVisited(true);
        cells.get(pos).setNorth(true);
        cells.get(pos).setMirror();
        cells.get(new Pair<>(0, 1)).setSouth(true);
        cells.get(new Pair<>(0, 1)).setBlack();
    }

    public void addCell(int x, int y) {
        Cell c = new Cell(x * size - size / 2, y * size - size / 2,-x * size - size / 2, -y * size - size / 2, size);
        child.add(c);
        robot.toFront();
        cells.put(new Pair<>(x, y), c);
    }

    public void setSize(double height, double width) {
        setLayoutX(width / 2 + pos.key);
        setLayoutY(height / 2 + pos.value);
    }

    public void moveTo(int x, int y) {
        robotTransition.stop();
        floorTransition.stop();

        robotTransition.setFromX(pos.key * size);
        floorTransition.setFromX(-pos.key * size);

        robotTransition.setFromY(pos.value * size);
        floorTransition.setFromY(pos.value * size);
        pos.key = x;
        pos.value = y;
        robotTransition.setToX(pos.key * size);
        floorTransition.setToX(-pos.key * size);

        robotTransition.setToY(pos.value * size);
        floorTransition.setToY(pos.value * size);

        robotTransition.play();
        floorTransition.play();
        cells.get(pos).setVisited(true);
    }
}