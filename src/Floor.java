import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Floor {
    Canvas canvas;
    GraphicsContext graphics;
    /*Translation to apply to (x, y) coordinates to implement negative values*/
    Pair<Integer, Integer> negativeTranslation = new Pair<>(0, 0);
    /*Translation to apply to the parent container to fix its translation when adding cells on the X axys*/
    Pair<Integer, Integer> planeTranslation = new Pair<>(0, 0);

    private int unit;
    private Paint cellBorder = Color.rgb(60, 63, 66);

    Floor(Canvas canvas, int u) {
        unit = u * 2;
        this.canvas = canvas;
        graphics = canvas.getGraphicsContext2D();
        canvas.setHeight(unit);
        canvas.setWidth(unit);
        canvas.setScaleX(1);
        canvas.setScaleY(-1);
        canvas.relocate(-unit / 2, -unit / 2);
        new Thread(() -> {
            try {
                Platform.runLater(() -> drawcell(0, 0));
                Thread.sleep(1000);
                Platform.runLater(() -> drawcell(1, 1));
                Thread.sleep(1000);
                Platform.runLater(() -> drawcell(2, 0));
                Thread.sleep(1000);
                //Platform.runLater(() -> drawcell(-1, 0));
            } catch (InterruptedException e) {

            }
        }).start();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void drawcell(int x, int y) {
        x *= unit;
        y *= unit;

        if (x < 0) {
            x -= unit;
            if (-x > negativeTranslation.key) {
                double w = canvas.getWidth() - negativeTranslation.key;
                negativeTranslation.key = -x;
                canvas.setWidth(w + negativeTranslation.key);
                graphics.translate(negativeTranslation.key, negativeTranslation.value);
            }
        } else if (x + unit > canvas.getWidth()) {
            canvas.setWidth(x + unit);
            planeTranslation.key = x / 2;
        }
        if (y + unit > canvas.getHeight()) {
            canvas.setHeight(y + unit);
            canvas.relocate(-unit / 2, -unit / 2 - y);
            planeTranslation.value = -y / 2;
        }
        graphics.setStroke(cellBorder);
        graphics.strokeRect(x + negativeTranslation.key, y + negativeTranslation.value, unit, unit);
        canvas.getParent().setTranslateX(planeTranslation.key);
        canvas.getParent().setTranslateY(planeTranslation.value);
    }
}
