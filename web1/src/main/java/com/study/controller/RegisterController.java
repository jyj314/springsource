package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.dto.RegisterDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class RegisterController {

	//register.jsp 보여주는 컨트롤러 생성
	@GetMapping("/register")
	public void registerGet(){
		log.info("register.jsp 폼 보여주기");
	}
	
	//register.jsp 에서 post 들어오는 요청 컨트롤러 생성(RegisterDTO)
	@PostMapping("/register")
	public String registerPost(RegisterDTO register){
		log.info("회원가입정보 " + register);
		//로그인 페이지 보여주기 
		return "redirect:/login";
	}

	
}
