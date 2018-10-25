package edu.ncsu.csc316.compression_manager.compressor;

import java.io.*;
import java.util.Scanner;

import edu.ncsu.csc316.compression_manager.io.TextFileIO;
import edu.ncsu.csc316.compression_manager.util.ArrayList;
import edu.ncsu.csc316.compression_manager.util.LinkedList;

/**
 * This class takes in a file that needs to be compressed. The class reads through the file, 
 * picking out words as it reads and uses the move to front hueristic for efficient searching
 * of words.
 * @author John Ruisi
 *
 */
public class Compressor {
	
	/** the list of text to be printed */
	private ArrayList<String> textList;
	/** The list of words that occur in the text*/
	private LinkedList<String> dictionary;
	/** The file to be compressed*/
	private String file;

	/**
	 * Constructor assigns the file name to its private variable
	 * and intializes the ArrayList and LinkedList
	 * @param file the file to be compressed
	 */
	public Compressor(String file) {
		textList = new ArrayList<String>();
		dictionary = new LinkedList<String>();
		this.file = file;
	}
	
	/**
	 * Gets the text list to be printed
	 * @return text list the list to be printed
	 */
	public ArrayList<String> getTextList() {
		return textList;
	}
	
	/**
	 * Gets the list of words that occur in the text
	 * @return dictionary the words that occur in the text
	 */
	public LinkedList<String> getDictionary() {
		return dictionary;
	}
	
	/**
	 * Gets the file to be compressed
	 * @return file the file to be compressed
	 */
	public String getFile() {
		return file;
	}
	
	/**
	 * The compress method reads in every line of text and converts it into an array
	 * of characters. Each array is sent to the detect() method to detect words or symbols
	 * inside the array. Once every line has been searched for words and symbols, the text list
	 * gets sent to be written out to a file.
	 * @throws FileNotFoundException if the file does not exist or cannot be created
	 */
	public void compress() throws FileNotFoundException {
		Scanner scan = new Scanner(new FileInputStream(file), "UTF8");
		
		char prepend1 = '0';
		char prepend2 = ' ';
		textList.add(Character.toString(prepend1));
		textList.add(Character.toString(prepend2));
		
		while (scan.hasNextLine()) {
			
			String line = scan.nextLine();
			char[] ch = new char[line.length() + 1];
			
		    for (int i = 0; i < line.length(); i++) {
		    	char c = line.charAt(i);
		    	ch[i] = c;
		    }
		    
		    ch[line.length()] = '\n';
			
		    detect(ch);
		}
		
		File f = new File(file);
		int inBytes = (int) f.length();

		TextFileIO.writeCompress(getOutFile(file), textList, inBytes);
		scan.close();
	}	
	
	/**
	 * Uses a java regular expression to determine if a character is a letter
	 * @param c the character to be tested
	 * @return true if the character is a letter
	 */
	public boolean isLetter(char c) {
		String ch = Character.toString(c);
		return ch.matches("[a-zA-Z]");
	}
	
	/**
	 * Uses a java regular expression to determine if a character is a symbol
	 * @param c the character to be tested
	 * @return true if the character is a symbol
	 */
	public boolean isSymbol(char c) {
		String ch = Character.toString(c);
		return ch.matches("[-'.\",/*~(){}`%&:;!=+@#$?-_><\\s\\n\\r]");
	}
	
	/**
	 * The lookUp method uses the move to front hueristic. Whenever a word is found
	 * in the file, it is added to the front of the list which allows for easier
	 * searching since most common words will be at the front.
	 * @param word the words to be looked up
	 */
	public void lookUp (String word) {
		int index = dictionary.get(word);
		if (index == -1) {
			dictionary.add(word);
			textList.add(word);
		} else {
			dictionary.remove(index);
			dictionary.add(word);
			textList.add(Integer.toString(index + 1));
		}
	}
	
	/**
	 * Iterates through the entire length of the char array to detect a word or symbol.
	 * Since only symbols seperates words, whenever a symbol is found, the word that was
	 * created before the symbol is found is sent to the lookUp() method.
	 * @param c the array of characters to detect words and symbols from
	 */
	public void detect(char[] c) {
		
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < c.length; i++) {
			
			if (isLetter(c[i])) {
				s.append(c[i]);
				
			} else if (isSymbol(c[i])) {
				if (s.length() == 0) {
					textList.add(Character.toString(c[i]));
				} else {
					lookUp(s.toString());
					textList.add(Character.toString(c[i]));
					s = new StringBuilder();
				}
			}
		}
		if (s.length() != 0) {
			lookUp(s.toString());
		}

	}
	
	/**
	 * Returns the file name in a syntax that will allow it to be written
	 * without problem.
	 * @param file the file that was compressed
	 * @return b the file to be written
	 */
	public String getOutFile(String file) {
		StringBuilder a = new StringBuilder();
		a.append(file);
		a.delete(a.length() - 4, a.length());
		a.append("-compressed.txt");
		a.delete(0, 6);
		String b = "output/compressed/" + a.toString();
		return b;
	}
}
	

