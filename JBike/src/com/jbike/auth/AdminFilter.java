package com.jbike.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jbike.session.UserSession;

public class AdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UserSession UserSession = (UserSession) ((HttpServletRequest) request).getSession().getAttribute("userSession");

		if (UserSession == null || !UserSession.isLoggedIn() || !UserSession.getLoggedUser().isAdmin()) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			System.out.println("AdminFilter: REJECT");
			((HttpServletResponse) response).sendRedirect(contextPath + "/users/log-in.xhtml");
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
