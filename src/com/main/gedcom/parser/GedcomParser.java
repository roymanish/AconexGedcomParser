/**
 * 
 */
package com.main.gedcom.parser;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.main.gedcom.constants.GedcomConstants;
import com.main.gedcom.data.Node;
import com.main.gedcom.helper.NodeParser;

/** This is the main implementation class for GEDCOM parser. This class converts
 * 	the input document into Tree data structure to maintain the hierarchy.
 *  And writes the document to an XML file.
 * 	This class will only parse GEDCOM format documents. For any other format 
 * 	document separate implementation of {@link ATextToXMLParser} should be created.
 *	@author MaRoy
 */
public class GedcomParser extends ATextToXMLParser{

	private Node rootNode;
	private Node currentNode;
	private NodeParser nodeParser;

	public GedcomParser(){

		nodeParser = new NodeParser();
	}

	public NodeParser getNodeParser(){
		return nodeParser;
	}

	/* (non-Javadoc)
	 * @see com.main.gedcom.parser.ATextToXMLParser#process(java.lang.String)
	 */
	@Override
	public void process(String inputLine) {

		int level = Integer.parseInt(String.valueOf(inputLine.trim().charAt(0)));		
		if(level == 0){
			rootNode = nodeParser.createRootNode(inputLine);
			currentNode = rootNode;
		}
		else{

			Node child = nodeParser.createChildNode(inputLine);

			if(level > currentNode.getLevel()){
				currentNode.addChild(child);
				child.setParent(currentNode);
				currentNode = child;
			}
			else if(nodeParser.getNodeAtLevel(rootNode, level) != null){
				Node parent = nodeParser.getNodeAtLevel(rootNode, level).getParent();
				parent.addChild(child);
				child.setParent(parent);
				currentNode = child;
			}
			else{
				currentNode.getParent().addChild(child);
				currentNode = child;
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.main.gedcom.parser.ATextToXMLParser#populateXMLDocument(org.w3c.dom.Document)
	 */
	@Override
	public void populateXMLDocument(Document xmlDocument){

		Element root = xmlDocument.createElement(GedcomConstants.ROOT);
		xmlDocument.appendChild(root);

		if(nodeParser.getNodeTreeList() != null && !nodeParser.getNodeTreeList().isEmpty()){
			for(Node parentNode : nodeParser.getNodeTreeList()){

				if(parentNode.getTagName() != null && !parentNode.getTagName().isEmpty()){
					Element parentElement = xmlDocument.createElement(parentNode.getTagName());
					parentElement.setAttribute(GedcomConstants.ATTRIBUTE_NAME_ID, parentNode.getValue());
					addChildren(parentElement, parentNode.getChildList(), xmlDocument);
					root.appendChild(parentElement);
				}

			}
		}
	}

	/**
	 * @param parentElement
	 * @param childList
	 * @param xmlDocument
	 */
	private void addChildren(Element parentElement, List<Node> childList,
			Document xmlDocument) {

		if(childList == null)
			return;

		for(Node child : childList){

			if(child.getTagName() != null && !child.getTagName().isEmpty()){
				Element childElement = xmlDocument.createElement(child.getTagName());

				if(child.isLeafNode())
					childElement.setTextContent(child.getValue());
				else if(child.getValue() != null && !child.getValue().isEmpty())
					childElement.setAttribute(GedcomConstants.ATTRIBUTE_NAME_VALUE, child.getValue());

				addChildren(childElement, child.getChildList(), xmlDocument);
				parentElement.appendChild(childElement);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.main.gedcom.parser.ATextToXMLParser#isValidContent(java.lang.String)
	 */
	@Override
	public boolean isValidContent(String input) {

		try {

			Integer.parseInt(String.valueOf(input.trim().charAt(0)));
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Invalid GEDCOM File!!");
			return false;
		}
	}

}
