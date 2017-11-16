package com.mford.graphlib;

public class NodeImpl implements Node {
	
	private String name;

	public NodeImpl(String nodeName) {
		if (nodeName == null || nodeName.trim().isEmpty()) {
			throw new IllegalArgumentException("nodeName must have non-whitespace characters");
		}
		this.name = nodeName;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals (Object o) {
		if (o == null)
			return false;
	    if (!(o instanceof Node))
	    	return false;
	    Node thatNode = (Node)o;
	    return this.name.equals(thatNode.getName());
	}
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	@Override
	public String toString() {
		return name;
	}
}
