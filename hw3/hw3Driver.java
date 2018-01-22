/* CSC 403
 * 
 * Your name goes here
 * 
 * Get help from anyone?  put that here
 * 
 * Homework 1 Driver 
 * 
 * Instructions:  using sizeTest  as a template, create additional functions to test
 *                the member functions in your LinkedListST implementation.
 *                AND
 *                create a reasonable set of test cases for each; 
 *                call your testing functions from main
 *                
 */
package hw3;

import stdlib.StdIn;
import stdlib.StdOut;

public class hw3Driver {

		public static void main(String[] args)
		{

				
			//  label each test case with a comment describing what you are testing for.
//			
//			sizeTest("",0);				// test size on an empty ST (symbol table)
//			sizeTest("dbeac",5);        // test size on a non-empty ST
//			sizeTest("a", 1);
//			sizeTest("abcdefghi",9);
//			putTest("abcde", "01234", "f", "5", "abcdef");
			
//			// middle key test
//			getTest("abcde", "01234", "c", "2");
//			// left most key test
//			getTest("abcde", "01234", "a", "0");
//			// right most key test
//			getTest("abcde", "01234", "e", "4");
//			// null test
//			getTest("abcde", "01234", "f", "0");
			
//			numLeavesTest("dbeac", "31402", 3);
//			numLeavesTest("cbaed", "21043", 2);
//			numLeavesTest("bcdae", "21043", 2);
//			numLeavesTest("ecdba", "21043", 2);
//			numLeavesTest("edcba", "21043", 1);
//			numLeavesTest("hczijbd", "2104398", 3);
//			numLeavesTest("uvwxyz", "012345", 1);
//			numLeavesTest("bac", "012", 2);
//			numLeavesTest("b", "0", 1);
//			shortestPathTest("dbfge", "01234", 2);
//			shortestPathTest("cdbe", "0123", 2);
//			shortestPathTest("hapbnv", "012345", 2);
//			shortestPathTest("ngvdhpyceioqaxz", "012345678912345", 3);
//			shortestPathTest("oeubhqyacgirxz", "01234567891234", 3);
			
//			deleteTest("ofwagvz","0123456", "w", "2");
//			deleteTest("ofwagvz", "0123456", "f", "1");
			deleteTest("ofwagvz", "0123456", "o", "0");

		}

		
		public static void sizeTest( String vals, int answer ) {
			
			// create and populate the table from the input string vals
			BST403<String,Integer> bst = new BST403<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				bst.put(vals.substring(i, i+1),i);
			}
			//  call the size function
			int result = bst.size();
			//report result
			if ( result == answer)  // test passes
				StdOut.format("sizeTest: Correct  String %s Answer: %d\n", vals,result);
			else
				StdOut.format("sizeTest: *Error*  String %s Answer: %d\n", vals,result);
		}
		
		public static void putTest( String keys, String vals, String inKey, String inVal, String expected) {
			
			// create and populate the table from the input string vals
			BST403<String,String> bst = new BST403<String,String>();
			for (int i=0; i < vals.length(); i++) {
				bst.put( (keys.substring(i, i+1)), (vals.substring(i, i+1)) );
			}
			
			//report result
			if ( true )  // test passes
				StdOut.format("sizeTest: Correct  String %s Answer: %d\n", vals);
			else
				StdOut.format("sizeTest: *Error*  String %s Answer: %d\n", vals);
		}
		
		public static void getTest( String keys, String vals, String getKey, String expectedVal) {
			
			// create and populate the table from the input string vals
			BST403<String,String> bst = new BST403<String,String>();
			for (int i=0; i < vals.length(); i++) {
				bst.put( (keys.substring(i, i+1)), (vals.substring(i, i+1)) );
			}
			
			String retrievedKey = bst.get(getKey);
			
			//report result
			if ( expectedVal.equals(retrievedKey) )  // test passes
				StdOut.format("getTest: Correct, the input key %s returns the expected value %s \n", getKey, expectedVal);
			else
				StdOut.format("getTest: *Error*  The input key %s does not return the expected value %s, it returned %s\n", getKey, expectedVal, retrievedKey);
		}
		
		public static void numLeavesTest( String keys, String vals, Integer expectedVal) {
			
			// create and populate the table from the input string vals
			BST403<String,String> bst = new BST403<String,String>();
			for (int i=0; i < vals.length(); i++) {
				bst.put( (keys.substring(i, i+1)), (vals.substring(i, i+1)) );
			}
			
			Integer numberOfLeaves = bst.numLeaves();
			
			//report result
			if ( expectedVal.equals(numberOfLeaves) )  // test passes
				StdOut.format("numLeavesTest: Correct, the number of leaves in %s is = %s \n", keys, expectedVal);
			else
				StdOut.format("numLeavesTest: *Error*  The outputted value was %s, it should have returned %s\n", numberOfLeaves, expectedVal);
		}
		
		public static void shortestPathTest( String keys, String vals, Integer expectedVal) {
			
			// create and populate the table from the input string vals
			BST403<String,String> bst = new BST403<String,String>();
			for (int i=0; i < vals.length(); i++) {
				bst.put( (keys.substring(i, i+1)), (vals.substring(i, i+1)) );
			}
			
			Integer numberOfLinks = bst.lenShortestPathToNull();
			
			//report result
			if ( expectedVal.equals(numberOfLinks) )  // test passes
				StdOut.format("shortestPathTest: Correct, the number of links in %s is = %s \n", keys, expectedVal);
			else
				StdOut.format("shortestPathTest: *Error*  The number of links is %s, it should have returned %s\n", numberOfLinks, expectedVal);
		}
		
		public static void deleteTest( String keys, String vals, String delKey, String delVal) {
			
			// create and populate the table from the input string vals
			BST403<String,String> bst = new BST403<String,String>();
			for (int i=0; i < vals.length(); i++) {
				bst.put( (keys.substring(i, i+1)), (vals.substring(i, i+1)) );
			}
			//  call the size function
			bst.delete(delKey, delVal);
			String retrievedKey = bst.get(delKey);
			//report result
			if ( retrievedKey == null)  // test passes
				StdOut.format("deleteTest: Correct  You deleted %s from %s\n", delKey,keys);
			else
				StdOut.format("deleteTest: *Error*  You were supposed to delete %s, but something went wrong\n", delKey);
		}

}
