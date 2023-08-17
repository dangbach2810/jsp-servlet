package com.bachcoder.dao.impl;

import java.util.List;

import com.bachcoder.dao.ICategoryDAO;
import com.bachcoder.mapper.CategoryMapper;
import com.bachcoder.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

}
