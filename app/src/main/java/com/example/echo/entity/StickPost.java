package com.example.echo.entity;

import java.util.Date;

/**
 * 置顶帖子实体类
 * 
 * @author Shawn
 *
 */
public class StickPost {
	/**
	 * 帖子ID
	 */
	private int postID;
	
	/**
	 * 置顶时间
	 */
	private Date stickTime;

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
	 * @return the stickTime
	 */
	public Date getStickTime() {
		return stickTime;
	}

	/**
	 * @param stickTime
	 *            the stickTime to set
	 */
	public void setStickTime(Date stickTime) {
		this.stickTime = stickTime;
	}
	
}
