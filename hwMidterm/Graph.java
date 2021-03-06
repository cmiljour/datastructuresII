// Exercise 4.1.3 (Solution published at http://algs4.cs.princeton.edu/)
package hwMidterm;
import stdlib.*;

import java.util.Arrays;

import algs13.Bag;
import algs41.GraphGenerator;

/**
 *  The <tt>Graph</tt> class represents an undirected graph of vertices
 *  named 0 through V-1.
 *  It supports the following operations: add an edge to the graph,
 *  iterate over all of the neighbors adjacent to a vertex.
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/51undirected">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Graph {
	private final int V;
	private int E;
	private final Bag<Integer>[] adj;

	/**
	 * Create an empty graph with V vertices.
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0) throw new Error("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		this.adj = new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	/**
	 * Return the number of vertices in the graph.
	 */
	public int V() { return V; }

	/**
	 * Return the number of edges in the graph.
	 */
	public int E() { return E; }


	/**
	 * Add the undirected edge v-w to graph.
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
	 */
	public void addEdge(int v, int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}
	
	// 4.a
	
	public void deleteNode(int v) {
		for (w : adj(v)) {
			
		}
		adj[v] = null;
		
	}
	
	// 4.b
	public void deleteEdge(int v, int w) {
		Bag<Integer>[] adjCopy = Arrays.copyOf(adj, adj.length);
		for (int i = 0; i < V ; i ++) {
			adj[i] = null;
			for (int z : adjCopy[i]) {
				if (z != v || z != w) {
					adj[i].add(z);
				}
			}
		};
		E--;
	}
	// 4.d
	public int maxDegree(Graph g) {
		int max = 0;
		
		for (int i = 0; i < V; i++) {
			for (int w: g.adj[i]) {
				if (degree(w) > max) max = degree(w);
			}
		}
		return max;
	}
	
	private int degree(int w) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 4.c
	public void addNode(int v) {
		adj[adj.length + 1] = new Bag<>();
		adj[adj.length + 1].add(v);
		
	}
	// 4.e
	public Graph complementGraph(Graph g) {
		Graph h = new Graph(g.V);
		for (int i = 0; i < g.V;i++) {
			for (int p = 0;i < g.V;p++) {
				if (!Arrays.asList(adj[i]).contains(p));
				h.adj[i].add(p);
			}
		}
		return h;
	}
	
	// 5 https://stackoverflow.com/questions/526331/cycles-in-an-undirected-graph
	
	// 7 https://stackoverflow.com/questions/20901274/why-do-we-need-to-run-dfs-on-the-complement-of-a-graph-in-the-kosarajus-algorit

	// 8 
	
	// 10 e
	
	/**
	 * Return the list of neighbors of vertex v as in Iterable.
	 * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
	 */
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		return adj[v];
	}


	/**
	 * Return a string representation of the graph.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Save a graphviz representation of the graph.
	 * See <a href="http://www.graphviz.org/">graphviz.org</a>.
	 */
	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		for (int v = 0; v < V; v++) {
			gb.addNode (v);
			boolean showSelfLoop = false;
			for (int w : adj[v]) {
				if (v < w) // only once each edge
					gb.addEdge (v, w);
				if (v == w) {
					showSelfLoop = !showSelfLoop;
					if (showSelfLoop)
						gb.addEdge (v, w);
				}
			}
		}
		gb.toFileUndirected (filename);
	}

	/**
	 * Test client.
	 */
	public static void main(String[] args) {
		//args = new String [] { "data/tinyCG.txt" };
		args = new String [] { "data/tinyG.txt" };
		//args = new String [] { "20", "40" };

//		Graph G;
//		if (args.length == 1) {
//			In in = new In(args[0]);
//			G = GraphGenerator.fromIn (in);
//		} else {
//			int V = Integer.parseInt (args[0]);
//			int E = Integer.parseInt (args[1]);
//			G = GraphGenerator.simple(V, E);
//		}
//		StdOut.println(G);
//		G.toGraphviz ("g.png");
	}
}
