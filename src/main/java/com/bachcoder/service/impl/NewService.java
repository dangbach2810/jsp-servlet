package com.bachcoder.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.bachcoder.dao.INewDAO;
import com.bachcoder.model.NewModel;
import com.bachcoder.paging.Pageble;
import com.bachcoder.service.INewService;

public class NewService implements INewService{

	@Inject // let Interface auto find suitable Method() from children=]
	private INewDAO newDAO;
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
	}
	@Override
	//vừa lưu data xuống vừa lấy lại id của model vừa dc thêm
	public NewModel save(NewModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setCreatedBy("bachILLM");
		Long id = newDAO.save(newModel);
		return newDAO.findOne(id);
	}
	@Override
	public NewModel update(NewModel newModel) {
		NewModel oldModel = newDAO.findOne(newModel.getId());
		//set some param newUpdate dont have
		newModel.setCreatedDate(oldModel.getCreatedDate());
		newModel.setCreatedBy(oldModel.getCreatedBy());
		newModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setModifiedBy("BachIuM");
		if(newModel.getCategoryId() == null) {			
			newModel.setCategoryId(oldModel.getCategoryId());
		}
		newDAO.update(newModel);
		return newDAO.findOne(newModel.getId());
	}
	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			newDAO.delete(id);
		}
	}
	@Override
	public NewModel findOne(Long id) {
		// TODO Auto-generated method stub
		return newDAO.findOne(id);
	}
	@Override
	public List<NewModel> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}
	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

}
