package com.study.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/sample/*") // http://localhost:9090/sample/**
public class SampleController {
	
		// 앞쪽 고정 : /WEB-INF/views/
		// 뒤쪽 고정 : .jsp
	
		// 컨트롤러의 리턴 타입이 void 인 경우
		// http://localhost:9090/ 다음 부분이 jsp 페이지를 찾는 데 사용됨
	
		// 컨트롤러의 리턴 타입이 String 인 경우
		// return 값 만을 가지고 jsp 페이지를 찾음

		//@RequestMapping(path = "/basic", method = RequestMethod.GET) // http://localhost:9090/sample/basic
		@GetMapping("/basic")
		public void basic(@ModelAttribute("page") int page, Model model) {
			log.info("basic..."); // WEB-INF/views/sample/basic.jsp
			
			//page 변수값을 jsp 보여주기 : Model에 담기
			//model.addAttribute("page",page);
			
		}
		
		// Controller 파라미터 수집
		// ① 변수명 사용
		// ② ~DTO 객체 사용
		// ③ HttpServletRequest 객체 사용 (필요한 경우만- 대부분 사용 잘 안 함)
		
		// @RequestParam("이름") : 파라미터로 사용된 이름과 전달되는 파라미터의 이름이 다른 경우 사용

		
		//@RequestMapping(path = "/doA", method = RequestMethod.GET) //http://localhost:9090/sample/doA
		@GetMapping("/doA")
		public void doA(String userid) {
			log.info("doA.."+userid); // WEB-INF/views/sample/doA.jsp
		}
		
		//@RequestMapping(value = "/insert", method = RequestMethod.GET) //http://localhost:9090/sample/insert
	    @GetMapping("/insert")
	    public String insert(@RequestParam("ids") ArrayList<String> ids) {
	       log.info("insert...."+ids); //  /WEB-INF/views/insert.jsp
	       return "insert";
	   }
}
