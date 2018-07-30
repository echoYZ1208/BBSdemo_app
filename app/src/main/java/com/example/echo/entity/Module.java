package com.example.echo.entity;

import java.io.Serializable;

/**
 * 板块实体类
 * 
 * @author Shawn
 *
 */
public class Module implements Serializable{
	
	/**
	 * 板块名称
	 */
	private String moduleName;
	
	/**
	 * 板块ID
	 */
	private int ID;
	
	/**
	 * 版主ID
	 */
	private int ownerID;

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
		
}
