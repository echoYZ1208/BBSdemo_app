package com.example.echo.entity;

/**
 * 敏感词实体类
 * 
 * @author Shawn
 *
 */
public class SensitiveWord {
	
	/**
	 * ID
	 */
	private int ID;
	
	/**
	 * 敏感词
	 */
	private String word;
	
	/**
	 * @return the ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param ID
	 *            the ID to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
}
