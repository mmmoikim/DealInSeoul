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
import com.dis.dis.service.RankingService;
import com.dis.dis.vo.PlayerVO;

@Controller
public class RankingController {
	@Autowired
	private RankingService rankingservice;
	
	@RequestMapping(value="/rankingAction",method=RequestMethod.GET)
	public String RankingAction(HttpServletRequest request, Model model){
		System.out.println("Ranking Action");
		System.out.println(request.getSession().getAttribute("loginInfo"));
		Map<Object, Object> player = (Map<Object, Object>)request.getSession().getAttribute("loginInfo");
		System.out.println(player.get("ASSET"));
		int asset = Integer.parseInt(player.get("ASSET").toString());
		model.addAttribute("rankinglist", rankingservice.getRanking(asset));
		return "ranking";
	}
	
	@RequestMapping(value="/lineupAction",method=RequestMethod.GET)
	public void LineupAction(HttpServletRequest request, Model model){
		System.out.println("lineup Action");
		rankingservice.setRankinglineup();
		
	}
}
