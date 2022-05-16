package com.study.board.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.study.board.dto.AttachDTO;
import com.study.board.mapper.AttachMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileCheckTask {
	
	@Autowired
	private AttachMapper attachMapper;
	
	//전일자 폴더 구하기
	private String getFolderYesterDay() {
		//어제 날짜 추출
		LocalDate yesterday = LocalDate.now().minusDays(1); // 2022-05-09
		//추출된 날짜의 포맷 변경 // "2022-05-09"
		String str = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return str.replace("-", File.separator); // "2022-05-09" => "2022\05\09"
	}
	
	@Scheduled(cron="0 0 2 * * *") //매일 새벽 2시
	public void checkFiles() {
		log.info("file check task run...");
		
		//어제 날짜의 첨부파일 리스트 가져오기(db)
		List<AttachDTO> oldList = attachMapper.getOldFiles();
		List<Path> fileListPaths = oldList.stream()
										  .map(dto -> Paths.get("e:\\coding\\upload", dto.getUploadPath(),dto.getUuid()+"_"+dto.getFileName()))
										  .collect(Collectors.toList());
		
		log.info("db"+fileListPaths);
		
		
		oldList.stream()
			   .filter(dto -> dto.isFileType() == true)
			   .map(dto -> Paths.get("e:\\coding\\upload", dto.getUploadPath(),"s_"+dto.getUuid()+"_"+dto.getFileName()))
			   .forEach(f -> fileListPaths.add(f));
		
		//전일자 폴더의 파일 목록 추출
		File targetDir = Paths.get("e:\\coding\\upload", getFolderYesterDay()).toFile();
		
		log.info("targetDir"+targetDir);
		
		
		File[] removeFiles = targetDir.listFiles(f -> fileListPaths.contains(f.toPath()) == false);
		
		//비교 후 폴더 안의 파일 삭제
		for(File remove:removeFiles) {
			log.info("제거파일" + remove.getAbsolutePath());
			remove.delete();
		}
	}
}
