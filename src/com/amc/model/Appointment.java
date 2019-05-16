package com.amc.model;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Appointment {
	
	private int appointmentId;
	private String customerName;
	private String doctorName;
	private Date date;
	private int durationLeft;
	private double charge;
	
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public int getAppointmentId() {
		return appointmentId;
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
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	

}
