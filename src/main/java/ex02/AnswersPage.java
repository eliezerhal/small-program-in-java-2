package ex02;

/**
 * This servlet takes care of updating the answers
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AnswersPage", value = "/AnswersPage")
public class AnswersPage extends HttpServlet {
    @Override
    /**
     * This function handles the request of get
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{int questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();
        DataBase db = (DataBase) context.getAttribute("db");
        request.getRequestDispatcher("index.html").include(request, response);

        Cookie cookie = new Cookie("numOfQuestion", String.valueOf(questionNumber));
        response.addCookie(cookie);
        out.println("<h2>" + db.getQuestions().get(questionNumber) + "</h2>");
        request.getRequestDispatcher("button.html").include(request, response);
        out.close();}
        catch (Exception e) {response.sendRedirect("/");}
    }

    @Override
    /**
     * This function handles the request of post
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{doGet(request, response);}
        catch (Exception e) {response.sendRedirect("/");}
    }
}
