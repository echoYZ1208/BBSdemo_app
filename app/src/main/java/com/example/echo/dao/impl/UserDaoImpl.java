package com.example.echo.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.BaseDao;
import com.example.echo.dao.UserDao;
import com.example.echo.entity.Member;

/**
 *用户数据库操作类
 */

public class UserDaoImpl extends BaseDao implements UserDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/* (non-Javadoc)
	 * @see dao.impl.UserDao#getAllUser()
	 */
	@Override
	public List<Member> getAllUser() {
		List<Member> userList = new ArrayList<Member>();
		try {
		String preparedSql = "select * from member ";
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				Member user = new Member();
				user.setID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setUserType(rs.getString(5));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return userList;
	}

	/* (non-Javadoc)
	 * @see dao.impl.UserDao#updateUser(java.lang.String, java.lang.String[])
	 */
	@Override
	public int updateUser(String sql, String[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.impl.UserDao#selectUser(java.lang.String, java.lang.String[])
	 */
	@Override
	public Member selectUser(String sql, String[] param) {
		Member user = null;
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
				user = new Member();
				user.setID(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setUserType(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return user;
	}

}