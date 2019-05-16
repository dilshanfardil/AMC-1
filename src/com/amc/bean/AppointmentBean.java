package com.amc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.amc.model.Appointment;
import com.amc.model.User;
import com.amc.service.AppointmentService;
import com.amc.service.UserService;

@ManagedBean(name = "appointmentBean")
@ViewScoped
public class AppointmentBean {

	private List<String> customers = new ArrayList<>();
	private List<String> doctors = new ArrayList<>();
	private List<Appointment> allAppointments = new ArrayList<>();
	private List<User> allCustomers = new ArrayList<>();
	private List<Appointment> pendingAppointments = new ArrayList<>();

	public void onTabChange() {
		UserService us = new UserService();
		customers = us.getCategorizedUsers("Customer");
		doctors = us.getCategorizedUsers("Doctor");

	}

	public String addAppointment(Appointment appointment) {
		// System.out.println(appointment.getCustomerName()+"
		// "+appointment.getDoctorName());
		AppointmentService as = new AppointmentService();
		if (as.addAppointment(appointment)) {
			return "staff-dashboard";
		}

		return "";
	}

	public void loadAllAppointments() {
		allAppointments = new AppointmentService().getAllAppointments();
	}

	public void loadCustomers() {
		allCustomers = new UserService().getCustmorUsers();
	}

	public void onTabChangeOnView() {
		allAppointments = null;
		allCustomers = null;
		loadAllAppointments();
		loadCustomers();
	}

	public String loadAppointment(int appointmentId) {

		Appointment selectedAppointment = new AppointmentService().loadAppointment(appointmentId);
		System.out.println(selectedAppointment.getCustomerName() + " " + selectedAppointment.getDoctorName());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getSessionMap();
		requestMap.put("editAppointment", selectedAppointment);
		// System.out.println("updating user added.");
		return "staff-appointment-update";
	}

	public void loadUpdateViewData() {
		customers = null;
		doctors = null;
		onTabChange();
	}

	
	public String updateAppointment(Appointment appointment) {
		AppointmentService as = new AppointmentService();
		if (as.updateAppointment(appointment)) {
			System.out.println("update done.");
			return "staff-dashboard";
		} else {
			System.out.println("Error on update");
			return "";
		}
	}
	
	public String deleteAppointment(int appointmentId) {
		AppointmentService as = new AppointmentService();
		if (as.deleteAppointment(appointmentId)) {
			System.out.println("update done.");
			return "staff-dashboard";
		} else {
			System.out.println("Error on update");
			return "";
		}
	}
	
	public void loadAppointmentsByDoctorId(int userId) {
		//System.out.println("user "+userId);
		pendingAppointments=null;
		pendingAppointments = new AppointmentService().loadAppointmentsByUserId(userId,"doc_id");
	}
	
	public void loadAppointmentsByCustomerId(int userId) {
		//System.out.println("user "+userId);
		pendingAppointments=null;
		pendingAppointments = new AppointmentService().loadAppointmentsByUserId(userId, "user_id");
	}
	

	public List<String> getCustomers() {
		return customers;
	}

	public List<String> getDoctors() {
		return doctors;
	}

	public List<User> getAllCustomers() {
		return allCustomers;
	}

	public List<Appointment> getAllAppointments() {
		return allAppointments;
	}
	
	public List<Appointment> getPendingAppointments() {
		return pendingAppointments;
	}
	

}
