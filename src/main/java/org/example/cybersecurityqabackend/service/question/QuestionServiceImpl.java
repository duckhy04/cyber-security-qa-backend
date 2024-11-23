package org.example.cybersecurityqabackend.service.question;

import org.example.cybersecurityqabackend.dto.QuestionDto;
import org.example.cybersecurityqabackend.entity.Category;
import org.example.cybersecurityqabackend.entity.Question;
import org.example.cybersecurityqabackend.entity.User;
import org.example.cybersecurityqabackend.exception.CustomResourceNotFoundException;
import org.example.cybersecurityqabackend.repository.CategoryRepository;
import org.example.cybersecurityqabackend.repository.QuestionRepository;
import org.example.cybersecurityqabackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        User user = userRepository.findById(questionDto.getUser().getId()).orElseThrow(() ->
                new CustomResourceNotFoundException("User not found"));
        Category category = categoryRepository.findById(questionDto.getCategory().getId()).orElseThrow(
                () -> new CustomResourceNotFoundException("Category not found")
        );
        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setContent(questionDto.getContent());
        question.setUser(user);
        question.setCategory(category);
        Question createQuestion = questionRepository.save(question);
        return createQuestion.toDto();
    }

    @Override
    public QuestionDto update(QuestionDto questionDto) {
        return null;
    }

    @Override
    public List<QuestionDto> getAllQuestion() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(Question::toDto).collect(Collectors.toList());
    }

    @Override
    public QuestionDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new CustomResourceNotFoundException("Question not found"));
        return question.toDto();
    }

    @Override
    public void delete(Long id) {

    }
}
