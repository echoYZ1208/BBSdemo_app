package com.example.echo.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * 
 * @author Shawn
 *
 */
public class Member implements Serializable{
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 用户ID
	 */
	private int ID;
	
	/**
	 * 用户密码
	 */
	private String userPassword;
	
	/**
	 * 用户邮箱
	 */
	private String email;
	
	/**
	 * 用户类型
	 */
	private String userType;
	
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	public void setID(int ID) {
		this.ID = ID;
	}
	
	/**
	 * @return the userPassword
	 */
	public String getPassword() {
		return userPassword;
	}
	
	/**
	 * @param userPassword
	 *            the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
