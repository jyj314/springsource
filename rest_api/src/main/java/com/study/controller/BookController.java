package com.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.dto.BookDTO;
import com.study.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping("/index")
	public String insert() {
		return "/book/book_test";
	}
	
	@GetMapping(path="/list", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDTO>>list(){
		List<BookDTO>list = service.getList();
		
		return new ResponseEntity<List<BookDTO>>(list, HttpStatus.OK);
	}

	// /book/1000 + GET
	
	@GetMapping(path="/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDTO> get(@PathVariable("code") int code){
		return new ResponseEntity<BookDTO>(service.getRow(code),HttpStatus.OK);
	}
	
	// /book/1000 + DELETE ==> 성공시 success, 실패시 fail
	@DeleteMapping(path="/{code}")
	public ResponseEntity<String> delete(@PathVariable("code")int code){
		return service.bookDelete(code)? new ResponseEntity<String>("success",HttpStatus.OK) :
				new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	// /book/1000 + PUT + body(수정내용-json형태) 성공시 success, 실패시 fail
	 
	@PutMapping(path="/{code}")
	public ResponseEntity<String> update(@PathVariable("code")int code, @RequestBody BookDTO dto){
		return service.bookUpdate(code,dto.getPrice())?new ResponseEntity<String>("success",HttpStatus.OK) :
			new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/new")
	public ResponseEntity<String> insert(@RequestBody BookDTO insertDto){
		return service.bookInsert(insertDto)?new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	// 클라이언트로부터 데이터를 가져올 때 객체에 담고자 한다면?
	@PostMapping("/new2")
	public ResponseEntity<String> insert2(BookDTO insertDto){
		
		log.info("입력 "+insertDto);
		
		return service.bookInsert(insertDto)?new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	
	
}







