package com.learn.Quiz_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learn.Quiz_service.QuestionDao.QuizDao;
import com.learn.Quiz_service.QuestionDao.QuizInterface;
import com.learn.Quiz_service.model.QuestionWrapper;
import com.learn.Quiz_service.model.Quiz;
import com.learn.Quiz_service.model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao qdao;
	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Integer> selectedQuestions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

		// Create a new Quiz object
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(selectedQuestions);
		qdao.save(quiz);
		return new ResponseEntity<>("Quiz created successfully", HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Optional<Quiz> quiz = qdao.findById(id);
		List<Integer> questions = quiz.get().getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questionsforUser = quizInterface.getQuestionsByIds(questions);
		return questionsforUser;
	}

	public ResponseEntity<Integer> submitQuiz(int id, List<Response> responses) {
		ResponseEntity<Integer> score = quizInterface.getScore(responses);
		return score;
	}
}
