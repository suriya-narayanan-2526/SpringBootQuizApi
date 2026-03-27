package finalDemoApplication.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finalDemoApplication.demo.dto.QuizsResponseDto;
import finalDemoApplication.demo.entity.Quizs;
import finalDemoApplication.demo.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizsController {
	@Autowired
	private QuizService service;
	@PostMapping("/create")
	public ResponseEntity<QuizsResponseDto> createQuiz (@RequestBody Quizs quiz)
	{
		return ResponseEntity.ok().body(service.createQuiz(quiz));
	}
}
