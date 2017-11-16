package com.mford.graphlib;
/**
 * Implementation of Node interface.  This is currently little more than a String wrapper.
 * @author mattfo
 *
 */
public class NodeImpl implements Node {
	
	private String name;
	/**
	 * Creates a Node with the given name.  For this implementation, the name must have
	 * non-whitespace characters.  Nodes with the same name are equal to each other.
	 * 
	 * @param nodeName
	 */
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
