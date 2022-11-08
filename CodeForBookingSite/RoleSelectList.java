/*
	Jul 4, 2019
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

import beans.UserRole;
import utils.DBUtils;
import utils.MyUtils;


public class RoleSelectList extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	 
    public RoleSelectList() {
		// TODO Auto-generated constructor stub
       super();
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       Connection conn = MyUtils.getStoredConnection(request);

       String errorString = null;
       List<UserRole> list = null;
       
           try {
			list = DBUtils.queryRole(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       request.setAttribute("roleList", list);
       
       RequestDispatcher dispatcher = request.getServletContext()
               .getRequestDispatcher("/WEB-INF/views/editUserView.jsp");
       dispatcher.forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
}
