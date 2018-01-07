package hw1;

import algs13.Queue;

/**
 * Complete the methods marked TODO.
 * You must not change the declaration of any method.
 */

/**
 *  The LinkedListST class represents an (unordered) symbol table of
 *  generic key-value pairs.  It supports put, get, and delete methods.
 */
public class LinkedListST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public LinkedListST() {
    }

    /**
     * Returns the value associated with the given key in this symbol table.
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null"); 
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is null.
     */
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("first argument to put() is null"); 
        if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     */
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to delete() is null"); 
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * size returns the number of key-value pairs in the symbol table.
     * it returns 0 if the symbol table is empty.
     */
   
    public int size () {
    	return -1;  // ToDo
    }
    /**
     * secondMaxKey returns the second maximum key in the symbol table.
     * it returns null if the symbol table is empty or if it has only one key.
     *  See if you can write it with only one loop
     */
    public Key secondMaxKey () {
    	return null; // TODO
    }


    /**
     * rank returns the number of keys in this symbol table that is less than the given key.
     * your implementation should be recursive. 
     */
    public int rank (Key key) {
    	Node x = first;
    	int count = 0;
    	
    	if (x == null) {
    		return 0;
    	}
    	
    	int keyCompare = key.compareTo(x.key);
    	
    	if (keyCompare < 0 || keyCompare == 0) {
    		first = x.next;
    		return rank(key);
    	}
    	
    	if (keyCompare > 0) {
    		first = x.next;
    		count++;
    		count = rank(key) + count;	
    	}
    
        return count; // TODO
    }

    /**
     * floor returns the largest key in the symbol table that is less than or equal to the given key.
     * it returns null if there is no such key.
     */
    public Key floor (Key key) {
    	Node x = first;
    	Key floorKey = x.key;
    	Boolean isFloor = false;
    	
    	while (x != null) {
	    	
    		int compareKey = key.compareTo(x.key);
        	int compareFloor = floorKey.compareTo(x.key);
        	
	    	if (compareKey > 0 && (compareFloor < 0 || compareFloor ==0 )) {
	    		floorKey = x.key;
	    		isFloor = true;
	    	}
	    	
	    	x = x.next;
    	
    	}
 
    	if (isFloor) {
    		return floorKey; // TODO
    	}
    	else {
    		return null;
    	}
    }

    /**
     * inverse returns the inverse of this symbol table.
     * if the symbol table contains duplicate values, you can use any of the keys for the inverse
     */
    public LinkedListST<String, Integer> inverse () {
    	
    	for (Node x = first ; x != null; x = x.next) {
    		
    		
	    	int NodeValueToInt = (int) x.val;
	    	String intNodeValueToString = Integer.toString(NodeValueToInt);
	    	String nodeKeyToString = (String)x.key;
	    	
	    	x.key = (Key) intNodeValueToString;
	    	x.val = (Value) nodeKeyToString;
    	}
    	
    	return null; // TODO
    }

	public Iterable<Key>  keys() {
    	Queue<Key> theKeys = new Queue<Key>();
    	for ( Node temp = first; temp != null; temp=temp.next) {
    		theKeys.enqueue(temp.key);
    	}
    	return theKeys;
    }
}