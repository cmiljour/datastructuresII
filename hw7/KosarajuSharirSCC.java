package hw7;

// Cory Miljour
// CSC403W18HW7B

import stdlib.*;

import algs41.GraphGenerator;
import algs42.DepthFirstOrder;
import algs42.Digraph;
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
	
	/* My code starts here!! */
	public int sccMaxNumber() {
		int[] sccMaxArr = new int[id.length];
		// use an alternate array to assign component id to an array index value
		// this makes it easy to do simple addition when running across component id members
		for (int i : id) {
			sccMaxArr[i] = sccMaxArr[i] + 1;
		}
		// loop through that array to cc id that has most members
		int max = sccMaxArr[0];
		for (int i = 0; i < sccMaxArr.length; i++) {
			if (sccMaxArr[i] > max) max = sccMaxArr[i];
		}
		return max;
	}
	/* and ends here */

	public static void main(String[] args) {
		/* my code starts here!! */
		int sum = 0;
		int reps = 1000;
		int average;
		double probability = .06;
		
		for (double p = .000; p < probability; p += .001) {
			for (int i = 0; i < reps; i ++) {
				Digraph G2 = GraphGenerator.erRandomDi(101, p);
				KosarajuSharirSCC scc = new KosarajuSharirSCC(G2);
				sum = scc.sccMaxNumber() + sum;
				
			}
			average = sum / reps;
			StdOut.format("The directed graph probability of %.3f yields largest strongly connected component size of %d\n", p, average);
//			StdOut.format("%.3f\n", p);
//			StdOut.println(diAverage);
			// set sum back to 0 to get ready for next loop
			sum = 0;
		}
		/* my code ends here */
	}
}
