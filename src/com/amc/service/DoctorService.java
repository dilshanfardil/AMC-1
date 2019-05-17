package com.amc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amc.util.DbUtil;

public class DoctorService {

	public List<String> getDoctorNameList() {
		List<String> list = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT name FROM users WHERE role='Doctor'";
			pstmt = connection.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			System.out.println("Error: doctor " + e);
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

	public String getDoctorId(String doctorName) {

		String doctorId = "";
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT user_id FROM users WHERE name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, doctorName);
			rs = pstmt.executeQuery();
			rs.next();
			doctorId = rs.getString(1);
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

		return doctorId;

	}

}
