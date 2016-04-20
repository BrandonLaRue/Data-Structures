import java.util.ArrayList;

public class Node {
	
	public long id;
	public String label;
	public ArrayList<Edge> edgeOutList;
	public ArrayList<Edge> edgeInList;
	
	public Node(long id, String label) {
		// check arguments
		if(label == null) {
			throw new IllegalArgumentException("Must specify a label");
		}
		if(id < 0) {
			throw new IllegalArgumentException("id must be greater than zero");
		}
		
		// assign instance properties
		this.id = id;
		this.label = label;		// label can be the empty string
		edgeOutList = new ArrayList<Edge>();
		edgeInList = new ArrayList<Edge>();
	}
	
	public int getInDegree() {
		return edgeInList.size();
	}
	
	public int getOutDegree() {
		return edgeOutList.size();
	}
	
	
	
}
