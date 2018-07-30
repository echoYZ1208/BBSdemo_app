package com.example.echo.dao;

import java.util.List;

import com.example.echo.entity.Member;

/**
 * 用户操作接口
 * 
 * @author Shawn
 */
public interface UserDao {
	/**
	 * 查询所有用户信息
	 */
	public abstract List<Member> getAllUser();

	/**
	 * 更新信息
	 */
	public abstract int updateUser(String sql, String[] param);

	/**
	 * 根据查询条件查询用户信息
	 */
	public abstract Member selectUser(String sql, String[] param);
}
