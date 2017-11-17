package com.mford.graphlib;

public class EdgeImpl implements Edge {

	private boolean isDirected = false;
	private Node fromNode;
	private Node toNode;

	/**
	 * Create non directed Edge with two Nodes.  The nodes must be non-null.  For this 
	 * implementation the nodes could be the same node(creating a loop).  The from node
	 * will be the one with the lexicographically earlier name.
	 */
	public EdgeImpl(Node n1, Node n2) {
		this(n1, n2, false);
	}
	/**
	 * Create Edge with two Nodes.  The nodes must be non-null.  For this implementation
	 * the nodes could be the same node(creating a loop).  If the edge is not directed,
	 * the from node will be the one with the lexicographically earlier name.
	 * 
	 * @param from
	 * @param to 
	 * @param isDirected true if the edge is directed
	 */
	public EdgeImpl(Node from, Node to, boolean isDirected) {
		if ((from == null) || (to == null)) {
			throw new IllegalArgumentException("Nodes must be non-null");
		}
		// we allow fromNode and toNode to be the same node
		if (isDirected || from.getName().compareTo(to.getName()) <= 0) {
			// from node name is lexicographically earlier or equal to to node name
			this.fromNode = from;
			this.toNode = to;
			this.isDirected = isDirected;
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
	
	public boolean isDirected() {
		return isDirected;
	}
	
	@Override
	public boolean equals (Object o) {
		if (o == null)
			return false;
	    if (!(o instanceof Edge))
	    	return false;
	    Edge thatEdge = (Edge)o;
	    return this.toNode.equals(thatEdge.getToNode()) && 
	    	   this.fromNode.equals(thatEdge.getFromNode()) &&
	    	   (this.isDirected == thatEdge.isDirected());
	}
	@Override
	public int hashCode() {
		// we don't use ^ so the hashCode won't always be 0 if the edge points back to the same node
		return this.toNode.hashCode() * this.fromNode.hashCode() + (this.isDirected ? 0 : 1);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(32);
		sb.append(fromNode);
		if (isDirected) {
			sb.append(" -> ");
		}
		else {
			sb.append(" <-> ");
		}
		sb.append(toNode);
		return sb.toString();
	}

}
