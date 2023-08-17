package com.bachcoder.dao;

import com.bachcoder.model.UserModel;

public interface IUserDAO {
	public UserModel findByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status);
}
