package edu.ncsu.csc316.compression_manager.util;

/**
 * Array list that is used for storing String objects that will written to an output file
 * @author John Ruisi
 *
 * @param <E> generic element
 */
public class ArrayList<E> {

	/** Initial size of the array */
	private static final int RESIZE = 10;
	/** Array of generic objects*/
	private E[] list;
	/** size of the array */
	private int size;
	
	/**
	 * Constructor initalizes the lsit and sets the size to zero
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) (new Object[RESIZE]);
		size = 0;
	}
	
	/**
	 * Returns the size of the array
	 * @return size the size of the array
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Determins if the array is empty
	 * @return true if the array is empty
	 */
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Grows the array if it reaches max capacity
	 */
	@SuppressWarnings("unchecked")
	public void growArray() {
		E[] temp = (E[]) (new Object[list.length * 2]);
		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}
	
	/**
	 * Adds a generic object to the end of the list
	 * @param o the object to be added
	 */
	public void add(E o) {
		if (size() == list.length) {
			growArray();
		}
		list[size()] = o;
		size++;
	}
	
	/**
	 * Gets the object at the specific index
	 * @param idx the specific index
	 * @return the object at that index
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		return list[idx];
	}
	
	/**
	 * Removes an object at a given index
	 * @param idx the index to be removed
	 * @return removed the object that was removed
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid Index");
		}
		
		E removed = list[idx];
		for (int i = idx; i < size; i++) {
			list[i] = list [i + 1];
		}
		list[size - 1] = null;
		size--;
		return removed;
	}
	
	/**
	 * Removes the last element in the list
	 */
	public void removeLast() {
		list[size - 1] = null;
		size--;
	}

}
