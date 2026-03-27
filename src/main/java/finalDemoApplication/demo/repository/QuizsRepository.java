package finalDemoApplication.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import finalDemoApplication.demo.entity.Quizs;

public interface QuizsRepository extends JpaRepository<Quizs,Long> {

}
