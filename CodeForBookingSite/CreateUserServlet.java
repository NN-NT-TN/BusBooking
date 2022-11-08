/*
	Jul 3, 2019
	Tien
*/
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Product;
import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/createUser" })
public class CreateUserServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	    public CreateUserServlet() {
	        super();
	    }
	 
	    // Hiá»ƒn thá»‹ trang táº¡o sáº£n pháº©m.
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	 
	        RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection conn = MyUtils.getStoredConnection(request);
	        int id=0;
	 	    String name=(String) request.getParameter("name");
	 	    String address=(String) request.getParameter("address");
	 	    String email=(String) request.getParameter("email");
	 	    String phone=(String) request.getParameter("phone");
	 	    String account=(String) request.getParameter("account");
	 	    String password=(String) request.getParameter("password");
	 	    int role_id=Integer.parseInt(request.getParameter("role_id"));
	 	    

	 	    	UserAccount user = new UserAccount( id,name, phone, address,  email,  account,  password, role_id,"");
			
	        
	 
	        String errorString = null; 
	        String error= null;
	        boolean haserror=false;
	        
	        if (errorString == null) {
	            try {
	                DBUtils.insertUser(conn, user);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                errorString = e.getMessage();
	            }
	        }
	 
	    
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("error", "8 charater");
	        request.setAttribute("users", user);
	        
	  
	        if (password.length()<8) {
				
	        	RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
	            dispatcher.forward(request, response);
	 	    }
	        if (errorString != null) {
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
	            dispatcher.forward(request, response);
	        }
	    
	        else {
	            response.sendRedirect(request.getContextPath() + "/userList");
	        }
	    }
	    
	
}
