package com.learn.Quiz_service.QuestionDao;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.Quiz_service.model.QuestionWrapper;
import com.learn.Quiz_service.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@GetMapping("getQuestionForQuiz")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int numQ);

	@PostMapping("getQuestionsByIds")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Integer> questionIds);

	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> Responses);

}
