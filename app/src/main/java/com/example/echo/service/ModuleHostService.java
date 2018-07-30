package com.example.echo.service;

import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;

/**
 * 版主接口
 * 
 * @author Shawn
 *
 */
public interface ModuleHostService extends PostHostService {
	
	/**
	 * 删除帖子
	 */
	public boolean deletePost(Post post);
	
	/**
	 * 置顶帖子
	 */
	public boolean stickPost(Post post);
	
	/**
	 * 拉黑用户
	 */
	public boolean black(Member member, Module module);
	
}
