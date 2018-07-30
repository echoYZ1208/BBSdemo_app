package com.example.echo.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.BaseDao;
import com.example.echo.dao.StickPostDao;
import com.example.echo.entity.StickPost;

/**
 *置顶数据库操作类
 *
 */

public class StickPostDaoImpl extends BaseDao implements StickPostDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/* (non-Javadoc)
	 * @see dao.impl.StickPostDao#getAllStickPost()
	 */
	@Override
	public List<StickPost> getAllStickPost() {
		List<StickPost> stickPostList = new ArrayList<StickPost>();
		try {
		String preparedSql = "select * from stickPost ";
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				StickPost stickPost = new StickPost();
				stickPost.setPostID(rs.getInt(1));
				stickPost.setStickTime(rs.getTimestamp(2));

				stickPostList.add(stickPost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return stickPostList;
	}

	/* (non-Javadoc)
	 * @see dao.impl.StickPostDao#updateStickPost(java.lang.String, java.lang.String[])
	 */
	@Override
	public int updateStickPost(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.impl.StickPostDao#selectStickPost(java.lang.String, java.lang.String[])
	 */
	@Override
	public StickPost selectStickPost(String sql, String[] param) {
		StickPost stickPost = null;
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
				stickPost = new StickPost();
				stickPost.setPostID(rs.getInt(1));
				stickPost.setStickTime(rs.getTimestamp(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return stickPost;
	}

}