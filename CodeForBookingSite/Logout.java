/*
	Jul 3, 2019
	Tien
*/
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 
@WebServlet("/logout")
public class Logout extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
            
			response.setContentType("text/html");  
			PrintWriter out=response.getWriter();  
			request.getRequestDispatcher("home").include(request, response);  
			HttpSession session=request.getSession();  
			session.invalidate();  
			out.print("You are successfully logged out!");  
			out.close();  
}  

}


