package com.jbike.view;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.DatatypeConverter;

import com.jbike.model.User;
import com.jbike.persistence.UserDaoHibernate;
import com.jbike.session.UserSession;
import com.sun.mail.smtp.SMTPTransport;

@ManagedBean(name = "userView")
@ViewScoped
public class UserView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<User> filteredUsers;

	@ManagedProperty(value = "#{userSession.selectedUser}")
	private User user;

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	@PostConstruct
	public void init() {
		// FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
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

	// FIXME
	public List<User> getUsers() {
		UserDaoHibernate udh = new UserDaoHibernate();

		return udh.findAll();
	}

	public String signIn() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		String s = "my Password";

		String password = DatatypeConverter.printHexBinary(md5.digest(s.getBytes("UTF-8"))).substring(0, 8);
		String digestedPassword = DatatypeConverter.printHexBinary(md5.digest(password.getBytes("UTF-8")));

		this.getUser().setPassword(digestedPassword);

		UserDaoHibernate udh = new UserDaoHibernate();

		udh.save(this.getUser());

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Bike successfully saved."));

		return "home";
	}

	public void emailAndres() throws AddressException, MessagingException {
		UserView.Send("webmaster.jbike", "jyaa2015", "andrescimadamore@gmail.com", "andrescimadamore@gmail.com", "hola",
				"hola");
	}

	public void emailLucio() throws AddressException, MessagingException {
		UserView.Send("webmaster.jbike", "jyaa2015", "daniffig@gmail.com", "daniffig@gmail.com", "hola",
				"hola");
	}

	public static void Send(final String username, final String password, String recipientEmail, String ccEmail,
			String title, String message) throws AddressException, MessagingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");

		/*
		 * If set to false, the QUIT command is sent and the connection is
		 * immediately closed. If set to true (the default), causes the
		 * transport to wait for the response to the QUIT command.
		 * 
		 * ref :
		 * http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/
		 * package-summary.html
		 * http://forum.java.sun.com/thread.jspa?threadID=5205249 smtpsend.java
		 * - demo program from javamail
		 */
		props.put("mail.smtps.quitwait", "false");

		Session session = Session.getInstance(props, null);

		// -- Create a new message --
		final MimeMessage msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		msg.setFrom(new InternetAddress(username + "@gmail.com"));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

		if (ccEmail.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
		}

		msg.setSubject(title);
		msg.setText(message, "utf-8");
		msg.setSentDate(new Date());

		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", username, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
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

	public String backToList() {
		return "/admin/users/list.xhtml?faces-redirect=true";
	}

	public void delete(User user) {
		UserDaoHibernate udh = new UserDaoHibernate();

		udh.delete(user);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User successfully deleted."));
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
}
