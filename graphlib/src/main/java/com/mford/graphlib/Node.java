package com.mford.graphlib;

import java.util.*;

public interface Node {
	String getName();
	Iterator<Edge> getEdges();	
}
