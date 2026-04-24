package problem02;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Drawing extends Application {
    private final int SIZE_PX = 400;
    //    private final int DRAWING_BREAKPOINT = 400 / 2;
    private final int LINE_COUNT = 20;

    public static void main(String[] args) {
        launch(args);
    }

    private void drawCoolFunctionAt(Group parent, int startX, int startY) {
        int distance = SIZE_PX / LINE_COUNT;

        for (int pos = 0; pos < SIZE_PX; pos += distance) {
            Line line = new Line(Math.abs(startX - SIZE_PX + pos), startY, startX, Math.abs(startY - pos));

            line.setStroke(Color.ORANGERED);

            parent.getChildren().add(line);
        }
    }

    private void drawCoolGraphicOn(Group parent) {
        drawCoolFunctionAt(parent, 0, 0);
        drawCoolFunctionAt(parent, SIZE_PX, 0);
        drawCoolFunctionAt(parent, 0, SIZE_PX);
        drawCoolFunctionAt(parent, SIZE_PX, SIZE_PX);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, SIZE_PX, SIZE_PX);

        // MAIN

        drawCoolGraphicOn(group);

        // end MAIN
        stage.setTitle("Drawing scene"); // Update Title as required
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}
