package com.example.echo.entity;

import java.util.Date;

/**
 * 评论实体类
 * 
 * @author Shawn
 *
 */
public class Comment {
	
	/**
	 * 评论ID
	 */
	private int ID;
	
	/**
	 * 帖子ID
	 */
	private int postID;
	
	/**
	 * 评论者ID
	 */
	private int ownerID;
	
	/**
	 * 评论时间
	 */
	private Date commentTime;
	
	/**
	 * 评论内容
	 */
	private String commentContent;

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

	/**
	 * @return the ownerID
	 */
	public int getOwnerID() {
		return ownerID;
	}

	/**
	 * @param ownerID
	 *            the ownerID to set
	 */
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	/**
	 * @return the commentTime
	 */
	public Date getCommentTime() {
		return commentTime;
	}

	/**
	 * @param commentTime
	 *            the commentTime to set
	 */
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	/**
	 * @return the commentContent
	 */
	public String getCommentContent() {
		return commentContent;
	}

	/**
	 * @param commentContent
	 *            the commentContent to set
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
}
