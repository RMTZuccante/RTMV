import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Floor {
    Canvas canvas;
    GraphicsContext graphics;
    Point2D transation = new Point2D(0, 0);
    private int unit;
    private Paint cellBorder = Paint.valueOf("rgb(60,63,66)");

    Floor(Canvas canvas, int unit) {
        this.unit = unit;
        this.canvas = canvas;
        canvas.setHeight(unit);
        canvas.setWidth(unit);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void cell(int x, int y) {

    }
}
