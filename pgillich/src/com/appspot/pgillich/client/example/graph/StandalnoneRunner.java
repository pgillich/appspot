package com.appspot.pgillich.client.example.graph;

import java.util.ArrayList;
import java.util.List;

import com.appspot.pgillich.client.example.graph.Visitor.AllVisitedException;

public class StandalnoneRunner 
{
	public static void main(String [] args)
	{
		Visitor v = new Visitor();
		
		List<Node> nodes = new ArrayList<Node>();
		
		createGraphX(nodes);
		
		try {
			v.visitAllNodes(nodes, nodes.get(0));
		} catch (AllVisitedException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void createGraphX(List<Node> nodes)
	{
		Node a = new Node("a",0,0);
		nodes.add(a);
		Node b = new Node("b",0,0);
		nodes.add(b);
		Node c = new Node("c",0,0);
		nodes.add(c);
		Node d = new Node("d",0,0);
		nodes.add(d);
		Node e = new Node("e",0,0);
		nodes.add(e);
		Node f = new Node("f",0,0);
		nodes.add(f);
		Node g = new Node("g",0,0);
		nodes.add(g);
		Node h = new Node("h",0,0);
		nodes.add(h);
		Node i = new Node("i",0,0);
		nodes.add(i);
		
		new Edge(a,b);
		new Edge(b,c);
		new Edge(c,e);
		new Edge(e,f);
		new Edge(e,g);
		new Edge(e,h);
		new Edge(h,i);
		new Edge(i,d);
		new Edge(d,a);
		new Edge(d,c);
	}
}
