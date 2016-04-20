//==== client code =================================================
//this is the test framework where you make some queues
//and exercise them
//our goal is for each to behave the same regardless
//of the way the LIST is implemented

class BridgeQueueListDemo {

	public void main( String[] args ) {

		Queue qLL = new Queue("linked"); // one with links LIST implementation
		Queue qAL = new Queue(); // one with array LIST implementation

		// load first element
		qLL.enq(args[0]);
		qAL.enq(args[0]);

		// show size
		System.out.println(qLL.size());
		System.out.println(qAL.size());

		// show first
		System.out.println(qLL.front());
		System.out.println(qAL.front());

		// deq
		qLL.deq();
		qAL.deq();

		// print size
		System.out.println(qLL.size());
		System.out.println(qAL.size());

		// add other things to queue
		for(int i = 1; i < args.length; i++) {
			qLL.enq(args[i]);
			qAL.enq(args[i]);
		}

		// show rest of cue
		for(int i = 1; i < args.length; i++) {
			System.out.println(qLL.front());
			System.out.println(qAL.front());
			qLL.deq();
			qAL.deq();
			System.out.println(qLL.size());
			System.out.println(qAL.size());
		}    
	}
}

interface ListImp {  
	// LIST common operations defined here	
	public void ins(String s);
	public void rem(int i);
	public String get(int i);
	public int size();
	public boolean empty();
}

//=== Abstraction side ==================================
//this is the "front end"
//the client code talks to this abstract object
//the abstraction delegates executions of its operations 
//to a "back end" object that contains the implementation details

class Queue {   
	protected ListImp imp;     

	public Queue( String iType ) { 
		// decide which LIST implementation to install in imp ... 
		if(iType.equalsIgnoreCase("linked")) {
			imp = new ListImpLinks();
		}
		else {
			new Queue();
		}
	}

	public Queue() { 
		imp = new ListImpArray();
	}

	public void enq(String s) {
		imp.ins(s);
	}
	public void deq() {
		imp.rem(0);
	}

	public String front() {
		return imp.get(0);
	}

	public int size() {
		return imp.size();
	}

	public boolean empty() {
		return imp.empty();
	}
}


class LinkedStringNode {
	public String value;
	public LinkedStringNode next;

	public LinkedStringNode(String s) {
		value = s;
	}

	public LinkedStringNode(String s, LinkedStringNode l) {
		next = l;
	}
}


class ListImpArray implements ListImp {
	private String[] array;

	public ListImpArray() {
		array = new String[0];
	}

	@Override
	public void ins(String s) {
		// create new longer array
		String[] tempArray = new String[array.length+1];

		// copy array over
		for(int i = 0; i < array.length; i++) {
			tempArray[i] = array[i];
		}

		// add new value and reassign
		tempArray[array.length] = s;
		array = tempArray;		
	}

	@Override
	public void rem(int i) {
		// create temp array
		String[] tempArray = new String[array.length-1];
		for(int j = 0; j < array.length; j++) {
			if(j < i) {
				tempArray[j] = array[j];
			}
			else if(j > i) {
				tempArray[j-1] = array[j];
			}
		}

		// reassign array
		array = tempArray;	
	}

	@Override
	public String get(int i) {
		try {
			return array[i];
		}
		catch(Exception e) {
			throw new RuntimeException("IndexOutOfBounds");
		}
	}

	@Override
	public int size() {
		return array.length;
	}

	@Override
	public boolean empty() {
		return (size() == 0) ? true : false;
	} 
}


class ListImpLinks implements ListImp {

	private LinkedStringNode head;
	private int size;

	public ListImpLinks() {
		size = 0;
	}

	@Override
	public void ins(String s) {
		LinkedStringNode l = new LinkedStringNode(s);
		if(head == null) {
			head = l;
		}
		else {
			LinkedStringNode lastElement = getNode(size()-1);
			lastElement.next = l;
		}
		size++;
	}

	@Override
	public void rem(int i) {
		// if remove the first node
		if(i == 0) {
			head = getNode(1);
		}

		// if remove the last node
		else if(i == size()-1) {
			getNode(size()-2).next = null;
		}

		// if remove any middle node
		else {
			getNode(i-1).next = getNode(i+1);
		}
		size--;
	}

	private LinkedStringNode getNode(int i) {
		LinkedStringNode temp = head;

		// get the nodes that we must change the links to
		for(int j = 1; j <= i; j++) {
			// assign temp to the current node
			temp = temp.next;
		}
		return temp;
	}

	@Override
	public String get(int i) {
		return getNode(i).value;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean empty() {
		return (size()==0) ? true : false;
	} 
}