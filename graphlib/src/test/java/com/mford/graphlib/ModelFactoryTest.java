package com.mford.graphlib;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for factory class.  Some edge and node implementation tests.
 */
public class ModelFactoryTest extends TestCase
{

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ModelFactoryTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( ModelFactoryTest.class );
    }
    
    public void testNodeNullName() {
    	try {
    		ModelFactory.getNode(null);
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
    }
    public void testNodeEmptyName() {
    	try {
    		ModelFactory.getNode("");
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
    }
    public void testNodeWhitespaceName() {
    	try {
    		ModelFactory.getNode(" ");
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
    }
    public void testGetNodeAndToString() {
    	Node n = ModelFactory.getNode("Chicago");
    	assertEquals("Chicago", n.getName());
    	assertEquals("Chicago", n.toString());
    }
    public void testEdgeWithNullNode() {
    	try {
    		NodeImpl n = new NodeImpl("Detroit");
    		ModelFactory.getEdge(null, n);
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
    }
    public void testEdgeWithNullOtherNode() {
    	try {
    		NodeImpl n = new NodeImpl("Cleveland");
    		ModelFactory.getEdge(n, null);
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
    }
    public void testEdgeWithNullNodes() {
    	try {
    		ModelFactory.getEdge(null, null);
    		fail("Should have thrown IllegalArgumentException");
    	}
    	catch (IllegalArgumentException iae) {
    	}
    }
    public void testGetEdgeAndToString() {
		NodeImpl n1 = new NodeImpl("Detroit");
		NodeImpl n2 = new NodeImpl("Chicago");
		Edge e = ModelFactory.getEdge(n1, n2);
		assertEquals(n2, e.getFromNode()); // Chicago is alphabetically first
		assertEquals(n1, e.getToNode());
		assertEquals("Chicago <-> Detroit", e.toString());
    }
    public void testGetLoopEdge() {
		NodeImpl n1 = new NodeImpl("Detroit");
		Edge e = ModelFactory.getEdge(n1, n1);
		assertEquals(n1, e.getFromNode());
		assertEquals(n1, e.getToNode());
    }
    public void testGetGraph() {
		assertTrue(ModelFactory.getGraph() instanceof Graph);
    }
}

