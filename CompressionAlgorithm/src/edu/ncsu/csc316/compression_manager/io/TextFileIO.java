package edu.ncsu.csc316.compression_manager.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Scanner;

import edu.ncsu.csc316.compression_manager.util.ArrayList;

/**
 * This class reads files to find if they shouold be compressed or decompressed.
 * It also writes to output files depending on if the file needs to be compressed
 * or decompressed.
 * @author John Ruisi
 *
 */
public class TextFileIO {

	/**
	 * Writes the output of compressed files to a specific location
	 * @param fileName the location to be written to
	 * @param list the text list of the file
	 * @param size the size of the uncompressed file
	 */
	public static void writeCompress(String fileName, ArrayList<String> list, int size) {
		
		PrintStream writer;
		
		try {
			writer = new PrintStream(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found");
		}
		
		
		for (int i = 0; i < list.size(); i++) {
			writer.print(list.get(i));
		}
		
		int size2 = (int) new File(fileName).length() - 3;		
		writer.print("0 Uncompressed: " + size + " bytes;  Compressed: " + size2 + " bytes");
		
		writer.close();
		

	}
	
	/**
	 * Writes the output of decompressed files to a specific location
	 * 
	 * (This method was created using the code given in the part 2 spec)
	 * 
	 * @param fileName the output location
	 * @param list the text list of the file
	 */
    public static void write(String fileName, ArrayList<String> list) {
    	
    	String filePath = "output/decompressed/";    // The directory in which you want to create the file
    	File f = new File(filePath);               // Declare the File directory
    	f.mkdirs();                                // Creates any directories that do not exist yet

    	// NOW you can continue writing the file as usual
    	File file = new File(f, fileName);
    	
    	try(FileOutputStream fos = new FileOutputStream(file);
    	    Writer write = new OutputStreamWriter(fos, "UTF8"))
    	{
    		
    		for (int i = 0; i < list.size(); i++) {
    			write.write(list.get(i));
    		}
    		
    	} catch (FileNotFoundException e) {
    		throw new IllegalArgumentException("File not found");
		} catch (IOException e) {
			throw new IllegalArgumentException("IOexception");
		}
	}
    
    /**
     * Determines if the file needs to be compressed or decompressed
     * @param file the file to be read
     * @return COMPRESS if the file needs to be compressed
     * @throws FileNotFoundException if the file was not found
     */
    public static String getProcess(String file) throws FileNotFoundException {

		Scanner scan = new Scanner(new FileInputStream(file), "UTF8");
		
	    while (scan.hasNextLine()) {
	    	
		    String line = scan.nextLine();
		    char c = line.charAt(0);
		    
		    if (c == '0') {
		    	scan.close();
		    	return "DECOMPRESS";
		    } else {
		    	scan.close();
		    	return "COMPRESS";
		    }
		}
	    
	    scan.close();
		return "EMPTY";
	}
}
