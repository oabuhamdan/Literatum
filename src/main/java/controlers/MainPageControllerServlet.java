package controlers;

import utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "backstage.mainPageController", urlPatterns = {"/mainPage", "/journal","/issue"})
public class MainPageControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("journals", Utils.journals);
        request.setAttribute("issues", Utils.issues);
        request.setAttribute("articles", Utils.articles);
        RequestDispatcher rd = null;

        if (request.getRequestURL().toString().endsWith("mainPage"))
            rd = request.getRequestDispatcher("viewFiles/jsps/mainpage.jsp");

        else if (request.getRequestURL().toString().endsWith("journal"))
            rd = request.getRequestDispatcher("viewFiles/jsps/issueViewer.jsp");

        else if (request.getRequestURL().toString().endsWith("issue"))
            rd = request.getRequestDispatcher("viewFiles/jsps/articleViewer.jsp");


        rd.forward(request, response);
    }

}
