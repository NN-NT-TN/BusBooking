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

@WebServlet(urlPatterns = { "/EditInfo" })
public class ChangeUserInfoServlet extends HttpServlet {
	public ChangeUserInfoServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        HttpSession session = request.getSession();
        
        UserAccount loginedUser = MyUtils.getLoginedUser(session);
 
       
        UserAccount user = null;
        
        
        
        String errorString = null;
 
        try {
        	
            user = DBUtils.findUser1(conn,loginedUser.getId());
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
	       
	       
	 
	        String errorString = null;
	 
	        try {
	            DBUtils.updateUserindi(conn,id, name, phone, address, email, account);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            errorString = e.getMessage();
	        }
	        // Lưu thông tin vào request attribute trước khi forward sang views.
	        request.setAttribute("errorString", errorString);
	       
	 
	        // Nếu có lỗi forward sang trang edit.
	        if (errorString != null) {
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/editInfoView.jsp");
	            dispatcher.forward(request, response);
	        }
	    
	        else {
	        	response.sendRedirect("/busticketbooking/userList");
	        }
	    }
}
