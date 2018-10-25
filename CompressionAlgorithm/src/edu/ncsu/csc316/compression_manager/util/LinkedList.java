package edu.ncsu.csc316.compression_manager.util;

/**
 * Linked list used for storing generic elements that will be used as a dictionary in
 * the program.
 * @author John Ruisi
 *
 * @param <E> generic element
 */
public class LinkedList<E> {

	/** Head node of the list */
    private Node head;
    /** Size if the list */
    private int size;
	
    /**
     * Constructor sets head to null and size to zero
     */
    public LinkedList() {
    	head = null;
    	size = 0;
    }
    
    /**
     * Adds the element to the beginning of the lsit
     * @param o the element to be added
     */
    public void add(E o) {
    	if (head == null) {
    		head = new Node(o, null);
    	} else {
    		Node temp = new Node(o, null);
        	temp.next = head;
        	head = temp;
    	}
    	size++;
    }
    
    /**
     * Removes the element from the given index
     * @param idx the index of th element to be removed
     */
    public void remove(int idx) {
    	if (idx > size || idx < 0) {
    		throw new IllegalArgumentException("Invalid Index");
    	}
    	

    	if (idx == 0) {
    		head = head.next;
    	} else {
    		Node current = head;
    		for (int i = 0; i < idx - 1; i++) {
    			current = current.next;
    		}
    		current.next = current.next.next;
    	}
    	size--;
    }
    
    /**
     * Gets the element from the given index
     * @param idx the index of the element
     * @return data the element searched for
     */
    public E get(int idx) {
    	if (idx > size || idx < 0) {
    		throw new IllegalArgumentException("Invalid Index");
    	}
    	
    	if (idx == 0) {
    		E headData = head.data;
    		return headData;
    	}
    	Node current = head;
    	
    	for (int i = 0; i < idx; i++) {
    		current = current.next;
    	}
    	
    	E data = current.data;
    	return data;
    	
    }
    
    /**
     * Gets the index of a specific element
     * @param o the element to be searched for
     * @return count the index of the element
     */
    public int get(E o) {
    	Node current = head;
    	int count = 0;
    	
    	while (current != null) {
    		
    		if (current.data.equals(o)) {
    			return count;
    		}
    		count++;
    		current = current.next;
    	}
    	return -1;
    }
    
    /**
     * Returns the size of the list
     * @return size the size
     */
    public int size() {
    	return size;
    }
    
    /**
     * Determines if the list is empty
     * @return true if the list is empty
     */
    public boolean isEmpty() {
    	if (size() == 0) {
    		return true;
    	}
    	return false;
    }
	
    /**
     * Node class stores the nodes data and the pointer to 
     * the next node
     * @author John Ruisi
     *
     */
	public class Node {
		/** Node's data */
		private E data;
		/** Next node */
		private Node next;
		
		/**
		 * Constructor assigns the Nodes data and its pointer
		 * @param data the element of the node
		 * @param next the next node in the list
		 */
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
		
	}
}
