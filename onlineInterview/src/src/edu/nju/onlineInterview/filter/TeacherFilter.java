package edu.nju.onlineInterview.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nju.onlineInterview.model.Account;

/**
 * 
 * @author margine
 * @description
 * @createTime 2015年11月15日下午2:40:49
 * @contact ch_margine@163.com
 */
public class TeacherFilter extends HttpServlet implements javax.servlet.Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1227555110985617155L;

	@Override
	public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) _request;
		HttpServletResponse response = (HttpServletResponse) _response;
		HttpSession session = request.getSession();

		String contextPath = request.getContextPath();
		String url = request.getServletPath();

		if (!url.contains("css") && !url.contains("js") && url.contains("image") && !url.contains("student")
				&& !url.contains("admin") && url.contains("teacher")) {
			Account admin = (Account) session.getAttribute("cur_user");
			if (admin == null) {
				response.sendRedirect(contextPath + "/teacher/login.jsp");
				return;
			}
		}
		chain.doFilter(_request, _response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//do nothing
	}

}
