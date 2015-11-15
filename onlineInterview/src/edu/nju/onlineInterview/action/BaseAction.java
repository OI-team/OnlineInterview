package edu.nju.onlineInterview.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author margine
 * @description 
 * @createTime 2015年11月15日下午5:02:14
 * @contact ch_margine@163.com
 */
public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2635627094504571707L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> session;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession(){
		return session;
	}
}
