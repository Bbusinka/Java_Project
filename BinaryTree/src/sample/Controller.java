package sample;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;
import java.util.function.UnaryOperator;

public class Controller {

    @FXML
    private Button build;

    @FXML
    private Button clear;

    @FXML
    private Button inOrder;

    @FXML
    private Button insert;

    @FXML
    private Button postOrder;

    @FXML
    private Button preOrder;

    @FXML
    private Button search;

    @FXML
    private MenuItem typedou;

    @FXML
    private MenuItem typeint;

    @FXML
    private MenuItem typestr;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField value;

    @FXML
    private Pane panel;

    public StringBuilder stringBuilder = new StringBuilder(" ");

    private GraphicTree graphicTree = new GraphicTree();

    private boolean postOrderFlag = false;
    private boolean inOrderFlag = false;
    private boolean preOrderFlag = false;
    private boolean builtTree = false;

    public String TypeChoice="Integer";

    public enum Traversals {PostOrder, InOrder, preOrder}

    @FXML
    void initialize(){
        textint();
        insert.setOnAction(event -> {
            if (!value.getText().isEmpty()){
                if (graphicTree.binary_tree.maxDepth(graphicTree.binary_tree.getRoot()) > 5) {
                    clearBut();
                    alertBox("Nie można zastosować drzewa o głębokości większej niż 5!\nProszę wypełnić ponownie.");
                    return;
                }

                if (builtTree) {
                    alertBox("Nie można dodać numeru do wbudowanego drzewa!\nProszę najpierw posprzątać. ");
                    value.clear();
                    return;
                }
                String saveStringVal = value.getText();
                for (int i = 0; i < graphicTree.struktura.keyForCircles.size(); i++)
                    if (Objects.equals(saveStringVal, graphicTree.struktura.keyForCircles.get(i))) {
                        value.clear();
                        return;
                    }
                graphicTree.binary_tree.insert(saveStringVal,TypeChoice);
                graphicTree.struktura.keyForCircles.add(saveStringVal);
                value.clear();}
        });

        typeint.setOnAction(event -> {
            textint();
            TypeChoice = "Integer";
        });
        typestr.setOnAction(event -> {
            TypeChoice = "String";
            value.setTextFormatter(null);});
        typedou.setOnAction(event -> {
            TypeChoice = "Double";
            textdou();
        });

        build.setOnAction(event -> {
            if (graphicTree.binary_tree.getRoot() == null || builtTree)
                return;
            stringBuilder.append("Numbers" + " : ");
            for (int i = 0; i < graphicTree.struktura.keyForCircles.size(); i++) {
                graphicTree.struktura.circleAndNumMap.put(new Text(graphicTree.struktura.keyForCircles.get(i)), new Circle());
                stringBuilder.append(graphicTree.struktura.keyForCircles.get(i) + "  ");
            }
            textArea.setText(stringBuilder.toString());

            graphicTree.locateNodeDown();
            for (Map.Entry<Text, Circle> e : graphicTree.struktura.circleAndNumMap.entrySet())
                panel.getChildren().addAll(e.getValue(), e.getKey());

            graphicTree.drawTree(graphicTree.binary_tree.getRoot(), graphicTree.struktura.circleAndNumMap, 700, 120, graphicTree.getSplitTreeArea());
            graphicTree.locateLinesOnTree(graphicTree.struktura.lines, graphicTree.binary_tree.getRoot(), 700, 140, 210, graphicTree.getDivLineLenghtX(), graphicTree.getDivLineLenghtY(), graphicTree.getDivXdrawLine());
            displayLines(graphicTree.struktura.lines);
            builtTree = true;
        });

        search.setOnAction(event -> {
            for (Map.Entry<Text, Circle> g : graphicTree.struktura.circleAndNumMap.entrySet()) {
                if (Objects.equals(g.getKey().getText(), value.getText())) {
                    g.getValue().setStrokeWidth(6);
                    g.getValue().setStroke(Color.GREEN);
                    return;
                }

            }
            value.clear();

            alertBox("Węzeł nie istnieje");
        });

        clear.setOnAction(event -> { clearBut();});
        postOrder.setOnAction(event -> {
            if (!postOrderFlag)
                displayTraversals(Traversals.PostOrder);
            else
                alertBox("PostOrder już wyświetlane dla tego drzewa");
        });

        inOrder.setOnAction(event -> {
            if (!inOrderFlag)
                displayTraversals(Traversals.InOrder);
            else
                alertBox("InOrder już wyświetlane dla tego drzewa");
        });

        preOrder.setOnAction(event -> {
            if (!preOrderFlag)
                displayTraversals(Traversals.preOrder);
            else
                alertBox("PreOrder już wyświetlane dla tego drzewa");
        });

    }
    public void textint(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change; }
            return null; };
        value.setTextFormatter(new TextFormatter<String>(integerFilter));
    }

    public void textdou(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("\\d{0,1}([\\.]\\d{0,1})?")) {
                return change; }
            return null; };
        value.setTextFormatter(new TextFormatter<String>(integerFilter));
    }

    public void clearBut() {
        for (Map.Entry<Text, Circle> e : graphicTree.struktura.circleAndNumMap.entrySet()) {
            panel.getChildren().removeAll(e.getKey(), e.getValue());
        }
        for (int i = 0; i < graphicTree.struktura.lines.size(); i++)
            panel.getChildren().remove(graphicTree.struktura.lines.get(i));

        graphicTree.struktura.lines.clear();
        graphicTree.setLinesCounter(0);
        graphicTree.struktura.keyForCircles.clear();
        graphicTree.struktura.circleAndNumMap.clear();
        stringBuilder.setLength(0);
        textArea.clear();
        value.clear();
        graphicTree.binary_tree.deleteTree(graphicTree.binary_tree.getRoot());
        postOrderFlag = false;
        preOrderFlag = false;
        inOrderFlag = false;
        builtTree = false;
    }

    public void alertBox(String text) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        window.setTitle("Uwaga");

        Label label = new Label(text);
        Button Closebutton = new Button("Zamknij to okno");
        Closebutton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, Closebutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 100);
        window.setScene(scene);
        window.showAndWait();
    }


    public void displayLines(ArrayList<Line> lines) {
        for (int i = 0; i < graphicTree.struktura.lines.size(); i++)
            panel.getChildren().add(graphicTree.struktura.lines.get(i));
    }

    public void displayTraversals(Traversals traversals) {
        switch (traversals) {
            case InOrder:
                inOrderFlag = true;
                graphicTree.binary_tree.inOrder(graphicTree.binary_tree.getRoot());
                stringBuilder.append(" | " + "  InOrder " +  " : ");
                break;
            case preOrder:
                preOrderFlag = true;
                graphicTree.binary_tree.preOrder(graphicTree.binary_tree.getRoot());
                stringBuilder.append(" | " + "  PreOrder " +  " : ");
                break;
            case PostOrder:
                postOrderFlag = true;
                graphicTree.binary_tree.postOrder(graphicTree.binary_tree.getRoot());
                stringBuilder.append(" | " + "  PostOrder "  + " : ");
                break;
        }
        for (int i = 0; i < graphicTree.struktura.keyForCircles.size(); i++)
            stringBuilder.append(graphicTree.binary_tree.saveValForDisplay.get(i) + "  ");

        textArea.setText(stringBuilder.toString());
        graphicTree.binary_tree.saveValForDisplay.clear();
    }

}



