package com.example.tracnghiem.DTO;

import com.example.tracnghiem.Model.UserResult;

import java.util.Date;
import java.util.List;

public class QuizResultDTO {
    private int id;
    private UserDTO user;
    private int idQuiz;
    private String quizTitle;
    private int score;
    private int totalQuestions;
    private Date submitted_at;
    private List<UserResultDTO> userResultDTOList;

    public QuizResultDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Date getSubmitted_at() {
        return submitted_at;
    }

    public void setSubmitted_at(Date submitted_at) {
        this.submitted_at = submitted_at;
    }

    public List<UserResultDTO> getUserResultDTOList() {
        return userResultDTOList;
    }

    public void setUserResultDTOList(List<UserResultDTO> userResultDTOList) {
        this.userResultDTOList = userResultDTOList;
    }
}
