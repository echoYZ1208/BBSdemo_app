package com.example.echo.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.BaseDao;
import com.example.echo.dao.PraiseDao;
import com.example.echo.entity.Praise;

/**
 *点赞数据库操作类
 *
 */

public class PraiseDaoImpl extends BaseDao implements PraiseDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/* (non-Javadoc)
	 * @see dao.impl.PraiseDao#getAllPraise()
	 */
	@Override
	public List<Praise> getAllPraise() {
		List<Praise> praiseList = new ArrayList<Praise>();
		try {
		String preparedSql = "select * from praise ";
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				Praise praise = new Praise();
				praise.setUserID(rs.getInt(1));
				praise.setPostID(rs.getInt(2));

				praiseList.add(praise);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return praiseList;
	}

	/* (non-Javadoc)
	 * @see dao.impl.PraiseDao#updatePraise(java.lang.String, java.lang.String[])
	 */
	@Override
	public int updatePraise(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.impl.PraiseDao#selectPraise(java.lang.String, java.lang.String[])
	 */
	@Override
	public Praise selectPraise(String sql, String[] param) {
		Praise praise = null;
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
				praise = new Praise();
				praise.setUserID(rs.getInt(1));
				praise.setPostID(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return praise;
	}

}