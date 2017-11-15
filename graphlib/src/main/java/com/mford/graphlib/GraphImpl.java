package com.mford.graphlib;

import java.util.*;

public class GraphImpl implements Graph {
	
	private Map<String, Node> nameNodeMap;
	private Map<Node, Set<Edge>> nodeEdgeMap;

	public GraphImpl() {
		nameNodeMap = new HashMap<String, Node>();
		nodeEdgeMap = new HashMap<Node, Set<Edge>>();
	}

	public Node getNodeByName(String name) {
		return nameNodeMap.get(name);
	}
	
	public Collection<Edge> getEdgesForNode(Node n) {
		return Collections.unmodifiableCollection(nodeEdgeMap.get(n));
	}

	public void addNode(Node n) {
		nameNodeMap.put(n.getName(), n);
	}

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
	
	public void addEdge(Edge e) {
		addToNodeEdgeMap(e.getFromNode(), e);
		addToNodeEdgeMap(e.getToNode(), e);
	}

	public void removeEdge(Edge e) {
		nodeEdgeMap.remove(e.getFromNode()).remove(e);
		nodeEdgeMap.remove(e.getToNode()).remove(e);
	}

	public boolean areConnected(Node n1, Node n2) {
		Set<Node> visitedNodes = new HashSet<Node>();
		LinkedList<Node> nodesToVisit = new LinkedList<Node>();
		
		nodesToVisit.push(n1);
		while (!nodesToVisit.isEmpty()) {
			Node n = nodesToVisit.pop();
			visitedNodes.add(n);
			if (n == n2) {
				return true;
			}
			Collection<Edge> edgeList = getEdgesForNode(n);
			for (Edge e: edgeList) {
				Node otherNode = e.getFromNode().equals(n) ? e.getToNode() : e.getFromNode();
				if (!visitedNodes.contains(otherNode)) {
					nodesToVisit.push(otherNode);
				}
			}
		}
		return false;
	}

}
