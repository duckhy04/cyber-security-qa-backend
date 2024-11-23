package org.example.cybersecurityqabackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.cybersecurityqabackend.dto.QuestionDto;
import org.example.cybersecurityqabackend.service.question.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/get")
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<QuestionDto> questions = questionService.getAllQuestion();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id) {
        QuestionDto questions = questionService.getQuestionById(id);
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/save")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionDto question = questionService.createQuestion(questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }
}
