package com.bachcoder.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.bachcoder.dao.GenericDAO;
import com.bachcoder.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {
	private ResourceBundle bundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
			Class.forName(bundle.getString("driver"));
			String url = bundle.getString("url");
			String user = bundle.getString("user");
			String password = bundle.getString("password");
			return DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	@Override
	public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			setParameter(preparedStatement, parameters);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				results.add(rowMapper.mapRow(rs));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	private void setParameter(PreparedStatement preparedStatement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object obj = parameters[i];
				// lay ra -> check type -> set vi tri(bat dau tu 1) + set tham so
				int index = i + 1;
				if (obj instanceof Long) {
					preparedStatement.setLong(index, (Long) obj);
				} else if (obj instanceof String) {
					preparedStatement.setString(index, (String) obj);					
				}else if (obj instanceof Float) {
					preparedStatement.setFloat(index, (Float) obj);					
				}else if (obj instanceof Timestamp) {
					preparedStatement.setTimestamp(index, (Timestamp) obj);					
				}else if (obj instanceof Integer) {
					preparedStatement.setInt(index, (Integer) obj);					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Long insert(String sql, Object...parameters) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			con = getConnection(); 	
			con.setAutoCommit(false);
			preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//set param
			setParameter(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();//return id
			Long id = null;
			while(rs.next()) {
				id = rs.getLong(1);
			}
			con.commit();
			return id;
		}catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}

	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = getConnection(); 	
			con.setAutoCommit(false);
			preparedStatement = con.prepareStatement(sql);
			//set param
			setParameter(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			con.commit();	
		}catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			int count = 0;
			con = getConnection(); 	
			preparedStatement = con.prepareStatement(sql);
			//set param
			setParameter(preparedStatement, parameters);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		}catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return 0;
			}
		}
	}

}
