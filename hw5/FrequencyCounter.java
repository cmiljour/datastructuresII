package hw5;
import stdlib.*;
import algs34.LinearProbingHashST;
import algs34.SeparateChainingHashST;
import hw5.HashKey;


public class FrequencyCounter {

	public static void main(String[] args) {
		//int minlen = 2; String file = "data/tinyTale.txt";
//		int minlen = 6; String file = "data/tale.txt";
		int minlen = 12; String file = "data/leipzig1M.txt";
		
//		StdOut.println ("LinearProbingHashST"); testLinearProbingHashST(minlen, file);
		StdOut.println ("SeparateChainingHashST"); testSeparateChainingHashST(minlen, file);

	}
	
	public double testNumPlusOne(int numberOfKeys) {
		StdOut.format("Get %s Time", numberOfKeys);
		SeparateChainingHashST<String, Integer> test = new SeparateChainingHashST<>();
		int count = 1;
		int number = numberOfKeys;
		Stopwatch sw = new Stopwatch();
		for (String key: test.keys()) {
			test.put(key, 0);
			if (count ++ == number) break;
		
		StdOut.format("%.4f\n", sw.elapsedTime() );
		}
		return sw.elapsedTime();
	}
	

	private static void testLinearProbingHashST (int minlen, String file) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();

		In in = new In (file);
		
		while (!in.isEmpty()) {
			String key = in.readString();
//			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}

		StdOut.println("table size: " + st.size());
		StdOut.println("Create and Put Table Time");
		int reps = 10;
		
		for (int N = 1024; N < 300000; N = N * 2) {
			StdOut.format("table size: %d\n", N);
			Stopwatch sw = new Stopwatch();
			for (int r = 1; r<=reps; r++) {
				LinearProbingHashST<String, Integer> test = new LinearProbingHashST<>();
				
				int count = 1;
				for (String key: st.keys()) {
					test.put(key, 0);
					if (count ++ == N) break;
				}
			}
			
			StdOut.format("%.4f\n", sw.elapsedTime() / reps);
		}
		
		StdOut.println("Get Table Time");
		
		for (int N = 1024; N < 300000; N = N * 2) {
			Stopwatch sw = new Stopwatch();
			for (int r = 1; r<=reps; r++) {
				LinearProbingHashST<String, Integer> test = new LinearProbingHashST<>();
				
				int count = 1;
				for (String key: st.keys()) {
					test.get(key);
					if (count ++ == N) break;
				}
			}
			
			StdOut.format("%.4f\n", sw.elapsedTime() / reps);
		}

	}
	private static void testSeparateChainingHashST (int minlen, String file) {
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
		In in = new In (file);
		
		while (!in.isEmpty()) {
			String key = in.readString();
//			if (key.length() < minlen) continue;
			if (st.contains(key)) { st.put(key, st.get(key) + 1); }
			else                  { st.put(key, 1); }
		}

		StdOut.println("table size: " + st.size());
		StdOut.println("Create and Put Table Time");
		int reps = 10;
		
		for (int N = 1024; N < 300000; N = N * 2) {
			Stopwatch sw = new Stopwatch();
			for (int r = 1; r<=reps; r++) {
				SeparateChainingHashST<String, Integer> test = new SeparateChainingHashST<>();
				
				int count = 1;
				for (String key: st.keys()) {
					test.put(key, 0);
					if (count ++ == N) break;
				}
			}
			
			StdOut.format("%.4f\n", sw.elapsedTime() / reps);
		}
		
		StdOut.println("Get Table Time");
		for (int N = 1024; N < 300000; N = N * 2) {
			Stopwatch sw = new Stopwatch();
			for (int r = 1; r<=reps; r++) {
				SeparateChainingHashST<String, Integer> test = new SeparateChainingHashST<>();
				
				int count = 1;
				for (String key: st.keys()) {
					test.get(key);
					if (count ++ == N) break;
				}
			}
			
			StdOut.format("%.4f\n", sw.elapsedTime() / reps);
		}
		
		
		StdOut.println("Get 100 Time");
		SeparateChainingHashST<String, Integer> test100 = new SeparateChainingHashST<>();
		Stopwatch sw = new Stopwatch();
		int count = 1;
		int number = 100;
		for (String key: st.keys()) {
			test100.put(key, 0);
			if (count ++ == 100) break;
		double hundredTime = sw.elapsedTime();
		StdOut.format("%.4f\n", sw.elapsedTime() );
		}
		
		
		
	}
	

}
