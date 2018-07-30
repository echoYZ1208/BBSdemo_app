package com.example.echo.dao;

import java.util.List;

import com.example.echo.entity.SensitiveWord;

/**
 * 敏感词操作接口
 * 
 * @author Shawn
 */
public interface SensitiveWordDao {
	/**
	 * 查询所有信息
	 */
	public abstract List<SensitiveWord> getAllSensitiveWord();

	/**
	 * 更新信息
	 */
	public abstract int updateSensitiveWord(String sql, Object[] param);

	/**
	 * 根据查询条件查询信息
	 */
	public abstract SensitiveWord selectSensitiveWord(String sql, String[] param);
}