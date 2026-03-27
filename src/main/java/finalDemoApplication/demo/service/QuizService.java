package finalDemoApplication.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finalDemoApplication.demo.dto.QuizsResponseDto;
import finalDemoApplication.demo.entity.Questions;
import finalDemoApplication.demo.entity.Quizs;
import finalDemoApplication.demo.repository.QuestionRepository;
import finalDemoApplication.demo.repository.QuizsRepository;

@Service
public class QuizService {
	@Autowired
	private QuizsRepository quizRepo;
	@Autowired
	private ModelMapper model;
	@Autowired
	private QuestionRepository quesRepo;

	public List<Quizs> getAllQuiz() {
		return quizRepo.findAll();
	}
	public QuizsResponseDto createQuiz(Quizs quiz)
	{
		for(Questions questions : quiz.getQuestions())
		{
			questions.setQuizs(quiz);
		}
		Quizs data =quizRepo.save(quiz);
		return model.map(data, QuizsResponseDto.class);
	}
	public List<Questions> getAllQuizById(Long id)
	{
		return quesRepo.findByQuizs_Id(id);
	}
}
