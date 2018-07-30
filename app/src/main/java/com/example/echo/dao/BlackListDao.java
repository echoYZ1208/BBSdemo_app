package com.example.echo.dao;

import java.util.List;

import com.example.echo.entity.BlackList;

/**
 * 黑名单操作接口
 * 
 * @author Shawn
 */
public interface BlackListDao {
	/**
	 * 查询所有信息
	 */
	public abstract List<BlackList> getAllBlackList();

	/**
	 * 根据已知信息查询信息
	 */
	public abstract BlackList selectBlackList(String sql, String[] param);

	/**
	 * 更新信息
	 */
	public abstract int updateBlackList(String sql, Object[] param);
}
