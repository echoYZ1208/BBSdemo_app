package com.example.echo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子实体类
 * 
 * @author Shawn
 *
 */
public class Post implements Serializable{
	/**
	 * 帖子标题
	 */
	private String title;
	
	/**
	 * 帖子内容
	 */
	private String postContent;
	
	/**
	 * 帖子ID
	 */
	private int ID;
	
	/**
	 * 所属板块ID
	 */
	private int moduleID;
	
	/**
	 * 楼主ID
	 */
	private int ownerID;
	
	/**
	 * 建贴时间
	 */
	private Date sendTime;
	
	/**
	 * 赞数
	 */
	private int praise;
	

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the postContent
	 */
	public String getPostContent() {
		return postContent;
	}

	/**
	 * @param postContent
	 *            the postContent to set
	 */
	public void setPostContent(String content) {
		this.postContent = content;
	}

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
	public void setID(int Id) {
		ID = Id;
	}

	/**
	 * @return the moduleID
	 */
	public int getModuleID() {
		return moduleID;
	}

	/**
	 * @param moduleID
	 *            the moduleID to set
	 */
	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
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
	 * @return the sendTime
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * @param sendTime
	 *            the sendTime to set
	 */
	public void setSendTime(Date time) {
		this.sendTime = time;
	}

	/**
	 * @return the praise
	 */
	public int getPraise() {
		return praise;
	}

	/**
	 * @param praise
	 *            the praise to set
	 */
	public void setPraise(int praise) {
		this.praise = praise;
	}
	
}
