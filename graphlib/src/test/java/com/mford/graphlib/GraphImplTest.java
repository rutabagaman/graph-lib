package com.mford.graphlib;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for graph implementation
 */
public class GraphImplTest extends TestCase
{
	private GraphImpl graph;
	Node nodeList[] = new Node[7];
	
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
    
    @Before
    public void setUp() {
    	// add seven nodes named "0" to "6"
    	graph = new GraphImpl();
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
    }

    public void testGraphAreConnected() {
    	assertTrue(graph.areConnected(nodeList[1], nodeList[6]));
    }
    
    public void testGraphAreNotConnected() {
    	assertFalse(graph.areConnected(nodeList[0], nodeList[6]));
    }

    public void testGraphAreNotConnected2() {
    	assertFalse(graph.areConnected(nodeList[6], nodeList[0]));
    }
    
    public void testGraphShortestPath() {
    	List<Node> result = graph.shortestPath(nodeList[1], nodeList[6]);
    	List<Node> expectedResult = Arrays.asList(nodeList[1], nodeList[5], nodeList[4], nodeList[6]);
    	assertEquals(expectedResult, result);
    }
    public void testGetNodeByName() {
    	for(int i=0; i<=6; i++) {
    		String nodeName = String.valueOf(i);
    		assertEquals(nodeList[i], graph.getNodeByName(nodeName));
    	}
    }
}

