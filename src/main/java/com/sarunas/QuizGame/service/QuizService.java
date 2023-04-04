package com.sarunas.QuizGame.service;

import com.sarunas.QuizGame.model.Question;
import com.sarunas.QuizGame.model.QuestionDTO;
import com.sarunas.QuizGame.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {


    @Autowired
    private QuestionRepository questionRepository;


    public QuestionDTO addQuestion(Question question) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        question.setUserName(userName);
        questionRepository.save(question);
        return new QuestionDTO(question.getId(),
                question.getTitle(),
                question.getText(), question.getOptions(),
                question.getAnswer());
    }

    public QuestionDTO getQuestionById(int id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()) {
            return null;
        } else {
            Question question = optionalQuestion.get();
            return new QuestionDTO(question.getId(),
                    question.getTitle(),
                    question.getText(), question.getOptions(),
                    question.getAnswer());
        }
    }

    public List<QuestionDTO> getAllQuestions() {
        Iterable<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionsDto = Collections.synchronizedList(new ArrayList<>());
        for (Question question : questions) {
            questionsDto.add(new QuestionDTO(question.getId(), question.getTitle(),
                    question.getText(), question.getOptions(), question.getAnswer()));
        }
        return questionsDto;
    }

    public Optional<Question> getQuestionToDeleteById(int id) {
        return questionRepository.findById(id);
    }

    public void deleteQuiz(Question question) {
        questionRepository.delete(question);
    }
}