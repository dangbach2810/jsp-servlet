package com.bachcoder.service;

import com.bachcoder.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status);
}
