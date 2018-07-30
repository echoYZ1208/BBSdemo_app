package com.example.echo.dao;


import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import com.example.echo.dao.BaseDao;

/**
 * 数据库操作基类
 * 
 * @author Shawn
 */
public class BaseDao {
	
	public static String DRIVER; // 数据库驱动

	public static String URL ; // url

	public static String DBNAME; // 数据库用户名

	public static String DBPASS; // 数据库密码
	
	Connection conn = null;// 数据连接对象
	
	static{//静态代码块,在类加载的时候执行
		init();
	}
	
	/**
	 * 初始化连接参数,从配置文件里获得
	 */
		public static void init(){
//			Properties params=new Properties();
//			String configFile = "database.properties";//配置文件路径
//			//加载配置文件到输入流中
//			InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
//
//			try {
//				//从输入流中读取属性列表
//				params.load(is);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			//根据指定的获取对应的值
			DRIVER="net.sourceforge.jtds.jdbc.Driver";
			URL="jdbc:jtds:sqlserver://10.6.35.142:1433;databaseName=Dutforum";
			DBNAME="sa";
			DBPASS="sqlserver_2017";
		}   

	/**
	 * 得到数据库连接
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return 数据库连接
	 */
	public Connection getConn() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // 注册驱动
			Log.d("SQLtest", "加载驱动成功");
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // 获得数据库连接
			Log.d("SQLtest", "sql连接成功");
		} catch (SQLException e) {
			Log.d("SQLtest", "失败");
			e.printStackTrace();
		}
		return conn; // 返回连接
	}
	/**
	 * 释放资源
	 * 
	 * @param conn
	 *            数据库连接
	 * @param pstmt
	 *            PreparedStatement对象
	 * @param rs
	 *            结果集
	 */
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {

		/* 如果rs不空，关闭rs */
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/* 如果pstmt不空，关闭pstmt */
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/* 如果conn不空，关闭conn */
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	/**
	 * 执行SQL语句，可以进行增、删、改的操作，不能执行查询
	 * 
	 * @param preparedSql
	 *            预编译的 SQL 语句
	 * @param param
	 *            预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return 影响的条数
	 */
	public int executeSQL(String preparedSql, Object[] param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;

		/* 处理SQL,执行SQL */
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			// System.out.println(preparedSql);
			num = pstmt.executeUpdate(); // 执行SQL语句
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // 处理ClassNotFoundException异常
		} catch (SQLException e) {
			e.printStackTrace(); // 处理SQLException异常
		} finally {
			this.closeAll(conn, pstmt, null);
		}
		return num;
	}
}
