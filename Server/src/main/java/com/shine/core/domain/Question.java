package com.shine.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Table(name = "QUESTION")
@Entity
public class Question {
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
