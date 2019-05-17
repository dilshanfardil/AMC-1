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
import com.amc.service.CustomerService;
import com.amc.service.DoctorService;
import com.amc.service.UserService;



@ManagedBean(name="staffBean")
@ViewScoped
public class CounterStaffBean {
	
	private List<String> customerNames = new ArrayList<>();
	private List<String> doctorNames = new ArrayList<>();
	private List<Appointment> allAppointments = new ArrayList<>();
	private List<User> allCustomers = new ArrayList<>();
	
	public void onTabChange() {
		customerNames = null;
		doctorNames = null;
		customerNames = new CustomerService().getCustomerNameList();
		doctorNames = new DoctorService().getDoctorNameList();
	}
	
	public void loadAllCustomers() {
		allCustomers = null;
		allCustomers = new CustomerService().getAllCustomers();
	}
	
	public void loadAllAppointments() {
		allAppointments = null;
		allAppointments = new AppointmentService().getAllAppointments();
	}
	
	public void onTabChangeToView() {
		loadAllAppointments();
		loadAllCustomers();
	}
	
	public String loadCustomer(String userId) {
		User selectedUser = new UserService().loadUser(userId);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getSessionMap();
		requestMap.put("editUser", selectedUser);
		
		return "staff-customer-update";
	}
	
	public String updateCustomer(User user) {
		user.setRole("Customer");
		UserService us = new UserService();
		if (us.updateUser(user)) {
		
			return "staff-dashboard";
		} else {
			
			return "";
		}

	}
	
	public String deleteCustomer(String userId) {
		UserService us = new UserService();
		if (us.deleteUser(userId)) {
			return "staff-dashboard";
		} else {
			
			return "";

		}
	}
	
	public String loadAppointment(String appointmentId) {

		Appointment selectedAppointment = new AppointmentService().loadAppointment(appointmentId);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getSessionMap();
		requestMap.put("editAppointment", selectedAppointment);
		
		return "staff-appointment-update";
	}
	
	public String updateAppointment(Appointment appointment) {
		
		String customerId = new CustomerService().getCustomerId(appointment.getCustomerId());
		String doctorId = new DoctorService().getDoctorId(appointment.getDoctorId());
		appointment.setCustomerId(customerId);
		appointment.setDoctorId(doctorId);
		
		AppointmentService as = new AppointmentService();
		if (as.updateAppointment(appointment)) {
			
			return "staff-dashboard";
		} else {
			
			return "";
		}
	}
	
	public String deleteAppointment(String appointmentId) {
		AppointmentService as = new AppointmentService();
		if (as.deleteAppointment(appointmentId)) {
			
			return "staff-dashboard";
		} else {
			System.out.println("Error on update");
			return "";
		}
	}
	
	
	
	

	public List<String> getCustomerNames() {
		return customerNames;
	}

	public List<String> getDoctorNames() {
		return doctorNames;
	}
	
	public List<Appointment> getAllAppointments() {
		return allAppointments;
	}

	public List<User> getAllCustomers() {
		return allCustomers;
	}
}
