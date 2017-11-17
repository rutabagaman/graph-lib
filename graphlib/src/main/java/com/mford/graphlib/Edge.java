package com.mford.graphlib;

public interface Edge {
	boolean isDirected();
	Node getFromNode();
	Node getToNode();
}
