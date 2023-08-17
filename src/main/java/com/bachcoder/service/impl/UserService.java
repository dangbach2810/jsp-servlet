package com.bachcoder.service.impl;

import javax.inject.Inject;

import com.bachcoder.dao.IUserDAO;
import com.bachcoder.model.UserModel;
import com.bachcoder.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Override
	public UserModel findByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status) {
		return userDAO.findByUserNameAndPassWordAndStatus(userName, passWord, status);
	}

}
