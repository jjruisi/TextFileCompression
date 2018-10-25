package edu.ncsu.csc316.compression_manager.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.compression_manager.compressor.Compressor;
import edu.ncsu.csc316.compression_manager.decompressor.Decompressor;
import edu.ncsu.csc316.compression_manager.io.TextFileIO;

/**
 * CompressionManager class is responsible for figuring out if a file
 * should be compressed or decompressed and then sending that information to the proper
 * classes to be compressed or deocmpressed.
 * @author John Ruisi
 *
 */
public class CompressionManager {
	
	/**
	* The process method accepts a file name as a parameter. The
	* method then compresses or decompresses the file, based
	* on whether the file begins with "0 " (if so, decompress the file;
	* if not, compress the file).
	* @param fileName the name of the file to process
	* @return    "DECOMPRESS" if the file was decompressed
	*            "COMPRESS" if the file was compressed
	*            "EMPTY" if the file is empty (has no contents)
	* @throws FileNotFoundException if the file doesn't exist
	*/
	public String process(String fileName) {

		try {
			if (TextFileIO.getProcess(fileName).equals("COMPRESS")) {
				Compressor c = new Compressor(fileName);
				c.compress();
				return "COMPRESS";
	    	
			} else if (TextFileIO.getProcess(fileName).equals("DECOMPRESS")) {
				Decompressor d = new Decompressor(fileName);
				d.decompress();
				return "DECOMPRESS";
	    	} else {
	    		return "EMPTY";
	    	}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File Not Found");
		}
	}
}
