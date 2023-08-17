package com.bachcoder.dao;

import java.util.List;

import com.bachcoder.model.NewModel;
import com.bachcoder.paging.Pageble;

public interface INewDAO {
	NewModel findOne(Long id);//find by id
	List<NewModel> findByCategoryId(Long categoryId);
	Long save(NewModel newModel);
	void update(NewModel newModel);
	void delete(long id);
	List<NewModel> findAll(Pageble pageble);
	int getTotalItem();
}
