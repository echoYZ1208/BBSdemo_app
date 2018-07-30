package com.example.echo.entity;

/**
 * 黑名单实体类
 * 
 * @author Shawn
 * 
 */
public class BlackList {
	/**
	 * 用户ID
	 */
	private int userID;
	
	/**
	 * 板块ID
	 */
	private int moduleID;

	
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
	
}
