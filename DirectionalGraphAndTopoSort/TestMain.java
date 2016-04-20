public class TestMain {
	public static void main(String[] args) {
		//test1();
		test2();
	}
	
	public static void test1() {
	DiGraph d = new DiGraph();
		
		// check initial number of edges and nodes
		System.out.println(d.numEdges() == 0);
		System.out.println(d.numNodes() == 0);
		
		// attempt to add edge with no nodes and check edge an node count
		System.out.println(d.addEdge(123456, "taco", "llama", -5252, "This is wrong") == false);
		System.out.println(d.numEdges() == 0);
		System.out.println(d.numNodes() == 0);
		
		// attempt to add valid node and check edge and node count
		System.out.println(d.addNode(0, "fish") == true);
		
		// attempt to add invalid node by id and string
		System.out.println(d.addNode(-1, "toeu") == false);
		System.out.println(d.addNode(1, "fish") == false);
		System.out.println(d.addNode(0, "nttoeunth") == false);
		System.out.println(d.addNode(23, null) == false);
		
		// add 2nd valid node to test edge
		System.out.println(d.addNode(1, "taco") == true);
		
		// try to add valid edge
		System.out.println(d.addEdge(0, "fish", "taco", -5, "Fish Taco") == true);
		
		// try to add invalid edges
		System.out.println(d.addEdge(0, "fis", "tco", 2, "Fih Taco") == false);
		System.out.println(d.addEdge(1, "fish", "tco", -5, "Fish Taco") == false);
		System.out.println(d.addEdge(1, "fsh", "taco", -5, "Fish Taco") == false);
		System.out.println(d.addEdge(0, "fish", "tao", -5, "Fish Taco") == false);
		System.out.println(d.addEdge(0, "fish", "taco", -5, "Fish Taco") == false);
		
		// test num edges and nodes
		System.out.println(d.numEdges() == 1);
		System.out.println(d.numNodes() == 2);
		
		// try to delete node
		System.out.println(d.delNode("fish"));
		
		// check edge and node numbers
		System.out.println(d.numEdges() == 0);
		System.out.println(d.numNodes() == 1);
		
		// try to delete invalid node
		System.out.println(d.delNode("t") == false);
		
		// remake valid edge again
		System.out.println(d.addNode(4, "fish") == true);
		System.out.println(d.addEdge(85, "fish", "taco",6, "oeunt") == true);
		System.out.println(d.addEdge(5, "fish", "fish",6, "oeunt") == true);
		System.out.println(d.addEdge(26, "taco", "taco",6, "oeunt") == true);
		System.out.println(d.addEdge(85, "taco", "fish",6, "oeunt") == true);
		
		// test size
		System.out.println(d.numEdges() == 4);
		
		/*
		// delete node and see num edges
		System.out.println(d.delNode("fish"));
		System.out.println(d.numEdges() == 1);
		System.out.println(d.numNodes() == 1);
	*/
		d.print();
	}
	
	public static void test2() {
		DiGraph d = new DiGraph();
		
		// Add several new nodes
		d.addNode(0, "A");
		d.addNode(1, "B");
		d.addNode(2, "C");
		
		// Connect nodes A -> B -> C -> A; B -> B
		// node id's correspond to the starting node
		d.addEdge(0, "A", "B", 1, "AB");
		d.addEdge(1, "B", "C", 2, "BC");
		d.addEdge(2, "C", "A", 3, "CA");
		d.addEdge(42, "B", "B", 99, "BB");
		
		// print
		d.print();
	}
}
