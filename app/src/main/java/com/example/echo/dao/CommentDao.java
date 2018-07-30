package com.example.echo.dao;

import java.util.List;

import com.example.echo.entity.Comment;

/**
 * 评论操作接口
 * 
 * @author Shawn
 */
public interface CommentDao {
	/**
	 * 查询所有信息
	 */
	public abstract List<Comment> getAllComment(int PostId);

	/**
	 * 更新信息
	 */
	public abstract int updateComment(String sql, Object[] param);

	/**
	 * 根据查询条件查询信息
	 */
	public abstract Comment selectComment(String sql, String[] param);
}