package com.jbike.auth;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.jbike.navigation.NavigationBean;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	
    private static final String[] users = {"anna:qazwsx","kate:123456"};

	private String email;
	private String password;
	
	private boolean loggedIn; 
	
    @ManagedProperty(value="#{navigationBean}")
    private NavigationBean navigationBean;
	
	/*
	@PostConstruct
	public void init() {
		List<User> users = new ArrayList<User>();
		
		for (int i = 1; i <= 10; i++) {
			users.add(new User(i, "mail" + i, "password" + i));
		}
	}
	*/
	
	/**
     * Login operation.
     * @return
     */
    public String doLogin() {
        // Get every user from our sample database :)
        for (String user: users) {
            String dbUsername = user.split(":")[0];
            String dbPassword = user.split(":")[1];
             
            // Successful login
            if (dbUsername.equals(email) && dbPassword.equals(password)) {
                loggedIn = true;
                return navigationBean.redirectToWelcome();
            }
        }
         
        // Set login ERROR
        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        // To to login page
        return navigationBean.toLogin();         
    }
     
    /**
     * Logout operation.
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
         
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        return navigationBean.toLogin();
    }
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	} 
    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
}
