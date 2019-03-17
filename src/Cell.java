import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Cell extends Group {
    private static final Color VISITED = Color.rgb(219, 198, 142), NONVISITED = Color.rgb(0, 204, 40), BORDER = Color.rgb(60, 63, 66); //Color of visited/non visited marker; Color of the cell border
    private static final int lineWidth = 3;
    private Line wNorth, wSouth, wEast, wWest; //Lines representing walls
    private Line balanceLeft, balanceRight;
    private Ellipse marker; //Marker that identify if the cell is visited
    private Group balance;
    private Rectangle fill;

    public Cell(boolean north, boolean south, boolean east, boolean west, double x, double y, double bx, double by, double size) {
        create(x, y, bx, by, size);
        if (north) setNorth(true);
        if (south) setSouth(true);
        if (east) setEast(true);
        if (west) setWest(true);
    }

    Cell(double x, double y, double bx, double by, double size) {
        create(x, y, bx, by, size);
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

    private void create(double x, double y, double bx, double by, double size) {
        ObservableList<Node> child = getChildren();
        setTranslateX(x);
        setTranslateY(y);
        marker = new Ellipse(size / 2, size / 2, size * 0.05, size * 0.05);
        wNorth = new Line(0, size, size, size);
        wSouth = new Line(0, 0, size, 0);
        wEast = new Line(size, 0, size, size);
        wWest = new Line(0, 0, 0, size);
        fill = new Rectangle(lineWidth, lineWidth, size - lineWidth * 2, size - lineWidth * 2);
        fill.setStroke(Color.TRANSPARENT);
        fill.setFill(Color.TRANSPARENT);

        wNorth.setStrokeWidth(lineWidth);
        wWest.setStrokeWidth(lineWidth);
        wEast.setStrokeWidth(lineWidth);
        wSouth.setStrokeWidth(lineWidth);
        marker.setFill(NONVISITED);


        balanceRight = new Line(size, 0, size, size);
        balanceLeft = new Line(0, 0, 0, size);
        balance = new Group(balanceLeft, balanceRight);
        balance.setTranslateX(bx - x);
        balance.setTranslateY(by - y);

        balanceLeft.setStrokeWidth(lineWidth);
        balanceRight.setStrokeWidth(lineWidth);
        balanceLeft.setStroke(Color.TRANSPARENT);
        balanceRight.setStroke(Color.TRANSPARENT);

        setBorder(BORDER);
        child.addAll(fill, wNorth, wEast, wSouth, wWest, marker, balance);
    }

    public void setBorder(Color color) {
        wNorth.setStroke(color);
        wSouth.setStroke(color);
        wEast.setStroke(color);
        wWest.setStroke(color);
    }

    public void setVisited(boolean visited) {
        if (visited)
            marker.setFill(VISITED);
        else
            marker.setFill(NONVISITED);
    }

    public void setMirror() {
        fill.setFill(Color.rgb(186, 186, 186));
    }

    public void setBlack() {
        fill.setFill(Color.BLACK);
    }

}
