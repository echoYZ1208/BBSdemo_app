package com.example.echo.service.impl;

import java.util.ArrayList;

import com.example.echo.dao.CommentDao;
import com.example.echo.dao.PostDao;
import com.example.echo.dao.PraiseDao;
import com.example.echo.dao.impl.CommentDaoImpl;
import com.example.echo.dao.impl.PostDaoImpl;
import com.example.echo.dao.impl.PraiseDaoImpl;
import com.example.echo.dao.impl.UserDaoImpl;
import com.example.echo.entity.Comment;
import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;
import com.example.echo.service.MemberService;

/**
 * 普通用户实现类
 * 
 * @author Shawn
 *
 */
public class MemberServiceImpl extends VisitorServiceImpl implements MemberService {

	/**
	 * 登录
	 */
	@Override
	public Member logIn(String userName, String passward) {
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		String[] param = {userName,passward};
		String sql = "select * from member where userName = ? and userPassword = ?";
		Member member = userDao.selectUser(sql,param);
		if(member != null)
			return member;
		else
			return null;
	}

	/**
	 * 点赞帖子
	 */
	@Override
	public boolean praise(Post post,Member member) {
		// TODO Auto-generated method stub
		String sql = "insert into praise values(?,?)";
		String[] param = {String.valueOf(member.getID()), String.valueOf(post.getID())};
		PraiseDao praiseDao = new PraiseDaoImpl();
		int count = praiseDao.updatePraise(sql,param);
		if(count>0)
			return true;
		else
			return false;
	}

	/**
	 * 新建帖子
	 */
	@Override
	public boolean addPost(Post post) {
		// TODO Auto-generated method stub
		//这地方可能会有类型转换的问题
		String[] param = {post.getTitle(),post.getPostContent(), String.valueOf(post.getModuleID()),
				String.valueOf(post.getOwnerID()), String.valueOf(post.getSendTime())};
		String sql = "insert into post values(?,?,?,?,?)";
		PostDao postDao = new PostDaoImpl();
		int count = postDao.updatePost(sql,param);
		if(count>0)
			return true;
		else
			return false;
	}

	/**
	 * 新建评论
	 */
	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		//这里也可能有类型转化的问题
		String[] param = { String.valueOf(comment.getPostID()), String.valueOf(comment.getOwnerID()),
				String.valueOf(comment.getCommentTime()),comment.getCommentContent()};
		String sql = "insert into comment values(?,?,?,?)";
		CommentDao commentDao = new CommentDaoImpl();
		int count = commentDao.updateComment(sql,param);
		if(count>0)
			return true;
		else
			return false;
	}


}
