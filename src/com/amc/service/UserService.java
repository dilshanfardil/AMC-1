package com.amc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amc.model.User;
import com.amc.util.DbUtil;

public class UserService {

	public boolean addUser(User user) {
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?)";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getGender());
			pstmt.setInt(6, user.getPhone());
			pstmt.setInt(7, user.getNic());
			pstmt.setString(8, user.getEmail());
			pstmt.setString(9, user.getAddress());
			pstmt.setString(10, user.getRole());
			
			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("User add successfully...");

			completed = true;
		} catch (Exception e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return completed;
	}

	

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM users";

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

	public User loadUser(String userId) {
		User user = new User();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM users WHERE user_id =?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			rs.next();
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

		return user;
	}
	
	
	public boolean updateUser(User user) {
		
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "UPDATE users SET user_name=?, password=?, name=?, gender=?, phone=?, "
					+ "nic=?, email=?, address=? ,role=? WHERE user_id=?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getGender());
			pstmt.setInt(5, user.getPhone());
			pstmt.setInt(6, user.getNic());
			pstmt.setString(7, user.getEmail());
			pstmt.setString(8, user.getAddress());
			pstmt.setString(9, user.getRole());
			pstmt.setString(10, user.getUserId());
		
			
			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("User update successfully...");

			completed = true;
		} catch (Exception e) {
			System.out.println("Error: " + e);
		} finally {
			try {

				pstmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return completed;

	}
	
	
	public boolean deleteUser(String userId) {
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "DELETE FROM users WHERE user_Id=?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("User delete successfully...");

			completed = true;
		} catch (Exception e) {
			System.out.println("Error: " + e);
		} finally {
			try {

				pstmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return completed;
	}
	
	
	
	/*public int getUserId(String userName) {
		int userId = 0;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT reg_no FROM users WHERE user_name = ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userName);
			

			rs = pstmt.executeQuery();
			if (rs.next()) {
				userId = rs.getInt(1);
				// System.out.println("numberOfRows= " + numberOfRows);

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

		return userId;
		
	}*/
	
	
	
	

}
