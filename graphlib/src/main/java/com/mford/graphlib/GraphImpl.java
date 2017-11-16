package com.mford.graphlib;

import java.util.*;
/**
 * Implementation of the Graph interface.
 * @author mattfo
 *
 */
public class GraphImpl implements Graph {
	
	private Map<String, Node> nameNodeMap;
	private Map<Node, Set<Edge>> nodeEdgeMap;

	public GraphImpl() {
		nameNodeMap = new HashMap<String, Node>();
		nodeEdgeMap = new HashMap<Node, Set<Edge>>();
	}

	/**
	 * Returns the node in the graph with the given name.
	 */
	public Node getNodeByName(String name) {
		return nameNodeMap.get(name);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Gets all of the edges in this graph that can be traversed from the given node. 
	 */
	public Collection<Edge> getEdgesForNode(Node n) {
		Set<Edge> result = nodeEdgeMap.get(n);
		return result != null ? Collections.unmodifiableCollection(nodeEdgeMap.get(n)) : Collections.EMPTY_SET;
	}
	/**
	 * Adds the argument Node to the graph.  For this implementation, it throws an IllegalArgumentException
	 * if the node is already in the graph.
	 */
	public void addNode(Node n) {
		if (nameNodeMap.containsKey(n.getName())) {
			throw new IllegalArgumentException("Can't add Node already in Graph");
		}
		nameNodeMap.put(n.getName(), n);
	}
	/**
	 * Removes the argument Node from the graph, and returns any corresponding Edges that
	 * refer to the argument Node.  These Edges are also removed from the graph.
	 */
	public Collection<Edge> removeNode(Node n) {
		Set<Edge> edgesToRemove = nodeEdgeMap.get(n); 
		for (Edge e: edgesToRemove) {
			Node otherNode = e.getFromNode().equals(n) ? e.getToNode() : e.getFromNode();
			nodeEdgeMap.get(otherNode).remove(e);
		}
		nodeEdgeMap.remove(n);

		nameNodeMap.remove(n.getName());
		return edgesToRemove;
	}
	
	private void addToNodeEdgeMap(Node n, Edge e) {
		if (nodeEdgeMap.containsKey(n)) {
			nodeEdgeMap.get(n).add(e);
		}
		else {
			Set<Edge> edgeSet = new HashSet<Edge>();
			edgeSet.add(e);
			nodeEdgeMap.put(n, edgeSet); 
		}
		
	}
	/**
	 * Adds the argument Edge to the graph.  For this implementation, it throws an IllegalArgumentException
	 * if the Edge is already in the graph.  Also, the nodes referred to by the argument Edge must exist
	 * in the graph.
	 */
	public void addEdge(Edge e) {
		// verify edge is not in graph already
		if (nodeEdgeMap.containsKey(e.getFromNode())) {
			for (Edge edge: nodeEdgeMap.get(e.getFromNode())) {
				if (edge.equals(e)) {
					throw new IllegalArgumentException("Can't add Edge already in Graph");
				}
			}
		}
		// verify edge nodes _are_ in graph
		if (!nameNodeMap.containsKey(e.getFromNode().getName()) || !nameNodeMap.containsKey(e.getToNode().getName())) {
			throw new IllegalArgumentException("Can't add Edge with Nodes not in Graph");
		}
		addToNodeEdgeMap(e.getFromNode(), e);
		addToNodeEdgeMap(e.getToNode(), e);
	}
	/**
	 * Removed the argument Edge from the graph.
	 */
	public void removeEdge(Edge e) {
		nodeEdgeMap.get(e.getFromNode()).remove(e);
		nodeEdgeMap.get(e.getToNode()).remove(e);
	}
	/**
	 * Returns true iff there is a path from the first Node argument to the second Node argument.
	 */
	public boolean areConnected(Node n1, Node n2) {
		return shortestPath(n1, n2) != null;
	}
	/**
	 * Returns a List of Nodes that represents the shortest path from the first Node argument
	 * to the second Node argument, or null if no such path exists.  
	 */
	public List<Node> shortestPath(Node from, Node to) {
		HashMap<Node, Node> visitedNodeParentMap = new HashMap<Node, Node>();
		LinkedList<Node> nodesToVisit = new LinkedList<Node>();
		
		nodesToVisit.addLast(from);
		visitedNodeParentMap.put(from, null);
		while (!nodesToVisit.isEmpty()) {
			Node n = nodesToVisit.removeFirst();
			if (n.equals(to)) {
				// there is a path, unwind it from the visitedNodeParentMap
				LinkedList<Node> resultPath = new LinkedList<Node>();
				while (n != null) {
					resultPath.push(n);
					n = visitedNodeParentMap.get(n);
				}
				return resultPath;
			}
			Collection<Edge> edgeList = getEdgesForNode(n);
			for (Edge e: edgeList) {
				Node otherNode = e.getFromNode().equals(n) ? e.getToNode() : e.getFromNode();
				if (!visitedNodeParentMap.containsKey(otherNode)) {
					nodesToVisit.addLast(otherNode);
					visitedNodeParentMap.put(otherNode, n);
				}
			}
		}
		return null;
	}

}
