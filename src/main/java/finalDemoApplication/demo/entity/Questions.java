package finalDemoApplication.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="questions")
public class Questions {
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getRight_answer() {
		return right_answer;
	}
	public void setRight_answer(String right_answer) {
		this.right_answer = right_answer;
	}
	public Quizs getQuizs() {
		return quizs;
	}
	public void setQuizs(Quizs quizs) {
		this.quizs = quizs;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Must need question title")
	private String question_title;
	@NotBlank(message="Option mandatory")
	private String option1;
	@NotBlank(message="Option mandatory")
	private String option2;
	@NotBlank(message="Option mandatory")
	private String option3;
	@NotBlank(message="Option mandatory")
	private String option4;
	@NotBlank(message="Must correct Answer")
	private String right_answer;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="quizs_id")
	private Quizs quizs;

}
