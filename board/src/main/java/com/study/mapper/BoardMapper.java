package com.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.dto.BoardDTO;
import com.study.dto.Criteria;

public interface BoardMapper {

	//CRUD 작업 메소드 선언
	
	//C
	public int insert(BoardDTO insertDto);
	
	//R 전체 리스트
	public List<BoardDTO> select(Criteria cri);
	
	//R 게시물 들어가기
	public BoardDTO read(int bno);
	
	//U
	public int update(BoardDTO updateDto);
	
	//D
	public int delete(int bno);
	
	//페이지나누기
	public int totalCnt(Criteria cri);
	
	//댓글 cnt 수정
	public int updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);
}
