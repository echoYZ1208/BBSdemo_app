package com.example.echo.entity;

/**
 * 点赞实体类
 * 
 * @author Shawn
 *
 */
public class Praise {

	/**
	 * 点赞用户ID
	 */
	private int userID;
	
	/**
	 * 点赞帖子ID
	 */
	private int postID;
	
	
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	/**
	 * @return the postID
	 */
	public int getPostID() {
		return postID;
	}

	/**
	 * @param postID
	 *            the postID to set
	 */
	public void setPostID(int postID) {
		this.postID = postID;
	}
}
