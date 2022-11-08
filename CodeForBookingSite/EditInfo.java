/*
	Aug 1, 2019
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
import javax.servlet.http.HttpSession;

import beans.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/editInfo" })
public class EditInfo extends HttpServlet  {
		 private static final long serialVersionUID = 1L;
		 
		    public EditInfo() {
		        super();
		    }
		 
		    
		    @Override
		    protected void doGet(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        Connection conn = MyUtils.getStoredConnection(request);
		        HttpSession session = request.getSession();
		        
		        
		        UserAccount loginedUser = MyUtils.getLoginedUser(session);
		 
		       
		        UserAccount user = null;
		 
		        String errorString = null;
		        int un=loginedUser.getId();
		        String a=loginedUser.getUserName();
		        System.out.println(loginedUser.getId());
		        try {
		        		
		        		   user = DBUtils.findUser(conn, a);
		   		
		        } catch (SQLException e) {
		            e.printStackTrace();
		            errorString = e.getMessage();
		        }
		 
		       
		        if (errorString != null && user == null  && loginedUser==null ) {
		            response.sendRedirect("/home");
		            return;
		        }
		     
		       
		        request.setAttribute("errorString", errorString);
		        request.setAttribute("users", user);
		 
		        RequestDispatcher dispatcher = request.getServletContext()
		        		 .getRequestDispatcher("/WEB-INF/views/editInfoView.jsp");
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
		        String password2 = (String) request.getParameter("password2");
		        int  role_id =Integer.parseInt(request.getParameter("role_id"));
		        UserAccount userAccount = new  UserAccount(id, name, phone, address, email, account, password, 1,"");
		        
		        String errorString = null;
		 
		        try {
		        	//if (password!=password2) {
					//	errorString="wrong password";
						
					//}
		            DBUtils.updateUser(conn, userAccount);
		        } catch (SQLException e) {
		            e.printStackTrace();
		            errorString = e.getMessage();
		        }
		        // LÆ°u thÃ´ng tin vÃ o request attribute trÆ°á»›c khi forward sang views.
		        request.setAttribute("errorString", errorString);
		        request.setAttribute("user", userAccount);

		        if (errorString != null) {
		            RequestDispatcher dispatcher = request.getServletContext()
		                    .getRequestDispatcher("/WEB-INF/views/editInfoView.jsp");
		            dispatcher.forward(request, response);
		        }
		    
		        else {
		        	response.sendRedirect("userList");
		        }
		        System.out.println(account);
		    }
		
}
