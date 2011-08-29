package com.appspot.pgillich.client.example.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Edge> edges;
	private String name;
	private int visited;
	private double x;
	private double y;

	public String getName() {
		return name;
	}

	public Node(String name, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
		
		edges = new ArrayList<Edge>();
		visited = 0;
	}

	public void addEdge(Edge e) {
		edges.add(e);
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

	public List<Edge> getEdges() {
		return edges;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	
}
