package sample;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Struktura {
    public Map<Text, Circle> circleAndNumMap = new HashMap<Text, Circle>(); // // będzie zawierał tekst i kształt koła
    public ArrayList<String> keyForCircles = new ArrayList<>(); //będzie zawierał listę kluczy, które trafią do key map
    public  ArrayList<Line> lines = new ArrayList<>();// zawiera tablicę linii zgodnie z drzewem

}
