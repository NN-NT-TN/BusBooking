/*
	Jun 26, 2019
	Tien
*/
package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import beans.UserAccount;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	 
    public UserInfoServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
      
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
 
        
        if (loginedUser == null) {
           
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
              request.setAttribute("user", loginedUser);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
        String e=loginedUser.getUserName();
        System.out.println(loginedUser.getId());
        
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        
    }
}
