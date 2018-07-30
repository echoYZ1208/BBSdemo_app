package com.example.echo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.echo.dao.ModuleDao;
import com.example.echo.dao.UserDao;
import com.example.echo.dao.impl.ModuleDaoImpl;
import com.example.echo.dao.impl.UserDaoImpl;
import com.example.echo.entity.Comment;
import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;
import com.example.echo.service.AdministratorService;

/**
 * 管理员实现类
 * 
 * @author Shawn
 *
 */
public class AdministratorServiceImpl extends ModuleHostServiceImpl implements AdministratorService {

	/**
	 * 添加板块
	 * 要求主键自增且版主已定？
	 */
	@Override
	public boolean addModule(Module module) {
		// TODO Auto-generated method stub
		ModuleDao moduleDao = new ModuleDaoImpl();
		String sql = "insert into module values(?,?);";
		Object []param = {module.getModuleName(),module.getOwnerID()};
		int updateModule = moduleDao.updateModule(sql, param);
		if(updateModule>0) {
			return true;
		}else
		
		return false;
	}

	/**
	 * 删除板块，参数板块的信息完备
	 */
	@Override
	public boolean deleteModule(Module module) {
		// TODO Auto-generated method stub
		ModuleDao moduleDao = new ModuleDaoImpl();
		String sql = "delete from module where id = ?";
		Object []param = {module.getID()};
		int updateModule = moduleDao.updateModule(sql, param);
		if(updateModule>0) {
			return true;
		}else
		    return false;
	}

	/**
	 * 更新版主
	 */
	@Override
	public boolean addModuleHost(Module module, Member member) {
		// TODO Auto-generated method stub
		ModuleDao moduleDao = new ModuleDaoImpl();
		String sql = "update module set ownerId = ? where id = ?";
		Object param[] = {member.getID(),module.getID()};
		int updateModule = moduleDao.updateModule(sql, param);
		if(updateModule>0) return true;
		else return false;
	}

	/**
	 * 获取用户名单
	 */
	@Override
	public List<Member> getMember() {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		List<Member> memberList = userDao.getAllUser();
		return memberList;
		
		
	}
	
//	/**
//	 * 删除帖子
//	 */
//	@Override
//	public boolean deletePost(Post post) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	/**
//	 * 置顶帖子
//	 */
//	@Override
//	public boolean stickPost(Post post) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	/**
//	 * 拉黑用户
//	 */
//	@Override
//	public boolean black(Member member,Module module) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
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
