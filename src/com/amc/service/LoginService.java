package com.amc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amc.model.User;
import com.amc.util.DbUtil;

public class LoginService {
	
	public User validateUser(User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT user_id,role FROM users WHERE user_name = ? AND password = ?";

			pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			
			rs = pstmt.executeQuery();
			
			int counter = 0;
			
			while(rs.next()) {
				counter++;
			}
			System.out.println(counter);
			
			if (counter==1 ) {
				rs.previous();
				user.setUserId(rs.getString(1));
				user.setRole(rs.getString(2));
			} else {
				System.out.println("error: could not get the record counts");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return user;
	}
}
