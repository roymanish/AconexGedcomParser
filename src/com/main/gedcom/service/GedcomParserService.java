package com.main.gedcom.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.main.gedcom.constants.GedcomConstants;
import com.main.gedcom.parser.GedcomParser;
import com.main.gedcom.parser.IDocumentParser;

/**
 * 
 */

/**
 * @author MaRoy
 *
 */
@Path("/gedcom")
public class GedcomParserService {

	private static String inputFile = GedcomConstants.DEFAULT_INPUT_FILE;
	private static String outputFile = GedcomConstants.DEFAULT_OUTPUT_FILE;
	
	@GET
	@Path("/parseDocument")
	public void parseDocument(@QueryParam("filePath") String inputFilePath){
		
		IDocumentParser parser = new GedcomParser();
		
		if(inputFilePath != null && !inputFilePath.isEmpty())
			inputFile = inputFilePath;
		
		parser.parseDocument(inputFile, outputFile);
	}
}
