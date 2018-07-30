package com.example.echo.service.impl;

import java.util.List;

import com.example.echo.dao.BlackListDao;
import com.example.echo.dao.SensitiveWordDao;
import com.example.echo.dao.impl.BlackListDaoImpl;
import com.example.echo.dao.impl.SensitiveWordDaoImpl;
import com.example.echo.entity.BlackList;
import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;
import com.example.echo.entity.SensitiveWord;
import com.example.echo.service.CommonService;

public class CommenServiceImpl implements CommonService {

	@Override
	public boolean hasSensitiveWord(Post post) {
		// TODO Auto-generated method stub
		SensitiveWordDao sensitiveWordDao = new SensitiveWordDaoImpl();
		List<SensitiveWord> sensitiveWords= sensitiveWordDao.getAllSensitiveWord();
		String title = post.getTitle();
		String content = post.getPostContent();
		if(sensitiveWords!=null&&sensitiveWords.size()>0) {
			for(SensitiveWord sensitiveWord :sensitiveWords) {
				if(title.indexOf(sensitiveWord.getWord()) > -1) return true;
				if(content.indexOf(sensitiveWord.getWord()) > -1) return true;
			}
		}
		return false;
	}

	@Override
	public boolean isInBlackList(Module module, Member member) {
		// TODO Auto-generated method stub
        int ownerId = member.getID();
        int moduleId = module.getID();
		BlackListDao blackListDao = new BlackListDaoImpl();
		List<BlackList> blackLists = blackListDao.getAllBlackList();
		if(blackLists!=null&&blackLists.size() > 0) {
		for(BlackList blackList : blackLists) {
			if (blackList.getUserID() == ownerId && blackList.getModuleID() == moduleId) {
				return true;
			}
		}
		}
		return false;
	}

}
