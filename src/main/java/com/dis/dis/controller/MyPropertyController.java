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

import com.dis.dis.service.MyPropertyService;

@Controller
public class MyPropertyController {
	@Autowired
	private MyPropertyService mypropertyService;
	
	@RequestMapping(value = "/getmypropertyAction", method = RequestMethod.GET)
	public String getmypropertyAction(Model model,HttpServletRequest request) {
		System.out.println("getmypropertyAction");
		Map<Object, Object> session = (Map<Object, Object>) request.getSession().getAttribute("loginInfo");
		model.addAttribute("myproperty", mypropertyService.getmyproperty((String) session.get("PLAYER_ID")));
		
		return "myproperty";
	}
}
