package org.example.cybersecurityqabackend.service.question;

import org.example.cybersecurityqabackend.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    QuestionDto update(QuestionDto questionDto);

    List<QuestionDto> getAllQuestion();

    QuestionDto getQuestionById(Long id);

    void delete(Long id);

    QuestionDto createQuestion(QuestionDto questionDto);
}
