/**
 * 
 */
package com.main.gedcom.client;

import com.main.gedcom.constants.GedcomConstants;
import com.main.gedcom.parser.GedcomParser;
import com.main.gedcom.parser.IDocumentParser;

/**
 * @author MaRoy
 *
 */
public class GedcomClient {

	private static String inputFile = GedcomConstants.DEFAULT_INPUT_FILE;
	private static String outputFile = GedcomConstants.DEFAULT_OUTPUT_FILE;
	
	public static void main(String[] args) {
		
		if(args.length == 2){
			inputFile = args[0];
			outputFile = args[1];
		}
		else if(args.length == 1){
			
			inputFile = args[0];
		}
		
		IDocumentParser parser = new GedcomParser();
		parser.parseDocument(inputFile, outputFile);

	}

}
