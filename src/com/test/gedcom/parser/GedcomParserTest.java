package com.test.gedcom.parser;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.main.gedcom.constants.GedcomConstants;
import com.main.gedcom.dao.GedcomDao;
import com.main.gedcom.data.Node;
import com.main.gedcom.parser.GedcomParser;

public class GedcomParserTest {
	
	
	private GedcomDao gedcomDao;
	
	private GedcomParser instance;
	
	private File inputFile;
	
	@Before
	public void initialize(){
		
		gedcomDao = Mockito.mock(GedcomDao.class);
		Mockito.when(gedcomDao.getFileId()).thenReturn(20);
		Mockito.when(gedcomDao.getFileSize()).thenReturn("200kb");
		
		instance = new GedcomParser();
		String inputTextFile = GedcomConstants.DEFAULT_INPUT_FILE;
		inputFile = new File(inputTextFile);
	}

	@Test
	public void testParseDocumentValid(){
		
		System.out.println(gedcomDao.getFileId());
		System.out.println(gedcomDao.getFileSize());
		String inputTextFile = GedcomConstants.DEFAULT_INPUT_FILE;
		String outputFile = GedcomConstants.DEFAULT_OUTPUT_FILE;
		instance.parseDocument(inputTextFile, outputFile);
	}
	
	@Test
	public void testParseDocumentInvalid(){
		
		System.out.println(gedcomDao.getFileId());
		System.out.println(gedcomDao.getFileSize());
		String inputTextFile = GedcomConstants.DEFAULT_INPUT_FILE_INVALID;
		String outputFile = GedcomConstants.DEFAULT_OUTPUT_FILE+"_invalid";
		instance.parseDocument(inputTextFile, outputFile);
	}
	
	@Test
	public void testParseDocumentWith3rdLevelHierarcy(){
		
		System.out.println(gedcomDao.getFileId());
		System.out.println(gedcomDao.getFileSize());
		String inputTextFile = GedcomConstants.DEFAULT_INPUT_FILE_TEST1;
		String outputFile = GedcomConstants.DEFAULT_OUTPUT_FILE;
		instance.parseDocument(inputTextFile, outputFile);
	}
	
	@Test
	public void testValidateInputFileValid() {
		
		boolean isValidFile = instance.validateInputFile(inputFile);
		Assert.assertTrue(isValidFile);
	}
	
	@Test
	public void testValidateInputFileInvalid() {
		
		inputFile = new File(GedcomConstants.EMPTY_STRING);
		boolean isValidFile = instance.validateInputFile(inputFile);
		Assert.assertTrue(!isValidFile);
	}
	
	@Test
	public void testFileToNodeTreeCreation(){
		
		instance.processInputTextFile(inputFile);
		List<Node> nodeTreeList = instance.getNodeParser().getNodeTreeList();
		Assert.assertTrue(nodeTreeList.size() > 0);
	}
	
	@Test
	public void testGenerateOutputFileWithData(){
		
		String outputFile = GedcomConstants.DEFAULT_OUTPUT_FILE+"_With_Data";
		File output = new File(outputFile);
		instance.processInputTextFile(inputFile);
		instance.generateOutputFile(output);
		Assert.assertTrue(output.exists() && output.canRead() && (output.length() != 0));
	}
	
	@Test
	public void testGenerateOutputFileWithoutData(){
		
		String outputFile = GedcomConstants.DEFAULT_OUTPUT_FILE+"_Without_Data";
		File output = new File(outputFile);
		instance.generateOutputFile(output);
		Assert.assertTrue(output.exists() && output.canRead() && (output.length() != 0));
	}
}
