package com.amc.bean;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.amc.model.User;
import com.amc.service.LoginService;
import com.amc.service.UserService;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {
	User currentUser = new User();;

	public String login(User user) {
		// System.out.println("Execute Submit Operation");
		try {
			// System.out.println(user);
			LoginService ls = new LoginService();
			currentUser = ls.validateUser(user);

			if (currentUser != null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put("currentUserId", currentUser.getUserId());

				switch (currentUser.getRole()) {
				case "Manager":
					return "manager-dashboard";

				case "Counter Staff":
					return "staff-dashboard";

				case "Doctor":
					return "doctor-dashboard";

				case "Customer":
					return "customer-dashboard";

				default:
					return "";

				}

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Login Fail..."));
				return "";

			}
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Login Fail..."));

			return "";

		}

	}

	public String loadUser(String currentUserId) {

		User selectedUser = new UserService().loadUser(currentUserId);

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getSessionMap();
		requestMap.put("editCurrentUser", selectedUser);

		if(selectedUser.getRole().equals("Doctor")) {
			return "current-doctor-update";
		}else {
			return "current-customer-update";
		}
		

	}

	public String updateUser(User user) {

		UserService us = new UserService();
		if (us.updateUser(user)) {

			if (user.getRole().equals("Doctor")) {
				return "doctor-dashboard";
			} else {
				return "customer-dashboard";
			}
		} else {
			System.out.println("Error on update");
			return "";

		}
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();

		return "index";
	}

	public User getCurrentUser() {
		return currentUser;
	}

}
