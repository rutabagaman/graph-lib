package com.mford.graphlib;

import java.util.*;

public interface Graph {
		//private: Map<String, Node> nameNodeMap = new HashMap<String, Node>.   	
		Iterator<Node> getNodes(); 
		Iterator<Edge> getEdges();
		Node getNodeByName(String name); //(note—duplicate node names are disallowed)  
		Edge getEdge(Node n1, Node n2); 
		void addNode(Node n); 
		List<Edge> removeNode(Node n);
		void addEdge(Node n1, Node n2); 
		void addDirectedEdge(Node from, Node to);
		void removeEdge(Edge e);
		boolean areConnected(Node n1, Node n2);
}
