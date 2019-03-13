import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.TriangleMesh;

import java.io.File;
import java.util.HashMap;

public class Viewer implements ChangeListener<Number> {
    private static PhongMaterial tint = new PhongMaterial();

    static {
        tint.setDiffuseColor(Color.rgb(28, 234, 189));
        tint.setSpecularColor(Color.rgb(31, 196, 160));
    }

    private HashMap<Integer, Floor> floors = new HashMap(2);
    private Shape3D robot;
    private int floorId = 0;
    private Scene scene;
    private Floor floor;

    Viewer(Scene scene) {
        this.scene = scene;
        scene.widthProperty().addListener(this);
        scene.heightProperty().addListener(this);
        robot = new Box(60, 80, 10);
        getRobot();

        floors.put(0, new Floor(robot, 150));
        changeFloor(0);
    }

    private void getRobot() {
        try {
            StlMeshImporter importer = new StlMeshImporter();
            File modelFile = new File("robot.stl");
            importer.read(modelFile);
            TriangleMesh model = importer.getImport();
            MeshView robotModel = new MeshView();
            robotModel.setMesh(model);
            robotModel.setScaleX(.5);
            robotModel.setScaleY(.5);
            robotModel.setLayoutX(-110);
            robotModel.setLayoutY(85);
            robot = robotModel;
        } catch (ImportException e) {
            System.out.println("Error importing robot.stl: " + e.getMessage());
        }
        robot.setMaterial(tint);
    }

    public void changeFloor(int f) {
        floor = floors.get(f);
        scene.setRoot(floor);
        floor.setSize(scene.getHeight(), scene.getWidth());
    }

    public void moveRobot(int x, int y) {
        floors.get(0).moveTo(x, y);
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        changeFloor(floorId);
    }
}
