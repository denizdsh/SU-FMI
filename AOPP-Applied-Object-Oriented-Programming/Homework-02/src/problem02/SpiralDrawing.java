package problem02;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SpiralDrawing extends Application {
    private final int WINDOW_SIZE = 300;
    private final int PADDING = 25;
    private final int workingSpacePx = WINDOW_SIZE - 2 * PADDING;

    public static void main(String[] args) {
        launch(args);
    }

    private void drawSquareSpiralOn(ObservableList<Node> children) {
        final int SUBSQUARE_COUNT = 5;

        int distance = workingSpacePx / SUBSQUARE_COUNT / 2;

        for (int i = 0; i < SUBSQUARE_COUNT; i++) {
            int offset = i * distance;
            int innerPadding = offset + distance;

            Line lineTop = new Line(offset, offset, workingSpacePx - offset, offset);

            Line lineBottom = new Line(offset, workingSpacePx - offset, workingSpacePx - innerPadding, workingSpacePx - offset);

            Line lineLeft = new Line(offset, offset, offset, workingSpacePx - offset);

            Line lineRight = new Line(workingSpacePx - innerPadding, innerPadding, workingSpacePx - innerPadding, workingSpacePx - offset);

            lineTop.setStroke(Color.RED);
            lineBottom.setStroke(Color.RED);
            lineLeft.setStroke(Color.RED);
            lineRight.setStroke(Color.RED);

            children.addAll(lineTop, lineBottom, lineLeft, lineRight);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();

        StackPane root = new StackPane(pane);
        root.setPadding(new Insets(PADDING));

        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE);

        // Main
        drawSquareSpiralOn(pane.getChildren());

        // end Main
        stage.setTitle("Square Spiral"); // Update Title as required
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}
