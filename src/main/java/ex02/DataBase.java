package ex02;

import java.util.ArrayList;

/**
 * This is a classroom that takes care of the program's data retention
 */
public class DataBase {
    /**
     * This is the class constructor function
     * @param questions the array of questions
     */
    public DataBase(ArrayList<String> questions) {
        myQuestions = questions;
        int numOfQuestions = myQuestions.size();
        answers = new ArrayList[numOfQuestions];
        for (int i = 0; i < numOfQuestions; i++)
            answers[i] = new ArrayList<String>();
        names = new ArrayList[numOfQuestions];
        for (int i = 0; i < numOfQuestions; i++)
            names[i] = new ArrayList<String>();
    }
    private final ArrayList<String> myQuestions;
    private final ArrayList[] answers;
    private final ArrayList[] names;

    /**
     * This function returns the number of answers of te question
     * @param index The index of question
     * @return The number of answers of te question
     */
    public int getNumOfAnswers(int index) {
        return answers[index].size();
    }

    /**
     * This function returns the array of questions
     * @return The array of questions
     */
    public ArrayList<String> getQuestions() {
        return myQuestions;
    }

    /**
     * This function updates the received response and the details of the author
     * @param answer The answer was written
     * @param name The name of author
     * @param index The index of question
     */
    public void setAnswers(String answer, String name, int index) throws Exception {
        answers[index].add(answer);
        try {
            names[index].add(name);
        } catch (Exception e) {
            throw new Exception();
        }
    }
    /**
     * This function returns the array of answers
     * @return The array of answers
     */
    public ArrayList<String> getAnswers(int index) throws Exception {
        if(answers[index] == null)
            throw new Exception();
        return answers[index];
    }
    /**
     * This function returns the array of names
     * @return The array of names
     */
    public ArrayList<String> getNames(int index) throws Exception {
        if(answers[index] == null)
            throw new Exception();
        return names[index];
    }
}
