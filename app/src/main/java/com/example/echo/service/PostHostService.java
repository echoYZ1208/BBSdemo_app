package com.example.echo.service;

import com.example.echo.entity.Post;

/**
 * 楼主接口
 * 
 * @author Shawn
 *
 */
public interface PostHostService extends MemberService {
	
	/**
	 * 删除自己的帖子
	 */
	public boolean deleteSelfPost(Post post);
	
}
