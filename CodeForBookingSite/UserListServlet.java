/*
	Jul 2, 2019
	Tien
*/
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Product;
import beans.UserAccount;
import beans.UserRole;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/userList" })
public class UserListServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	 
     public UserListServlet() {
		// TODO Auto-generated constructor stub
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
        Connection conn = MyUtils.getStoredConnection(request);
       
        
        
        String errorString = null;
        List<UserAccount> list = null;
       
        try {
            list = DBUtils.queryUsers(conn);
        	//DBUtils.usersList(conn);
          
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("userList", list);
     
        
        if (session==null || session.getAttribute("loginedUser")==null) {
        	 RequestDispatcher dispatcher = request.getServletContext()
                     .getRequestDispatcher("/WEB-INF/views/loginView.jsp");
             dispatcher.forward(request, response);
 		}
        request.setAttribute("user", loginedUser);
        // Forward sang /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/userListView.jsp");
        dispatcher.forward(request, response);
        
        System.out.println(loginedUser.getId());
        // Lưu thông tin vào request attribute trước khi forward sang views.
        
      
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

