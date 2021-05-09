package ex02;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainPage", value = "")
/**
 * This servlet is the main servlet of the program
 */
public class MainPage extends HttpServlet {
    private final In in = new In();

    @Override
    /**
     * This function initializes the global parameters in the program
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // application wide parameter stored in web.xml
        String pathFile = this.getServletContext().getInitParameter("pathFile");
        in.readFile(pathFile);
        ServletContext context = getServletContext();
        // this param will be available to all other servlets
        context.setAttribute("db", new DataBase(in.getQuestions()));
    }

    /**
     * This function handles the request of get
     * @param request The request of the user
     * @param response The response to the user
     * @throws ServletException if there is problems in the servlet
     * @throws IOException If there is problems in reading the file
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();
        DataBase db = (DataBase) context.getAttribute("db");

        request.getRequestDispatcher("index.html").include(request, response);
        out.println("<div class=\"design\">" +
                "                <h1>Exercise 2</h1>" +
                "       <h2>list of questions:</h2>");
        out.println("<form action=\"/AnswersPage\" method=\"get\">");
        out.println("<ul>");
        for (int i = 0; i < db.getQuestions().size(); i++)
            out.println("<li class= \"list-group-item\"> <p class=\"alert alert-info\">" + db.getQuestions().get(i) + "</p>" +  db.getNumOfAnswers(i) +  " Answers " +
                    "<button type=\"submit\" class=\"btn btn-secondary\" name=\"questionNumber\" value=\"" + i + "\" >Answer</button>" +
                    "<button type=\"button\" style=\"margin: 3px\" \"display:block\" class=\"btn btn-secondary\" data-id=\"" + i + "\" name=\"Show answers\" id=\"Show answers" + i + "\">Show answers</button>" +
                    //"<button type=\"button\" style=\"margin: 3px\" \"display:none\" class=\"btn btn-secondary\" data-id=\"" + i + "\" name=\"Hide answers\" id=\"Show answers" + i + "\">Hide answers</button>" +
                    "<ul id=\"ans" + i + "\" style=\"display:none\"></ul></li>");
        out.println("</ul></form>");
        request.getRequestDispatcher("end.html").include(request, response);
        out.close();
    }

    @Override
    /**
     * This function handles the request of post
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{doGet(request, response);}
        catch (Exception e) {response.sendRedirect("/");}
    }
}
