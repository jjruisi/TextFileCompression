package edu.ncsu.csc316.compression_manager.compressor;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Tests the compressor class
 * @author John Ruisi
 *
 */
public class CompressorTest {

	/**
	 * Tests the detect method
	 */
	@Test
	public void testDetect() {
		Compressor c = new Compressor("file.txt");
		
		char[] ch = {'j', 'o', 'h', 'n', ' ', 'r', ' ', 'r'};
		c.detect(ch);
		
		assertEquals(c.getTextList().get(0), "john");
		assertEquals(c.getTextList().get(1), " ");
		assertEquals(c.getTextList().get(2), "r");
		assertEquals(c.getTextList().get(4), "1");
		
		assertEquals(c.getDictionary().get(0), "r");
		assertEquals(c.getDictionary().get(1), "john");

	}
	
	/**
	 * Tests the get output file method
	 */
	@Test
	public void testGetOutFile() {
		Compressor c = new Compressor("input/file.txt");
		
		String a = c.getOutFile(c.getFile());
		assertEquals(a, "output/compressed/file-compressed.txt");
	}
	
	/**
	 * Tests the creation of the char array in the compress method
	 */
	@Test 
	public void testCharArray() {
		Compressor c = new Compressor("input/DeclarationOfIndependence.txt");
		try {
			c.compress();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(c);
	}

}
