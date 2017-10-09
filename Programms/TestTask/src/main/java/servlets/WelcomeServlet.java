package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ruslan on 07.10.2017.
 */

@WebServlet("/junior-3/welcome")

public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        if(session.getAttribute("user") == null) {
            response.sendRedirect("/junior-3/sign-in");

        } else {
            Date date = new Date();
            if(6 <= date.getHours() && date.getHours() < 10) {
                request.setAttribute("morning","утро");
            } else if ( 10 <= date.getHours() && date.getHours() < 18) {
                request.setAttribute("afternoon","день");
            } else if( 18 <= date.getHours() && date.getHours() < 22) {
                request.setAttribute("evening","вечер");
            } else if( 22 <= date.getHours() || date.getHours() < 6 ) {
               request.setAttribute("night","ночь");
           }

            request.setAttribute("user", session.getAttribute("user"));

            getServletConfig().getServletContext().getRequestDispatcher("/welcome.ftl").forward(request,response);

        }
    }

}
