package com.sample.todolistjpamysql.repository;

import org.springframework.data.repository.CrudRepository;

import com.sample.todolistjpamysql.model.dto.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}