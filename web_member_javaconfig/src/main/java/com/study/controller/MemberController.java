package com.study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;
import com.study.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService service;

	//step1 보여주는 컨트롤러 작성
	@GetMapping("/step1")
	public void step1() {
		log.info("step1 페이지 요청");
	}
	
	//step2 보여주는 컨트롤러 작성
	//http://localhost:9090/member/step2
	@PostMapping("/step2")
	public String step2(boolean agree, RedirectAttributes rttr) {
		log.info("step2(회원가입) 페이지 요청"+agree);
		//약관 동의를 했다면 step2 페이지를 보여주기
		if(!agree) {
			//안했다면 step1으로 되돌려 보내기
			rttr.addFlashAttribute("check","false");
			return "redirect:/member/step1";
		}
		return "/member/step2";
	}
	
	
	// step2 post 요청 처리하는 컨트롤러 작성
	// 회원가입 서비스 호출
	// 회원가입 성공시 signin.jsp 보여주기 (redirect)
	
	@PostMapping("/regist")
	public String regist(MemberDTO regist) {
		log.info("회원 가입 요청 " + regist);
		
		if(service.register(regist)) { //회원가입 성공
			return "redirect:/member/signin"; //로그인 페이지 보여주기 위해 이동
		}
		return "/member/step2"; //WEB-INF/view/member/step2.jsp 가기
	}
	
	@GetMapping("/signin")
	public void signin() {
		log.info("로그인 폼 요청");
	}
	
	//signin post
	//로그인 성공시 index 보여주기
	
	@PostMapping("/signin")
	public String signinPost(String userid, String password, HttpSession session) {
		log.info("로그인 요청 " + userid +" password "+password);
		
		AuthDTO authDto = service.login(userid, password);
		
		if(authDto==null) {
			return "redirect:/member/signin"; //로그인 실패시 로그인 페이지를 보여줘야 하니까
		}
		
		session.setAttribute("login", authDto);
		return "redirect:/";
	}
	
	//logout+ get => session 해제 후 index 이동
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("로그아웃 요청");
		
		session.invalidate();
		
		return "redirect:/";
		
	}
	
	//비밀번호 변경 폼 요청
	@GetMapping("/changePwd")
	public void changePwd() {
		log.info("비밀번호 변경 폼 요청");
	}
	
	//비밀번호 변경 -post
	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDto, HttpSession session, RedirectAttributes rttr) {
		log.info("비밀번호 변경 폼 요청"+changeDto);
		
		//현재비밀번호 확인
		//일치=> 비밀번호 변경, 세션해제, 로그인 폼으로 이동
		AuthDTO authDto = (AuthDTO)session.getAttribute("login");
		//세션에 있는 userid를 ChangeDTO 에 담아주기
		changeDto.setUserid(authDto.getUserid());
		
		if(service.login(changeDto.getUserid(), changeDto.getPassword())!=null) {
			service.changePwd(changeDto);
			session.invalidate();
			return "redirect:/member/signin";
			
		}else {
			//일치 안하면 비밀번호 변경 폼으로 돌아가기	
			rttr.addFlashAttribute("error", "현재 비밀번호를 확인해 주세요");
			return "redirect:/member/changePwd";
		}

	}
	
	//탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("탈퇴 폼 요청");
	}
	
	//탈퇴 post
	@PostMapping("/leave")
	public String leavePost(String userid, @RequestParam("current_password") String password, HttpSession session, RedirectAttributes rttr) {
		log.info("탈퇴 요청"+userid+" "+password);
		
		if(!service.leave(userid, password)) {
			rttr.addFlashAttribute("error", "현재 비밀번호를 확인해 주세요");
			return "redirect:/member/leave";
		}else {
			session.invalidate();
			return "redirect:/";
		}
		
	}
	
	// @Controller => 컨트롤러 종료시점에 view가 결정
	//				  void + /member/checkId => WEB-INF/views/member/checkId.jsp
	//				  String+ "checkId" => WEB-INF/views/checkId.jsp
	
	// 중복 아이디 검사
	@ResponseBody // 리턴하는 값이 jsp 아님. 문자그대로 받아드려라
	@PostMapping("/checkId")
	public String checkId(String userid) {
		log.info("중복 아이디 검사 "+userid);
		
		//userid1 값이 있다면 중복, 널이면 사용 가능
		if(service.dupId(userid)!=null) {
			return "false";
		}
		
		return "true";
	}

	
}













