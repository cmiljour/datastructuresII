// CSC 403 Programming assignment 2
// version 1.0
//  Complete the implementation of the SortedArrayST 
//     by completing the TO DO items
//   This is a simple variation of the example from 3.2
//
//   You may not change the other methods without permission
//      if you want to do this, your probably on the wrong track
//
//   You may add additional methods for modularity
//   You may not use other Java data structures (e.g. ArrayList, HashSet, etc)

package hw2;   // change the package if you want

import java.util.Arrays;
import javax.management.RuntimeErrorException;

import algs13.Queue;

import stdlib.StdOut;

public class SortedArrayST<Key extends Comparable<Key>, Value> {
	private static final int MIN_SIZE = 2;
	private Key[] keys;      // the keys array
	private Value[] vals;    // the values array
	private int N = 0;       // size of the symbol table, 
							 // may be different from the size of the arrays
	
	/**
	 * Initializes an empty symbol table.
	 */
	public SortedArrayST() {
		this(MIN_SIZE);
	}
	
	/**
	 * Initializes an empty symbol table of given size.
	 */
	@SuppressWarnings("unchecked")
	public SortedArrayST(int size) {
		keys = (Key[])(new Comparable[size]);
		vals = (Value[])(new Object[size]);
	}
	
	/**
	 * Initializes a symbol table with given sorted key-value pairs.
	 * If given keys list is not sorted in (strictly) increasing order,
	 * then the input is discarded and an empty symbol table is initialized.
	 */
	public SortedArrayST(Key[] keys, Value[] vals) {
		this(keys.length < MIN_SIZE ? MIN_SIZE : keys.length);
		N = (keys.length == vals.length ? keys.length : 0);
		int i;
		for (i = 1; i < N && keys[i].compareTo(keys[i - 1]) > 0; i++);
		if (i < N) { // input is not sorted
			System.err.println("SortedArrayST(Key[], Value[]) constructor error:");
			System.err.println("Given keys array of size " + N + " was not sorted!");
			System.err.println("Initializing an empty symbol table!");
			N = 0;
		} else {
			for (i = 0; i < N; i++) {
				this.keys[i] = keys[i];
				this.vals[i] = vals[i];
			}
		}
	}
	
	/**
	 * Returns the keys array of this symbol table.
	 */
	public Comparable<Key>[] keysArray() {
		return keys;
	}
	
	/**
	 * Returns the values array of this symbol table.
	 */
	public Object[] valsArray() {
		return vals;
	}
	
	/**
	 * Returns the number of keys in this symbol table.
	 */
	public int size() {
		return N;
	}
	
	/**
	 * Returns whether the given key is contained in this symbol table at index r.
	 */
	private boolean checkFor(Key key, int r) {
		return (r >= 0 && r < N && key.equals(keys[r]));
	}
	
	/**
	 * Returns the value associated with the given key in this symbol table.
	 */
	public Value get(Key key) {
		int r = rank(key);
		if (checkFor(key, r)) return vals[r];
		else return null;
	}
	
	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is null.
	 */
	public void put(Key key, Value val) {
		int r = rank(key);
		if (!checkFor(key, r)) {
			shiftRight(r);
			keys[r] = key;
		}
		vals[r] = val;
	}
	
	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 */
	public void delete(Key key) {
		int r = rank(key);
		if (checkFor(key, r)) {
			shiftLeft(r);
		}
	}
	
	public boolean contains(Key key) {
		return ( this.get(key)!= null);
	}
	
	/**
	 * Shifts the keys (and values) at indices r and above to the right by one
	 * The key and value at position r do not change.
	 * This function must resize the keys,vals arrays as needed
	 * 
	 */
	private void shiftRight(int r) {
		Key [] keyCopyArr = Arrays.copyOf(keys, (keys.length + 1));
		Value [] valCopyArr = Arrays.copyOf(vals, (vals.length + 1));
		
		int keyCopyArrLen = keyCopyArr.length;
		int valCopyArrLen = valCopyArr.length;
		
		for ( int i = keyCopyArr.length - 1; i > r; i--) {
			keyCopyArr[i ] = keyCopyArr[i - 1];
			valCopyArr[i ] = valCopyArr[i - 1];
		}
		keys = keyCopyArr;
		vals = valCopyArr;
		
		return; // TODO1

	}
	
	/**
	 * Shifts the keys (and values) at indices x > r to the left by one
	 * in effect removing the key and value at index r 
	 */
	private void shiftLeft(int r) {
		Key [] keyCopyArr;
		Value [] valCopyArr; 
		
		for (int i = r; i < keys.length - 1; i++ ) {
			keys[i] = keys[i + 1];
			vals[i] = vals[i + 1];
		}
		keys = Arrays.copyOf(keys, (keys.length - 1));
		vals = Arrays.copyOf(vals, (vals.length - 1));
		// TODO2
	
	}
	
	/**
	 * rank returns the number of keys in this symbol table that is less than the given key. 
	 */
	
	
	public int keyBinarySearch (Key key, int lo, int hi) {
		
		if (hi >= lo) {
			
			int mid = lo + (hi - lo)/2;
			
			if (key.compareTo(keys[mid]) == 0) {
				return mid;
			}
			
			if(key.compareTo(keys[mid]) > 0) {
				return keyBinarySearch(key, mid + 1, hi);
			}
			
			if(key.compareTo(keys[mid]) < 0) {
				return keyBinarySearch(key, lo, mid - 1);
			}
			
		}
		
		return lo;
	}
	
	public int rank(Key key) {
		
		int hi = keys.length - 1;
		int lo = 0;
		int mid =  lo + (hi - lo)/2;
		
		if(key.compareTo(keys[hi]) > 0 ) {
			return keys.length;
		}
		
		if(key.compareTo(keys[mid]) == 0 ) {
			
			return mid;
		}
		
		if(key.compareTo(keys[mid]) > 0 ) {
			return keyBinarySearch(key, mid+1, hi);
		}
		
		
		if(key.compareTo(keys[mid]) < 0) {
			
			return keyBinarySearch(key, lo, mid - 1);
		}
		
		return 0;

		// TODO3 : logarithmic time implementation
	}

	/**floorOfKey
	 * Linear time implementation of rank
	 */
	private int linearTimeRank(Key key) {
		int r;
		for (r = 0; r < N && key.compareTo(keys[r]) > 0; r++);
		return r;
	}
	// Compare two  ST for equality
	// TODO4
	public boolean equals(SortedArrayST<String, String> x) {
		if ( (Arrays.equals(vals, x.vals)) && (Arrays.equals(keys, x.keys)) ) {
			return true;
		}
		
		return false;
	}
	/**
	 * floor returns the largest key in the symbol table that is less than or equal to key.
	 * it returns null if there is no such key.
	 */
	public Key floor(Key key) {
		
		Key minFloorKey = null;
		
		for (int i = 0; i < keys.length ; i++) {

			
			if(key.compareTo(keys[i]) == 0) {
				return keys[i];
			}
			
			if (key.compareTo(keys[i]) > 0 && minFloorKey == null) {
				minFloorKey = keys[i];
			}
			
			if (key.compareTo(keys[i]) > 0 && minFloorKey.compareTo(keys[i]) < 0) {
				minFloorKey = keys[i];
			}
		}
		
		return minFloorKey; // TODO5

	}
	/**
	 * countRange returns the number of keys within the range (key1, key2) (inclusive)
	 * note that keys may not be in order (key1 may be larger than key2)
	 */
	public int countRange(Key key1, Key key2) {
		
		Integer key1Index = Arrays.asList(keys).indexOf(key1);
		Integer key2Index = Arrays.asList(keys).indexOf(key2);
		
		Integer count = (key2Index - key1Index) + 1;
		
		
		return count; // TODO6
	}
	
	/*
	 *    a Utility function used by the testing framework to
	 *    build and return a symbol table from a pair of strings.
	 *    The individual characters of each string are extracted as substrings of length 1
	 *    and then stored in parallel arrays.
	 *    The parallel arrays are used as input to the SortArrayST constructor
	 *    The characters in the keyData need to be in sorted order.
	 *    
	 */
	public static SortedArrayST<String,String> from (String keyData, String valData) {
		int n = keyData.length();
		if ( n != valData.length()) throw new NullPointerException(" from: mismatch sizes");
		String[] keys = new String[n];
		String[] vals = new String[n];
		for (int i=0; i < n; i++ ) {
			keys[i] = keyData.substring(i, i+1);  // ith key is ith char-string of keyData string
			vals[i] = valData.substring(i, i+1);  // ith key is ith char-string of valData string
		}
		return new SortedArrayST(keys,vals);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		// Testing the rank function
			// for 0 keys
		testRank("A",0,"BDFK","1234");
		testRank("B",0,"BDFK","1234");
			// for 1 key
		testRank("C",1,"BDFK","1234");
			// key higher than all keys
		testRank("Z",4,"BDFK","1234");
			// big one
		testRank("H",7,"ABCDEFGHI","123456789");
		
		// Testing the delete function  (actually testing your shiftLeft implementation)
			// delete one key/value in 1st position
		testDelete("ABDFK","12345", "A","BDFK","2345");
			// delete one key/value in 2nd position
		testDelete("ABDFK","12345", "B","ADFK","1345");
			// delete one key/value in last position
		testDelete("ABDFK","12345", "K","ABDF","1234");
		// TO DO   add two  additional test cases
		//    include comments to describe what your case is checking for
		
			// insert self explanatory
		testPut("AEIOU","13456", "B","2", "ABEIOU","123456");
			// empty input , should throw an error
		testPut("AEIOU","13456", "","", "AEIOU","13456");
			// adding to end
		testPut("UVWXY","45678", "Z","9", "UVWXYZ","456789");
			// inverse key, value input, does it work?
		testPut("AEIOU","13456", "1","Z", "1AEIOU","Z13456");
		// TO DO   add three  additional test cases
		//include comments to describe what your case is checking for
			// basic equality
		testEquals("ABCDE", "12345", "ABCDE", "12345");
			// long basic equality
		testEquals("ABCDEFGHI", "123456789", "ABCDEFGHI", "123456789");
			// different values, should throw an error
		testEquals("ACDE", "1235", "ACDE", "1245");
			// lowercase key vs uppercase key, should throw an error
		testEquals("ABCD", "1234", "abcd", "1234");
		
			// normal test
		testFloor("ABCDEF", "123456", "D", "D");
			// key not in table
		testFloor("ABCEF", "12346", "D", "C");
			// key is last position in table
		testFloor("ABCDEF", "123456", "Z", "F");
			// normal range test
		testCountRange("ABCDEF", "123456", "B", "D", 3);
			// 2nd normal range test for inclusivity of 2 keys
		testCountRange("ABCDEF", "123456", "B", "C", 2);
			// larger range test
		testCountRange("FGHIJKLMN", "153246829", "H", "L", 5);
			// range is the whole table
		testCountRange("FGHIJKLMN", "153246829", "F", "N", 9);
	}
	/*
	 * Test the rank function. 
	 * build a symbol table from the input key,val strings
	 * (keyData characters must be in sorted order)
	 * then call the rank function, compare to the expected answer
	 */
	public static void testRank(String theKey, int expected, String keyData, String valData) {
		SortedArrayST<String, String> x = from(keyData,valData);
		int actual = x.rank(theKey);
		if ( actual == expected)  // test passes
			StdOut.format("rankTest: Correct  String %s Key %s rank: %d\n", keyData, theKey, actual);
		else
			StdOut.format("rankTest: *Error*  String %s Key %s rank: %d\n", keyData, theKey, actual);
			
	}
	/*
	 * Test the Put function. 
	 * build a symbol table from the input key,val strings
	 * (keyData characters must be in sorted order)
	 * then call the rank function, compare to the expected answertestFloor("ABCDEF", "123456", "D", "D");
	 */
	public static void testPut(String keyInData, String valInData, 
			                   String putKey, String putVal, 
			                   String keyOutData, String valOutData) {
		SortedArrayST<String, String> actual = from(keyInData,valInData);
		
		actual.put(putKey, putVal);
		SortedArrayST<String, String> expected = from(keyOutData, valOutData);
		
		
		
		if ( (Arrays.equals(actual.vals, expected.vals)) && (Arrays.equals(actual.keys, expected.keys)) )  // test passes
			StdOut.format("testPut: Correct  Before %s put:%s After: %s\n", keyInData, putKey, keyOutData);
		else
			StdOut.format("testPut: *Error*  Before %s put:%s After: %s\n", keyInData, putKey, keyOutData);
			
	}
	/*
	 * Test the delete function. 
	 * build a symbol table from the input key,val strings
	 * (keyData characters must be in sorted order)
	 * then call the rank function, compare to the expected answer
	 */
	public static void testDelete(String keyInData, String valInData, String delKey, 
								  String keyOutData, String valOutData) {
		SortedArrayST<String, String> actual = from(keyInData,valInData);
		SortedArrayST<String, String> expected = from(keyOutData, valOutData);
				actual.delete(delKey);
		
		
				if ( (Arrays.equals(actual.vals, expected.vals)) && (Arrays.equals(actual.keys, expected.keys)) )  // test passes
					StdOut.format("testDelete: Correct  Before %s delete:%s After: %s\n", keyInData, delKey, keyOutData);
				else
					StdOut.format("testDelete: *Error*  Before %s delete:%s After: %s\n", keyInData, delKey, keyOutData);
			
	}
	
	public static void testEquals(String key1, String val1, String key2, String val2) {
		SortedArrayST<String, String> st1 = from(key1,val1);
		SortedArrayST<String, String> st2 = from(key2, val2);
		
		String table1 = key1 + ": " + val1; 
		String table2 = key2 + ": " + val2; 
		
		if (st1.equals(st2))  // test passes
		StdOut.format("testEquals:  Correct  Symbol Table 1 %s is equal to Symbol Table 2 %s\n", table1, table2);
		else
		StdOut.format("testEquals:  *ERROR*  Symbol Table 1 %s is not equal to Symbol Table 2 %s\n", table1, table2);
	}
	
	public static void testFloor(String keyInData, String valInData, String floorOfKey, String expectedKey) {
		SortedArrayST<String, String> st1 = from(keyInData, valInData);

		String floor = st1.floor(floorOfKey);
		
		if (floor.equals(expectedKey))  // test passes
		StdOut.format("testFloor:  Correct  The floor of %s is equal to the expected key %s\n", floorOfKey, expectedKey);
		else
		StdOut.format("testFloor:  *ERROR* Something went wrong.  The floor of %s is not equal to the expected key %s\n", floorOfKey, expectedKey);
	}
	
	public static void testCountRange(String keyInData, String valInData, String key1InData, String key2InData, Integer expectedCountRange) {
		SortedArrayST<String, String> st1 = from(keyInData, valInData);
		
		Integer number = st1.countRange(key1InData, key2InData);
		
		if (number.equals(expectedCountRange))  // test passes
		StdOut.format("testCountRange:  Correct.  The number of keys in the range is %s\n", number);
		else
		StdOut.format("testCountRange:  *ERROR*  The range outputted is %s is not equal to the expected key %s\n", number, expectedCountRange);
	}

}
