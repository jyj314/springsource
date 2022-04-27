package com.study.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Spring 이 관리하지 않음

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private int code;
	private String title;
	private String writer;
	private int price;
}
