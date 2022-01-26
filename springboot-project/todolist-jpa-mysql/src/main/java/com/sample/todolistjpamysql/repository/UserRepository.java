package com.sample.todolistjpamysql.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.todolistjpamysql.model.dto.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}