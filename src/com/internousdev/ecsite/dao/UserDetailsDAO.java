package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.dto.UserInfoDTO;
import com.internousdev.ecsite.util.DBConnector;

public class UserDetailsDAO {
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
	public UserInfoDTO getUserInfo(String loginId) throws SQLException {
		UserInfoDTO dto = new UserInfoDTO();
		String sql ="SELECT * FROM login_user_transaction WHERE login_id = ?";
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

				dto.setId(resultSet.getString("id"));
				dto.setLoginId(resultSet.getString("login_id"));
				dto.setLoginPass(resultSet.getString("login_pass"));
				dto.setUserName(resultSet.getString("user_name"));
				dto.setInsert_date(resultSet.getString("insert_date"));
				dto.setUpdate_date(resultSet.getString("updated_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return dto;
	}

}
