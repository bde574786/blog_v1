package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.model.User;

// Bean으로 등록 될 수 있나요? --> 스프링에서 Ioc에서 객체를 가지고 있나요,
public interface UserRepository extends JpaRepository<User, Integer>{

}
