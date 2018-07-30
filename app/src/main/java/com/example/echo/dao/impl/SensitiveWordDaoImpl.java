package com.example.echo.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.BaseDao;
import com.example.echo.dao.SensitiveWordDao;
import com.example.echo.entity.SensitiveWord;

/**
 *敏感词数据库操作类
 *
 */

public class SensitiveWordDaoImpl extends BaseDao implements SensitiveWordDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/* (non-Javadoc)
	 * @see dao.impl.SensitiveWordDao#getAllSensitiveWord()
	 */
	@Override
	public List<SensitiveWord> getAllSensitiveWord() {
		List<SensitiveWord> sensList = new ArrayList<SensitiveWord>();
		try {
		String preparedSql = "select * from sensitiveWord ";
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				SensitiveWord sens = new SensitiveWord();
				sens.setID(rs.getInt(1));
				sens.setWord(rs.getString(2));

				sensList.add(sens);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return sensList;
	}

	/* (non-Javadoc)
	 * @see dao.impl.SensitiveWordDao#updateSensitiveWord(java.lang.String, java.lang.String[])
	 */
	@Override
	public int updateSensitiveWord(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.impl.SensitiveWordDao#selectSensitiveWord(java.lang.String, java.lang.String[])
	 */
	@Override
	public SensitiveWord selectSensitiveWord(String sql, String[] param) {
		SensitiveWord sens = null;
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
				sens = new SensitiveWord();
				sens.setID(rs.getInt(1));
				sens.setWord(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return sens;
	}

}