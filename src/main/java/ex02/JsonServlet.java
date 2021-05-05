package ex02;

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
/**
 * This servlet handles the creation of the json
 */
public class JsonServlet extends HttpServlet {
    @Override
    /**
     * This function handles the request of get
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // we build some random data to demonstrate JSON
        // ["items":"
        System.out.println("hiiiii");
        ServletContext context = getServletContext();
        DataBase db = (DataBase) context.getAttribute("db");
        int index = Integer.parseInt(request.getParameter("questionNumber"));
        System.out.println(index);
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
            System.err.println(e.getMessage());
        }
    }

    @Override
    /**
     * This function handles the request of post
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
