package com.sample.todolistjpamysql.model.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // DBの設定に従い値を設定される。（AUTOの場合JPAがそれぞれのDBに最適化された値を自動で設定）
	private long id;

	@NotEmpty
	private String description;

	@Size(min = 1, max = 5)
	private String status;

	@CreatedDate
	private Date createdDate;

	@CreatedBy
	private String createdBy;

	@LastModifiedDate
	private Date lastModifiedDate;

	@LastModifiedBy
	private String lastModifiedBy;

	private boolean isDeleted;

	public Todo() {

	}

	public Todo(String description, String status) {
		super();
		this.description = description;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", status=" + status + ", createdDate=" + createdDate
				+ isDeleted
				+ "]";
	}
}
