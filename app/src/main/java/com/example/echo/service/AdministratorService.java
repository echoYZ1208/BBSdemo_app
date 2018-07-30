package com.example.echo.service;

import java.util.List;

import com.example.echo.entity.Member;
import com.example.echo.entity.Module;

/**
 * 管理员接口
 * 
 * @author Shawn
 *
 */
public interface AdministratorService extends ModuleHostService {
	
	/**
	 * 添加板块
	 */
	public boolean addModule(Module module);
	
	/**
	 * 删除板块
	 */
	public boolean deleteModule(Module module);
	
	/**
	 * 添加版主
	 */
	public boolean addModuleHost(Module module,Member member);
	
	/**
	 * 获取用户名单
	 */
	public List<Member> getMember();
	
}
