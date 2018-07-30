package com.example.echo.service.impl;

import java.util.ArrayList;

import com.example.echo.dao.CommentDao;
import com.example.echo.dao.impl.CommentDaoImpl;
import com.example.echo.dao.impl.ModuleDaoImpl;
import com.example.echo.dao.impl.PostDaoImpl;
import com.example.echo.dao.impl.UserDaoImpl;
import com.example.echo.entity.Comment;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;
import com.example.echo.service.VisitorService;

/**
 * 游客实现类
 * 
 * @author 邓春雨
 *
 */
public class VisitorServiceImpl implements VisitorService {

	@Override
	public boolean register(String userName, String email, String password) {
		// TODO Auto-generated method stub
		UserDaoImpl userDao = new UserDaoImpl();
		String[] param = {userName,password,email};
		String sql = "insert into member values(?,?,?,'会员')";
		int count = userDao.updateUser(sql,param);
		if(count>0)
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<Module> getModule() {
		// TODO Auto-generated method stub
		ModuleDaoImpl moduleDao = new ModuleDaoImpl();
		ArrayList<Module> moduleList = (ArrayList<Module>) moduleDao.getAllModule();
		return moduleList;
	}

	@Override
	public ArrayList<Post> getPost(int ModuleId) {
		// TODO Auto-generated method stub
		PostDaoImpl postDao = new PostDaoImpl();
		ArrayList<Post> postArrayList = (ArrayList<Post>) postDao.getAllPost(ModuleId);
		return postArrayList;
	}

	@Override
	public ArrayList<Comment> getComment(int postID) {
		// TODO Auto-generated method stub
		CommentDaoImpl commentDao = new CommentDaoImpl();
		ArrayList<Comment> commentArrayList = (ArrayList<Comment>) commentDao.getAllComment(postID);
		return commentArrayList;
	}

}
