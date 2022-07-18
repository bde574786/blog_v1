package com.tencoding.blog.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.tencoding.blog.dto.ReplyCountOfBoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 컴포넌트 스캔을 할 때 자동으로 메모리에 올라감
@Repository
public class ReplyCountOfBoardRepository {
	
	private final EntityManager em;
	
	public List<ReplyCountOfBoardDto> getReplyCount() {
		
		List<ReplyCountOfBoardDto> list = new ArrayList<ReplyCountOfBoardDto>();
		
		// 1. 직접 쿼리문 만들기
		String queryStr = "SELECT A.id, A.content, (SELECT COUNT(boardId) "
				+ "			FROM reply AS b "
				+ "            WHERE b.boardId = a.id) AS replyCount "
				+ "FROM board AS a "; // 줄바꿈 없애고 공백추가, 세미 콜론도 지우고 공백 추가 
		 
		Query nativeQuery = em.createNativeQuery(queryStr); // 문자열을 통해 질의어를 생성
		
		// 2가지 방식
		// 1. 직접 문자열을 컨트롤해서 object로 매핑하는 방식
		// 2. QLRM 라이브러리를 사용해서 objec로 매핑하는 방식
		
//		List<Object[]> resultList = nativeQuery.getResultList();
//		System.out.println(resultList.toString());
//		resultList.forEach(t -> {
//			System.out.println(t.toString());
//		});
		
		// QLRM 라이브러리 사용
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		list = jpaResultMapper.list(nativeQuery, ReplyCountOfBoardDto.class);
		
		
		return list;
	}
}
