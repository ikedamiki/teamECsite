package com.internousdev.laravel.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.laravel.dao.DestinationInfoDAO;
import com.internousdev.laravel.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware{

	private List<DestinationInfoDTO> destinationInfoDTOList;
	private Map<String,Object> session;
	public String execute(){

		String tempLogined=String.valueOf(session.get("login_flg"));
		int logined="null".equals(tempLogined) ? 0 : Integer.parseInt(tempLogined);

		if(logined != 1){

			return "loginError";
		}

		//ログインしている場合はユーザーIDを取得する
		String userId=session.get("user_id").toString();

		//ユーザーに紐付く
		DestinationInfoDAO destinationInfoDAO=new DestinationInfoDAO();
		destinationInfoDTOList=destinationInfoDAO.getUserAddress(userId);

		return SUCCESS;
	}

	public List<DestinationInfoDTO> getDestinationInfoDTOList(){
		return destinationInfoDTOList;
	}

	public void setDestinationInfoDTOList(List<DestinationInfoDTO> destinationInfoDTOList){
		this.destinationInfoDTOList=destinationInfoDTOList;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	public void setSession(Map<String,Object> session){
		this.session=session;
	}

}
