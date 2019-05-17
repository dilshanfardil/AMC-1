package com.amc.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;

import com.amc.model.User;
import com.amc.service.UserService;


@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean {

	
	private List<User> allUsers = new ArrayList<User>(0);
	

	public void addUser(User user) {

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

	public void loadUsersList() {
		UserService us = new UserService();
		allUsers = us.getAllUsers();
	}

	public String loadUser(String userId) {

		User selectedUser = new UserService().loadUser(userId);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getSessionMap();
		requestMap.put("editUser", selectedUser);
		//System.out.println("updating user added.");
		return "manager-update";
	}
	
	public String updateUser(User user) {

		UserService us = new UserService();
		if (us.updateUser(user)) {
			return "manager-dashboard";
		} else {
			System.out.println("Error on update");
			return "";

		}

	}
	
	public String deleteUser(String userId) {
		UserService us = new UserService();
		if (us.deleteUser(userId)) {
			return "manager-dashboard";
		} else {
			System.out.println("Error on delete");
			return "";

		}
	}
	

	public List<User> getallUsers() {
		return allUsers;
	}
	
	

}
