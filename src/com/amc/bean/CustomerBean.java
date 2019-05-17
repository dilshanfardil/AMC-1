package com.amc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.amc.model.Appointment;
import com.amc.model.User;
import com.amc.service.AppointmentService;
import com.amc.service.UserService;

@ManagedBean(name="customerBean")
@ViewScoped
public class CustomerBean {
	
	private List<Appointment> appointments = new ArrayList<>();
	
	public void addCustomer(User user) {
		user.setRole("Customer");
		UserService us = new UserService();
		boolean status = us.addUser(user);

		if (status) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registration Completed"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Registration Failed"));
		}

	}
	

	public void loadAppointments(String userId) {
		
		appointments=null;
		appointments = new AppointmentService().loadAppointmentsById(userId,"cus_id");
	}
	
	public String loadAppointment(String appointmentId) {

		Appointment selectedAppointment = new AppointmentService().loadAppointment(appointmentId);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getSessionMap();
		requestMap.put("editAppointment", selectedAppointment);
		
		return "customer-update";
	}
	
	public String updateAppointment(Appointment appointment) {
		
		AppointmentService as = new AppointmentService();
		if (as.updateAppointment(appointment)) {
			
			return "customer-dashboard";
		} else {
			
			return "";
		}
	}
	
	
	public List<Appointment> getAppointments(){
		return appointments;
	}

}
