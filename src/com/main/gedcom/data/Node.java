/**
 * 
 */
package com.main.gedcom.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaRoy
 *
 */
public class Node {

	private int level;
	private String tagName;
	private String value;
	private Node parent;
	private List<Node> childList;
	
	
	public Node() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Node(int level, String tagName, String value) {
		super();
		this.level = level;
		this.tagName = tagName;
		this.value = value;
	}
	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}
	
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param tagName the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/**
	 * @return the child
	 */
	public List<Node> getChildList() {
		return childList;
	}

	/**
	 * @param child the child to set
	 */
	public void setChildList(List<Node> childList) {
		this.childList = childList;
	}
	
	public void addChild(Node child){
		
		if(this.childList == null)
			this.childList = new ArrayList<Node>();
		this.childList.add(child);
	}
	
	public boolean isLeafNode(){
		
		if(this.childList == null || this.childList.isEmpty())
			return true;
		return false;
	}
	
	
}
