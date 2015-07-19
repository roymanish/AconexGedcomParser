/**
 * 
 */
package com.main.gedcom.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.main.gedcom.helper.XMLHelper;

/**An abstraction created for various types of input files. This class's implementation
 * will be different for different type of input file. Here the only implementation
 * we have is GEDCOM format text document. The output from this class will always be an XML.
 * @author MaRoy
 *
 */
public abstract class ATextToXMLParser implements IDocumentParser{

	/** Handles all the processing of converting the text file into some data-structure
	 * 	for storage. Should be implemented by concrete sub-classes.
	 * @param inputLine
	 */
	public abstract void process(String inputLine);

	/** 
	 * Writes the data-structure created to an XML document. 
	 * Should be implemented by concrete sub-classes. Implementation will vary based
	 * on the choice of data-structure for storage.
	 * @param xmlDocument
	 */
	public abstract void populateXMLDocument(Document xmlDocument);
	
	/** Validates the content of the input file based on the format that
	 * 	the sub-classes intend to support.Should be implemented by concrete sub-classes.
	 * @param input
	 * @return boolean
	 */
	public abstract boolean isValidContent(String inputLine);

	
	
	/* (non-Javadoc)
	 * @see com.main.gedcom.parser.IDocumentParser#parseDocument(java.lang.String, java.lang.String)
	 */
	@Override
	public void parseDocument(String inputTextFile, String outputXMLFile){

		File inputFile = new File(inputTextFile);
		File outputFile = new File(outputXMLFile);

		if(validateInputFile(inputFile)){

			processInputTextFile(inputFile);
			
			generateOutputFile(outputFile);
		}
	}

	/* (non-Javadoc)
	 * @see com.main.gedcom.parser.IDocumentParser#validateInputFile(java.io.File)
	 */
	@Override
	public boolean validateInputFile(File inputFile){

		if(inputFile.exists() && inputFile.canRead() && (inputFile.length() !=0)){

			System.out.println("Valid File :: Parsing document.");
			return true;
		}

		System.out.println("######## Please give a valid input File #########");
		return false;
	}

	/** Reads the input file line by line and passes the line to the implementation
	 * 	class for further processing.
	 * @param file
	 */
	public void processInputTextFile(File file){

		try {
			BufferedReader buf = new BufferedReader(new FileReader(file));

			String line;
			while ((line = buf.readLine()) != null) {

				line = line.trim();
				
				if(isValidContent(line))
					process(line);
				else
					break;
			}
			System.out.println("Done!!");
		} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.main.gedcom.parser.IDocumentParser#generateOutputFile(java.io.File)
	 */
	@Override
	public void generateOutputFile(File outputFile){

		Document xmlDocument = null;
		
		try{
			xmlDocument = XMLHelper.createXMLDocument();
		}
		catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}
		 
		populateXMLDocument(xmlDocument);
		
		XMLHelper.writeDocumentToFile(xmlDocument, outputFile);
	}

}
