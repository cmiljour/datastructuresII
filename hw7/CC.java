package hw7;
import stdlib.*;
import algs13.Queue;
import algs41.Graph;
import algs41.GraphGenerator;
import algs42.Digraph;
/* ***********************************************************************
 *  Compilation:  javac CC.java
 *  Execution:    java CC filename.txt
 *  Dependencies: Graph.java StdOut.java Queue.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/tinyG.txt
 *
 *  Compute connected components using depth first search.
 *  Runs in O(E + V) time.
 *
 *  %  java CC tinyG.txt
 *  3 components
 *  0 1 2 3 4 5 6
 *  7 8
 *  9 10 11 12
 *
 *************************************************************************/


public class CC {
	private final boolean[] marked;   // marked[v] = has vertex v been marked?
	private final int[] id;           // id[v] = id of connected component containing v
	private final int[] size;         // size[id] = number of vertices in component containing v
	private int count;                // number of connected components

	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		size = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}
	}
	
	public CC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		size = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}
	}

	// depth first search
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		size[count]++;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		size[count]++;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	// id of connected component containing v
	public int id(int v) {
		return id[v];
	}

	// size of connected component containing v
	public int size(int v) {
		return size[id[v]];
	}

	// number of connected components
	public int count() {
		return count;
	}

	// are v and w in the same connected component?
	public boolean areConnected(int v, int w) {
		return id(v) == id(w);
	}


	// test client
	public static void anotherTest() {
		Graph G;
		do {
			G = GraphGenerator.simple(20,40);
		} while (new CC(G).count() != 1);
		G.toGraphviz ("g.png");
	}
	
	/* this is my code!!! */
	public int biggestCcSize () {
		int max = size[0];
		for (int i : size) {
			if (i > max) {
				max = size[i];
			}
		}
		return max;
	}
	/* my code ends here */
	
	
	public static void main(String[] args) {
		
		int sumUndirected = 0;
		int sumDirected = 0;
		int reps = 1000;
		int diAverage;
		int unAverage;
		int stopper;
		double probability = .06;
//		G2.toGraphviz("g.png");
//		G.toGraphviz("undirected.png");
//		StdOut.println(cc.biggestCcSize());
//		StdOut.println(cc2.biggestCcSize());
		
		// Undirected loop
		for (double p = .000; p < probability; p += .001) {
			for (int i = 0; i < reps; i ++) {
				Graph G = GraphGenerator.erRandom(101, p);
				CC cc = new CC(G);
				sumUndirected = cc.biggestCcSize() + sumUndirected; 
			}
			unAverage = sumUndirected / reps;
			//StdOut.format("The undirected graph probability of %.3f is %d\n", p, unAverage);
//			StdOut.format("%.3f\n",p);
//			StdOut.println(unAverage);
			sumUndirected = 0;
			
		}
		
		// Directed loop
		for (double p = .000; p < probability; p += .001) {
			for (int i = 0; i < reps; i ++) {
				Digraph G2 = GraphGenerator.erRandomDi(101, p);
				CC cc2 = new CC(G2);
				sumDirected = cc2.biggestCcSize() + sumDirected;
				
			}
			diAverage = sumDirected / reps;
			//StdOut.format("The directed graph probability of %.3f is %d\n", p, diAverage);
//			StdOut.format("%.3f\n", p);
			StdOut.println(diAverage);
			sumDirected = 0;
		}
		
		stopper = 0;
	}
}
