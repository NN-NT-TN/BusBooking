/*
	Jul 29, 2019
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

import beans.UserAccount;
import beans.ticket;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/showTicket" })
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public TicketServlet() {
	        super();
	    }
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 Connection conn = MyUtils.getStoredConnection(request);
		 
	        String errorString = null;
	        
	       
	        
	        UserAccount loginedUser = MyUtils.getLoginedUser(session);

	        List<ticket> list = null;
	       
	        if (loginedUser == null) {
	           
	            response.sendRedirect(request.getContextPath() + "/login");
	            return;
	        }
	              request.setAttribute("user", loginedUser);
	              try {
	  	            list = DBUtils.ticketList(conn);
	  	        	//DBUtils.usersList(conn);
	  	          
	  	        } catch (SQLException e) {
	  	            e.printStackTrace();
	  	            errorString = e.getMessage();
	  	        }
	              
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("ticketlist", list);
	        RequestDispatcher dispatcher //
	                = this.getServletContext().getRequestDispatcher("/ticketListView.jsp");
	        dispatcher.forward(request, response);
	        String e=loginedUser.getUserName();
	        System.out.println(loginedUser.getId());
	        
	 
	    }
	 
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	   doGet(request, response);
	    			
	    }
}
