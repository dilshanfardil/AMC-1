package com.amc.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.amc.model.User;
import com.amc.service.UserService;
import com.amc.util.DbUtil;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
	User currentUser = null;
	
	public String login(User user) {
		System.out.println("Execute Submit Operation");
		try {
			System.out.println(user);
			UserService us = new UserService();
			currentUser = us.validateUser(user);

			if (currentUser != null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put("currentUserId", currentUser.getId());

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
			System.out.println("Exeption occured");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Login Fail..."));

			return "";

		}

	}
	
	public String logout() {
		//System.out.println("logout");
		DbUtil.closeConnection();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		
		
		return "index";
	}

}
