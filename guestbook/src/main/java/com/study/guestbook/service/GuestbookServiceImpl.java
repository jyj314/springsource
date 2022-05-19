package com.study.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.guestbook.dto.GuestbookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.Guestbook;
import com.study.guestbook.entity.QGuestbook;
import com.study.guestbook.repository.GuestbookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuestbookServiceImpl implements GuestbookService {

	@Autowired
	private GuestbookRepository repository; 
	
	@Override
	public Long register(GuestbookDTO insertDto) {
		log.info("service register "+insertDto);
		
		//entity 변환
		Guestbook entity = dtoToEntity(insertDto);
		
		log.info("entity "+entity);
		
		//DB작업
		repository.save(entity);
		
		return entity.getGno();
	}

	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
		
		//Sort 기준
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		
		
		BooleanBuilder booleanBuilder = getSearch(requestDTO);
		
		
		//findAll 호출
		Page<Guestbook> result = repository.findAll(booleanBuilder,pageable);
		
		//Guestbook 타입의 매개변수를 받아 GuestbookDTO로 리턴
		Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<GuestbookDTO, Guestbook>(result,fn);
	}

	@Override
	public GuestbookDTO read(Long gno) {
		
		Optional<Guestbook> result = repository.findById(gno);
		
		// 화면단에서 사용 DTO
		// 서버단에서 사용 Entity
		
		return result.isPresent() ? entityToDto(result.get()) : null;
	}

	@Override
	public void remove(Long gno) {
		repository.deleteById(gno);
	}

	@Override
	public void modify(GuestbookDTO updateDto) {
		Optional<Guestbook> result = repository.findById(updateDto.getGno());
		
		if(result.isPresent()) {
			Guestbook entity = result.get();
			
			entity.setTitle(updateDto.getTitle());
			entity.setContent(updateDto.getContent());
			
			repository.save(entity);
		}
	}
	
	
	private BooleanBuilder getSearch(PageRequestDTO requestDto) {
	      
	      //검색 조건 가져오기
	      String type = requestDto.getType();
	      //검색어 가져오기
	      String keyword = requestDto.getKeyword();
	      
	      BooleanBuilder booleanBuilder = new BooleanBuilder();
	      QGuestbook qGuestbook = QGuestbook.guestbook;
	      
	      BooleanExpression expression = qGuestbook.gno.gt(0L);
	      booleanBuilder.and(expression);
	      
	      if(type == null || type.trim().length() == 0) {
	         return booleanBuilder;
	      }
	      
	      BooleanBuilder conditionBuilder = new BooleanBuilder();
	      
	      if(type.contains("t")) {
	         conditionBuilder.or(qGuestbook.title.contains(keyword));
	      }
	      if(type.contains("c")) {
	         conditionBuilder.or(qGuestbook.content.contains(keyword));
	      }
	      if(type.contains("w")) {
	         conditionBuilder.or(qGuestbook.writer.contains(keyword));
	      }
	      
	      booleanBuilder.and(conditionBuilder);
	      
	      return booleanBuilder;
	   }

}














