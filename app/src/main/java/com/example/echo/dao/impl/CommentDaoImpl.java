package com.example.echo.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.BaseDao;
import com.example.echo.dao.CommentDao;
import com.example.echo.entity.Comment;

/**
 *评论数据库操作类
 */

public class CommentDaoImpl extends BaseDao implements CommentDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/* (non-Javadoc)
	 * @see dao.impl.CommentDao#getAllComment()
	 */
	@Override
	public List<Comment> getAllComment(int PostId) {
		List<Comment> commentList = new ArrayList<Comment>();
		try {
		String preparedSql = "select * from comment where postId = "+PostId;
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setID(rs.getInt(1));
				comment.setPostID(rs.getInt(2));
				comment.setOwnerID(rs.getInt(3));
				comment.setCommentTime(rs.getTimestamp(4));
				comment.setCommentContent(rs.getString(5));
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return commentList;
	}

	/* (non-Javadoc)
	 * @see dao.impl.CommentDao#updateComment(java.lang.String, java.lang.String[])
	 */
	@Override
	public int updateComment(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.impl.CommentDao#selectComment(java.lang.String, java.lang.String[])
	 */
	@Override
	public Comment selectComment(String sql, String[] param) {
		Comment comment = null;
		try {
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
			}
		}
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				comment = new Comment();
				comment.setID(rs.getInt(1));
				comment.setPostID(rs.getInt(2));
				comment.setOwnerID(rs.getInt(3));
				comment.setCommentTime(rs.getTimestamp(4));
				comment.setCommentContent(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return comment;
	}

}