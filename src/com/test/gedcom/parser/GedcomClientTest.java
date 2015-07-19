package com.test.gedcom.parser;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import com.main.gedcom.client.GedcomClient;
import com.main.gedcom.constants.GedcomConstants;

public class GedcomClientTest {

	@Test
	public void testClientWithArguments() {
		String[] args = new String[]{GedcomConstants.DEFAULT_INPUT_FILE,GedcomConstants.DEFAULT_OUTPUT_FILE};
		GedcomClient.main(args);
		File file = new File(GedcomConstants.DEFAULT_OUTPUT_FILE);
		Assert.assertTrue(file.exists()&&file.canRead()&&(file.length()!=0));
	}
	
	@Test
	public void testClientWithoutArguments() {
		String[] args = new String[]{};
		GedcomClient.main(args);
		File file = new File(GedcomConstants.DEFAULT_OUTPUT_FILE);
		Assert.assertTrue(file.exists()&&file.canRead()&&(file.length()!=0));
	}
	
	@Test
	public void testClientWithOnlyInputArgument() {
		String[] args = new String[]{GedcomConstants.DEFAULT_INPUT_FILE};
		GedcomClient.main(args);
		File file = new File(GedcomConstants.DEFAULT_OUTPUT_FILE);
		Assert.assertTrue(file.exists()&&file.canRead()&&(file.length()!=0));
	}

}
