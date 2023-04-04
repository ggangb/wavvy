package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.OAuthService;

@RestController
@RequestMapping("/oauth")
public class OAuthController {
	
	@Autowired
	private OAuthService service;
	
	@ResponseBody
	@GetMapping("/kakao")
	public ModelAndView kakaoCallback(@RequestParam String code, HttpSession session) {
		System.out.println(code);
		String token = service.getKakaoAccessToken(code);
		session.setAttribute("loginId", "[Kakao]"+service.loginKakao(token).substring(0, 5));
//		session.setAttribute("kakaoId", service.loginKakao(token));
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("page/login_success");
		return mv;
			
		
	}
	
	@ResponseBody
	@GetMapping("/naver")
	public ModelAndView naverCallback(@RequestParam String code, @RequestParam String state, HttpSession session) {
		System.out.println(code);
		String token = service.getNaverAccessToken(code, state);
		service.loginNaver(token);
		session.setAttribute("loginId", "[Naver]"+service.loginNaver(token));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page/login_success");
		return mv;
		
	}
	
}
