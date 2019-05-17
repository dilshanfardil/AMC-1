package com.amc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.amc.model.Appointment;
import com.amc.service.AppointmentService;
import com.amc.service.CustomerService;
import com.amc.service.DoctorService;


@ManagedBean(name = "appointmentBean")
@ViewScoped
public class AppointmentBean {

	public String addAppointment(Appointment appointment) {
		String customerId = new CustomerService().getCustomerId(appointment.getCustomerId());
		String doctorId = new DoctorService().getDoctorId(appointment.getDoctorId());
		appointment.setCustomerId(customerId);
		appointment.setDoctorId(doctorId);
		
		AppointmentService as = new AppointmentService();
		if (as.addAppointment(appointment)) {
			return "staff-dashboard";
		}

		return "";
	}

}
