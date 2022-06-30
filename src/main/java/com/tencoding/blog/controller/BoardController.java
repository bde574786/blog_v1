package com.tencoding.blog.controller;


import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 5, 
		sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		Page<Board> pageBoards = boardService.getBoardList(pageable);
		
		// [1 2 3 4 5 '6' 7 8 9 10]
		// 1. 현재 페이지에 앞 뒤로 5 블록(칸)씩 보여야 한다.
		// 2. 현재 페이지에 'active' 하기
		// 3. 페이지 버튼을 누르면(블록) 해당 페이지로 화면을 이동해야 한다.
		
		int startPage = pageBoards.getPageable().getPageNumber() - 5;
		int endPage = pageBoards.getPageable().getPageNumber() + 5;
		
		
		// 시작 페이지를 설정해야한다.
		
		// 페이지 번호를 배열로 만들어서 던져주기
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		// 마지막 번호 까지 저장하기
		for (int i = 0; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("pageable", pageBoards);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
//		model.addAllAttributes("pageNumbers" pageNumbers);
		
		
		return "index";
	}
	
	@GetMapping("/board/save_form")
	public String saveForm() {
		log.info("saveForm 메서드 호출");
		return "/board/save_form";		
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}

	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/update_form";
	}
	
	
	
}
