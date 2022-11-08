/*
	Jul 4, 2019
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


import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/editUser" })
public class EditUserServlet extends HttpServlet  {
	 private static final long serialVersionUID = 1L;
	 
	    public EditUserServlet() {
	        super();
	    }
	 
	    
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection conn = MyUtils.getStoredConnection(request);
	 
	        String  id =request.getParameter("eid");
	        System.out.println(id);
	        UserAccount user = null;
	        
	        
	        
	        String errorString = null;
	 
	        try {
	        	if (Integer.parseInt(id)!=3) {
					
				}
	            user = DBUtils.findUser1(conn, Integer.parseInt(id));
	        } catch (SQLException e) {
	            e.printStackTrace();
	            errorString = e.getMessage();
	        }
	 
	       
	        if (errorString != null && user == null) {
	            response.sendRedirect("/busticketbooking/userList");
	            return;
	        }
	 
	       
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("users", user);
	 
	        RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
	        dispatcher.forward(request, response);
	 
	    }
	 
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        Connection conn = MyUtils.getStoredConnection(request);
	 
	        int  id =Integer.parseInt(request.getParameter("id"));
	        String name = (String) request.getParameter("name");
	        String phone = (String) request.getParameter("phone");
	        String address = (String) request.getParameter("address");
	        String email = (String) request.getParameter("email");
	        String account = (String) request.getParameter("account");
	        String password = (String) request.getParameter("password");
	        int  role_id =Integer.parseInt(request.getParameter("role_id"));
	        UserAccount userAccount = new  UserAccount(id, name, phone, address, email, account, password, role_id,"");
	 
	        String errorString = null;
	 
	        try {
	            DBUtils.updateUser(conn, userAccount);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            errorString = e.getMessage();
	        }
	        // LÆ°u thÃ´ng tin vÃ o request attribute trÆ°á»›c khi forward sang views.
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("users", userAccount);
	 
	        // Náº¿u cÃ³ lá»—i forward sang trang edit.
	        if (errorString != null) {
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
	            dispatcher.forward(request, response);
	        }
	    
	        else {
	        	response.sendRedirect("/BusBookingAdmin/userList");
	        }
	    }
}
