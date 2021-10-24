import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class AstarGuiClass extends Application {

    private Timeline timeline;
    GridPane gridPane;
    private int rowOfStart;
    private int columnOfStart;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true;");
        AStarAlgorithm.doSetup();
        ArrayList<NodePosition> nodePositions = new ArrayList<>();

        String[][] basicGrid = AStarAlgorithm.getBasicGrid();
        for (int i = 0; i < basicGrid.length; i++) {
            for (int j = 0; j < basicGrid[0].length; j++) {
                Text t = new Text();
                if (basicGrid[i][j].equals("x")) {
                    Button b = new Button();
                    b.setStyle("-fx-background-color: black;");
                    b.setMinSize(75, 37);
                    b.setId(basicGrid[i][j]);
                    gridPane.add(b, j, i);
                } else {
                    Button b = new Button();
                    NodePosition pos = new NodePosition();
                    pos.setRowIndex(i);
                    pos.setColumnIndex(j);
                    pos.setId(basicGrid[i][j]);
                    nodePositions.add(pos);
                    b.setText(" " + basicGrid[i][j] + " ");
                    b.setMinSize(75, 37);
                    t.setText(" " + basicGrid[i][j] + " ");
                    if(Integer.parseInt(basicGrid[i][j])==AStarAlgorithm.getStartNode().getId()){
                        rowOfStart = i;
                        columnOfStart = j;
                        b.setStyle("-fx-background-color: orange;");
                    }
                    if(Integer.parseInt(basicGrid[i][j])==AStarAlgorithm.getEndNode().getId()){
                        b.setStyle("-fx-background-color: darkgreen;");
                    }
                    gridPane.add(b, j, i);
                }
            }
        }
        ObservableList<Node> childrens = gridPane.getChildren();
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<GraphNode> closedNodes = AStarAlgorithm.doStepInAlgorithm();
                for (GraphNode n : closedNodes) {
                    int rowindex = 0;
                    int columnindex = 0;
                    for(int i = 0; i<nodePositions.size();i++){
                            int id = Integer.parseInt(nodePositions.get(i).getId());
                            if(id == n.getId()){
                                rowindex = nodePositions.get(i).getRowIndex();
                                columnindex = nodePositions.get(i).getColumnIndex();
                        }
                    }
                    for (Node node : childrens) {
                        if(gridPane.getRowIndex(node) == rowindex && gridPane.getColumnIndex(node) == columnindex) {
                            if(!(gridPane.getRowIndex(node) == rowOfStart && gridPane.getColumnIndex(node) == columnOfStart))
                            node.setStyle("-fx-background-color: red;");
                            break;
                        }
                    }
                }
                ArrayList<GraphNode> openNodes = AStarAlgorithm.getOpenNodes();
                for (GraphNode n : openNodes) {
                    int rowindex = 0;
                    int columnindex = 0;
                    for(int i = 0; i<nodePositions.size();i++){
                        int id = Integer.parseInt(nodePositions.get(i).getId());
                        if(id == n.getId()){
                            rowindex = nodePositions.get(i).getRowIndex();
                            columnindex = nodePositions.get(i).getColumnIndex();
                        }
                    }
                    for (Node node : childrens) {
                        if(gridPane.getRowIndex(node) == rowindex && gridPane.getColumnIndex(node) == columnindex) {
                            node.setStyle("-fx-background-color: green;");
                            break;
                        }
                    }
                }
                if(AStarAlgorithm.isIsFinished()){
                    int[] finalPath = AStarAlgorithm.calculateFinalPath();
                    for(int i = 0; i<finalPath.length;i++){
                        int rowindex = 0;
                        int columnindex = 0;
                        for(int j = 0; j<nodePositions.size();j++){
                            int id = Integer.parseInt(nodePositions.get(j).getId());
                            if(id == finalPath[i]){
                                rowindex = nodePositions.get(j).getRowIndex();
                                columnindex = nodePositions.get(j).getColumnIndex();
                            }
                        }
                        for (Node node : childrens) {
                            if(gridPane.getRowIndex(node) == rowindex && gridPane.getColumnIndex(node) == columnindex) {
                                node.setStyle("-fx-background-color: blue;");
                                break;
                            }
                        }

                    }
                    timeline.stop();
                }

            }
        }));

        timeline.setCycleCount(gridPane.getColumnCount() * gridPane.getRowCount());
        timeline.play();

        Scene scene2 = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene2);
        scene2.setRoot(primaryStage.getScene().getRoot());
        primaryStage.show();
    }
}
