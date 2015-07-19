/**
 * 
 */
package com.main.gedcom.helper;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * @author MaRoy
 *
 */
public class XMLHelper {

	/**
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static Document createXMLDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        Document xmlDocument = builder.newDocument();
        return xmlDocument;
    }
	
	/**
	 * @param document
	 * @param outputFile
	 */
	public static void writeDocumentToFile(Document document, File outputFile) {
        if (document == null || outputFile == null) {
            throw new IllegalArgumentException("Incorrect usage, Document or File to be written is invalid");
        }
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
