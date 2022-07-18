package com.tencoding.blog.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ReplyCountOfBoardDto {
	
	private int id;
	private String content;
	private int replyCount;
	
	// JpaResultMapper과 동일한 컴럼 수를 맞추고 동일한 데이터 타입을 선언한다면 직접 커스텀 할 필요는 없다.
	// 알아서 매핑을 진행한다.
//	private Integer id;
//	private String content;
//	private BigInteger replyCount;
	
	// 직접 커스텀하기
	// 커스텀하지 않으려면 mysql에서 넘어오는 데이터 타입을 맞춰주면 된다.
	public ReplyCountOfBoardDto(Object[] objs) {
		this.id = ((Integer)objs[0]).intValue();
		this.content = ((String)objs[1]);
		this.replyCount = ((BigInteger)objs[2]).intValue();
	}
	
	public ReplyCountOfBoardDto(Integer id, String content, BigInteger replyCount) {
		this.id = id.intValue();
		this.content = content;
		this.replyCount = replyCount.intValue();
	}
	
}
