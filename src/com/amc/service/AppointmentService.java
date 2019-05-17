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
			String sql = "INSERT INTO appointments(app_id, cus_id, doc_id, app_date, duration_left) VALUES (?,?,?,?,?)";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, appointment.getAppointmentId());
			pstmt.setString(2, appointment.getCustomerId());
			pstmt.setString(3, appointment.getDoctorId());
			pstmt.setDate(4, new java.sql.Date(appointment.getDate().getTime()));
			pstmt.setInt(5, appointment.getDurationLeft());

			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("App add by staff successfully...");

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
		List<Appointment> list = new ArrayList<>();
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
				appointment.setAppointmentId(rs.getString(1));
				appointment.setCustomerId(rs.getString(2));
				appointment.setDoctorId(rs.getString(3));
				appointment.setDate(rs.getDate(4));
				appointment.setDurationLeft(rs.getInt(5));
				appointment.setMedicine(rs.getString(6));
				appointment.setFeedback(rs.getString(7));
				appointment.setComment(rs.getString(8));
				appointment.setCharge(rs.getDouble(9));

				list.add(appointment);
			}
		} catch (Exception e) {
			System.out.println("Error: appointment error " + e);
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

	public Appointment loadAppointment(String appointmentId) {
		Appointment appointment = new Appointment();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM appointments WHERE app_id = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, appointmentId);

			rs = pstmt.executeQuery();
			rs.next();

			appointment.setAppointmentId(rs.getString(1));
			appointment.setCustomerId(rs.getString(2));
			appointment.setDoctorId(rs.getString(3));
			appointment.setDate(rs.getDate(4));
			appointment.setDurationLeft(rs.getInt(5));
			appointment.setMedicine(rs.getString(6));
			appointment.setFeedback(rs.getString(7));
			appointment.setComment(rs.getString(8));
			appointment.setCharge(rs.getDouble(9));

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
			String sql = "UPDATE appointments SET cus_id=?, doc_id =?, app_date=?, duration_left=?, medicine=?, feedback=?, comment=?, charge=? WHERE app_id=?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, appointment.getCustomerId());
			pstmt.setString(2, appointment.getDoctorId());
			pstmt.setDate(3, new java.sql.Date(appointment.getDate().getTime()));
			pstmt.setInt(4, appointment.getDurationLeft());
			pstmt.setString(5, appointment.getMedicine());
			pstmt.setString(6, appointment.getFeedback());
			pstmt.setString(7, appointment.getComment());
			pstmt.setDouble(8, appointment.getCharge());

			pstmt.setString(9, appointment.getAppointmentId());

			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("Apppointment update successfully...");

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

	public boolean deleteAppointment(String appoinmentId) {
		boolean completed = false;
		Connection connection = null;

		PreparedStatement pstmt = null;

		try {
			connection = DbUtil.getConnection();
			String sql = "DELETE FROM appointments WHERE app_id=?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, appoinmentId);

			int affectedRows = pstmt.executeUpdate();

			System.out.println(affectedRows + " row(s) affected !!");
			System.out.println("Apppointment delete successfully...");

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

	public List<Appointment> loadAppointmentsById(String id, String column) {
		List<Appointment> list = new ArrayList<>();
		Connection connection = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();
			String sql = "SELECT * FROM appointments WHERE " + column + "=?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Appointment appointment = new Appointment();
				appointment.setAppointmentId(rs.getString(1));
				appointment.setCustomerId(rs.getString(2));
				appointment.setDoctorId(rs.getString(3));
				appointment.setDate(rs.getDate(4));
				appointment.setDurationLeft(rs.getInt(5));
				appointment.setMedicine(rs.getString(6));
				appointment.setFeedback(rs.getString(7));
				appointment.setComment(rs.getString(8));
				appointment.setCharge(rs.getDouble(9));

				list.add(appointment);
			}
		} catch (Exception e) {
			System.out.println("Error: doctor error " + e);
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
