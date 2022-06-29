package com.tencoding.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.Reply;
import com.tencoding.blog.repository.BoardRepository;
import com.tencoding.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@GetMapping("/test/board/{boardId}")
	public Board getBoard(@PathVariable int boardId) {
		// jackson 라이브러리 실행될 때 오브젝트 파싱(json을 파싱할 때 getter을 호출)
		//무한 참조 발생
		return boardRepository.findById(boardId).get();
	}

	
	/**
	 * 
	 * board를 호출했을 때 reply에 포함된 board를 무시하고
	 * Reply에서 호출했을 때는 무시하지 않는다.
	 * 
	 * detail.jsp에서 reply.board를 호출하는 순간 무한참조가 일어난다. (stack overflow 발생)
	 * 하지만 호출하지 않았기 때문에 발생하지 않았다.
	 * 
	 * 해결 방법은 jsonIgnoreProperties 사용
	 */
	
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();
	}
	
	
}
