package com.example.echo.service;

import com.example.echo.entity.Member;
import com.example.echo.entity.Module;
import com.example.echo.entity.Post;

public interface CommonService {
	/**
	 * 发帖前检测帖子是否有敏感词
	 * @param post
	 * @return true则包括敏感词
	 */
	public boolean hasSensitiveWord(Post post);
	
	
	/**
	 * 检测帖子主人id是否在所属module的黑名单里
	 * @param post
	 * @return true则在黑名单中
	 */
	public boolean isInBlackList(Module module, Member member);

}
