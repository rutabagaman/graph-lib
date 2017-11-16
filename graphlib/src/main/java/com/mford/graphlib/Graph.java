package com.mford.graphlib;

import java.util.*;

public interface Graph {
		Node getNodeByName(String name); //(note—duplicate node names are disallowed)
		Collection<Edge> getEdgesForNode(Node n);
		void addNode(Node n); 
		Collection<Edge> removeNode(Node n);
		void addEdge(Edge e);
		void removeEdge(Edge e);
		boolean areConnected(Node n1, Node n2);
		List<Node> shortestPath(Node from, Node to);
}
