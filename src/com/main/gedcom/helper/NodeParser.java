/**
 * 
 */
package com.main.gedcom.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.main.gedcom.constants.GedcomConstants;
import com.main.gedcom.data.Node;

/**
 * @author MaRoy
 *
 */
public class NodeParser {
	
	private List<Node> nodeTreeList;
	
	

	/**
	 * @return the nodeTreeList
	 */
	public List<Node> getNodeTreeList() {
		return nodeTreeList;
	}

	/**
	 * @param nodeTreeList the nodeTreeList to set
	 */
	public void setNodeTreeList(List<Node> nodeTreeList) {
		this.nodeTreeList = nodeTreeList;
	}
	
	public void addNode(Node rootNode){
		
		if(this.nodeTreeList == null)
			this.nodeTreeList = new ArrayList<Node>();
		this.nodeTreeList.add(rootNode);
	}

	public Node createRootNode(String inputLine){

		StringTokenizer st = new StringTokenizer(inputLine);

		int level = Integer.parseInt(st.nextToken());
		String id = st.hasMoreTokens() ? st.nextToken() : GedcomConstants.EMPTY_STRING;
		String tagName = st.hasMoreTokens() ? st.nextToken() : GedcomConstants.EMPTY_STRING;
		Node rootNode = new Node(level, tagName, id);
		rootNode.setParent(null);
		this.addNode(rootNode);
		return rootNode;
	}

	public Node createChildNode(String inputLine){

		StringTokenizer st = new StringTokenizer(inputLine);

		int level = Integer.parseInt(st.nextToken());

		String tagName = st.hasMoreTokens() ? st.nextToken() : GedcomConstants.EMPTY_STRING;

		StringBuilder value = new StringBuilder();

		while(st.hasMoreTokens()){
			value.append(st.nextToken()+GedcomConstants.SINGLE_SPACE_STRING);
		}

		Node node = new Node(level, tagName, value.toString().trim());

		return node;
	}

	public Node getNodeAtLevel(Node rootNode, int level){

		Node node = null;
		
		if(rootNode.getChildList() == null || rootNode.getChildList().isEmpty())
			return null;
		
		for(Node child : rootNode.getChildList()){

			if(level == child.getLevel())
				node = child;
			else{
				node = getNodeAtLevel(child, level);
			}
		}
		return node;
	}
}
