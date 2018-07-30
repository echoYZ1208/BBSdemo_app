package com.example.echo.dao;

import java.util.List;

import com.example.echo.entity.StickPost;

/**
 * 置顶帖子操作接口
 * 
 * @author Shawn
 */
public interface StickPostDao {
	/**
	 * 查询所有信息
	 */
	public abstract List<StickPost> getAllStickPost();

	/**
	 * 更新信息
	 */
	public abstract int updateStickPost(String sql, Object[] param);

	/**
	 * 根据查询条件查询信息
	 */
	public abstract StickPost selectStickPost(String sql, String[] param);
}
