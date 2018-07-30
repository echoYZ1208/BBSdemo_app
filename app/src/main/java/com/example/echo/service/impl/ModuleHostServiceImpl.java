package com.example.echo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.example.echo.dao.BlackListDao;
import com.example.echo.dao.PostDao;
import com.example.echo.dao.StickPostDao;
import com.example.echo.dao.impl.BlackListDaoImpl;
import com.example.echo.dao.impl.PostDaoImpl;
import com.example.echo.dao.impl.StickPostDaoImpl;
import com.example.echo.entity.Comment;
import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;
import com.example.echo.service.ModuleHostService;

/**
 * 版主实现类
 * 
 * @author Shawn
 *
 */
public class ModuleHostServiceImpl extends PostHostServiceImpl implements ModuleHostService {

	/**
	 * 删除帖子
	 */
	@Override
	public boolean deletePost(Post post) {
		// TODO Auto-generated method stub
		PostDao postDao = new PostDaoImpl();
		String sql = "delete from post where id = ?";
		Object [] param = {post.getID()};
		int updatePost = postDao.updatePost(sql, param);
		if(updatePost>0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 置顶帖子
	 */
	@Override
	public boolean stickPost(Post post) {
		// TODO Auto-generated method stub
		StickPostDao stickPostDao = new StickPostDaoImpl();
		String sql = "insert into stickPost values (?,?)";
		int postId = post.getID();
		//Timestamp 类型对应 数据库的datetime格式，年月日-时分秒
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Object[] param = {postId,timestamp};
		int updateStickPost = stickPostDao.updateStickPost(sql, param);
		if(updateStickPost > 0) {
			return true;
		}else {
			return false;
		}

	}

	/**
	 * 拉黑用户
	 * unfinished
	 */
	@Override
	public boolean black(Member member,Module module) {
		// TODO Auto-generated method stub
		BlackListDao blackListDao =new BlackListDaoImpl();
		String sql = "insert into blackList values(?,?)";
		Object[] param = {member.getID(),module.getID()};
		int updateBlacklist = blackListDao.updateBlackList(sql, param);
		if(updateBlacklist>0)return true;
		else return false;
	}
	
//	/**
//	 * 删除自己的帖子
//	 */
//	@Override
//	public boolean deleteSelfPost(Post post) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
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
//	public ArrayList<Post> getPost(String ModuleName) {
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