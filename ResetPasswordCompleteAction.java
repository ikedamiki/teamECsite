package com.internousdev.laravel.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.laravel.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware {

	private Map<String,Object> session;

	public String execute() throws SQLException {

		String result=ERROR;
		UserInfoDAO userInfoDAO=new UserInfoDAO();
		int count=userInfoDAO.updatePassword(session.get("user_id_for_reset_password").toString(), session.get("new_password").toString());

		if (count > 0){
			result = SUCCESS;
		}

		session.remove("user_id_for_reset_password");
		session.remove("newPassword");
		session.remove("concealed_password");

		return result;
	}

	public Map<String,Object> getSession() {
		return session;
	}

	public void setSession(Map<String,Object>session) {
		this.session = session;
	}
}
