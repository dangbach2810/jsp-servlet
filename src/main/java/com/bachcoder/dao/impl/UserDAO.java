package com.bachcoder.dao.impl;

import java.util.List;

import com.bachcoder.dao.IUserDAO;
import com.bachcoder.mapper.UserMapper;
import com.bachcoder.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPassWordAndStatus(String userName, String passWord, Integer status) {
		String sql ="SELECT * FROM user INNER JOIN role ON user.roleid = role.id WHERE username = ? AND password = ? AND status = ?";
		List<UserModel> userModels = query(sql, new UserMapper(), userName, passWord, status);
		return userModels.isEmpty()?null:userModels.get(0);
	}
}
