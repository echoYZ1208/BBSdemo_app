package com.example.echo.service;

import java.util.ArrayList;

import com.example.echo.entity.Post;
import com.example.echo.entity.Module;
import com.example.echo.entity.Comment;

/**
 * 游客接口
 * 
 * @author Shawn
 *
 */
public interface VisitorService {
	/**
	 * 注册信息
	 */
	public boolean register(String userName,String email,String passward); 
	
	/**
	 * 浏览板块
	 */
	public ArrayList<Module> getModule();
	
	/**
	 * 浏览帖子
	 */
	public ArrayList<Post> getPost(int ModuleId);
	
	/**
	 * 浏览评论
	 */
	public ArrayList<Comment> getComment(int postID);
	
}
