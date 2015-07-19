/**
 * 
 */
package com.test.gedcom.parser;

import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.main.gedcom.constants.GedcomConstants;
import com.main.gedcom.helper.XMLHelper;

/**
 * @author MaRoy
 *
 */
public class XMLHelperTest {

	Document xmlDocument;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		xmlDocument = XMLHelper.createXMLDocument();
	}
	
	@Test
	public void testWriteDocumentToFile() {

		String outputFile = GedcomConstants.DEFAULT_OUTPUT_FILE;
		File output = new File(outputFile);

		try{
			XMLHelper.writeDocumentToFile(xmlDocument, output);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		Assert.assertTrue(output.exists() && (output.length() != 0));
	}

	@Test
	public void testWriteDocumentToFileException() {

		Throwable e = null;

		try{
			XMLHelper.writeDocumentToFile(xmlDocument, null);
		}
		catch(Exception ex){
			e = ex;
		}

		Assert.assertTrue(e instanceof IllegalArgumentException);
	}

}
