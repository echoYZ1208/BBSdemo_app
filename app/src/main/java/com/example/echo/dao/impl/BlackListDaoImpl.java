package com.example.echo.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.BaseDao;
import com.example.echo.dao.BlackListDao;
import com.example.echo.entity.BlackList;

/**
 *黑名单数据库操作类
 */

public class BlackListDaoImpl extends BaseDao implements BlackListDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/* (non-Javadoc)
	 * @see dao.impl.BlackListDao#getAllBlackList()
	 */
	@Override
	public List<BlackList> getAllBlackList() {
		List<BlackList> blackList = new ArrayList<BlackList>();
		try {
		String preparedSql = "select * from blackList ";
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				BlackList black = new BlackList();
				black.setUserID(rs.getInt(1));
				black.setModuleID(rs.getInt(2));
				
				blackList.add(black);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return blackList;
	}

	/* (non-Javadoc)
	 * @see dao.impl.BlackListDao#updateBlackList(java.lang.String, java.lang.String[])
	 */
	@Override
	public int updateBlackList(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.impl.BlackListDao#selectBlackList(java.lang.String, java.lang.String[])
	 */
	@Override
	public BlackList selectBlackList(String sql, String[] param) {
		BlackList black = null;
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
				black = new BlackList();
				black.setUserID(rs.getInt(1));
				black.setModuleID(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return black;
	}

}