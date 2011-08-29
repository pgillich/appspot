package com.appspot.pgillich.client.example.graph;

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge e1, Edge e2) {
		if (e1.getId() == e2.getId())
			return 0;

		if (e1.getVisited() == e2.getVisited())
			return e1.getId() < e2.getId() ? -1 : 1;

		return e1.getVisited() < e2.getVisited() ? -1 : 1;
	}

}
