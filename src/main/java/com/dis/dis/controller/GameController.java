package com.dis.dis.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dis.dis.service.GameService;
import com.dis.dis.service.HomeService;

@Controller
public class GameController {
	@Autowired
	private GameService gameservice;
	
	@RequestMapping(value = "/firstpurchaseAction", method = RequestMethod.GET)
	public String firstpurchaseAction(Model model,HttpServletRequest request) {
		String signal = "";
		HttpSession session = request.getSession();
		Map<Object, Object> info = (Map<Object, Object>) session.getAttribute("info");//매입 할 정보
		
		int money = Integer.parseInt(session.getAttribute("money").toString());
		int salePrice = Integer.parseInt(info.get("SALE_PRICE").toString());
		
		signal = "purchaseError";
		
		if(info != null && money > salePrice)
		{
		gameservice.firstpurchase(info);
		signal = "purchaseOK";
		}
		
		
		//홈에 보낼 건물 정보 받아오기
		List<Map<Object, Object>> propertylist = gameservice.getPropertyList((String) request.getSession().getAttribute("station"));
	
		if(propertylist != null)
			session.setAttribute("propertylist", propertylist);
			
			session.setAttribute("money", gameservice.getmoney((String) session.getAttribute("email")));
			session.setAttribute("station", request.getSession().getAttribute("station"));
			
			return "redirect:home?signal=" + signal;
	}
	
	@RequestMapping(value = "/purchaseAction", method = RequestMethod.GET)
	public String purchaseAction(Model model,HttpServletRequest request) {
		String signal = "";
		HttpSession session = request.getSession();
		Map<Object, Object> info = (Map<Object, Object>) session.getAttribute("info");
		
		int money = Integer.parseInt(session.getAttribute("money").toString());
		int salePrice = Integer.parseInt(info.get("SALE_PRICE").toString());
		signal = "purchaseError";
		if(info != null && money > salePrice)
		{
			gameservice.purchase(info);
			signal ="purchaseOK";
		}
		//홈에 보낼 건물 정보 받아오기
				List<Map<Object, Object>> propertylist = gameservice.getPropertyList((String) request.getSession().getAttribute("station"));
			
				if(propertylist != null)
					session.setAttribute("propertylist", propertylist);//건물정보는 model로
					
					session.setAttribute("money", gameservice.getmoney((String) session.getAttribute("email")));
					session.setAttribute("station", request.getSession().getAttribute("station"));
					
					return "redirect:home?signal=" + signal;
	}
	
	@RequestMapping(value = "/saleAction", method = RequestMethod.GET)
	public String saleAction(HttpServletRequest request) {
		String signal = "";
		HttpSession session = request.getSession();
		Map<Object, Object> info = (Map<Object, Object>) session.getAttribute("info");
		
		gameservice.sale(info);
		signal ="saleOK";
		
		
		List<Map<Object, Object>> propertylist = gameservice.getPropertyList((String) request.getSession().getAttribute("station"));
		
		if(propertylist != null)
			session.setAttribute("propertylist", propertylist);//건물정보는 model로
			
			session.setAttribute("money", gameservice.getmoney((String) session.getAttribute("email")));
			session.setAttribute("station", request.getSession().getAttribute("station"));
			
			return "redirect:home?signal=" + signal;
	}
	
	
	@RequestMapping(value = "/investAction", method = RequestMethod.GET)
	public String investAction(HttpServletRequest request) {
		String signal = "";
		HttpSession session = request.getSession();
		Map<Object, Object> info = (Map<Object, Object>) session.getAttribute("info");
		
		int money = Integer.parseInt(session.getAttribute("money").toString());
		int investcost = Integer.parseInt(info.get("INVEST_COST").toString());
		signal = "investError";
		if(info != null && investcost < money){
			List<Map<Object, Object>> propertylist = gameservice.getPropertyList((String) request.getSession().getAttribute("station"));
			gameservice.invest(info);
			signal = "investOK";
		}
		
		
	List<Map<Object, Object>> propertylist = gameservice.getPropertyList((String) request.getSession().getAttribute("station"));
		
		if(propertylist != null)
			session.setAttribute("propertylist", propertylist);//건물정보는 model로
			
			session.setAttribute("money", gameservice.getmoney((String) session.getAttribute("email")));
			session.setAttribute("station", request.getSession().getAttribute("station"));
			
			return "redirect:home?signal=" + signal;
	}
	
	

	
}
