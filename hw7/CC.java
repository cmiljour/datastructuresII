package hw7;

//Cory Miljour
//CSC403W18HW7B

import stdlib.*;
import algs41.Graph;
import algs41.GraphGenerator;
import algs42.Digraph;

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
	// find biggest component size 
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
	
	/* this is my code!!! */
		int sum = 0;
		int reps = 1000;
		int average;
		double probability = .06;

//		G.toGraphviz("undirected.png");
//		StdOut.println(cc.biggestCcSize());

		
		// create testing loop of 1000 tests increasing by .001 after each loop
		for (double p = .000; p < probability; p += .001) {
			// 1000 tests loop
			for (int i = 0; i < reps; i ++) {
				Graph G = GraphGenerator.erRandom(101, p);
				CC cc = new CC(G);
				sum = cc.biggestCcSize() + sum; 
			}
			average = sum / reps;
			StdOut.format("The undirected graph probability of %.3f yields largest component size of %d\n", p, average);
//			StdOut.format("%.3f\n",p);
//			StdOut.println(unAverage);
			sum = 0;
			
		}
	}
	/* my code ends here... */
}
