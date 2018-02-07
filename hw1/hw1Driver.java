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
package hw1;

import stdlib.StdIn;
import stdlib.StdOut;

public class hw1Driver {

		public static void main(String[] args)
		{

			
			// the simple testing code from the textbook pg 370
			// you may delete/comment this out if you wish
//			LinkedListST<String, Integer> st = new LinkedListST<>();
//			StdIn.fromFile("data/tinyST.txt");
//			for (int i = 0; !StdIn.isEmpty(); i++)
//			{
//				String key = StdIn.readString();
//				st.put(key, i);
//			}
//			for (String s : st.keys())
//				StdOut.println(s + " " + st.get(s));
			
			// To do:   call you testing functions with your test cases. 
			//  label each test case with a comment describing what you are testing for.
			
//			sizeTest("",0);				// test size on an empty ST (symbol table)
//			sizeTest("abcde",5);        // test size on a non-empty ST
//			sizeTest("sldafwe", 7);
//			secondMaxKeyTest("abcde", "d");
//			secondMaxKeyTest("radwsghz", "w");
//			secondMaxKeyTest("ba", "a");
//			rankTest("bzcwd", "j");
//			rankTest("bzcwd", "a");
//			rankTest("bxcwd", "z");
//			rankTest("a", "z");
			floorTest("codazn", "p");
			floorTest("codazn", "a");
			floorTest("r", "x");
			floorTest("z", "x");
			floorTest("rstuvy", "x");
//			inverseTest("abcdefg");
										// add more more test here

		}

		// sample testing function.
		// param vals: all substrings of length 1 are added to the ST
		// param answer:  the correct value of the ST for the input:vals
		public static void sizeTest( String vals, int answer ) {
			
			// create and populate the table from the input string vals
			LinkedListSTStudy<String,Integer> aList = new LinkedListSTStudy<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the size function
			int result = aList.size();
			//report result
			if ( result == answer)  // test passes
				StdOut.format("sizeTest: Correct  String %s Answer: %d\n", vals,result);
			else
				StdOut.format("sizeTest: *Error*  String %s Answer: %d\n", vals,result);
		}
		
		public static void secondMaxKeyTest( String vals, String answer ) {
			
			// create and populate the table from the input string vals
			LinkedListSTStudy<String,Integer> aList = new LinkedListSTStudy<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the size function
			String result = aList.secondMaxKey();
			//report result
			if ( result.equals(answer))  // test passes
				StdOut.format("secondMaxKeyTest: Correct  String %s Answer: %s\n", vals,result);
			else
				StdOut.format("secondMaxKeyTest: *Error*  String %s Answer: %s\n", vals,result);
		}
		
		//Todo: add your testing functions here
		
		public static void rankTest( String vals, String key ) {
			
			// create and populate the table from the input string vals
			LinkedListSTStudy<String,Integer> aList = new LinkedListSTStudy<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the size function
			int result = aList.rank(key);
			StdOut.format("rankTest: String vals %s , How many less than %s , Answer: %d\n", vals, key, result);
			//report result
			
		}
		
		public static void floorTest( String vals, String key ) {
			
			// create and populate the table from the input string vals
			LinkedListSTStudy<String,Integer> aList = new LinkedListSTStudy<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the size function
			String result = aList.floor(key);
			StdOut.format("floorTest: String vals %s , What is the floor of %s , Answer: %s\n", vals, key, result);
			//report result
			
		}
		//See note about testing inverse function
		public static void inverseTest(String vals) {
			LinkedListSTStudy<String,Integer> aList = new LinkedListSTStudy<String,Integer>();
			LinkedListSTStudy<Integer,String> bList = new LinkedListSTStudy<Integer,String>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			StdOut.println("before LL inverse");
			for (String s : aList.keys())
				StdOut.println(s + " " + aList.get(s));
			bList = aList.inverse();
			StdOut.println("after LL inverse");
			for (Integer s : bList.keys())
				StdOut.println(s + " " + bList.get(s));
			
		}
}
