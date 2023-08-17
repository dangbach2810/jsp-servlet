package com.bachcoder.dao;

import java.util.List;

import com.bachcoder.mapper.RowMapper;
/**
 * 
 * @author bachcoder
 *
 * @param <T>
 * Object... có thể chưa được nhiều đối tượng khác nhau 
 * ví dụ
 * 
 */
public interface GenericDAO <T>{
	List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);//select db
	Long insert(String sql, Object... parameters);//update+delete and insert 
	void update(String sql, Object... parameters);
	int count(String sql, Object... parameters);
}
