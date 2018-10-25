package edu.ncsu.csc316.compression_manager.decompressor;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the decompressor class
 * @author John Ruisi
 *
 */
public class DecompressorTest {

	/**
	 * Tests the detect method of the decompressor class
	 */
	@Test
	public void testDetect() {
		Decompressor c = new Decompressor("file");
		
		char[] ch = {'j', 'o', 'h', 'n', ' ', '1'};
		c.detect(ch);
		
		assertEquals(c.getTextList().get(0), "john");
		assertEquals(c.getTextList().get(1), " ");
		assertEquals(c.getTextList().get(2), "john");
		
		assertEquals(c.getDictionary().get(0), "john");
	}

	/**
	 * Test the get output file method
	 */
	@Test
	public void testGetOutFile() {
		Decompressor c = new Decompressor("input/file-compressed.txt");
		String a = c.getOutFile(c.getFile());
		assertEquals("file.txt", a);
	}
}
