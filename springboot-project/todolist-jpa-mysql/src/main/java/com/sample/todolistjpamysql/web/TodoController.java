package com.sample.todolistjpamysql.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sample.todolistjpamysql.model.dto.Todo;
import com.sample.todolistjpamysql.repository.TodoRepository;

@Controller
public class TodoController {
	@Autowired
	private TodoRepository repository;

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Show all todos
	@GetMapping(value = "/todos")
	public String todoList(Model model) {
		// model.addAttribute("todolist", repository.findAll()); // 全データを取得する場合はfindAllを使用
		model.addAttribute("todolist", repository.findByIsDeleted(false));
		return "todos";
	}

	// Add new todo
	@GetMapping(value = "/add")
	public String addTodo(Model model) {
		model.addAttribute("todo", new Todo());
		return "addtodo";
	}

	// Save todo
	@PostMapping(value = "/save")
	public String save(@Valid Todo todo, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addtodo";
		}
		// テーブルのレコード数を取得してプラス1の値をセット
		todo.setId(repository.count() + 1);
		repository.save(todo);
		return "redirect:todos";
	}

	// Delete todo 物理削除
	//	@PostMapping(value = "/delete")
	//	public String delete(long id) {
	//		repository.deleteById(id);
	//		return "redirect:todos";
	//	}

	// Delete todo 論理削除
	@PostMapping(value = "/delete")
	public String delete(long id) {
		Optional<Todo> todo = repository.findById(id); // Optional型はメソッドの戻り値に利用。
		todo.ifPresentOrElse(function -> todo.get().setDeleted(true), () -> todo.get().setDeleted(false)); // 1つ目がnullでない、2つ目がnullの場合
		repository.save(todo.get());
		return "redirect:todos";
	}

}
