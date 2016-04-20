import java.util.HashMap;
import java.util.LinkedList;
import java.util.Iterator;

public class DiGraph implements DiGraphInterface {

	// Instance variables
	public HashMap<String, Node> nodeMap;
	public HashMap<Long, Node> idMap;
	public HashMap<Long, Edge> edgeMap;

	public int edgeNum;

	// Default constructor
	public DiGraph ( ) { 
		nodeMap = new HashMap<String, Node>();
		idMap = new HashMap<Long, Node>();
		edgeMap = new HashMap<Long, Edge>();

		edgeNum = 0;

	}

	/**
	 * preconditions: idNum >= 0, label != null, no existing node has the same idNum or label
	 */
	@Override
	public boolean addNode(long idNum, String label) {	
		// check for unique label and unique id
		if(nodeMap.containsKey(label) || idMap.containsKey(idNum)){
			return false;
		}

		// make new node object
		Node newNode;
		try {
			newNode = new Node(idNum, label);
		}
		catch(Exception e) {
			// either idNum < 0 or label == null
			return false;
		}

		// add node to nodeMap and idMap
		nodeMap.put(label, newNode);
		idMap.put(idNum, newNode);

		return true;
	}

	/**
	 * preconditions: idNum >= 0, sLabel != null, dLabel != null, sLabel and dLabel
	 * exist as labels of pre-existing nodes, there must not aleady be an edge between the two nodes is the same direction
	 */
	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight,
			String eLabel) {
		// check preconditions
		if(sLabel == null || dLabel == null) {
			// Must specify start and destination labels
			return false;
		}
		if(!nodeMap.containsKey(sLabel) || !nodeMap.containsKey(dLabel)) {
			// sLabel and dLabel must reference valid nodes
			return false;
		}
		if(edgeMap.containsKey(idNum)) {
			// non-unique idNum
			return false;
		}

		// get start and end nodes
		Node startNode = nodeMap.get(sLabel);
		Node endNode = nodeMap.get(dLabel);

		// check if the edge already exists
		for(Edge e : startNode.edgeOutList) {
			if(e.toNode.label.equals(dLabel)) {
				// edge already exists
				return false;
			}
		}

		// make edge object
		Edge newEdge;
		try {
			newEdge = new Edge(idNum, eLabel, weight, startNode, endNode);
		}
		catch(Exception e) {
			// either the idNum < 0 or startNode != null or endNode != null
			return false;
		}

		// register edge with source and destination nodes
		startNode.edgeOutList.add(newEdge);
		endNode.edgeInList.add(newEdge);

		// increase edge count
		edgeNum++;

		// register edge with edgeMap
		edgeMap.put(newEdge.id, newEdge);

		// return 
		return true;

	}

	/**
	 * preconditions: label != null, label references node in the graph
	 */
	@Override
	public boolean delNode(String label) {	
		// check preconditions
		if(label == null || !nodeMap.containsKey(label)){
			return false;
		}

		// get node to delete
		Node node = nodeMap.get(label);

		// delete outgoing edges
		for(Edge e : node.edgeOutList) {
			// remove edge from the receiving nodes
			e.toNode.edgeInList.remove(e);
			edgeNum--;
		}

		// delete incoming edges
		for(Edge e : node.edgeInList) {
			// remove edge from the sending nodes
			e.fromNode.edgeOutList.remove(e);
			edgeNum--;
		}

		// remove node id from idmap
		if(!idMap.containsKey(node.id)) {
			throw new RuntimeException("This shouldn't happen. Something wrong with id register system");
		}
		idMap.remove(node.id);

		// remove node from nodeMap
		nodeMap.remove(label);

		// return 
		return true;
	}

	/**
	 * preconditions: sLabel != null, dLabel != null, sLabel and dLabel are keys in nodeMap,
	 * there exists an edge from sLabel to dLabel
	 */
	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// check preconditions
		if(sLabel == null || dLabel == null) {
			// invalid node
			return false;
		}
		if(!nodeMap.containsKey(sLabel) || !nodeMap.containsKey(dLabel)) {
			// invalid node
			return false;
		}

		// get nodes
		Node startNode = nodeMap.get(sLabel);
		Node endNode = nodeMap.get(dLabel);

		// get edge to delete
		Edge edgeToDelete = null;
		for(Edge e : startNode.edgeOutList) {
			if(e.toNode.label.equals(dLabel)) {
				edgeToDelete = e;
			}
		}

		// check if edge exists
		if(edgeToDelete == null) {
			// the edge did not exist
			return false;
		}

		// remove all references to the edge
		startNode.edgeOutList.remove(edgeToDelete);
		endNode.edgeInList.remove(edgeToDelete);

		// decrease edge count
		edgeNum--;

		// update edgeMap
		edgeMap.replace(edgeToDelete.id, edgeToDelete);

		// return
		return true;
	}

	/**
	 * preconditions: the number of nodes registered should be equal to the number of id's registered
	 */
	@Override
	public long numNodes() {
		if(nodeMap.size() != idMap.size()) {
			throw new RuntimeException("This shouldn't happen. Something wrong with id register system.");
		}
		return nodeMap.size();
	}

	@Override
	public long numEdges() {
		return edgeNum;
	}

	@Override
	public void print() {
		// make sure this works

		Iterator<Node> nodes = nodeMap.values().iterator();

		// loop over nodes
		Node n = null;
		while(nodes.hasNext()) {
			// get node
			n = nodes.next();

			// print node info
			System.out.println("(" + n.id + ")" + n.label);

			// print info for each edge
			for(Edge e : n.edgeOutList) {
				if(e.label != null) {
					System.out.println("   (" + e.id + ")--" + e.label + "," + e.weight + "--> " + e.toNode.label);
				}
				else {
					System.out.println("   (" + e.id + ")--" + e.weight + "--> " + e.toNode.label);
				}

			}

			// line between nodes
			System.out.println();
		}

	}

	@Override
	public String[] topoSort() {
		LinkedList<Node> output = new LinkedList<Node>();

		// hold nodes with in-degree of zero
		LinkedList<Node> zeroQueue = new LinkedList<Node>();

		// holds id's of nodes with the simplated in-degrees
		HashMap<Long, Integer> degreeMap = new HashMap<Long, Integer>();

		// inialize zeroQueue and degreeMap
		Iterator<Node> nodes = idMap.values().iterator();
		Node n = null;
		while(nodes.hasNext()) {
			// get node
			n = nodes.next();

			// populate zeroQueue
			if(n.getInDegree() == 0) {
				zeroQueue.add(n);
			}
			// populate degree map
			degreeMap.put(n.id, n.getInDegree());
		}



		// topo sort
		for(int i = 0; i < zeroQueue.size(); i++) {
			n = zeroQueue.get(i);

			// add to output
			output.add(n);

			LinkedList<Node> toAddToZeroQueue = new LinkedList<Node>();
			for(Edge edgeOut : n.edgeOutList) {
				// decrement nodes connected to this one by 1
				degreeMap.replace(edgeOut.toNode.id, degreeMap.get(edgeOut.toNode.id)-1);

				// if new degree is zero
				if(degreeMap.get(edgeOut.toNode.id) == 0) {
					// mark nodes to add node to zeroQueue
					toAddToZeroQueue.add(edgeOut.toNode);

				}
			}

			// add marked nodes to zero queue
			for(Node toAdd : toAddToZeroQueue) {
				zeroQueue.add(toAdd);
			}
			toAddToZeroQueue = new LinkedList<Node>();


		}

		// check to see if there is a cycle
		// if we don't have all the nodes in the output, but we have run out of nodes to process
		if(output.size() != idMap.size()) {
			return null;
		}

		// make string array of output
		String[] stringOutput = new String[output.size()];
		for(int i = 0; i < stringOutput.length; i++) {
			stringOutput[i] = output.get(i).label;
		}

		return stringOutput;
	}
}