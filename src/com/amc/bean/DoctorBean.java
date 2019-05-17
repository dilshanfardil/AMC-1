package com.amc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.amc.model.Appointment;
import com.amc.service.AppointmentService;

@ManagedBean(name="doctorBean")
@ViewScoped
public class DoctorBean {
	private List<Appointment> appointments = new ArrayList<>();
	
	public void loadAppointments(String userId) {
		
		appointments=null;
		appointments = new AppointmentService().loadAppointmentsById(userId,"doc_id");
	}
	
	public String loadAppointment(String appointmentId) {

		Appointment selectedAppointment = new AppointmentService().loadAppointment(appointmentId);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getSessionMap();
		requestMap.put("editAppointment", selectedAppointment);
		
		return "doctor-update";
	}
	
	public String updateAppointment(Appointment appointment) {
		
		AppointmentService as = new AppointmentService();
		if (as.updateAppointment(appointment)) {
			
			return "doctor-dashboard";
		} else {
			
			return "";
		}
	}
	
	
	public List<Appointment> getAppointments(){
		return appointments;
	}
}
