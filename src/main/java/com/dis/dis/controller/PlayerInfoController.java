package com.dis.dis.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dis.dis.service.HomeService;
import com.dis.dis.service.PlayerInfoService;
import com.dis.dis.vo.PlayerVO;
@Controller
public class PlayerInfoController {
	
	@Autowired
	private PlayerInfoService playerInfoService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
	return "signup";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginhome() {
	return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
	return "login";
	}
	@RequestMapping(value = "/myproperty", method = RequestMethod.GET)
	public String myproperty() {
	return "myproperty";
	}
	@RequestMapping(value = "/pwsetting", method = RequestMethod.GET)
	public String pwsetting() {
	return "pwsetting";
	}
	@RequestMapping(value = "/searchpassword", method = RequestMethod.GET)
	public String searchpassword() {
		return "searchpassword";
	}
	
	@RequestMapping(value = "/emailsetting", method = RequestMethod.GET)
	public String emailsetting() {
	return "emailsetting";
	}
	@RequestMapping(value = "/signupAction", method = RequestMethod.POST)
	public String signupAction(HttpServletRequest request) {
		String signal = "signupOK";
		try{
			playerInfoService.signup(new PlayerVO(request.getParameter("id"), request.getParameter("pw"), request.getParameter("email")));
		}catch(Exception e){
			signal = "signupError";
		}
	return "redirect:login?signal="+signal;
	}
	

	@RequestMapping(value = "/loginAction", method = RequestMethod.GET)
	public String loginAction(HttpServletRequest request,Model model) {
		
		String signal = "";
		try {
			playerInfoService.checkUpdateDay();
			Double [] location = { Double.parseDouble(request.getParameter("lat")),
					Double.parseDouble(request.getParameter("lon"))};
			
			Map<Object,Object> result = new HashMap<Object, Object>();
			
			Map<Object, Object> loginResult = playerInfoService.login(new PlayerVO(request.getParameter("email"),request.getParameter("pw")));
			List<Map<Object, Object>> propertylist = new ArrayList<Map<Object,Object>>();
			result = playerInfoService.searchStation(location[0],location[1]);
			
			
			if(loginResult != null)
			{
				request.getSession().setAttribute("loginInfo", loginResult);//로긴정보는 세션
			}
			else
			{
				return "redirect:login?signal=loginError";
			}
			
			request.getSession().setAttribute("station","정류장없음");
			if(result != null){
				request.getSession().setAttribute("station", (String)result.get("stationName"));
				request.getSession().setAttribute("stationid", (String)result.get("stationId"));
				propertylist = playerInfoService.getPropertyList((String)result.get("stationName"));
			}
			
			
			
			if(propertylist != null)
			request.getSession().setAttribute("propertylist", propertylist);//건물정보는 model로
			
			request.getSession().setAttribute("email", request.getParameter("email"));
			request.getSession().setAttribute("money", playerInfoService.getmoney(request.getParameter("email")));
			return "redirect:home?signal=loginOK";
		} 
		catch (ParseException e) {
			return "redirect:login?signal=loginError";
		}
		
		
	}
	
	
	@RequestMapping(value = "/changepwAction", method = RequestMethod.POST)
	public String changepwAction(HttpServletRequest request) {
		int temp = playerInfoService.changePw(request.getParameter("currentpw"), request.getParameter("newpw"));
		
		if(temp == 0)
			return "redirect:pwsetting?signal=changepwError";
		
		return "redirect:pwsetting?signal=changepwOK";
	}
	
	@RequestMapping(value = "/changeemailAction", method = RequestMethod.POST)
	public String changeemailAction(HttpServletRequest request) {
		int temp = playerInfoService.changeEmail(new PlayerVO(request.getParameter("newemail"), request.getParameter("pw")));
		if(temp == 0)
			return "redirect:emailsetting?signal=changeemailError";
		
		return "redirect:emailsetting?signal=changeemailOK";
	}
	
	@RequestMapping(value = "/logoutAction", method = RequestMethod.GET)
	public String logoutAction(HttpServletRequest request){
	request.getSession().invalidate();
		return "login";
	}
	//회원 탈퇴
	@RequestMapping(value = "/deleteplayerAction", method = RequestMethod.GET)
	public String deleteplayerAction(HttpServletRequest request){
		Map<Object, Object> session = (Map<Object, Object>) request.getSession().getAttribute("loginInfo");
		playerInfoService.deleteplayer((String) session.get("PLAYER_ID"));
		return "login";
	}
	@RequestMapping(value="/emailcheckAction",method=RequestMethod.POST)
	public String emailcheckAction(@RequestBody String email,HttpServletRequest request){
		int result=playerInfoService.emailcheck(email);
		String message="<img src='resources/image/ok.png' id='ok'>";
		if(result>=1)
			message="<img src='resources/image/error.png' id='error'>";
		request.getSession().setAttribute("resultmessage", message);
		return "emailcheck";
	}
	@RequestMapping(value="/idcheckAction",method=RequestMethod.POST)
	public String idcheckAction(@RequestBody String id,HttpServletRequest request){
		int result=playerInfoService.idcheck(id);
		String message="<img src='resources/image/ok.png' id='ok'>";
		if(result>=1)
			message="<img src='resources/image/error.png' id='error'>";
		request.getSession().setAttribute("resultemailmessage", message);

		return "idcheck";
	}
	
	@RequestMapping(value="/searchpasswordAction",method=RequestMethod.GET)
	public String searchpasswordAction(HttpServletRequest request,String email){
		String resultPW=playerInfoService.searchpassword(email);
		if(resultPW==null){
			resultPW="1";
			request.getSession().setAttribute("resultPW", resultPW);
			return "searchpassword";
		}
		request.getSession().setAttribute("resultPW", resultPW);

		return "login";
	}

}
