package com.example.echo.dao;

import java.util.List;

import com.example.echo.entity.Praise;

/**
 * 点赞操作接口
 * 
 * @author Shawn
 */
public interface PraiseDao {
	/**
	 * 查询所有信息
	 */
	public abstract List<Praise> getAllPraise();

	/**
	 * 更新信息
	 */
	public abstract int updatePraise(String sql, Object[] param);

	/**
	 * 根据查询条件查询信息
	 */
	public abstract Praise selectPraise(String sql, String[] param);
}