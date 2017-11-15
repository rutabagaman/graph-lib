package com.mford.graphlib;

public interface Edge {
	// implementation has references to Nodes
	Node getFromNode(); // if not directed edge, this is node with name alphabetically first
	Node getToNode();
}
