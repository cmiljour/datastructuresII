package hw6;  // change this if you want
import stdlib.*;

//  Version 1.0 
//
//This is basically exercise 4.1.16 from the text
//   see the exercise for definitions and hints
//
//  The provided structure follows the design pattern illustrated
//  by the examples in 4.1
//
// you're free to add instance variables and other methods
// you'll probably want to add in code to support bfs or dfs; 
//     feel free to grab and adapt these from the text and/or algs41
//  you might find queue or stack to be useful, if so I'd suggest you use
//  the versions from algs13
//
//  you shouldn't need (or use) anything else, ask me if not sure

// please document your code to explain your approach
// If I can't follow what you're doing, you will get reduced (or no) credit


import algs41.Graph;
import algs41.GraphGenerator;
import algs13.Queue;


public class CSC403HW6 {
	int[] eccentricity;  // the eccentricity of each vertex
	int diameter;        // the diameter of the graph
	int radius;	         // the radius of the graph
	boolean [] isAcenter;       // the set of all 
	private  int[] edgeTo;
	private int[] distTo;
	private  boolean[] marked;
	private Integer[] vList;
	private static final int INFINITY = Integer.MAX_VALUE;
	private Iterable<Integer> iterable;
	
	
	// Constructor can be linear in G.V() * G.E()
	public CSC403HW6(Graph G) {
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		
		this.eccentricity = new int[G.V()];
		int diameter = Integer.MIN_VALUE;
		int radius = Integer.MAX_VALUE;
		
		for (int i = 0; i < eccentricity.length; i++) {
			bfs(G, i);
			
			int max = distTo[0];
			for(int x = 1; x < distTo.length;x++)
			{
				if(distTo[x] > max)
				{
					max = distTo[x];
				}
			}
			eccentricity[i] = max;
			
			for (int z = 0; z < marked.length;z++) {
				marked[z] = false;
			}
		}
		
		diameter = eccentricity[0];
		radius = eccentricity[0];
		
		for (int j : eccentricity) {
			if (j > diameter) diameter = j;
			if (j < radius) radius = j;
		}
		

		// If G.V()==0, then these are the correct values.
		// If G is not connected, you should throw a new IllegalArgumentException()
		// I suggest that you first get it to work for a connected graph
		// This will require that you traverse the graph starting from some node (say 0)
		// You can then adjust your code so that it throws an exception in the case that all nodes are not visited

		// TODO
		// compute the eccentricity of each vertex, the diameter & radius 
		this.diameter = diameter;
		this.radius   = radius;

	}
	// breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
        for (boolean f : marked) {
			if (f == false) {
				throw new IllegalArgumentException("Graph is not connected!!!");
			}
		}
    }
    
	// Do not change the following constant time methods
	public int eccentricity(int v) { return eccentricity[v]; }
	public int diameter()          { return diameter; }
	public int radius()            { return radius; }
	public boolean isCenter(int v) { return eccentricity[v] == radius; }

	public static void main(String[] args) {
		// comment in/out for testing
//		Graph G = new Graph(new In("data/tinyG.txt")); // this is non-connected -- should throw an exception
//		Graph G = GraphGenerator.connected (10, 20, 2); // Random non-connected graph -- should throw an exception

//		Graph G = new Graph(new In("data/tinyCG.txt")); // diameter=2, radius=2, every node is a center
//		Graph G = GraphGenerator.binaryTree (10); // A complete binary tree
		Graph G = GraphGenerator.path (5); // A path -- diameter=V-1
//		Graph G = GraphGenerator.connected (20, 400); // Random connected graph

		StdOut.println(G);
		G.toGraphviz ("path.png");  // comment out if you don't have graphViz installed
		

		CSC403HW6 edrc = new CSC403HW6(G);
		for (int v = 0; v < G.V(); v++)
			StdOut.format ("eccentricity of %d: %d\n", v, edrc.eccentricity (v));
		StdOut.format ("diameter=%d, radius=%d\n", edrc.diameter(), edrc.radius() );
		for (int i = 0; i < G.V(); i++) {
			if ( edrc.isCenter(i))
			StdOut.format ("center=%d\n", i);
		}
		
	}
}
