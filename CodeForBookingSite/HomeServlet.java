/*
	Jun 26, 2019
	Tien
*/
package servlet;

import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserAccount;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/"})

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	   public HomeServlet() {
	       super();
	   }
	 
	   @Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
		   HttpSession session = request.getSession();
	        
	        
	       // Forward toi trang /WEB-INF/views/homeView.jsp
	       // (NgÆ°á»�i dÃ¹ng khÃ´ng bao giá»� truy cáº­p trá»±c tiáº¿p Ä‘Æ°á»£c vÃ o cÃ¡c trang JSP
	       // Ä‘áº·t trong WEB-INF)
	       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
	        
	       dispatcher.forward(request, response);
	        
	   }
	 
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
	       doGet(request, response);
	   }
	 
}
