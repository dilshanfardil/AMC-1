package com.amc.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amc.model.Appointment;

import com.amc.util.DbUtil;

public class AppointmentService {
	
	public boolean addAppointment(Appointment appointment) {
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "INSERT INTO appointments(user_id, doc_id, app_date, duration_left, charge) VALUES (?,?,?,?,?)";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1,1);
			pstmt.setInt(2,1);
			pstmt.setDate(3,new java.sql.Date(appointment.getDate().getTime()));
			pstmt.setInt(4, 1);
			pstmt.setDouble(5, appointment.getCharge());
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
	
	public List<Appointment> getAllAppointments() {
		List<Appointment> staffList = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM appointments";

			pstmt = connection.prepareStatement(sql);
			

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Appointment appointment = new Appointment();
				appointment.setAppointmentId(rs.getInt(1));
				appointment.setCustomerName(String.valueOf(rs.getInt(2)));
				appointment.setDoctorName(String.valueOf(rs.getInt(3)));
				appointment.setDate(rs.getDate(4));
				appointment.setDurationLeft(rs.getInt(5));
				appointment.setCharge(rs.getDouble(6));
				
				staffList.add(appointment);
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
	
	public Appointment loadAppointment(int appointmentId) {
		Appointment appointment = new Appointment();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM appointments WHERE app_id = ?";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, appointmentId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				appointment.setAppointmentId(rs.getInt(1));
				appointment.setCustomerName(String.valueOf(rs.getInt(2)));
				appointment.setDoctorName(String.valueOf(rs.getInt(3)));
				appointment.setDate(rs.getDate(4));
				appointment.setDurationLeft(rs.getInt(5));
				appointment.setCharge(rs.getDouble(6));
				
				
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

		return appointment;

	}
	
	public boolean updateAppointment(Appointment appointment) {
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "UPDATE appointments SET user_id=?, doc_id =?, app_date=?, duration_left=?, charge=? WHERE app_id=?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1,2);
			pstmt.setInt(2,2);
			pstmt.setDate(3,new java.sql.Date(appointment.getDate().getTime()));
			pstmt.setInt(4, 12);
			pstmt.setDouble(5, appointment.getCharge());
			pstmt.setInt(6, appointment.getAppointmentId());
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
	
	public boolean deleteAppointment(int appId) {
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "DELETE FROM appointments WHERE app_id=?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, appId);
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
	
	
	public List<Appointment> loadAppointmentsByUserId(int userId, String column) {
		List<Appointment> staffList = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM appointments WHERE "+column+"=?";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Appointment appointment = new Appointment();
				appointment.setAppointmentId(rs.getInt(1));
				appointment.setCustomerName(String.valueOf(rs.getInt(2)));
				appointment.setDoctorName(String.valueOf(rs.getInt(3)));
				appointment.setDate(rs.getDate(4));
				appointment.setDurationLeft(rs.getInt(5));
				appointment.setCharge(rs.getDouble(6));
				
				staffList.add(appointment);
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
	
	

}
