package com.example.echo.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.BaseDao;
import com.example.echo.dao.PostDao;
import com.example.echo.entity.Post;

/**
 *帖子数据库操作类
 *
 */

public class PostDaoImpl extends BaseDao implements PostDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/* (non-Javadoc)
	 * @see dao.impl.PostDao#getAllPost()
	 */
	@Override
	public List<Post> getAllPost(int moduleId) {                 //该过
		List<Post> postList = new ArrayList<Post>();
		try {
		String preparedSql = "select * " +
				"from post left join stickPost on stickPost.postId = post.id" +
				" where moduleId = "+moduleId +
				" order by stickTime DESC,sendTime DESC";

		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				Post post = new Post();
				post.setID(rs.getInt(1));
				post.setTitle(rs.getString(2));
				post.setPostContent(rs.getString(3));
				post.setModuleID(rs.getInt(4));
				post.setOwnerID(rs.getInt(5));
				post.setSendTime(rs.getTimestamp(6));
				postList.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return postList;
	}

	/* (non-Javadoc)
	 * @see dao.impl.PostDao#updatePost(java.lang.String, java.lang.String[])
	 */
	@Override
	public int updatePost(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.impl.PostDao#selectPost(java.lang.String, java.lang.String[])
	 */
	@Override
	public Post selectPost(String sql, String[] param) {
		Post post = null;
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
				post = new Post();
				post.setID(rs.getInt(1));
				post.setTitle(rs.getString(2));
				post.setPostContent(rs.getString(3));
				post.setModuleID(rs.getInt(4));
				post.setOwnerID(rs.getInt(5));
				post.setSendTime(rs.getTimestamp(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return post;
	}

}