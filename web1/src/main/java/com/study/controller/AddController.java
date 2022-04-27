package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.NumDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/calc/*")
public class AddController {
	
	@GetMapping("/add")
	public void addGet() {
		log.info("add.jsp 페이지 요청..");
	}	
	
//	@PostMapping("/add")
//	public void addPost(@RequestParam("num1") int op1,@RequestParam("num2")int op2) {
//		log.info("덧셈 요청");
//		//log.info("num1+num2 = "+(num1+num2));
//		log.info("num1+num2 = "+(op1+op2));
//	}
	
//	@PostMapping("/add")
//	public void addPost(@RequestParam(value="num1", required=false, defaultValue="0") int num1,
//			@RequestParam(value="num2", required=false, defaultValue="0")int num2) {
//		log.info("덧셈 요청");
//		log.info("num1+num2 = "+(num1+num2));
//		
//		// 어느 jsp로 갈것인가? calc/add
//	}	
	
	// @ModelAttribute("이름") : 바인딩 객체의 이름을 변경
	// 						  : Model 객체에 값을 담는 것과 같은 기능 제공
	
	@PostMapping("/add")
	public void addPost(@ModelAttribute("dto") NumDTO dto, Model model) {
		log.info("덧셈 요청");
		log.info("num1+num2 = "+(dto.getNum1()+dto.getNum2()));
		
		int result = dto.getNum1()+dto.getNum2();
		//result 값을 add.jsp 에서 보여주기 : Model 객체(request.setAttribute() 와 같은 개념)
		model.addAttribute("result",result);
	}
	
}
