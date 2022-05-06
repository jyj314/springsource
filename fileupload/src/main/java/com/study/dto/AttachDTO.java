package com.study.dto;

import lombok.Data;

@Data
public class AttachDTO {
	private String uuid;
	private String uploadPATH;
	private String fileName;
	private boolean fileType; //이미지면1, 아니면 0
}
