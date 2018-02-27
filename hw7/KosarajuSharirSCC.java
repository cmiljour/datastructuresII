package hw7;
import stdlib.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import algs13.Queue;
import algs42.DepthFirstOrder;
import algs42.Digraph;
import algs42.DigraphGenerator;
import algs42.TransitiveClosure;


public class KosarajuSharirSCC {
	private final boolean[] marked;     // marked[v] = has vertex v been visited?
	private final int[] id;             // id[v] = id of strong component containing v
	private int count;                  // number of strongly-connected components


	public KosarajuSharirSCC(Digraph G) {

		// compute reverse postorder of reverse graph
		Digraph R = G.reverse ();
		DepthFirstOrder dfs = new DepthFirstOrder(R);

		// run DFS on G, using reverse postorder to guide calculation
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for (int v : dfs.reversePost()) {
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}

		// check that id[] gives strong components
		assert check(G);
	}

	// DFS on graph G
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v)) {
			if (!marked[w]) dfs(G, w);
		}
	}

	// return the number of strongly connected components
	public int count() { return count; }

	// are v and w strongly connected?
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	// id of strong component containing v
	public int id(int v) {
		return id[v];
	}

	// does the id[] array contain the strongly connected components?
	private boolean check(Digraph G) {
		TransitiveClosure tc = new TransitiveClosure(G);
		for (int v = 0; v < G.V(); v++) {
			for (int w = 0; w < G.V(); w++) {
				if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
					return false;
			}
		}
		return true;
	}
	
	// My code starts here
	public int ccMaxNumber() {
		int[] ccMaxArr = new int[id.length];
		// use an alternate array to assign component id to an array index value
		// this makes it easy to do simple addition when running across component id members
		for (int i : id) {
			ccMaxArr[i] = ccMaxArr[i] + 1;
		}
		// loop through that array to find max cc membership value
		int max = ccMaxArr[0];
		for (int i = 0; i < ccMaxArr.length; i++) {
			if (ccMaxArr[i] > max) max = ccMaxArr[i];
		}
		return max;
	}
	// and ends here

	public static void main(String[] args) {
		args = new String[] { "data/tinyDG.txt" };
//		args = new String[] { "data/mediumDG.txt" };

		In in = new In(args[0]);
		Digraph G = DigraphGenerator.fromIn(in);
		G.toGraphviz ("directed.png");
		KosarajuSharirSCC scc = new KosarajuSharirSCC(G);
		int sccMax = scc.ccMaxNumber();
		if (!scc.check(G)) throw new Error ();

		// number of connected components
		int M = scc.count();
		StdOut.println(M + " components");

		// compute list of vertices in each strong component
		@SuppressWarnings("unchecked")
		Queue<Integer>[] components = new Queue[M];
		for (int i = 0; i < M; i++) {
			components[i] = new Queue<>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[scc.id(v)].enqueue(v);
		}

		// print results
		for (int i = 0; i < M; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}

	}

}
