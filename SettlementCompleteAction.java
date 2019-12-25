package com.internousdev.laravel.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.laravel.dao.CartInfoDAO;
import com.internousdev.laravel.dao.PurchaseHistoryInfoDAO;
import com.internousdev.laravel.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware{

	private String id;
	private Map<String,Object> session;

	public String execute(){

		String tempLogined = String.valueOf(session.get("login_flg"));
		int logined = "null".equals(tempLogined) ? 0 :Integer.parseInt(tempLogined);

		if(logined != 1){
			return "loginError";
		}

		String result = ERROR;

		String userId = session.get("user_id").toString();

		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDTOList = cartInfoDAO.getUserCartInfo(userId);

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		int count = 0;
		for(CartInfoDTO dto : cartInfoDTOList){
			count += purchaseHistoryInfoDAO.buyUserItemHistory(
					userId,
					dto.getProductId(),
					dto.getProductCount(),
					Integer.parseInt(id),
					dto.getPrice());
		}

		if(count > 0){
			count = cartInfoDAO.deleteAll(String.valueOf(session.get("user_id")));
			if(count > 0){
				result=SUCCESS;
			}
		}

		return result;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	public void setSession(Map<String,Object> session){
		this.session=session;
	}
}
