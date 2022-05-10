package com.study.service;

import java.util.List;

import com.study.dto.AttachDTO;
import com.study.dto.BoardDTO;
import com.study.dto.Criteria;

public interface BoardService {
	//전체리스트
	public List<BoardDTO> getList(Criteria cri);
	//글쓰기
	public void insert(BoardDTO insertDto);
	//게시물 읽어오기
	public BoardDTO getRow(int bno);
	//게시물 수정하기
	public boolean update(BoardDTO updateDto);
	//게시물 삭제하기
	public boolean delete(int bno);
	//페이지나누기
	public int getTotalCnt(Criteria cri);
	
	//첨부파일
	public List<AttachDTO> attachList(int bno);
}
