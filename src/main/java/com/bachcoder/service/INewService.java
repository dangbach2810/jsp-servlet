package com.bachcoder.service;

import java.util.List;

import com.bachcoder.model.NewModel;
import com.bachcoder.paging.Pageble;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
	NewModel save(NewModel newModel);
	NewModel update(NewModel newModel);
	void delete(long[] ids);
	NewModel findOne(Long id);
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
	
	
	
}
