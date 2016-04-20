import java.util.ArrayList;

public class MinBinHeap implements HeapInterface {

	// in here go all your data and methods for the heap
	ArrayList<EntryPair> heapArray = new ArrayList<EntryPair>();

	public MinBinHeap ( ) { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there

		heapArray.add(null);	// set the value of a[0] to null sincu a[0] is supposed to be unused
	}

	@Override
	public void insert(EntryPair entry) {
		if(entry == null) {
			throw new IllegalArgumentException("value null not allowed for entry");
		}

		// add new entry to end of array
		heapArray.add(entry);	

		// store indices of entry and parent
		int entryIndex = heapArray.size()-1;
		int parentIndex = entryIndex/2;

		// while child has lower priority than parent
		while(entryIndex != 1 && heapArray.get(entryIndex).priority < heapArray.get(parentIndex).priority) {
			// swap values
			swapValues(entryIndex, parentIndex);

			// re-assign indices to reflect updated values
			entryIndex = parentIndex;
			parentIndex = entryIndex/2;
		}
	}

	@Override
	public void delMin() {
		// swap last value entered to root
		swapValues(heapArray.size()-1, 1);

		// delete old root
		heapArray.remove(heapArray.size()-1);

		// index of node we are swapping down
		int movingNodeIndex = 1;

		// variables to hold movingNode's children
		EntryPair leftChild = null;
		EntryPair rightChild = null;

		// node has children
		while(hasChildren(movingNodeIndex)) {
			// update children variables
			leftChild = getLeftChild(movingNodeIndex);
			rightChild = getRightChild(movingNodeIndex);

			// if has two children
			if(rightChild != null) {
				// if either value is less
				if(leftChild.priority < heapArray.get(movingNodeIndex).priority ||
						rightChild.priority < heapArray.get(movingNodeIndex).priority) {
					// swap with smaller
					swapValues((leftChild.priority < rightChild.priority) ? 2*movingNodeIndex : 2*movingNodeIndex+1, movingNodeIndex);
					movingNodeIndex = (leftChild.priority < rightChild.priority) ? 2*movingNodeIndex : 2*movingNodeIndex+1;
				}
				// if neither are less, then it's in the right place
				else {
					break;
				}
			}
			// if only has one child
			else{
				// if child is smaller than parent
				if(leftChild.priority < heapArray.get(movingNodeIndex).priority) {
					swapValues(movingNodeIndex, 2*movingNodeIndex);
					movingNodeIndex = 2*movingNodeIndex;
				}
				// else is in the right place
				else {
					break;
				}
			}
		}
	}

	private EntryPair getLeftChild(int index) {
		try {
			return heapArray.get(2*index);
		}
		catch(Exception e) {
			return null;
		}
	}

	private EntryPair getRightChild(int index) {
		try {
			return heapArray.get(2*index+1);
		}
		catch(Exception e) {
			return null;
		}
	}

	private boolean hasChildren(int index) {
		try {
			// attempt to get left child
			heapArray.get(2*index);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public EntryPair getMin() {
		return heapArray.get(1);
	}

	@Override
	public int size() {
		return heapArray.size()-1;
	}

	public void insertBuild(int [] a){
		for(int i = 0; i < a.length; i++) {
			this.insert(new EntryPair(a[i]));
		}
	}

	public void buildInt(int[] a) {
		EntryPair[] e = new EntryPair[a.length];
		for(int i = 0; i < a.length; i++) {
			e[i] = new EntryPair(a[i]);
		}
		build(e);
	}
	@Override
	public void build(EntryPair[] entries) {	
		heapArray = new ArrayList<EntryPair>();

		// populate array
		heapArray.add(null);
		for(int i = 0; i < entries.length; i++) {
			heapArray.add(entries[i]);
		}

		// make valid minBinHeap
		boolean edited = true;
		int parentIndex;
		EntryPair parent;
		EntryPair leftChild;
		EntryPair rightChild;
		while(edited) {
			edited = false;
			for(int i = heapArray.size()-1; i > 1; i--) {
				// go through each nodes parents and see if fits rule
				// set nodes
				parentIndex = i/2;
				parent = heapArray.get(parentIndex);
				leftChild = getLeftChild(parentIndex);
				rightChild = getRightChild(parentIndex);

				// if has any 2 children
				if(leftChild != null && rightChild != null) {
					// if both are smaller or one is smaller
					if(leftChild.priority < parent.priority || rightChild.priority < parent.priority)
					{
						// swap with smaller
						swapValues(parentIndex, (leftChild.priority < rightChild.priority) ? 2*parentIndex: 2*parentIndex+1);
						edited = true;
					}
				}
				// if has one child
				else if(leftChild != null && rightChild == null){
					// if both are smaller or one is smaller
					if(leftChild.priority < parent.priority)
					{
						// swap with smaller
						swapValues(parentIndex, 2*parentIndex);
						edited = true;
					}
				}
			}
		}
	}


private void swapValues(int a, int b) {
	if(a <= 0 || b <= 0) {
		throw new IllegalArgumentException("a, b must be > 0");
	}

	// initialize values to switch
	int parentIndex;
	int entryIndex;

	if(a < b) {
		parentIndex = a;
		entryIndex =b;
	}
	else {
		parentIndex = b;
		entryIndex = a;
	}
	// assign temp to parent
	EntryPair temp = heapArray.get(parentIndex);

	// assign parent to child
	heapArray.set(parentIndex, heapArray.get(entryIndex));

	// assign child to parent
	heapArray.set(entryIndex, temp);
}

public String toString() {
	String value = "[ ";

	for(int i = 1; i < heapArray.size()-1; i++) {
		//value += i + ":" + heapArray.get(i).toString() + ", ";
		value += heapArray.get(i).toString() + ", ";
	}
	//value += heapArray.size()-1 + ":" + heapArray.get(heapArray.size()-1).toString();
	value += heapArray.get(heapArray.size()-1).toString();

	value += " ]";
	return value;
}
}
