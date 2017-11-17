package com.mford.graphlib;
/**
 * Factory Class used to generate model implementations.
 * 
 * @author mattfo
 *
 */
public class ModelFactory {

	public static Node getNode(String nodeName) throws IllegalArgumentException {
		return new NodeImpl(nodeName);
	}
	
	public static Edge getEdge(Node from, Node to, boolean isDirected) throws IllegalArgumentException {
		return new EdgeImpl(from, to, isDirected);
	}
	
	public static Graph getGraph() throws IllegalArgumentException {
		return new GraphImpl();
	}
}
