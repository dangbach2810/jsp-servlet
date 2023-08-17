package com.bachcoder.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.bachcoder.dao.ICategoryDAO;
import com.bachcoder.model.CategoryModel;
import com.bachcoder.service.ICategoryService;

public class CategoryService implements ICategoryService{
	
//	private ICategoryDAO categoryDAO;
//	
//	public CategoryService() {
//		categoryDAO = new CategoryDAO();
//	}
	@Inject
	private ICategoryDAO categoryDAO;
	@Override
	public List<CategoryModel> findAll() {
		 return categoryDAO.findAll(); 
	}
	

}
