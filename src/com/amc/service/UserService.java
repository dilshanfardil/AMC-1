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

	public User validateUser(User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT reg_no,role FROM users WHERE user_name = ? AND password = ?";

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
				user.setId(rs.getInt(1));
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

	public String getUserRole(User user) {

		String role = null;
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT role FROM users WHERE user_name = ? AND password = ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				role = rs.getString(1);
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

		return role;

	}

	public boolean addUser(User user) {
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "INSERT INTO users(user_name, password, role) VALUES (?,?,?)";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getRole());
			System.out.println("pstmt fine");
			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("Recode Insert successfully...");

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
		List<User> staffList = new ArrayList<>();
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
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRole(rs.getString(4));
				staffList.add(user);
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

		return staffList;

	}

	public User loadUser(int userId) {
		User user = new User();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM users WHERE reg_no =?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRole(rs.getString(4));

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

		return user;
	}
	
	
	public boolean updateStaffMember(User user) {
		System.out.println(user.getId()+" "+user.getUserName());
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "UPDATE users SET user_name=?, password=?, role=? WHERE reg_no=?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getRole());
			pstmt.setInt(4, user.getId());
			System.out.println("pstmt fine");
			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("Recode Insert successfully...");

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
	
	public boolean deleteUser(int userId) {
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "DELETE FROM users WHERE reg_no=?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			System.out.println("pstmt fine");
			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("Recode Insert successfully...");

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
	
	public List<String> getCategorizedUsers(String role) {
		List<String> resultList = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT user_name FROM users WHERE role=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, role);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				resultList.add(rs.getString(1));
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

		return resultList;

	}
	
	public int getUserId(String userName) {
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
		
	}
	
	public List<User> getCustmorUsers() {
		List<User> resultList = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM users WHERE role=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "Customer");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRole(rs.getString(4));
				resultList.add(user);
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

		return resultList;

	}
	
	

}
