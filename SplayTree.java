import java.util.Random;
import java.util.Scanner;

class Assn4Main {
	public static void main(String[] args) {
		// BST to use in either case
		SPLT myBST = new SPLT();

		// start interactive mode
		if(args.length == 0) {

			// setup input and prompt user
			System.out.println("Starting interactive mode...");		
			Scanner in = new Scanner(System.in);
			System.out.println("Type a command or type 'list' for a list of availible commands");

			// loop until user indicates to quit
			boolean end = false;
			while(!end) {
				//input = ;
				switch(in.next()) {
				case("list"):
					listOptions();
				break;
				case("new"):
					myBST = new SPLT();
				System.out.println("New tree created");
				break;
				case("i"):
					System.out.println("Enter value to insert:");
				myBST.insert(in.next());
				System.out.println("Value inserted");
				break;
				case("r"):
					System.out.println("Enter value to remove:");
				myBST.remove(in.next());
				System.out.println("Value removed");
				break;
				case("c"):
					System.out.println("Enter value to see if is in array");
				System.out.println("Contains: " + myBST.contains(in.next())); 
				break;
				case("g"):
					System.out.println("Enter value of node:");
				System.out.println("Node pointer: " + myBST.get(in.next()));
				break;
				case("x"):
					System.out.println("Max: " + myBST.findMax());
				break;
				case("n"):
					System.out.println("Min: " + myBST.findMin());
				break;
				case("v"):
					System.out.println("Value: " + myBST.val());
				break;
				case("e"):
					System.out.println("Empty: " + myBST.empty());
				break;
				case("s"):
					System.out.println("Size: " + myBST.size());
				break;
				case("h"):
					System.out.println("Height: " + myBST.height());
				break;
				case("q"):
					end = true;
				break;
				case("p"):
					printTree(myBST);
				break;
				case("f"):
					System.out.println("Enter max size of the tree:");
					myBST = makeRandomTree(in.nextInt());
					System.out.println("Tree created");
				}
			}
			in.close();
		}
		else {
			int index = 0;
			while(index < args.length) {
				switch(args[index++]) {
				case("list"):
					listOptions();
				break;
				case("new"):
					myBST = new SPLT();
				System.out.println("New tree created");
				break;
				case("i"):
					System.out.println("Enter value to insert:");
				myBST.insert(args[index++]);
				System.out.println("Value inserted");
				break;
				case("r"):
					System.out.println("Enter value to remove:");
				myBST.remove(args[index++]);
				System.out.println("Value removed");
				break;
				case("c"):
					System.out.println("Enter value to see if is in array");
				System.out.println("Contains: " + myBST.contains(args[index++])); 
				break;
				case("g"):
					System.out.println("Enter value of node:");
				System.out.println("Node pointer: " + myBST.get(args[index++]));
				break;
				case("x"):
					System.out.println("Max: " + myBST.findMax());
				break;
				case("n"):
					System.out.println("Min: " + myBST.findMin());
				break;
				case("v"):
					System.out.println("Value: " + myBST.val());
				break;
				case("e"):
					System.out.println("Empty: " + myBST.empty());
				break;
				case("s"):
					System.out.println("Size: " + myBST.size());
				break;
				case("h"):
					System.out.println("Height: " + myBST.height());
				break;
				case("q"):
					break;
				case("p"):
					printTree(myBST);
				break;
				case("f"):
					System.out.println("Enter max size of the tree:");
					myBST = makeRandomTree(Integer.parseInt(args[index++]));
					System.out.println("Tree created");
				}
			}
		}
	}

	/**
	 * Displays the list of commands for the user
	 */
	public static void listOptions() {
		System.out.println("new: make a new empty tree");
		System.out.println("i: insert a string (get the value from the user)");
		System.out.println("r: remove a node containing a string (remove it from the tree)");
		System.out.println("c: contains a string (return and report a boolean indicating success)");
		System.out.println("g: get a node that has a string as value");
		System.out.println("x: findMax, returns a string");
		System.out.println("n: findMin, returns a string");
		System.out.println("v: val, gets the value stored in the root node");
		System.out.println("e: empty");
		System.out.println("s: size");
		System.out.println("h: height");
		System.out.println("q: quit the tester loop");
		System.out.println("p: print the tree for inspection (see below)");
		System.out.println("f: (optional) fill the tree with some amount of random string data (see random code below)");
	}

	/**
	 * Visually displays the tree in a node-first fashion
	 * @param tree The tree to be displayed
	 */
	public static void printTree(SPLT b) {
		if(b.empty()) {
			System.out.println("Empty tree");
		}
		else {
			printTree(b.get(b.val()));
		}
	}
	public static void printTree(Node tree) {
		System.out.println(tree.val());

		// print child nodes
		// placeholder for number of tabs
		String tabs = "";
		// set up number of tabs for next line
		for(int i = 0; i <= 0; i++) {
			tabs += "|---";
		}
		// if there is as node, print it
		if(tree.leftNode != null) {
			System.out.print(tabs + "> " );
			printTree(tree.leftNode, 1);
		}
		if(tree.rightNode != null) {
			System.out.print(tabs + "< ");
			printTree(tree.rightNode, 1);
		}

	}
	/**
	 * Helper method for the print tree method, controls indentation
	 * @param tree a Binary Search Tree
	 * @param depth depth of node in tree for of setting purposes
	 */
	private static void printTree(Node tree, int depth) {
		// print value
		System.out.println(tree.val());

		// print child nodes
		// placeholder for number of tabs
		String tabs = "";
		// set up number of tabs for next line
		for(int i = 0; i <= depth; i++) {
			tabs += "|---";
		}
		// if there is as node, print it
		if(tree.leftNode != null) {
			System.out.print(tabs + "> " );
			printTree(tree.leftNode, depth+1);
		}
		if(tree.rightNode != null) {
			System.out.print(tabs + "< ");
			printTree(tree.rightNode, depth+1);
		}
	}

	/**
	 * makes a random tree with a max size of s
	 * @param size
	 */
	private static SPLT makeRandomTree(int s) {
		SPLT b = new SPLT();
		for(int i = 0; i < s ; i++) {
			b.insert(MyRandom.nextString());
		}
		return b;
	}
}

class SPLT {
	public Node root;

	public SPLT() {
		root = null;
	}
	public SPLT(Node n) {
		root = n;
	}

	/**
	 * Inserts non null value into binary tree
	 * @param value
	 */
	public void insert(String value) {
		if(root != null) {
			root.insert(value);
		}
		else {
			// if there is no root, create one
			root = new Node(value);
		}
		
		splay(get(value));
		

	}

	/**
	 * Removes value from binary tree if it exists
	 * @param value
	 */
	public void remove(String value) {
		if(root == null || value == null) {
			return;
		}
		boolean doesContain = contains(value);
		if(doesContain) {
			if(root.leftNode != null) {
				SPLT leftTree = new SPLT(root.leftNode);
				leftTree.findMax();
				leftTree.root.rightNode = root.rightNode;
				this.root = leftTree.root;
			}
			else {
				root = root.rightNode;
			}
			
		}
		
		
		/*	Normal version of remove
		if(root.val().equals(value)) {
			if(root.rightNode == null && root.leftNode == null) {	// if root is single node
				root = null;
			}
			else if(root.rightNode == null ^ root.leftNode == null) {	// if root has one child
				// assign root to that only child
				root = (root.leftNode == null) ? root.rightNode : root.leftNode;
			}
			else {	// root has two children
				root.remove(value);	// remove self leaving references intact
			}

		}
		else {
			root.remove(value);
		}
		*/
	}

	/**
	 * returns minimum value in BST
	 * @return
	 */
	public String findMin() {
		String value = root.findMin();
		splay(get(value));
		return value;
	}

	/**
	 * returns maximum value in BST
	 */
	public String findMax() {
		if(root == null) {
			return null;
		}
		String value = root.findMax();
		splay(get(value));
		return value;
	}

	/**
	 * returns true if BST contains value, false otherwise
	 * @param value
	 * @return
	 */
	public boolean contains(String value) {
		boolean doesContain = root.contains(value);
		if(doesContain) {
			splay(get(value));
		}
		else {
			splay(root.lastAccessContains(value));
		}
		return doesContain;
	}

	/**
	 * returns the node associated with the given value
	 * returns a null reference if the value does not exist 
	 * or if a null value is supplied
	 * @param value
	 * @return
	 */
	public Node get(String value) {
		return root.get(value);
	}

	/**
	 * Returns the value of the root
	 * @return
	 */
	public String val() {
		return root.val();
	}

	/**
	 * returns true if BST is empty, false otherwise
	 * @return
	 */
	public boolean empty() {
		if(root == null) {
			return true;
		}
		return false;
	}

	/**
	 * returns size of tree
	 * @return
	 */
	public int size() {
		return root.size();
	}

	/**
	 * returns height of root of tree
	 * @return
	 */
	public int height() {
		return root.height();
	}
	
	public void splay(Node x) {
		if(x == null) {
			return;
		}
		while(x != root) {
			partialSplay(x);
		}
	}
	
	private void partialSplay(Node x) {
		// get node, parent, and grandparent
		Node[] ancestors = root.getNodeAndParentAndGrandparents(x.myVal);
		Node parent = ancestors[1];
		Node grandParent = ancestors[2];
		Node greatGrandParent = ancestors[3];
		Node temp = null;
		Node temp2 = null;
		
		// if x is the root
		if(parent == null) {
			return;
		}
		
		// if x has a parent but no grandparent
		// AKA parent is the root
		if(parent != null && grandParent == null){
			// avl single rotation depending on direction
			
			// if x is left child
			if(parent.leftNode == x) {
				// avl LL rotation
				temp = x.rightNode;
				root = x;
				x.rightNode = parent;
				parent.leftNode = temp;
			}
			// if x is right child
			else if(parent.rightNode == x) {
				// avl RR rotation
				temp = x.leftNode;
				root = x;
				x.leftNode = parent;
				parent.rightNode = temp;
			}
			else {
				System.out.println("Something went wrong: Case 1");
			}
		}
		// if x has a parent and grandparent
		else if(parent != null && grandParent != null) {
			// if x is RR of grandParent
			if(grandParent.rightNode == parent && parent.rightNode == x) {
				// avl RR double rotation
				temp = parent.leftNode;
				temp2 = x.leftNode;
				x.leftNode = parent;
				parent.leftNode = grandParent;
				grandParent.rightNode = temp;
				parent.rightNode = temp2;
			}
			// if x is LL of the grandParent
			else if(grandParent.leftNode == parent && parent.leftNode == x) {
				// avl LL double rotation
				temp = x.rightNode;
				temp2 = parent.rightNode;
				x.rightNode = parent;
				parent.rightNode = grandParent;
				grandParent.leftNode = temp2;
				parent.leftNode = temp;
			}
			// if x is LR of the grandParent
			else if(grandParent.leftNode == parent && parent.rightNode == x) {
				// AVL LR double
				temp = x.leftNode;
				temp2 = x.rightNode;
				x.leftNode = parent;
				x.rightNode = grandParent;
				parent.rightNode = temp;
				grandParent.leftNode = temp2;
			}
			// if x is RL of the grandParent
			else if(grandParent.rightNode == parent && parent.leftNode == x) {
				// AVL RR double
				temp = x.leftNode;
				temp2 = x.rightNode;
				x.leftNode = grandParent;
				x.rightNode = parent;
				grandParent.rightNode = temp;
				parent.leftNode = temp2;
			}
			else {
				System.out.println("This shouldn't happen: Case 2");
			}
			
			// if have greatGrandParent, assign x to correct spot
			// otherwise, x is the root and should be assigned the root
			if(greatGrandParent != null) {
				if(greatGrandParent.leftNode == grandParent) {
					greatGrandParent.leftNode = x;
				}
				else if(greatGrandParent.rightNode == grandParent) {
					greatGrandParent.rightNode = x;
				}
				else {
					System.out.println("This shouldn't happen: Case 3");
				}
			}
			else {
				root = x;
			}
		}	
	}
}

class Node {
	public String myVal;
	public Node rightNode;
	public Node leftNode;

	/**
	 * Constructs a new binary search tree with one node
	 * @param value Value of the node, may not be null
	 */
	public Node(String value) {
		if(value == null) {
			throw new RuntimeException("Null not valid input for val");
		}
		myVal = value;
	}

	/**
	 * Adds a new cell to the tree, sorted according to binary search tree principles
	 * @param value may not be null
	 */
	public void insert(String value) {
		if(value == null) {
			throw new RuntimeException("Null not valid input for val");
		}

		if(myVal.equals(value)){
			// do nothing
		}
		else if(isLessThan(value, myVal)) {	
			if(leftNode != null) {
				leftNode.insert(value);
			}
			else {
				leftNode = new Node(value);
			}
		}
		else {	// if value > the val of this node
			if(rightNode != null) {
				rightNode.insert(value);
			}
			else {
				rightNode = new Node(value);
			}
		}
	}

	/**
	 * Removes node with the given value, 
	 * does nothing if value is null, 
	 * throws error if attempts to remove itself and it doesn't have 2 children
	 * removing self is only allowed if node has two children
	 * @param value value of node to be removed
	 */
	public void remove(String value) {
		Node[] b = getNodeAndParent(value);	// array to hold multiple return values
		Node node = b[0];	// node no be deleted
		Node parent = b[1];	// parent of node 

		if(parent == null && (node.leftNode == null || node.rightNode == null)) {	
			throw new RuntimeException("Cannot delete self if don't have 2 children");
		}
		
		// if has no children
		if(node.leftNode == null && node.rightNode == null) {
			// unlink node from parent
			if(parent.leftNode == node) {
				parent.leftNode = null;
			}
			else {	// if right node is the one to be deleted
				parent.rightNode = null;
			}
		}
		// if node has one child
		else if(node.rightNode == null ^ node.leftNode == null) {
			// give child to parent
			Node child = (node.leftNode == null) ? node.rightNode : node.leftNode;
			if(parent.leftNode == node) {
				parent.leftNode = child;
			}
			else {
				parent.rightNode = child;
			}
		}
		// if node has 2 children
		else {
			// move the smallest value to the deleted node
			Node minNodeRight = node.rightNode.findMinNode();
			node.remove(minNodeRight.val());
			node.myVal = minNodeRight.val();
		}
	}

	/**
	 * Returns the smallest value in the tree
	 * @return smallest value in tree
	 */
	public String findMin() {
		if(leftNode != null) {
			return leftNode.findMin();
		}
		return myVal;
	}

	/**
	 * Returns the largest value in the tree
	 * @return largest value in the tree
	 */
	public String findMax() {
		if(rightNode != null) {
			return rightNode.findMax();
		}
		return myVal;
	}


	/**
	 * Checks if the tree contains the given value
	 * @param value String, not null value to search for
	 * @return true if the tree contains the value, false otherwise
	 */
	public boolean contains(String value) {
		if(value == null) {
			return false;
		}
		if(myVal.equals(value)) {
			return true;
		}
		else if(isLessThan(value, myVal)) {
			if(leftNode != null) {
				return leftNode.contains(value);
			}
			return false;
		}
		else {	// if value > val
			if(rightNode != null) {
				return rightNode.contains(value);
			}
			return false;
		}
	}
	
	public Node lastAccessContains(String value) {
		if(value == null) {
			return null;
		}
		if(myVal.equals(value)) {
			return null;
		}
		else if(isLessThan(value, myVal)) {
			if(leftNode != null) {
				return leftNode.lastAccessContains(value);
			}
			return this;
		}
		else {	// if value > val
			if(rightNode != null) {
				return rightNode.lastAccessContains(value);
			}
			return this;
		}
	}

	/**
	 * Returns the node in the tree, if it exists, which has the given value
	 * @param value string not null, value to search for
	 * @return BST where root is a node with the val() of the value provided if it exists, otherwise null
	 */
	public Node get(String value) {
		if(value == null) {
			return null;
		}
		else if(myVal.equals(value)) {
			return this;
		}
		else if(isLessThan(value, myVal)) {
			if(leftNode != null) {
				return leftNode.get(value);
			}
			return null;
		}
		else {	// if value > val
			if(rightNode != null) {
				return rightNode.get(value);
			}
			return null;
		}
	}

	/**
	 * Returns the value associated with this node
	 * @return
	 */
	public String val() {
		return myVal;
	}

	// TODO Remove this
	/**
	 * Returns true if the tree is empty, false otherwise
	 * @return
	 */
	public boolean empty() {
		return false;
	}	

	/**
	 * Returns the number of nodes in the tree
	 * @return integer >= 0, number of nodes in the tree
	 */
	public int size() {
		int size = 1;	// count this node
		if(leftNode != null) {
			size += leftNode.size();	// count nodes on left
		}
		if(rightNode != null) {
			size += rightNode.size();	// count nodes on right
		}
		return size;
	}

	/**
	 * Returns number of edges between this node and the farthest leaf
	 * @return int >= 0
	 */
	public int height() {
		// get the heights of the left and right node
		int heightLeft = 0; 	// avoids a 'Cannot call method height of null' exception
		int heightRight = 0;
		if(leftNode != null) {
			heightLeft = 1 + leftNode.height();
		}
		if(rightNode != null) {
			heightRight = 1 + rightNode.height();
		}
		// return the greater of the two
		return heightLeft < heightRight ? heightRight : heightLeft;
	}

	/**
	 * Private method to determine whether string a is less than string b
	 * @param a String, not null
	 * @param b String, not null
	 * @return true if a < b, false otherwise
	 */
	private boolean isLessThan(String a, String b) {
		if(a == null) {
			a = "";
			//throw new IllegalArgumentException("String a, b may not be null");
		}
		if(b == null) {
			b = "";
		}
		if(a.compareTo(b) < 0) {
			return true;
		}
		return false;		
	}

	/**
	 * Helper method:
	 * Returns the specified node and the immediate parent of that node in an array
	 * of length 2
	 * 
	 * @param value the string value of the node to be found
	 * @return [node, parent] will return [null, null] if value is not found or
	 * 			[node, null] if the node has no parent
	 */
	private Node[] getNodeAndParent(String value) {
		Node[] b = {null, null};
		if(value == null) {
			throw new RuntimeException("Cannot search for null value");
		}
		else if(myVal.equals(value)) {
			b[0] = this;
		}
		else if(isLessThan(value, myVal)) {
			if(leftNode != null) {
				b = leftNode.getNodeAndParent(value, this);
			}
		}
		else {	// if value > val
			if(rightNode != null) {
				b = rightNode.getNodeAndParent(value, this);
			}
		}
		return b;
	}
	private Node[] getNodeAndParent(String value, Node parent) {
		Node[] b = {null, null};
		if(value == null) {
			throw new RuntimeException("Cannot search for null value");
		}
		else if(myVal.equals(value)) {
			b[0] = this;
			b[1] = parent;
		}
		else if(isLessThan(value, myVal)) {
			if(leftNode != null) {
				b = leftNode.getNodeAndParent(value, this);
			}
		}
		else {	// if value > val
			if(rightNode != null) {
				b = rightNode.getNodeAndParent(value, this);
			}
		}
		return b;
	}

	/**
	 * Helper method:
	 * Returns the specified node and the immediate parent of that node in an array
	 * of length 2
	 * 
	 * @param value the string value of the node to be found
	 * @return [node, parent] will return [null, null] if value is not found or
	 * 			[node, null] if the node has no parent
	 */
	public Node[] getNodeAndParentAndGrandparents(String value) {
		Node[] b = {null, null, null, null};
		if(value == null) {
			throw new RuntimeException("Cannot search for null value");
		}
		else if(myVal.equals(value)) {
			b[0] = this;
		}
		else if(isLessThan(value, myVal)) {
			if(leftNode != null) {
				b = leftNode.getNodeAndParentAndGrandparents(value, this, null, null);
			}
		}
		else {	// if value > val
			if(rightNode != null) {
				b = rightNode.getNodeAndParentAndGrandparents(value, this, null, null);
			}
		}
		return b;
	}
	public Node[] getNodeAndParentAndGrandparents(String value, Node parent, Node grandparent, Node greatGrandParent) {
		Node[] b = {null, null, null, null};
		if(value == null) {
			throw new RuntimeException("Cannot search for null value");
		}
		else if(myVal.equals(value)) {
			b[0] = this;
			b[1] = parent;
			b[2] = grandparent;
			b[3] = greatGrandParent;
		}
		else if(isLessThan(value, myVal)) {
			if(leftNode != null) {
				b = leftNode.getNodeAndParentAndGrandparents(value, this, parent, grandparent);
			}
		}
		else {	// if value > val
			if(rightNode != null) {
				b = rightNode.getNodeAndParentAndGrandparents(value, this, parent, grandparent);
			}
		}
		return b;
	}

	/**
	 * Helper method that finds the minimum node more efficiently than calling get(findMin())
	 * @return node contianing min value of tree
	 */
	public Node findMinNode() {
		if(leftNode != null) {
			return leftNode.findMinNode();
		}
		return this;
	}	
	
	
}

class MyRandom {

	  private static Random rn = new Random();

	  private MyRandom(){ }

	  public static int rand(int lo, int hi) {
	     int n = hi - lo + 1;
	     int i = rn.nextInt() % n;
	     if (i < 0) i = -i;
	     return lo + i;
	  }

	  public static String nextString(int lo, int hi) {
	     int n = rand(lo, hi);
	     byte b[] = new byte[n];
	     for (int i = 0; i < n; i++)
	     b[i] = (byte)rand('a', 'z');
	     return new String(b, 0);
	  }

	  public static String nextString() {
	     return nextString(5, 25);
	  }
	  
	}



