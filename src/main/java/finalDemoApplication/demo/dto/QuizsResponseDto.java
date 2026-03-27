package finalDemoApplication.demo.dto;

import java.util.List;

import finalDemoApplication.demo.entity.Questions;

public class QuizsResponseDto {
	public String getQuiz_title() {
		return quiz_title;
	}
	public void setQuiz_title(String quiz_title) {
		this.quiz_title = quiz_title;
	}
	public List<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
	private String quiz_title;
	private List<Questions> questions;
}
