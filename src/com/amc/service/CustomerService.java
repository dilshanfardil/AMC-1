package com.amc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amc.model.User;
import com.amc.util.DbUtil;

public class CustomerService {
	
	public List<String> getCustomerNameList() {
		List<String> list = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT name FROM users WHERE role='Customer'";
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("Error: Customer error " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}
	
	public String getCustomerId(String customerName) {
		
		String customerId = "";
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT user_id FROM users WHERE name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, customerName);
			rs = pstmt.executeQuery();
			rs.next();
			customerId = rs.getString(1);
		} catch (Exception e) {
			System.out.println("Error: staff error " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return customerId;
		
	}
	
	public List<User> getAllCustomers() {
		List<User> list = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM users WHERE role='Customer'";

			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setPhone(rs.getInt(6));
				user.setNic(rs.getInt(7));
				user.setEmail(rs.getString(8));
				user.setAddress(rs.getString(9));
				user.setRole(rs.getString(10));
				list.add(user);
			}
		} catch (Exception e) {
			System.out.println("Error: staff error " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;

	}

}
