
public class Edge {
	
	public long id;
	public String label;
	public long weight;
	public Node fromNode;
	public Node toNode;
	
	public Edge(long id, String label, long weight, Node fromNode, Node toNode) {
		// check args
		if (fromNode == null || toNode == null) {
			throw new IllegalArgumentException("fromNode or toNode undefined");
		}
		if(id < 0) {
			throw new IllegalArgumentException("id must be greater than zero");
		}
		
		// set instance properties
		this.id = id;
		this.label = label;		// label can be null or empty string
		this.weight = weight;
		this.fromNode = fromNode;
		this.toNode = toNode;
	}
	
}
