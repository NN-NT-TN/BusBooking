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


import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/deleteUser" })
public class DeleteUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
     public DeleteUsersServlet() {
		// TODO Auto-generated constructor stub
	
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        //int code = Integer.parseInt(request.getParameter("id"));
 
        String errorString = null;
        int  id =Integer.parseInt(request.getParameter("id"));
        try {
            DBUtils.deleteUser(conn,id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 
         
        
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
             
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/deleteUsersErrorView.jsp");
            dispatcher.forward(request, response);
        }
        // Náº¿u má»�i thá»© tá»‘t Ä‘áº¹p.
        // Redirect (chuyá»ƒn hÆ°á»›ng) sang trang danh sÃ¡ch sáº£n pháº©m.
        else {
            response.sendRedirect(request.getContextPath() + "/userList");
        }
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}


