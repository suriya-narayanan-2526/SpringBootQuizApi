package finalDemoApplication.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import finalDemoApplication.demo.entity.Questions;

public interface QuestionRepository extends JpaRepository<Questions,Long>{
	List<Questions> findByQuizId(Long id);

}
