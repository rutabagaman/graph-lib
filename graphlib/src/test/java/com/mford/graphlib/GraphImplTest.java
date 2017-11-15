package com.mford.graphlib;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for graph implementation
 */
public class GraphImplTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GraphImplTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( GraphImplTest.class );
    }

    public void testGraphAreConnected() {
    	// add seven nodes named "0" to "6"
    	Graph graph = new GraphImpl();
    	Node nodeList[] = new Node[7];
    	for(int i=0; i<=6; i++) {
    		nodeList[i] = new NodeImpl(String.valueOf(i));
    		graph.addNode(nodeList[i]);
    	}
    	// node "0" is not connected, create edges for the rest
    	graph.addEdge(new EdgeImpl(nodeList[1], nodeList[2]));
    	graph.addEdge(new EdgeImpl(nodeList[1], nodeList[5]));
    	graph.addEdge(new EdgeImpl(nodeList[2], nodeList[3]));
    	graph.addEdge(new EdgeImpl(nodeList[2], nodeList[5]));
    	graph.addEdge(new EdgeImpl(nodeList[3], nodeList[4]));
    	graph.addEdge(new EdgeImpl(nodeList[4], nodeList[5]));
    	graph.addEdge(new EdgeImpl(nodeList[4], nodeList[6]));
    	
    	assertTrue(graph.areConnected(nodeList[1], nodeList[6]));
    }
}

