package com.sample.todolistjpamysql.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sample.todolistjpamysql.model.dto.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
	List<Todo> findByIsDeleted(boolean isDeleted);
}