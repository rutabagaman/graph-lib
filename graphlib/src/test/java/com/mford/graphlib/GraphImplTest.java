package com.mford.graphlib;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for graph, edge and node implementations
 */
public class GraphImplTest extends TestCase
{
	private GraphImpl graph;
	Node nodeList[] = new Node[7];
	Edge edgeList[] = new Edge[7];

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
    	edgeList[0] = new EdgeImpl(nodeList[1], nodeList[2]);
    	edgeList[1] = new EdgeImpl(nodeList[1], nodeList[5]);
    	edgeList[2] = new EdgeImpl(nodeList[2], nodeList[3]);
    	edgeList[3] = new EdgeImpl(nodeList[2], nodeList[5]);
    	edgeList[4] = new EdgeImpl(nodeList[3], nodeList[4]);
    	edgeList[5] = new EdgeImpl(nodeList[4], nodeList[5]);
    	edgeList[6] = new EdgeImpl(nodeList[4], nodeList[6]);
    	for(int i=0; i<=6; i++) {
    		graph.addEdge(edgeList[i]);
    	}
    }
    
    public void testAddDuplicateNode() {
    	try {
    		graph.addNode(nodeList[5]);
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
    }
    public void testAddDuplicateEdge() {
    	try {
    		graph.addEdge(edgeList[5]);
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
    }
    public void testAddEdgeWithNonExistentNodes() {
    	try {
    		graph.addEdge(new EdgeImpl(new NodeImpl("A"), new NodeImpl("B")));
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
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
    public void testGetNodeByNameNotFound() {
    	assertEquals(null, graph.getNodeByName("A"));
    }
    public void testGetEdgesForNodes() {
    	Collection<Edge> result = graph.getEdgesForNode(nodeList[2]);
    	assertTrue(result.size() == 3);
    	assertTrue(result.contains(edgeList[0]));
    	assertTrue(result.contains(edgeList[2]));
    	assertTrue(result.contains(edgeList[3]));
    }
    public void testGetEdgesForNodesNotFound() {
    	Collection<Edge> result = graph.getEdgesForNode(nodeList[0]);
    	assertTrue(result != null);
    	assertTrue(result.isEmpty());
    }
    public void testRemoveNode() {
    	Collection<Edge> removedEdges = graph.removeNode(nodeList[5]);
    	List<Node> result = graph.shortestPath(nodeList[1], nodeList[6]);
    	List<Node> expectedResult = Arrays.asList(nodeList[1], nodeList[2], nodeList[3], nodeList[4], nodeList[6]);
    	assertNotNull(result);
    	assertEquals(expectedResult, result);
    	Collection<Edge> expectedEdgesRemoved = new HashSet<Edge>(Arrays.asList(edgeList[1],edgeList[3],edgeList[5]));
    	assertEquals(expectedEdgesRemoved, removedEdges);
    }
    public void testRemoveEdge() {
    	graph.removeEdge(edgeList[5]);
    	List<Node> result = graph.shortestPath(nodeList[1], nodeList[6]);
    	List<Node> expectedResult = Arrays.asList(nodeList[1], nodeList[2], nodeList[3], nodeList[4], nodeList[6]);
    	assertNotNull(result);
    	assertEquals(expectedResult, result);
    }
}

