package com.mford.graphlib;

public class ModelFactory {

	public static Node getNode(String nodeName) throws IllegalArgumentException {
		return new NodeImpl(nodeName);
	}
	
	public static Edge getEdge(Node from, Node to) throws IllegalArgumentException {
		return new EdgeImpl(from, to);
	}
	
	public static Graph getGraph() throws IllegalArgumentException {
		return new GraphImpl();
	}
}
