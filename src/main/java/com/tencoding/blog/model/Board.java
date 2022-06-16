package com.tencoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content;

	@ColumnDefault(" 0 ") // int // String " '안녕' "
	private int count; // 조회수

	// 하나의 게시글에 작성자가 2 사람???
	// 여러개의 게시글은 하나의 유저를 가진다.
	@ManyToOne // Many == Board, One == User
	@JoinColumn(name = "userId")
	private User userId;

	@CreationTimestamp
	private Timestamp createDate;

}
