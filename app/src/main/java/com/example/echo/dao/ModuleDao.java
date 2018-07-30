package com.example.echo.dao;

import java.util.List;

import com.example.echo.entity.Module;

/**
 * 板块操作接口
 * 
 * @author Shawn
 */
public interface ModuleDao {
	/**
	 * 查询所有信息
	 */
	public abstract List<Module> getAllModule();

	/**
	 * 更新信息
	 */
	public abstract int updateModule(String sql, Object[] param);

	/**
	 * 根据查询条件查询信息
	 */
	public abstract Module selectModule(String sql, String[] param);
}

