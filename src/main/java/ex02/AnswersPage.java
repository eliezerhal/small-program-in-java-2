package ex02;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AnswersPage", value = "/AnswersPage")
public class AnswersPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();
        DataBase db = (DataBase) context.getAttribute("db");

        request.getRequestDispatcher("index.html").include(request, response);
        Cookie cookie = new Cookie("numOfQuestion", String.valueOf(questionNumber));
        response.addCookie(cookie);
        out.println("<body><div class=\"container-fluid\">" +
                "    <div class=\"row\">" +
                "        <div class=\"col-12\" style=\"text-align: left\">" +
                "            <div class=\"design\">" +
                "                <h1>Answers</h1>" +
                "               <p>" + db.getQuestions().get(questionNumber) + "</p>");
        request.getRequestDispatcher("button.html").include(request, response);
        request.getRequestDispatcher("end.html").include(request, response);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("").include(request, response);
    }
}
