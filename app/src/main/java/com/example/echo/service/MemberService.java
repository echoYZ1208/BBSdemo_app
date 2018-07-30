package com.example.echo.service;

import com.example.echo.entity.Member;
import com.example.echo.entity.Post;
import com.example.echo.entity.Comment;
import com.example.echo.service.VisitorService;

/**
 * 普通用户接口
 * 
 * @author Shawn
 *
 */
public interface MemberService extends VisitorService {
	
	/**
	 * 登录
	 */
	public Member logIn(String userName,String passward); 
	
	/**
	 * 点赞帖子
	 */
	public boolean praise(Post post,Member member);
	
	/**
	 * 新建帖子
	 */
	public boolean addPost(Post post);
	
	/**
	 * 新建评论
	 */
	public boolean addComment(Comment comment);
	
}
