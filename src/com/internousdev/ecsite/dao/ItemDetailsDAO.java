package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.dto.ItemInfoDTO;
import com.internousdev.ecsite.util.DBConnector;
public class ItemDetailsDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
	public ItemInfoDTO getItemInfo (String id) throws SQLException {
		ItemInfoDTO dto = new ItemInfoDTO();
		String sql ="SELECT * FROM item_info_transaction WHERE id = ? ";
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {

				dto.setId(resultSet.getString("id"));
				dto.setItemName(resultSet.getString("item_name"));
				dto.setItemPrice(resultSet.getString("item_price"));
				dto.setItemStock(resultSet.getString("item_stock"));
				dto.setInsert_date(resultSet.getString("insert_date"));
				dto.setUpdate_date(resultSet.getString("update_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return dto;
	}
}
