package week8.lectures;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST
{
	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph G)
	{
		marked = new boolean[G.V()];
		mst = new Queue<>();
		pq = new MinPQ<>();
		visit(G, 0);
		
		while(!pq.isEmpty() && mst.size() < G.V()-1)
		{
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (marked[v] && marked[w]) continue;
			mst.enqueue(e);
			if(!marked[v]) visit(G, v);
			if(!marked[w]) visit(G, w);
			
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v)
	{
		marked[v] = true;
		for (Edge e : G.adj(v))
			if (!marked[e.other(v)])
				pq.insert(e);
	}
	
	public Iterable<Edge> mst()
	{ return mst; }

}