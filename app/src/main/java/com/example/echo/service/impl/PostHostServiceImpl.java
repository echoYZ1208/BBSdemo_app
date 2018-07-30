package com.example.echo.service.impl;

import java.util.ArrayList;

import com.example.echo.dao.PostDao;
import com.example.echo.dao.impl.PostDaoImpl;
import com.example.echo.entity.Comment;
import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;
import com.example.echo.service.PostHostService;

/**
 * 楼主实现类
 * 
 * @author Shawn
 *
 */
public class PostHostServiceImpl extends MemberServiceImpl implements PostHostService {

	/**
	 * 删除自己的帖子
	 */
	@Override
	public boolean deleteSelfPost(Post post) {
		// TODO Auto-generated method stub
		PostDao postDao = new PostDaoImpl();
		String deletesql = "delete from post where id=?";
		Object[] param = {post.getID()};
		int updatepost = postDao.updatePost(deletesql,param);
		if(updatepost > 0)
			return true;
		else
			return true;
	}
	
//	/**
//	 * 登录
//	 */
//	@Override
//	public Member logIn(String userName, String passward) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/**
//	 * 点赞帖子
//	 */
//	@Override
//	public boolean praise(Post post) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	/**
//	 * 新建帖子
//	 */
//	@Override
//	public boolean addPost(Post post) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	/**
//	 * 新建评论
//	 */
//	@Override
//	public boolean addComment(Comment comment) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	/**
//	 * 注册信息
//	 */
//	@Override
//	public boolean register(String userName, String email, String passward) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	/**
//	 * 浏览板块
//	 */
//	@Override
//	public ArrayList<Module> getModule() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/**
//	 * 浏览帖子
//	 */
//	@Override
//	public ArrayList<Post> getPost(int ModuleId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	/**
//	 * 浏览评论
//	 */
//	@Override
//	public ArrayList<Comment> getComment(int postID) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
