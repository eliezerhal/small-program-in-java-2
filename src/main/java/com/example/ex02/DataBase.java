package com.example.ex02;

import java.util.ArrayList;

public class DataBase {
    public DataBase(ArrayList<String> questions) {
        myQuestions = questions;
        int numOfQuestions = myQuestions.size();
        answers = new ArrayList[numOfQuestions];
        for (int i = 0; i < answers.length; i++)
            answers[i] = new ArrayList<String>();
        names = new ArrayList[numOfQuestions];
        for (int i = 0; i < names.length; i++)
            names[i] = new ArrayList<String>();
    }
    private final ArrayList<String> myQuestions;
    private final ArrayList[] answers;
    private final ArrayList[] names;

    /*public int getNumOfQuestions() {
        return numOfQuestions;
    }*/
    public int getNumOfAnswers(int index) {
        return answers[index].size();
    }
    public ArrayList<String> getQuestions() {
        return myQuestions;
    }
    public void setAnswers(String answer, String name, int index) {
        answers[index].add(answer);
        try {
            names[index].add(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<String> getAnswers(int index) throws GeneralException {
        if(answers[index] == null)
            throw new GeneralException("the answers is null");
        return answers[index];
    }
    public ArrayList<String> getNames(int index) throws GeneralException {
        if(answers[index] == null)
            throw new GeneralException("the answers is null");
        return names[index];
    }
}
