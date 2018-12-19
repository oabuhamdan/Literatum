package frontend;


import database.DBProcessing;
import backstage.SubmissionHandler;
import data.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "frontend.loginControllerServlet", urlPatterns = {"/login"})
public class loginControllerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user_name") ;
        String password =request.getParameter("password") ;


        if(validateUser(userName,password)){
            User user=new User(userName,password);
            request.getSession().setAttribute("user",user);
            response.sendRedirect(request.getContextPath()+"/mainPage");

        }else{
            request.setAttribute("error","Either user name or password is wrong");
            RequestDispatcher rd = request.getRequestDispatcher("viewFiles/jsps/login.jsp");
            rd.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String classPath= SubmissionHandler.class.getProtectionDomain().getCodeSource().getLocation().toString();
        String UN_ZIPPING_PATH=classPath.replaceFirst("target.*","/unzipped/");
        System.out.println(UN_ZIPPING_PATH);
        RequestDispatcher rd = request.getRequestDispatcher("viewFiles/jsps/login.jsp");
        rd.forward(request, response);
    }

    private boolean validateUser(String userName, String password) {
        try {
            DBProcessing.Connect();
            if (DBProcessing.isUserValid(userName,password)){
                System.out.println("Valid");
                return true;
            }
                System.out.println("not Valid");
                return false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
