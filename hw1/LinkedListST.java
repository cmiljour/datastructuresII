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

     public int size () {
    	int count = 0;
    	Node x = first;
		while (x != null) {
			x = x.next;
			count++;
		}
    	return count;  // ToDo
    }
    /**
     * secondMaxKey returns the second maximum key in the symbol table.
     * it returns null if the symbol table is empty or if it has only one key.
     *  See if you can write it with only one loop
     */
    public Key secondMaxKey () {
    	Node x = first;
    	if (x == null || x.next == null) {
    		return null;
    	}
    	Key max = x.key;
    	Key secondMax = x.next.key;
    	
    	while (x.next != null ) {
    		
    		// I had to do some unfortunate casting key to string to char to int to get ASCII value.
    		// I didn't find a more efficient way in time.
//    		String stringKey1 = "" + max;
//    		String stringKey2 = "" + x.next.key;
//    		char charKey1 = stringKey1.charAt(0);
//    		char charKey2 = stringKey2.charAt(0);
//    		int key1 = (int)charKey1;
//    		int key2 = (int)charKey2;
//    		
//    		// More casting of the second maximum value to int to get ASCII value
//    		String stringSecondMaxKey = "" + secondMax;
//    		char charSecondMaxKey = stringSecondMaxKey.charAt(0);
//    		int secondMaxKey = (int)charSecondMaxKey;
    		
    		int key1CompareToKey2 = max.compareTo(x.next.key);
    		int secondMaxCompareToKey2 = secondMax.compareTo(x.next.key);
    		
    		// compare ASCII value of Node 1 against Node 2 
    		if  ( key1CompareToKey2 < 0 ) {
    			secondMax = max;
    			max = x.next.key;
    		}
    		// Compare ASCII value of Node 1 against Node 2 and 
    		// second maximum key against key 2
    		if (key1CompareToKey2 > 0 && secondMaxCompareToKey2 < 0) {
    			secondMax = x.next.key;
    		}
    		// move to the next node when finished
    		x = x.next;
    	}
    
    	return secondMax; // TODO
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
    	
    	LinkedListST<String,Integer> aList = new LinkedListST<String,Integer>();
    	Node x = first;
    	
    	
    	for (x = first ; x != null; x = x.next) {
    		
    		
    		
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
