package com.appspot.pgillich.client.example.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appspot.pgillich.client.example.graph.Visitor.AllVisitedException;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.ChartArea;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.ScatterChart;
import com.google.gwt.visualization.client.visualizations.corechart.TextStyle;

public class GwtRunner {

	private Map<String, Node> nodes;

	public void start(String nodesString, String edgesString, VerticalPanel resultPanel) {
		try {
			Visitor v = new Visitor();

			while (resultPanel.getWidgetCount() > 0) {
				resultPanel.remove(resultPanel.getWidgetCount() - 1);
			}

			nodes = new HashMap<String, Node>();

			Node firstNode = parseNodes(nodesString);
			parseEdges(edgesString);

			v.visitAllNodes(nodes.values(), firstNode);
		} catch (AllVisitedException e) {
			System.out.println(e.getMessage());

			resultPanel.add(new Label("Path: " + e.getMessage()));
			resultPanel.add(createChart(e.getPath(), resultPanel));
		} catch (Throwable e) {
			resultPanel.add(new Label("Error: " + e.getClass().getName() + ", " + e.getMessage()));
		}

		StackPanel parentPanel = (StackPanel) resultPanel.getParent();
		parentPanel.showStack(parentPanel.getWidgetIndex(resultPanel));
	}

	/**
	 * ?chxs=0,676767,10.5,0,l,676767|1,676767,10.5,0,l,676767 &chxt=x,y
	 * &chs=600x480 &cht=lxy &chco=00FF00
	 * &chd=e:GZZmZmsy..sy..sysyGZGZGZ,GZGZZmZmGZZmsyZmsysyZmGZ &chdl=Edges
	 * &chg=1,1,0,5 &chls=3 &chma=5,0,0,5|0,5
	 * &chm=o,FF0000,0,0:11,10,1|tA,0000FF
	 * ,0,0,10,1|tB,0000FF,0,1,10,1|tC,0000FF,
	 * 0,2,10,1|tE,0000FF,0,3,10,1|tF,0000F
	 * F,0,4,10,1|tG,0000FF,0,6,10,1|tH,0000F
	 * F,0,8,10|tI,0000FF,0,9,10,1|tD,0000FF,0,10,10,1 &chtt=Graph
	 */
	private Widget createChart(List<Node> path, VerticalPanel resultPanel) {
		DataTable data = DataTable.create();

		double xMin = 0;
		double xMax = 0;
		double yMin = 0;
		double yMax = 0;

		data.addColumn(ColumnType.NUMBER, "x");
		data.addRows(path.size() * 2);
		Node p = null;
		for (Node n : path) {
			if (p == null) {
				p = n;

				xMin = xMax = n.getX();
				yMin = yMax = n.getY();

				continue;
			}
			String label = p.getName() + "-" + n.getName();
			int c = data.addColumn(ColumnType.NUMBER, label);
			int r = data.addRow();
			data.setValue(r, 0, p.getX());
			data.setValue(r, c, p.getY());
			r = data.addRow();
			data.setValue(r, 0, n.getX());
			data.setValue(r, c, n.getY());

			xMin = Math.min(xMin, n.getX());
			xMax = Math.max(xMax, n.getX());
			yMin = Math.min(yMin, n.getY());
			yMax = Math.max(yMax, n.getY());

			p = n;
		}

		Options options = Options.create();
		options.setTitle("Graph traversal example");
		TextStyle titleStyle = TextStyle.create();
		titleStyle.setFontSize(20);
		options.setTitleTextStyle(titleStyle);
		options.setWidth(600);
		options.setHeight(400);
		ChartArea chartArea = ChartArea.create();
		chartArea.setWidth("70%");
		chartArea.setHeight("70%");
		options.setChartArea(chartArea);

		AxisOptions xOptions = AxisOptions.create();
		xOptions.setMinValue(xMin);
		xOptions.setMaxValue(xMin);
		options.setHAxisOptions(xOptions);

		AxisOptions yOptions = AxisOptions.create();
		yOptions.setMinValue(yMin);
		yOptions.setMaxValue(yMin);
		options.setVAxisOptions(yOptions);

		options.setLineWidth(4);

		ScatterChart lch = new ScatterChart(data, options);

		return lch;
	}

	private Node parseNodes(String nodesString) {
		Node firstNode = null;
		String[] parts = nodesString.split(" ");

		for (int i = 0; i < parts.length; i++) {
			String[] cparts = parts[i].split("[:,]", 3);
			String name = cparts[0];
			nodes.put(name, new Node(name, Double.valueOf(cparts[1]), Double.valueOf(cparts[2])));
			if (i == 0) {
				firstNode = nodes.get(name);
			}
		}

		return firstNode;
	}

	private void parseEdges(String edgesString) {
		String[] parts = edgesString.split(" ");

		for (int i = 0; i < parts.length; i++) {
			String[] eparts = parts[i].split("-", 3);
			Node from = nodes.get(eparts[0]);
			Node to = nodes.get(eparts[1]);

			new Edge(from, to);
		}
	}
}
