package edu.ncsu.csc316.compression_manager.decompressor;

import java.io.*;
import java.util.Scanner;

import edu.ncsu.csc316.compression_manager.io.TextFileIO;
import edu.ncsu.csc316.compression_manager.util.ArrayList;
import edu.ncsu.csc316.compression_manager.util.LinkedList;

/**
 * This class takes in a file that needs to be decmpressed. The class reads through the file, 
 * picking out words and numbers as it reads and uses the move to front hueristic for efficient searching
 * of words.
 * @author John Ruisi
 *
 */
public class Decompressor {
	
	/** The text list of words to be output */
	private ArrayList<String> textList;
	/** The dictionary of words */
	private LinkedList<String> dictionary;
	/** The file to be read */
	private String file;

	/**
	 * Constructor assigns the varaible file and intialized the
	 * linked list and the array list
	 * @param file the file to be read
	 */
	public Decompressor(String file) {
		textList = new ArrayList<String>();
		dictionary = new LinkedList<String>();
		this.file = file;
	}
	
	/**
	 * Gets the text list
	 * @return textList the text list
	 */
	public ArrayList<String> getTextList() {
		return textList;
	}
	
	/**
	 * Gets the dictionary
	 * @return dictionary the dictionary
	 */
	public LinkedList<String> getDictionary() {
		return dictionary;
	}
	
	/**
	 * Gets the file
	 * @return file the file to be read
	 */
	public String getFile(){ 
		return file;
	}
	
	/**
	 * The decompress method reads in every line of text and converts it into an array
	 * of characters. Each array is sent to the detect() method to detect words, numbers or symbols
	 * inside the array. Once every line has been searched, the text list
	 * gets sent to be written out to a file.
	 * @throws FileNotFoundException if the file does not exist or cannot be created
	 */
	public void decompress() throws FileNotFoundException {
		Scanner scan;
		
		try {
			 scan = new Scanner(new FileInputStream(file), "UTF8");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not Found");
		}
		
		int count = 0;
		
		while (scan.hasNextLine()) {
			
			
			String line = scan.nextLine();
			char[] ch = new char[line.length() + 1];
			
			ch[line.length()] = '\n';
			
		    for (int i = 0; i < line.length(); i++) {
		    	char c = line.charAt(i);
		    	ch[i] = c;
		    }
		    
		    if (ch[0] == '0' && ch[1] == ' ' && count == 0) {
		    	char[] ch1 = new char[ch.length - 1];
		 
		    	for (int i = 0; i < ch.length - 2; i++) {
		    		ch1[i] = ch[i + 2];
		    	}
		    	
		    	ch = ch1;
		    	count++;
		    	
		    } else if (ch[0] == '0' && ch[1] == ' ' && count == 1) {
		    	textList.removeLast();
		    	
		    	TextFileIO.write(getOutFile(file), textList);
		    }
		    
		    detect(ch);
		}
		scan.close();
	}
	
	/**
	 * Iterates through the entire length of the char array to detect a word, number or symbol.
	 * Since only symbols seperates words and numbers, whenever a symbol is found, the word or number that was
	 * created before the symbol was found is sent to the lookUp() method.
	 * @param c the array of characters
	 * @return 1 if the method runs completely
	 */
	public int detect(char[] c) {
		
		StringBuilder string = new StringBuilder();
		
		StringBuilder digit = new StringBuilder();
		
		for (int i = 0; i < c.length; i++) {
			
			if (isLetter(c[i])) {
				string.append(c[i]);
				
			} else if (isDigit(c[i])) {
				digit.append(c[i]);
				
			} else if (isSymbol(c[i])) {
				
				if (string.length() == 0 && digit.length() == 0) {
					textList.add(Character.toString(c[i]));
					
				} else if (string.length() != 0 && digit.length() == 0) {
					addString(string.toString());
					textList.add(Character.toString(c[i]));
					string = new StringBuilder();
					
				} else {
					
					if (Integer.valueOf(digit.toString()) == 0) {
						return -1;
					}
					
					lookUp(Integer.valueOf(digit.toString()));
					textList.add(Character.toString(c[i]));
					digit = new StringBuilder();
				}
			}
		}
		
		if (string.length() != 0) {
			addString(string.toString());
		} else if (digit.length() != 0) {
			lookUp(Integer.valueOf(digit.toString()));
		}
		
		return 1;
	}
	
	/**
	 * Looks up the number that was found in the text and finds the matching
	 * word in the dictionary to add to the text list using the move to front
	 * hueristic as it goes along
	 * @param index the index of the word to be found
	 */
	public void lookUp(int index) {
		String word = (String) dictionary.get(index - 1);
		dictionary.remove(index - 1);
		dictionary.add(word);
		textList.add(word);
	}
	
	/**
	 * Adds a word to the textlist or dictionary if it was found in the file
	 * @param s the word to be added
	 */
	public void addString(String s) {
		textList.add(s);
		dictionary.add(s);
	}
	
	/**
	 * Determines if a character is a letter
	 * @param c the character
	 * @return true if the character is a letter
	 */
	public boolean isLetter(char c) {
		String ch = Character.toString(c);
		return ch.matches("[a-zA-Z]");
	}
	
	/**
	 * Determines if a character is a symbol
	 * @param c the character
	 * @return true if the character is a symbol
	 */
	public boolean isSymbol(char c) {
		String ch = Character.toString(c);
		return ch.matches("[-'.\",/~(){}`%&;*:!@#=+$?-_><\\s\\n\\r]");
	}
	
	/**
	 * Determines if a character is a digit
	 * @param c the character
	 * @return true if the character is a digit
	 */
	public boolean isDigit(char c) {
		String ch = Character.toString(c);
		return ch.matches("[0-9]");
	}
	
	/**
	 * Gets the name of the output file to be written
	 * @param file the name of the input file
	 * @return the name of the output file
	 */
	public String getOutFile(String file) {
		StringBuilder a = new StringBuilder();
		a.append(file);
		a.delete(a.length() - 15, a.length());
		a.append(".txt");
		a.delete(0, a.lastIndexOf("/") + 1);
		return a.toString();
	}
}

