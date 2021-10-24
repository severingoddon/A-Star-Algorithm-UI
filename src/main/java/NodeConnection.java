import java.util.AbstractMap;


/**
 * This class represents a connection between two nodes. It does that using a pair of two node objects and putting them together
 * in a AbstractMap.SimpleEntry
 * @author Severin Goddon
 */
public class NodeConnection {
    private AbstractMap.SimpleEntry<GraphNode, GraphNode> connection;

    public AbstractMap.SimpleEntry<GraphNode, GraphNode> getConnection() {
        return connection;
    }

    public void setConnection(GraphNode firstNode, GraphNode secondNode) {
        connection = new AbstractMap.SimpleEntry<>(firstNode,secondNode);
    }
}
