package com.sarunas.QuizGame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="questions")
public class Question {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    private String title;
    @NotEmpty
    private String text;

    @NotNull
    @Size(min=2)
    private String[] options;

    @NotNull
    private int[] answer;

    @JsonIgnore
    private String userName;


    public Question() {
        options = new String[0];
        answer = new int[0];
    }


    public Question(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = (answer == null ? new int[]{} : answer);
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int[] getAnswer() {
        Arrays.sort(answer);
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && Objects.equals(title, question.title) && Objects.equals(text, question.text) && Arrays.equals(options, question.options) && Arrays.equals(answer, question.answer) && Objects.equals(userName, question.userName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, text, userName);
        result = 31 * result + Arrays.hashCode(options);
        result = 31 * result + Arrays.hashCode(answer);
        return result;
    }
}
