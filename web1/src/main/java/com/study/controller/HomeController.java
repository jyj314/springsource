package com.study.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.RegisterDTO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";	
	}
	
	// redirect 방식으로 움직일 때 값을 전송하는 방법
	// ⓢ rttr.addAttribute("age", 10); 주소줄에 age라는 이름으로 10 보내는 방식
	//   http:/~~~~~~~~~~~ ?age=10
	//   path +="?page=+page+"&amount="+amount...
	
	// ② rttr.addFlashAttribute("num","15"); 세션 객체에 담는 방식(일회성)
	
	@GetMapping("/doB")
	public String doB(RedirectAttributes rttr) {
		rttr.addAttribute("age", 10);
		rttr.addAttribute("addr", "서울");
		rttr.addAttribute("name", "홍길동");
		
		rttr.addFlashAttribute("num","15");
		return "redirect:/";
	}
	
	// 예전 방식
	@GetMapping("/doC")
	public ModelAndView doC() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("num", 35); // request.setAttribute()
		return mav;
	}
	
//	@GetMapping("/doD")
//	public RegisterDTO regist() {
//		return new RegisterDTO();
//	}
	
}
