package hwMidterm;

public class Problem8 {

	private boolean[] marked;
	
	public Problem8() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isConnected() {
		for (boolean connected : marked ) {
			if (connected) return true;
		}
		return false;
	}
	
	public boolean hasCycle(Graph G, int v, int parent) {
		
		dfs(G, v, parent ){
			if (marked[v])
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
