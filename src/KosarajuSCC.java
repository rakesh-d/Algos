import java.util.*;
import java.io.*;
class Graph implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	ArrayList<ArrayList<Integer>> adjList;
	final int nNodes;
	public Graph (int max) {
		
		nNodes = max+1;
		adjList = new ArrayList<ArrayList<Integer>>(
				Collections.<ArrayList<Integer>>nCopies(nNodes, null));
	}
	void addEdge (int i, int j) {
		if (adjList.get(i) == null)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(j);
			adjList.add(i, temp);
		}
		else
			adjList.get(i).add(j);
	}
}
public class KosarajuSCC {
	
	Graph input, revInp;
	int[] finishTime;
	int[] order;
	int[] leader;
	boolean[] visited;
	int time, curLeader;
	public KosarajuSCC (Graph input, Graph revInp) {
		this.input = input;
		this.revInp = revInp;
		order = new int[input.nNodes];
		for (int i = 1; i < input.nNodes; i++)
			order[i] = i;
		finishTime = new int[input.nNodes];
		leader = new int[input.nNodes];
		visited = new boolean[input.nNodes];
	}
	void makeReverse () {
		
		System.out.println("here0");
		for (int i = 1; i < input.nNodes; i++)
		{

			if (input.adjList.get(i) == null) continue;
			for (int j : input.adjList.get(i))
				revInp.addEdge(j, i);
		}
		
		
	}
	void getOrder () {
		for (int i = 1; i < order.length - 1; i++) 
			for (int j = i+1; j < order.length; j++)
			{
				if (finishTime[j] > finishTime[i])
				{
					int temp = finishTime[i];
					finishTime[i] = finishTime[j];
					finishTime[j] = temp;
					temp = order[i];
					order[i] = order[j];
					order[j] = temp;
				}
			}
	}
	
	void getSCC () {
		dfsLoop(revInp,order);
		getOrder();
		System.out.println("here");
		dfsLoop(input,order);
	}
	void dfsLoop (Graph graph, int[] order) {
		
		for (int i = 1; i < visited.length; i++)
			visited[i] = false;
		time = 0;
		for (int i = 1; i < order.length; i++)
		{
			curLeader = order[i];
			if (visited[curLeader] == false)
				dfs(graph, curLeader);
		}
		
	}
	void dfs (Graph graph, int i) {
		visited[i] = true;
		leader[i] = curLeader;
		if (graph.adjList.get(i) == null) return;
		for (int j: graph.adjList.get(i)) {
			if (visited[j] == false)
				dfs(graph, j);
		
		}
		time++;
		finishTime[i] = time;
	}
	
	
	
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		
		Graph inp = new Graph(875714), revGraph = new Graph (875714);
		BufferedReader br = new BufferedReader(new FileReader("/home/rakesh/Downloads/SCC.txt"));
		Scanner sc = new Scanner(br); 
		int loops = 0;
		while (sc.hasNext())
		{
			if (loops++ % 10000 == 0) System.out.println(loops);
			int i = sc.nextInt();
			int j = sc.nextInt();
			inp.addEdge(i, j);
			revGraph.addEdge(j, i);
		}
		System.out.println(inp.adjList.get(2));
		String filename = "Graph.ser";
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(inp);
		out.close();
		filename = "revGraph.ser";
		fos = new FileOutputStream(filename);
		out = new ObjectOutputStream(fos);
		out.writeObject(revGraph);
		out.close();
	/*	String filename = "Graph.ser";
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fis);
		Graph inp = (Graph) in.readObject();
		in.close();
		System.out.println(inp.adjList.get(875714));
		
		System.out.println(inp.nNodes);
		KosarajuSCC sccFinder = new KosarajuSCC(inp);
		sccFinder.getSCC();
		String outfile = "leader.ser";
		FileOutputStream fos = new FileOutputStream(outfile);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(sccFinder.leader);
		out.close();
		
		*/
		
		
		
		
	}

}
