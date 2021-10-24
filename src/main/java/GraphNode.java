
/**
 * This class represents one Node in the Graph. It has some attributes like costs or id's and the basic getter&setter
 * @author Severin Goddon
 */
public class GraphNode {
    private int id;
    private double gCost;
    private double hCost;
    private double fCost;
    private GraphNode parentNode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public double getfCost() {
        return fCost;
    }

    public void setfCost(double fCost) {
        this.fCost = fCost;
    }

    public void setParentNode(GraphNode parentNode){
        this.parentNode = parentNode;
    }

    public GraphNode getParentNode(){
        return parentNode;
    }
}
