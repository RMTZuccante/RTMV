import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Floor extends Group {
    TranslateTransition robotTransition;
    TranslateTransition floorTransition;
    private Box robot;
    private ObservableList<Node> child;
    private int size; //Size of a cell
    private Color cellBorder = Color.rgb(60, 63, 66); // Color of the cell border
    private Pair<Integer, Integer> pos = new Pair<>(0, 0); // (x, y) of the robot

    Floor(Box robot, int u) {
        size = u;
        robotTransition = new TranslateTransition(new Duration(1000), robot);
        floorTransition = new TranslateTransition(new Duration(1000), this);
        setScaleY(-1);
        child = getChildren();
        this.robot = robot;
        child.add(robot);
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                addCell(i, j);
            }
        }
    }

    public void addCell(int x, int y) {
        Rectangle r = new Rectangle(x * size - size / 2, y * size - size / 2, size, size);
        Rectangle mr = new Rectangle(-x * size - size / 2, -y * size - size / 2, size, size);
        r.setFill(Color.TRANSPARENT);
        mr.setFill(Color.TRANSPARENT);
        r.setStroke(cellBorder);
        child.addAll(r, mr);
        robot.toFront();
    }

    public void place(double height, double width) {
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
    }
}