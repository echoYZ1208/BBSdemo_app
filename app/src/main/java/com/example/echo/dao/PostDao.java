package com.example.echo.dao;

import java.util.List;

import com.example.echo.entity.Post;

/**
 * 帖子操作接口
 * 
 * @author Shawn
 */
public interface PostDao {
	/**
	 * 查询所有信息
	 */
	public abstract List<Post> getAllPost(int ModuleId);

	/**
	 * 更新信息
	 */
	public abstract int updatePost(String sql, Object[] param);

	/**
	 * 根据查询条件查询信息
	 */
	public abstract Post selectPost(String sql, String[] param);
}
