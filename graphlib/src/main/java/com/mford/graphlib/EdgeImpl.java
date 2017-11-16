package com.mford.graphlib;

public class EdgeImpl implements Edge {

	private Node fromNode;
	private Node toNode;
	
	public EdgeImpl(Node from, Node to) {
		if ((from == null) || (to == null)) {
			throw new IllegalArgumentException("Nodes must be non-null");
		}
		// we allow fromNode and toNode to be the same node
		if (from.getName().compareTo(to.getName()) <= 0) {
			// from node name is lexicographically earlier or equal to to node name
			this.fromNode = from;
			this.toNode = to;
		}
		else {
			this.fromNode = to;
			this.toNode = from;
		}
	}

	public Node getFromNode() {
		return fromNode;
	}

	public Node getToNode() {
		return toNode;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o == null)
			return false;
	    if (!(o instanceof Edge))
	    	return false;
	    Edge thatEdge = (Edge)o;
	    return this.toNode.equals(thatEdge.getToNode()) && this.fromNode.equals(thatEdge.getFromNode());
	}
	@Override
	public int hashCode() {
		// we don't use ^ so the hashCode won't always be 0 if the edge points back to the same node
		return this.toNode.hashCode() * this.fromNode.hashCode();
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(32);
		sb.append(toNode);
		sb.append(" <-> ");
		sb.append(fromNode);
		return sb.toString();
	}

}
