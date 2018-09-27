package com.shine.api.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping("question/")
public class QuestionEndPoint {

	@PostMapping(path = "/")
	public ResponseEntity createNewQuestion(HttpServletRequest request) {
		return null;
	}

}
