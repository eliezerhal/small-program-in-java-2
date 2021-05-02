package ex02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class In {
    public In() {
        questions = new ArrayList<>();
    }
    private final ArrayList<String> questions;
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
    public ArrayList<String> getQuestions() {
        return questions;
    }
}
