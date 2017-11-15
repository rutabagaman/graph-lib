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
		return connectedPath(n1, n2) != null;
	}
	
	public List<Node> connectedPath(Node from, Node to) {
		HashMap<Node, Node> visitedNodeParentMap = new HashMap<Node, Node>();
		LinkedList<Node> nodesToVisit = new LinkedList<Node>();
		
		nodesToVisit.push(from);
		visitedNodeParentMap.put(from, null);
		while (!nodesToVisit.isEmpty()) {
			Node n = nodesToVisit.pop();
			if (n.equals(to)) {
				// there is a path, unwind it from the visitedNodeParentMap
				LinkedList<Node> resultPath = new LinkedList<Node>();
				while (!n.equals(from)) {
					resultPath.push(n);
					n = visitedNodeParentMap.get(n);
				}
				resultPath.push(from);
				return resultPath;
			}
			Collection<Edge> edgeList = getEdgesForNode(n);
			for (Edge e: edgeList) {
				Node otherNode = e.getFromNode().equals(n) ? e.getToNode() : e.getFromNode();
				if (!visitedNodeParentMap.containsKey(otherNode)) {
					nodesToVisit.push(otherNode);
					visitedNodeParentMap.put(otherNode, n);
				}
			}
		}
		return null;
	}

}
