package com.amc.model;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Appointment {
	
	private String appointmentId;
	private String customerId;
	private String doctorId;
	private Date date;
	private int durationLeft;
	private String medicine;
	private String feedback;
	private String comment;
	private double charge;
	
	public String getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDurationLeft() {
		return durationLeft;
	}
	public void setDurationLeft(int durationLeft) {
		this.durationLeft = durationLeft;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	
	
	

}
