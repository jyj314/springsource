package com.study.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.study.jpa.entity.Memo;

//Spring Data JPA 는 여러종류의 인터페이스 기능을 통해서 JPA 관련작업을 처리
//CRUD, 페이징, 정렬을 인터페이스 메소드만 호출하면 처리됨

//JpaRepository <Entity, Id 타입> 인터페이스

//자동으로 스프링 빈으로 등록 됨
public interface MemoRepository extends JpaRepository<Memo, Long> {
	//사용할 수 있는 여러개의 메소드들이 제공 됨
	
	//새로운 메소드 생성도 가능함
	//mn를 기준으로 between 구문 사용하고 oreder by 적용 ==> 여러 개의 행이 조회
	//select * from memotbl where mno between 10 and 20 order by mno desc
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
	
	Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
	
	//메소드 생성 + @Query
//	@Query("select mno from memo order by mno desc")
//	List<Memo> getListDesc();
//	
//	@Query("update memo set momoText = :memoText where mno=:mno")
//	int updateMemoText(@Param("mno")Long mno,@Param("memoText") String memoText);
//	
//	//Native SQL 처리
//	@Query(value="select mno from memo oreder by mno desc", nativeQuery = true)
//	List<Memo> getNativeListDesc();
	
	
}





