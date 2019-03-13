import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Cell extends Group {
    private Line wNorth, wSouth, wEast, wWest;

    public Cell(boolean north, boolean south, boolean east, boolean west, double x, double y, double size) {
        create(x, y, size);
        if (north) setNorth(true);
        if (south) setSouth(true);
        if (east) setEast(true);
        if (west) setWest(true);
    }

    Cell(double x, double y, double size) {
        create(x, y, size);
    }

    public void setNorth(boolean north) {
        setDash(wNorth, north);
    }

    public void setSouth(boolean south) {
        setDash(wSouth, south);
    }

    public void setEast(boolean east) {
        setDash(wEast, east);
    }

    public void setWest(boolean west) {
        setDash(wWest, west);
    }

    private void setDash(Line l, boolean remove) {
        if (remove) l.getStrokeDashArray().addAll(2d, 21d);
        else l.getStrokeDashArray().remove(0, 2);
    }

    private void create(double x, double y, double size) {
        ObservableList<Node> child = getChildren();
        setTranslateX(x);
        setTranslateY(y);
        wNorth = new Line(0, size, size, size);
        wSouth = new Line(0, 0, size, 0);
        wEast = new Line(size, 0, size, size);
        wWest = new Line(0, 0, 0, size);
        wNorth.setStrokeWidth(3);
        wWest.setStrokeWidth(3);
        wEast.setStrokeWidth(3);
        wSouth.setStrokeWidth(3);
        child.addAll(wNorth, wEast, wSouth, wWest);
    }

    public void setColor(Color color) {
        wNorth.setStroke(color);
        wSouth.setStroke(color);
        wEast.setStroke(color);
        wWest.setStroke(color);
    }

}
