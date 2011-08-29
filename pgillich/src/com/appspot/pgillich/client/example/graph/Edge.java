package com.appspot.pgillich.client.example.graph;

import java.util.ArrayList;
import java.util.List;

public class Edge {
	private int visited;
	private String name;
	private List<Node> nodes;
	private static int idCounter = 1;
	private int id;

	public Edge(Node from, Node to) {
		id = idCounter++;
		
		nodes = new ArrayList<Node>();
		nodes.add(from);
		nodes.add(to);

		from.addEdge(this);
		to.addEdge(this);

		name = from.getName() + '-' + to.getName();
	}

	public Node getOtherNode(Node n)
	{
		return n.equals(nodes.get(0)) ? nodes.get(1) : nodes.get(0);
	}

	public String getName() {
		return name;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public int getVisited() {
		return visited;
	}

	public void increaseVisited() {
		this.visited++;
	}

	public void decreaseVisited() {
		this.visited--;
	}

	public int getId() {
		return id;
	}
	
	
}
