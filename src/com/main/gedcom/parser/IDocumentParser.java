/**
 * 
 */
package com.main.gedcom.parser;

import java.io.File;

/** 
 * Interface exposed to the client for any kind of document parsing.
 * Its implementation will vary based on the type of input document provided and
 * type of output document expected. For example here input provided in text document
 * and output expected is XML. {@link ATextToXMLParser}
 * @author MaRoy
 *
 */
public interface IDocumentParser {

   /** Validates the input file for readability, accessibility and existence.
	 * @param inputFile
	 * @return boolean
	 */
	public boolean validateInputFile(File inputFile);
	
	/**Interface method to take care of all the internal logic of
	 * parsing the document, converting it into some storage data-structure and finally
	 * writing that to an output file. Its implementation vary based on the type of input 
	 * provided and type of output expected. In this case we have {@link ATextToXMLParser}
	 * where input is of type text and output expected is of type XML.
	 * @param inputFile
	 * @param outputFile
	 */
	public void parseDocument(String inputFile, String outputFile);
	
	/**Generates the output from the parsed data from the input file. 
	 * Create XML document. Populate it with data. Write it to a file.
	 * Its implementation will vary based on the parsed input data and type 
	 * of output file expected.
	 * @param output
	 */
	public void generateOutputFile(File output);
	
	
}
