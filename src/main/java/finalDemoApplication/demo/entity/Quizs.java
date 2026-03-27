package finalDemoApplication.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="quiz")
public class Quizs {
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="quiz_title Mandatory")
	private String quiz_title;
	@NotNull(message="question Mandatory")
	@OneToMany(mappedBy="quizs",cascade=CascadeType.ALL)
	private List<Questions> questions;

}
