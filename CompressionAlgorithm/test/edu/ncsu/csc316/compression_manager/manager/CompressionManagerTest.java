package edu.ncsu.csc316.compression_manager.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Tests the compression manager class
 * @author John Ruisi
 *
 */
public class CompressionManagerTest {

	/**
	 * Tests the process method for returning "COMPRESS"
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testManagerCompress() throws FileNotFoundException {
		String file = "input/DeclarationOfIndependence.txt";
		CompressionManager c = new CompressionManager();
		assertEquals(c.process(file), "COMPRESS");
	}
	
	/**
	 * Tests the process method for returning "DECOMPRESS"
	 * @throws FileNotFoundException if the file doesn't exist
	 */
	@Test
	public void testManagerDecompress() throws FileNotFoundException {
		String file = "DeclarationOfIndependence-compressed.txt";
		CompressionManager c = new CompressionManager();
		assertEquals(c.process(file), "DECOMPRESS");
	}
	
	/**
	 * Tests the process method for a file that doesn't exist
	 * @throws FileNotFoundException if the file doesn't exist
	 */
	@Test
	public void testProcess() throws FileNotFoundException { 
		String file = "asdfasdfasdf.txt";
		CompressionManager c = new CompressionManager();
		
		try {
			c.process(file);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "File Not Found");
		}
	}
}
