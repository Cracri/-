package com.wx.dao;

import com.wx.pojo.Userinfo;

public interface UserDao {
	
	//�����û�
	public String saveUserinfo(Userinfo user);

	public int findUid(String username);

	public Userinfo findUserText();

}
