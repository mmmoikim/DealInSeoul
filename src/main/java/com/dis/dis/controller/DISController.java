package com.dis.dis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dis.dis.service.HomeService;


@Controller
public class DISController {
	
	@Autowired
	private HomeService homeservice;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
	return "home";
	}
	@RequestMapping(value = "/ranking", method = RequestMethod.GET)
	public String ranking() {
	return "ranking";
	}
	@RequestMapping(value = "/propertyinfo", method = RequestMethod.GET)
	public String propertyinfo() {
	return "propertyinfo";
	}
	
	//자신 위치 기반 근접 정류소 조회(api)
	@RequestMapping(value="/searchlocationAction",method=RequestMethod.GET)
	public String searchlocationAction(HttpServletRequest request, Model model){
		Double [] location = { Double.parseDouble(request.getParameter("lat")),
				Double.parseDouble(request.getParameter("lon"))};
		
		Map<Object,Object> result = new HashMap<Object, Object>();
		result = homeservice.searchStation(location[0],location[1]);
		
		request.getSession().setAttribute("station","정류장없음");
		
		if(result != null)
		{
		request.getSession().setAttribute("station", (String)result.get("stationName"));
		request.getSession().setAttribute("stationid", (String)result.get("stationId"));
		}
		
		HttpSession session = request.getSession();
		List<Map<Object, Object>> propertylist = homeservice.getPropertyList((String)result.get("stationName"));
		
		int money = Integer.parseInt(session.getAttribute("money").toString());
		if(propertylist != null)
		model.addAttribute("propertylist", propertylist);//건물정보는 model로
		
		session.setAttribute("money", homeservice.getmoney((String) session.getAttribute("email")));
		session.setAttribute("station", request.getSession().getAttribute("station"));
	
		
		return "home";
	}
	
	
	@RequestMapping(value = "/GameOKAction", method = RequestMethod.GET)
	public String GameOKAction(Model model,HttpServletRequest request) {
		
		//정류장 재검색
		HttpSession session = request.getSession();
		List<Map<Object, Object>> propertylist = homeservice.getPropertyList((String) request.getSession().getAttribute("station"));
		
		int money = Integer.parseInt(session.getAttribute("money").toString());
		if(propertylist != null)
		model.addAttribute("propertylist", propertylist);//건물정보는 model로
		
		session.setAttribute("money", homeservice.getmoney((String) session.getAttribute("email")));
		session.setAttribute("station", request.getSession().getAttribute("station"));
	return "home";
	}
	
}
