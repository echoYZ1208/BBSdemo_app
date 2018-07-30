package com.example.echo.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.BaseDao;
import com.example.echo.dao.ModuleDao;
import com.example.echo.entity.Module;

/**
 *板块数据库操作类
 *
 */

public class ModuleDaoImpl extends BaseDao implements ModuleDao {
	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/* (non-Javadoc)
	 * @see dao.impl.ModuleDao#getAllModule()
	 */
	@Override
	public List<Module> getAllModule() {
		List<Module> moduleList = new ArrayList<Module>();
		try {
		String preparedSql = "select * from module ";
		conn = getConn(); // 得到数据库连接
		pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
		rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				Module module = new Module();
				module.setID(rs.getInt(1));
				module.setOwnerID(rs.getInt(3));
				module.setModuleName(rs.getString(2));
				
				moduleList.add(module);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return moduleList;
	}

	/* (non-Javadoc)
	 * @see dao.impl.ModuleDao#updateModule(java.lang.String, java.lang.String[])
	 */
	@Override
	public int updateModule(String sql, Object[] param) {
		int count = super.executeSQL(sql, param);
		return count;
	}

	/* (non-Javadoc)
	 * @see dao.impl.ModuleDao#selectModule(java.lang.String, java.lang.String[])
	 */
	@Override
	public Module selectModule(String sql, String[] param) {
		Module module = null;
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
				module = new Module();
				module.setID(rs.getInt(1));
				module.setOwnerID(rs.getInt(2));
				module.setModuleName(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return module;
	}

}