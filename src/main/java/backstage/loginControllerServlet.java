package backstage;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "backstage.loginControllerServlet", urlPatterns = {"/login"})
public class loginControllerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user_name") ;
        String password =request.getParameter("password") ;

        if(validateUser(userName,password)){
            response.sendRedirect(request.getContextPath()+"/mainPage");
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("viewFiles/jsps/loginError.jsp");
            rd.forward(request, response);

        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
