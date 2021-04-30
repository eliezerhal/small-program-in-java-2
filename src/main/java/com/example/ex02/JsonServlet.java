package com.example.ex02;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

@WebServlet(name = "JsonServlet", value = "/JsonServlet")
public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // we build some random data to demonstrate JSON
        // ["items":"

        ServletContext context = getServletContext();
        DataBase db = (DataBase) context.getAttribute("db");
        int index = Integer.parseInt(request.getParameter("questionNumber"));
        try {
            ArrayList<String> answers = db.getAnswers(index);
            ArrayList<String> names = db.getNames(index);
            JsonArrayBuilder arrayBuild = Json.createArrayBuilder();
            for (int i = 0; i < db.getNumOfAnswers(index); i++) {
                JsonObjectBuilder questionBuilder = Json.createObjectBuilder()
                        .add("Name", names.get(i))
                        .add("Answer", answers.get(i));

                JsonObject questionJson = questionBuilder.build();
                arrayBuild.add(questionJson);
            }
            try (OutputStream out = response.getOutputStream()) {
                JsonWriter jsonElement = Json.createWriter(out);
                jsonElement.write(arrayBuild.build());
                jsonElement.close();
            }
        }
        catch (GeneralException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
