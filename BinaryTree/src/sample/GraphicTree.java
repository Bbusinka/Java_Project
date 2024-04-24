package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class GraphicTree {
    double splitTreeArea;
    double divXdrawLine;
    double divLineLenghtX;
    double divLineLenghtY;
    private int linesCounter;
    public Struktura struktura = new Struktura();
    public Binary_Tree binary_tree = new Binary_Tree();


    public GraphicTree() {
        this.splitTreeArea = 240;
        this.divXdrawLine = 240;
        this.divLineLenghtX = 225;
        this.divLineLenghtY = 15;
        this.linesCounter = 0;
    }


    public void locateNodeDown() {
        int coordinate = 0;
        int locationXCircle, locationXnum;
        for (Map.Entry<Text, Circle> e : struktura.circleAndNumMap.entrySet()) {
            if (struktura.keyForCircles.size() <= 15) {
                locationXCircle = 1130 - coordinate;
                locationXnum = 1120;
                locateNodesBottom(e, locationXCircle, locationXnum, coordinate);
                coordinate += 62;

            } else {
                locationXCircle = 1300 - coordinate;
                locationXnum = 1290;
                locateNodesBottom(e, locationXCircle, locationXnum, coordinate);
                coordinate += 55;
            }
        }
    }


    public void drawTree(Binary_Tree.Node binary_TreeNode, Map<Text, Circle> circleAndNumMap, double endX, int endY, double splitTreeArea) {
        if (binary_TreeNode == null)
            return;

        String newRootText = binary_TreeNode.getData();
        for (Map.Entry<Text, Circle> e : circleAndNumMap.entrySet()) {
            if (Objects.equals(e.getKey().getText(), newRootText)) {
                locateNodesUp(e, Integer.parseInt(e.getValue().getId()), endX, endY);
            }
        }
        drawTree(binary_TreeNode.getLeft(), circleAndNumMap, endX - (splitTreeArea), endY + 125, splitTreeArea / 2.1);
        drawTree(binary_TreeNode.getRight(), circleAndNumMap, endX + (splitTreeArea), endY + 125, splitTreeArea / 2.1);
    }



    public void locateNodesUp(Map.Entry<Text, Circle> e, int startX, double endX, int endY) {

        Line linePath1 = new Line(startX, 630, endX, endY);

        PathTransition path = new PathTransition();
        PathTransition path2 = new PathTransition();

        path.setNode(e.getValue());
        path.setPath(linePath1);
        path2.setNode(e.getKey());
        path2.setPath(linePath1);

        ParallelTransition pr = new ParallelTransition(path, path2);
        pr.play();


    }


    public void locateNodesBottom(Map.Entry<Text, Circle> e, int locationXCircle, int locationXnum, int coordinate) {

        e.getValue().setCenterX(locationXCircle);
        e.getValue().setCenterY(630);
        e.getValue().setRadius(25);
        e.getValue().setStroke(Color.GREEN);
        e.getValue().setId(Integer.toString(locationXCircle));
        e.getKey().setFont(new Font(20));
        e.getKey().setBoundsType(TextBoundsType.VISUAL);
        e.getKey().setX(locationXnum - coordinate);
        e.getKey().setY(640);
        e.getKey().setFill(Color.WHITE);

    }


    public void locateLinesOnTree(ArrayList<Line> linesAdd, Binary_Tree.Node root, double startX, double startY, double endY, double divLineLenghtX, double divLineLenghtY, double divXdrawLine) {
        if (root == null)
            return;
        if (root.getLeft() != null) {
            drawLine(linesAdd, startX - 15, startY, startX - divLineLenghtX, endY + divLineLenghtY);

        }
        if (root.getRight() != null) {
            drawLine(linesAdd, startX + 15, startY, startX + divLineLenghtX, endY + divLineLenghtY);
        }

        locateLinesOnTree(linesAdd, root.getLeft(), startX - divXdrawLine, startY + 125, startY + divLineLenghtY + 195, divLineLenghtX / 2.25, divLineLenghtY / 2.0, divXdrawLine / 2.1);
        locateLinesOnTree(linesAdd, root.getRight(), startX + divXdrawLine, startY + 125, startY + divLineLenghtY + 195, divLineLenghtX / 2.25, divLineLenghtY / 2.0, divXdrawLine / 2.1);

    }


    public void drawLine(ArrayList<Line> linesAdd, double startX, double startY, double endX, double endY) {

        linesAdd.add(linesCounter, new Line());
        linesAdd.get(linesCounter).setStartX(startX);
        linesAdd.get(linesCounter).setStartY(startY);
        linesAdd.get(linesCounter).setEndX(endX);
        linesAdd.get(linesCounter).setEndY(endY);
        linesAdd.get(linesCounter).setStrokeWidth(5);
        linesAdd.get(linesCounter).setStroke(Color.BLACK);

        linesCounter++;
    }


    public double getSplitTreeArea() {
        return splitTreeArea;
    }


    public double getDivXdrawLine() {
        return divXdrawLine;
    }


    public double getDivLineLenghtX() {
        return divLineLenghtX;
    }


    public double getDivLineLenghtY() {
        return divLineLenghtY;
    }

    public void setLinesCounter(int linesCounter) {
        this.linesCounter = linesCounter;
    }



}


