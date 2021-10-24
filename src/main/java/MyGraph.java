import java.util.ArrayList;

/**
 * This class holds the Graph. It knows of all it's nodes and all their connections.
 * @author Severin Goddon
 */
public class MyGraph {
    private ArrayList<NodeConnection> graphConnections = new ArrayList<>();
    private ArrayList<GraphNode> graphNodes = new ArrayList<>();

    public void addNode(GraphNode node){
        graphNodes.add(node);
    }

    public GraphNode findNode(int id){
        for (GraphNode n: graphNodes) {
            if(n.getId()==id) return n;
        }
        return null;
    }

    public void addConnection(NodeConnection connection){
        graphConnections.add(connection);
    }

    public ArrayList<GraphNode> getConnectedNodes(GraphNode node){
        ArrayList<GraphNode> connectedNodes = new ArrayList<>();
        for(NodeConnection connection : graphConnections){
            if(connection.getConnection().getValue().getId() == node.getId()){
                connectedNodes.add(connection.getConnection().getKey());
            }
        }
        return connectedNodes;
    }
}
