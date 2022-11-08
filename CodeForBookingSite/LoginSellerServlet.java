/*
	Jul 29, 2019
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

@WebServlet(urlPatterns = {"/loginSeller"})
public class LoginSellerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginSellerServlet() {
        super();
    }
 
    // Hiá»ƒn thá»‹ trang Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/loginSeller.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // Khi ngÆ°á»�i nháº­p userName & password, vÃ  nháº¥n Submit.
    // PhÆ°Æ¡ng thá»©c nÃ y sáº½ Ä‘Æ°á»£c thá»±c thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
        if (name == null || password == null || name.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                user = DBUtils.findSeller(conn, name, password);
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // Trong trÆ°á»�ng há»£p cÃ³ lá»—i,
        // forward (chuyá»ƒn hÆ°á»›ng) tá»›i /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setName(name);
            user.setPassword(password);
 
            // LÆ°u cÃ¡c thÃ´ng tin vÃ o request attribute trÆ°á»›c khi forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward (Chuyá»ƒn tiáº¿p) tá»›i trang /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/loginSeller.jsp");
 
            dispatcher.forward(request, response);
            
        }
        
        else {

            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
 
          
           
            
            System.out.println(name);
            request.setAttribute("useri", name);
            response.sendRedirect(request.getContextPath() + "/showTicket");
        }
        
    }
 
}
