package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.util.DBConnector;

public class UserCreateConfirmDAO {
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	public boolean isExistUser(String loginUserId) {
		String sql = "SELECT COUNT(*) AS COUNT FROM login_user_transaction where login_id = ?";
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginUserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				if(resultSet.getInt("COUNT") > 0) {
					result = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
