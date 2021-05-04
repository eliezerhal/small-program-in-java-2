package ex02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This is a class that takes care of reading the questions from the file
 */
public class In {
    /**
     * This is the class constructor function
     */
    public In() {
        questions = new ArrayList<>();
    }
    private final ArrayList<String> questions;

    /**
     * This is a function that takes care of reading the questions from the file
     * @param url This is the URL of the file
     */
    public void readFile(String url) {
        try (BufferedReader reader = new BufferedReader(new FileReader(url))) {
            String line;
            while ((line = reader.readLine()) != null)
                questions.add(line);
        } catch (Exception e) {
            //throw new Exception("error");
            System.err.println(e.getMessage());
        }
    }

    /**
     * This is a function that returns the array of questions
     * @return the array of questions
     */
    public ArrayList<String> getQuestions() {
        return questions;
    }
}
