package ex02;

/**
 * This servlet
 */

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
        /*out.println("<body>\n" +
                "<div class=\"container-fluid\">\n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-sm-8\">\n" +
                "            <h1 class=\"alert alert-danger\">Welcome</h1>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-sm-8; alert alert-secondary\">");*/
        Cookie cookie = new Cookie("numOfQuestion", String.valueOf(questionNumber));
        response.addCookie(cookie);
        out.println("<p>" + db.getQuestions().get(questionNumber) + "</p>");
        request.getRequestDispatcher("button.html").include(request, response);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("").include(request, response);
    }
}
