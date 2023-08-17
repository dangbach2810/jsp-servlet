package com.bachcoder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bachcoder.model.NewModel;

public class NewMapper implements RowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet rs) {
		
		// TODO Auto-generated method stub
		NewModel newModel = new NewModel();
		try {
			newModel.setTitle(rs.getString("title"));
			newModel.setContent(rs.getString("content"));
			newModel.setCategoryId(rs.getLong("categoryId"));
			newModel.setThumbnail(rs.getString("thumbnail"));
			newModel.setShortDescription(rs.getString("shortdescription"));
			return newModel;
		} catch (SQLException e) {
			return null;
		}
	}

}
