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
	
	@SuppressWarnings("unchecked")
	public Collection<Edge> getEdgesForNode(Node n) {
		Set<Edge> result = nodeEdgeMap.get(n);
		return result != null ? Collections.unmodifiableCollection(nodeEdgeMap.get(n)) : Collections.EMPTY_SET;
	}

	public void addNode(Node n) {
		if (nameNodeMap.containsKey(n.getName())) {
			throw new IllegalArgumentException("Can't add Node already in Graph");
		}
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

	public void removeEdge(Edge e) {
		nodeEdgeMap.remove(e.getFromNode()).remove(e);
		nodeEdgeMap.remove(e.getToNode()).remove(e);
	}

	public boolean areConnected(Node n1, Node n2) {
		return shortestPath(n1, n2) != null;
	}
	
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
