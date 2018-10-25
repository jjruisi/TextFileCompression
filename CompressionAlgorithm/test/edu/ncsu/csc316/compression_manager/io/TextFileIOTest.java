package edu.ncsu.csc316.compression_manager.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.compression_manager.util.ArrayList;

/**
 * Tests the TextFileIO class
 * @author John Ruisi
 *
 */
public class TextFileIOTest {

	/**
	 * Tests the get process method of the class
	 * @throws FileNotFoundException if the file could not be found
	 */
	@Test
	public void getProgress() throws FileNotFoundException {
		TextFileIO a = new TextFileIO();
		assertNotNull(a);
		
		String file1 = "input/DeclarationOfIndependence.txt";
		String file2 = "input/DeclarationOfIndependence-compressed.txt";
		String file3 = "input/empty.txt";
		
		assertEquals(TextFileIO.getProcess(file1), "COMPRESS");
		assertEquals(TextFileIO.getProcess(file2), "DECOMPRESS");
		assertEquals(TextFileIO.getProcess(file3), "EMPTY");
	}

	/**
	 * Tests the writeCompress() class that writes only compressed files
	 */
	@Test
	public void testCompress() {
		TextFileIO a = new TextFileIO();
		
		String file = "input/writerTest.txt";
		ArrayList<String> b = new ArrayList<String>();
		b.add("Test");
		TextFileIO.writeCompress(file, b, 4);
		
		assertNotNull(a);
	}
	
	/**
	 * Tests the write() method that only writes decompressed files
	 * @throws FileNotFoundException if the file could not be written
	 */
	@Test
	public void testDecompress() throws FileNotFoundException {
		TextFileIO a = new TextFileIO();
		
		String file = "writerTest2.txt";
		ArrayList<String> b = new ArrayList<String>();
		b.add("Test");
		TextFileIO.write(file, b);
		
		assertNotNull(a);
	}
}
