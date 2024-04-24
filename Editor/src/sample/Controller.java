package sample;


import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;

public class Controller {

    @FXML
    private Pane canva;

    @FXML
    private ColorPicker color;


    @FXML
    private MenuItem kolo;

    @FXML
    private Menu pomoc;

    @FXML
    private MenuItem prostokat;

    @FXML
    private MenuItem trojkat;
    public double OX1,OY1,OX2,OY2;
    public Rectangle rectangle = new Rectangle();
    public Polygon polygon = new Polygon();
    public Circle circle = new Circle();
    @FXML
    void initialize() {

        pomoc.setOnAction(event -> {
            Runtime r = Runtime.getRuntime();
            Process process =null;
            String cmd ="notepad README.txt";
            try {
                process = r.exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        kolo.setOnAction(event -> {
            circle = new Circle();
            canva.setOnMousePressed(event1 -> {
                OX1 = event1.getSceneX();
                OY1 = event1.getSceneY();
                circle.setCenterX(OX1);
                circle.setCenterY(OY1 - 26);
            });
            canva.setOnMouseReleased(event2 -> {
                OX2 = event2.getSceneX();
                OY2 = event2.getSceneY();
                circle.setRadius(Math.sqrt(Math.pow((OX2 - OX1), 2) + Math.pow((OY2 - OY1), 2)));
            });
            circle.setOnScroll((ScrollEvent event3) -> {
                double zoomFactor = 1.05;
                double deltaY = event3.getDeltaY();
                if (deltaY < 0){
                    zoomFactor = 2.0 - zoomFactor;
                }
                circle.setRadius(circle.getRadius()*zoomFactor);
            });
            canva.getChildren().add(circle);
            color.setValue(Color.BLACK);
            color.setOnAction(event1 -> {
                circle.setFill(color.getValue());
            });
        });

//---------------------------------------------------------------------------------------
        prostokat.setOnAction(event -> {
            rectangle = new Rectangle();
            canva.setOnMousePressed(event1 -> {
                OX1=event1.getSceneX();
                OY1=event1.getSceneY();
                rectangle.setX(OX1);
                rectangle.setY(OY1-26);
            });
            canva.setOnMouseReleased(event2 -> {
                OX2 = event2.getSceneX();
                OY2 = event2.getSceneY();
                double width=Math.sqrt(Math.pow((OX2-OX1),2)+Math.pow(0.0,2));
                double height=Math.sqrt(Math.pow(0.0,2)+Math.pow((OY2-OY1),2));
                rectangle.setWidth(width);
                rectangle.setHeight(height);
            });
            rectangle.setOnScroll((ScrollEvent event3) -> {
                double zoomFactor = 1.05;
                double deltaY = event3.getDeltaY();
                if (deltaY < 0){
                    zoomFactor = 2.0 - zoomFactor;
                }
                rectangle.setWidth(rectangle.getWidth()*zoomFactor);
                rectangle.setHeight(rectangle.getHeight()*zoomFactor);
            });
            canva.getChildren().add(rectangle);
            color.setValue(Color.BLACK);
            color.setOnAction(event1 -> {
                rectangle.setFill(color.getValue());
            });
        });
//----------------------------------------------------------------------------------------
        trojkat.setOnAction(event -> {
            polygon = new Polygon();
            canva.setOnMousePressed(event1 -> {
                polygon.getPoints().clear();
                OX1=event1.getSceneX();
                OY1=event1.getSceneY();
            });
            canva.setOnMouseReleased(event2 -> {
                OX2 = event2.getSceneX();
                OY2 = event2.getSceneY();
                polygon.getPoints().addAll(new Double[]{
                        OX1, OY1-26,
                        OX1, OY2-26,
                        OX2, OY2-26 });
            });
            polygon.setOnScroll((ScrollEvent event3) -> {
                double zoomFactor = 1.05;
                double deltaY = event3.getDeltaY();
                if (deltaY < 0){
                    zoomFactor = 2.0 - zoomFactor;
                }
                polygon.setScaleX(polygon.getScaleX()*zoomFactor);
                polygon.setScaleY(polygon.getScaleY()*zoomFactor);
                polygon.setScaleZ(polygon.getScaleZ()*zoomFactor);
            });
            canva.getChildren().add(polygon);
            color.setValue(Color.BLACK);
            color.setOnAction(event1 -> {
                polygon.setFill(color.getValue());
            });
        });
    }

}