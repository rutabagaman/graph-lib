package com.mford.graphlib;

public interface Edge {
	boolean isDirected();
	int getWeight();
	Node getFromNode(); // if not directed edge, this is node with name alphabetically first
	Node getToNode();
}
