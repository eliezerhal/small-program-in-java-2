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
public class MainPage extends HttpServlet {
    private final In in = new In();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // application wide parameter stored in web.xml
        String pathFile = this.getServletContext().getInitParameter("pathFile");
        in.readFile(pathFile);
        ServletContext context = getServletContext();
        // this param will be available to all other servlets
        context.setAttribute("db", new DataBase(in.getQuestions()));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //ArrayList<String> questionsList;
        ServletContext context = getServletContext();
        DataBase db = (DataBase) context.getAttribute("db");

        request.getRequestDispatcher("index.html").include(request, response);
        out.println("<body><div class=\"container-fluid\">" +
                "    <div class=\"row\">" +
                "        <div class=\"col-sm-8\" style=\"text-align: left\">" +
                "            <div class=\"design\">" +
                "                <h1>Exercise 2</h1>" +
                "       <h2>list of questions:</h2>");
        out.println("<form action=\"/AnswersPage\" method=\"get\"><ul>");
        for (int i = 0; i < db.getQuestions().size(); i++) {
            out.println("<li class= \"list-group-item\"> <p class=\"alert alert-info\">" + db.getQuestions().get(i) + "</p>" +  db.getNumOfAnswers(i) +  "Answer <button type=\"submit\" class=\"btn btn-secondary\" name=\"questionNumber\" value=\"" + i + "\" >Answer</button>" +
                    "       <button type=\"button\" data-id=\"" + i + "\" style=\"display:block\" style=\"margin: 3px\"  class=\"btn btn-secondary\" name=\"Show answers\">Show answers</button><ul id=\"ans" + i + "\" style=\"display:block\"></ul></li>");
        }
        out.println("</ul></form>");
        out.println("<script>src=\"button.js\"</script>");
        request.getRequestDispatcher("end.html").include(request, response);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
