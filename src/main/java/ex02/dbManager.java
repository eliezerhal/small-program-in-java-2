package ex02;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "dbManager", value = "/dbManager")
/**
 * This servlet handles the program's database
 */
public class dbManager extends HttpServlet {
    @Override
    /**
     * This function handles the request of get
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }

    @Override
    /**
     * This function handles the request of post
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index;
        Cookie[] cookies = request.getCookies();
        if(cookies == null)
            response.sendRedirect("/QuestionPage");
        for (int i = 0; cookies != null && i < cookies.length; i++)
            if (cookies[i].getName().equals("numOfQuestion")) {
                index = Integer.parseInt(cookies[i].getValue());
                String name = request.getParameter("usr");
                String answer = request.getParameter("ans");
                ServletContext context = getServletContext();
                DataBase db = (DataBase) context.getAttribute("db");
                try{db.setAnswers(answer, name, index);}
                catch (Exception e) {response.sendRedirect("/");}
            }
        response.sendRedirect("/");
    }
}
