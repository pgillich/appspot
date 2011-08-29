package com.appspot.pgillich.client.example.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Visitor {

	public static class AllVisitedException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<Node> path;
		
		public AllVisitedException(List<Node> path)
		{
			super(pathToString(path));

			this.path = path;
		}
		
		private static String pathToString(List<Node> path) {
			StringBuilder sb = new StringBuilder();
			for (Node n : path) {
				sb.append(n.getName());
				sb.append(" ");
			}
			
			return sb.toString();
		}

		public List<Node> getPath() {
			return path;
		}
	}
	
	private Comparator<Edge> edgeComparator = new EdgeComparator();

	public void visitAllNodes(Collection<Node> nodes, Node s) throws AllVisitedException {
		List<Node> path = new ArrayList<Node>();
		visitNode(nodes, path, s);
	}

	private void visitNode(Collection<Node> nodes, List<Node> path, Node s) throws AllVisitedException {
		
		List<Edge> edges = new ArrayList<Edge>(s.getEdges());
		Collections.sort(edges, edgeComparator);

		path.add(s);
		s.increaseVisited();

		boolean isAllVisited = true;
		for (Node n : nodes) {
			if (n.getVisited() == 0) {
				isAllVisited = false;
				break;
			}
		}

		if (isAllVisited) {
			throw new AllVisitedException(path);
		} else {
			for (Edge e : edges) {
				e.increaseVisited();
				visitNode(nodes, path, e.getOtherNode(s));
				e.decreaseVisited();
			}
		}

		path.remove(s);
		s.decreaseVisited();
	}

}
