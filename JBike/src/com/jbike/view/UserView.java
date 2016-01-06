package com.jbike.view;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;

import com.jbike.controller.UserBean;
import com.jbike.helper.UserHelper;
import com.jbike.mailer.GMailMailHandler;
import com.jbike.model.User;
import com.jbike.persistence.UserDaoHibernate;
import com.jbike.session.UserSession;

@ManagedBean(name = "userView")
@ViewScoped
public class UserView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9027251848155549128L;

	private List<User> filteredUsers;

	@ManagedProperty(value = "#{userSession.selectedUser}")
	private User user;

	@ManagedProperty(value = "#{userSession.loggedUser}")
	private User loggedUser;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	@PostConstruct
	public void init() {
		if (null == this.getUser()) {
			this.setUser(new User());
		}
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String viewForm(User user) {
		if (user == null) {
			user = new User();
		}

		this.getUserSession().setSelectedUser(user);

		return "/admin/users/form.xhtml?faces-redirect=true";
	}

	public String getFormTitle() {
		return this.getUser().isNew() ? "New User" : String.format("Edit User (%s)", this.getUser());
	}

	public List<User> getUsers() {
		return this.getUserBean().getUsers();
	}

	public String viewProfile(User user) {
		this.getUserSession().setSelectedUser(user);

		return "users/profile";
	}

	public String viewHistory(User user) {
		this.getUserSession().setSelectedUser(user);

		return "users/history";
	}

	public String viewPenalizations(User user) {
		this.getUserSession().setSelectedUser(user);

		return "users/penalizations";
	}

	public void activate(User user) {
		user.setActive(true);

		if (this.getUserBean().saveUser(user)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully activated."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while activating the user."));
		}
	}

	public void deactivate(User user) {
		user.setActive(false);

		if (this.getUserBean().saveUser(user)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully deactivated."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while deactivating the user."));
		}
	}

	public void promote(User user) {
		user.setIsAdmin(true);

		if (this.getUserBean().saveUser(user)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully promoted."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while promoting the user."));
		}
	}

	public void demote(User user) {
		user.setIsAdmin(false);

		if (this.getUserBean().saveUser(user)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully demoted."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while demoting the user."));
		}
	}

	public String signIn() {

		if (!this.getUserBean().userExists(this.getUser())) {
			String password;
			try {
				password = UserHelper.generateRandomPassword();

				this.getUser().setPassword(UserHelper.digest(password));
				if (this.getUserBean().saveUser(this.getUser())) {

					GMailMailHandler.send(this.getUser().getEmail(), "Welcome to JBike!",
							String.format("Your password is: <b>%s</b>", password));

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Success!", "User created. We have sent an email with your password."));

					return "home";
				}
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException | MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An user with the same email or dni already exists."));

			return "signin";
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
				"An error occurred while creating your user. Please, try again later."));

		return "home";
	}

	public String logIn() {
		return "success";
	}

	public String save() {
		UserDaoHibernate udh = new UserDaoHibernate();

		if (this.getUser().isNew()) {
			udh.save(this.getUser());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully created."));
		} else {
			udh.update(user);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully updated."));
		}

		return this.backToList();
	}

	public String updateMyProfile() {
		UserDaoHibernate udh = new UserDaoHibernate();

		udh.update(this.getLoggedUser());

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
				"Your profile has been successfully updated."));

		return "welcome";
	}

	public String backToList() {
		return "/admin/users/list.xhtml?faces-redirect=true";
	}

	public String delete(User user) {
		if (this.getUserBean().deleteUser(user)) {
			this.getUserSession().getMessageQueue()
					.offer(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully deleted."));
		} else {
			this.getUserSession().getMessageQueue().offer(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"An error occurred while deleting the user."));
		}

		return "users/list";
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
