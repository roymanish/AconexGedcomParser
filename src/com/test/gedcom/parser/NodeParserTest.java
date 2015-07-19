/**
 * 
 */
package com.test.gedcom.parser;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.main.gedcom.data.Node;
import com.main.gedcom.helper.NodeParser;

/**
 * @author MaRoy
 *
 */
public class NodeParserTest {

	NodeParser parser;
	
	@Before
	public void initialize(){
		
		parser = new NodeParser();
	}
	
	@Test
	public void testCreateRootNode() {
		
		String inputLine = "0 @I0001@ INDI";
		Node rootNode = parser.createRootNode(inputLine);
		Assert.assertNotNull(rootNode.getTagName());
		Assert.assertNotNull(rootNode.getValue());
	}
	
	@Test
	public void testCreateChildNode(){
		
		String inputLine = "1 NAME Elizabeth Alexandra Mary /Windsor/";
		Node rootNode = parser.createChildNode(inputLine);
		Assert.assertNotNull(rootNode.getTagName());
		Assert.assertNotNull(rootNode.getValue());
		
	}
	
	@Test
	public void testGetNodeAtLevel(){
		
		String inputLine = "0 @I0001@ INDI";
		Node rootNode = parser.createRootNode(inputLine);
		
		String inputLine1 = "1 NAME Elizabeth Alexandra Mary /Windsor/";
		Node child1Lev1 = parser.createChildNode(inputLine1);
		child1Lev1.setParent(rootNode);
		rootNode.addChild(child1Lev1);
		
		String inputLine2 = "1 SEX F";
		Node child2Lev1 = parser.createChildNode(inputLine2);
		child2Lev1.setParent(rootNode);
		rootNode.addChild(child2Lev1);
		
		String inputLine3 = "2 DATE 21 Apr 1926";
		Node childLev2 = parser.createChildNode(inputLine3);
		childLev2.setParent(child2Lev1);
		child2Lev1.addChild(childLev2);
		
		String inputLine4 = "3 PLAC London";
		Node childLev3 = parser.createChildNode(inputLine4);
		childLev3.setParent(childLev2);
		childLev2.addChild(childLev3);
		
		Assert.assertEquals(child2Lev1.getValue(), parser.getNodeAtLevel(rootNode, 1).getValue());
		Assert.assertEquals(childLev2.getValue(), parser.getNodeAtLevel(rootNode, 2).getValue());
		
		
	}
	
	@Test
	public void testGetNodeAtLevelForNull(){
		
		String inputLine = "0 @I0001@ INDI";
		Node rootNode = parser.createRootNode(inputLine);
		
		Assert.assertNull(parser.getNodeAtLevel(rootNode, 1));
		
	}

}
