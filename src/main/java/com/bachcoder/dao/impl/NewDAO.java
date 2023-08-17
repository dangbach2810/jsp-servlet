package com.bachcoder.dao.impl;

import java.util.List;

import com.bachcoder.dao.INewDAO;
import com.bachcoder.mapper.NewMapper;
import com.bachcoder.model.NewModel;
import com.bachcoder.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO{
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryId  = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO news ");
		sql.append("(title, thumbnail, shortdescription, content, categoryid, createddate, createdby)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getThumbnail(), 
				newModel.getShortDescription() ,newModel.getContent(), newModel.getCategoryId(),
				newModel.getCreatedDate(), newModel.getCreatedBy());//return id just insert
		
	}

	@Override
	public void update(NewModel updateNew) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" modifieddate = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getModifiedDate(), 
				updateNew.getModifiedBy(), updateNew.getId());
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id =?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return (news.isEmpty())?null:news.get(0);
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news ");
		if(pageble.getSorter() != null)
		{
			sql.append("ORDER BY "+pageble.getSorter().getSortName()+" "+ pageble.getSorter().getSortBy()+"");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+","+pageble.getLimit()+"");
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM news";
		return count(sql);
	}
}
