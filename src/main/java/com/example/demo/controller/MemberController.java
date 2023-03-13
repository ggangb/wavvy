package com.example.demo.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.userService;

import vo.BoardVO;
import vo.UserVO;




@Controller
public class MemberController {
	
	@Autowired
	private userService service;
	
	@RequestMapping("/login_form")
	public String login() {
		return "page/login_form";
	}
	
	
	@RequestMapping("/signup")
	public String join() {
		return "page/signup";
	}
	
	@RequestMapping("/join")
	public String join(UserVO user) {
		if(service.join(user)) {
			return "page/join_success";
		} else {
			return "page/join_fail";
		}
	}
	
	@RequestMapping("/login")
	public String login(String userId, @RequestParam("userPw") String userPw, HttpSession session) {
		System.out.println(userId);
		System.out.println(userPw);
		if(service.login(userId, userPw)) {
			session.setAttribute("loginId", userId);
			System.out.println("성공");
			return "page/login_success";
		} else {
			System.out.println("실패");
			return "page/login_fail";
		}
	}
	
	@RequestMapping("/myPage")
	public ModelAndView myPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		String loginId = (String) session.getAttribute("loginId");
		if (loginId != null && loginId.length() > 0 && !loginId.contains("kakao")) {
			System.out.println(!loginId.contains("kakao"));
			UserVO user = service.getUserInfo(loginId);
			mv.addObject("userInfo", user);
			mv.setViewName("page/my_page");
		} else {
			mv.setViewName("page/kakao_login");
		}
		return mv;
	}

	@RequestMapping("/userupdateForm")
	public ModelAndView userupdate(int userNum) {

		UserVO user = service.userselect(userNum);

		ModelAndView mv = new ModelAndView();

		mv.addObject("original", user);
		mv.setViewName("page/user_update");

		return mv;
	}

	@RequestMapping("/userdelete")
	public ModelAndView userdelete(int userNum, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");

		boolean result = service.userdelete(userNum, loginId);
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.setViewName("page/delete_user_result");
		return mv;
	}

	@RequestMapping(value = "/userupdate", method = RequestMethod.POST)
	public ModelAndView userupdate(UserVO user, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");

		boolean result = service.userupdate(user, loginId);
		ModelAndView mv = new ModelAndView();

		if (result) {
			mv.addObject("userNum", user.getUserNum());
			mv.setViewName("page/update_user_success");
		} else {
			mv.setViewName("page/update_user_fail");
		}
		return mv;
	}
	
//	@RequestMapping("/login")
//	public ModelAndView login(String userId, @RequestParam("userPw") String userPw, HttpSession session) {
//		ModelAndView mv = new ModelAndView();
//		System.out.println(userId);
//		System.out.println(userPw);
//		if(service.login(userId, userPw)) {
//			session.setAttribute("loginId", userId);
//			System.out.println("성공");
//			mv.setViewName("page/main");
//			return mv;
//		} else {
//			System.out.println("실패");
//			mv.setViewName("page/login_fail");
//			return mv;
//		}
//	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "page/logout";
	}
	
	/*
	 * @RequestMapping("") public ModelAndView main() { ModelAndView mv = new
	 * ModelAndView(); mv.setViewName("page/main"); return mv; }
	 */
	
	
}
