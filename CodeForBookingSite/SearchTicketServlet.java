/*
	Jul 31, 2019
	Tien
*/
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/searchTicket" })
public class SearchTicketServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, 
			  HttpServletResponse response)
			  throws ServletException,IOException{
			  response.setContentType("text/html");
			  PrintWriter out = response.getWriter();

			  System.out.println("MySQL Connect Example.");
			  Connection conn = null;
			  String url = "jdbc:mysql://localhost:3306/";
			  String dbName = "busbooking";
			  String driver = "com.mysql.jdbc.Driver";
			  String userName = "root"; 
			  String password = "";

			  
			  Statement st;
			  try {
			  Class.forName(driver).newInstance();
			  conn = DriverManager.getConnection(url+dbName,userName,password);
			  System.out.println("Connected to the database");
			  String  emp_name  = request.getParameter("emp_name");
			  String  dsc  = request.getParameter("dsc");
			  String  asc  = request.getParameter("asc");
			  ArrayList al=null;
			  ArrayList emp_list =new ArrayList();
			  String query = 
					  "SELECT booking.id, bus.name, booking.seat_number, booking.departure_date, "
				        		+ "bus.departure_time, station.depart_station_city, station.arrive_station_city , "
				        		+ "bus.seat_price, booking.is_payed "
				        		+ "from booking,users,bus,station "
				        		+ "where bus.station_id=station.id and "
				        		+ "booking.user_id=users.id and bus.id=booking.bus_id"
				        		+ " and booking.id like '%"+emp_name+"%'";
			 // "select users.id, users.name, users.phone, users.address, users.email, users.account, users.role_id, roles.role_name  "
			 // + "from users, roles where name like '%"+emp_name+"%' and users.role_id=roles.id";
			  /*String query="SELECT a.id, a.name, a.departure_time, a.arrive_time, "
			  			+ "	b.arrive_station, b.depart_station,b.depart_station_city, b.arrive_station_city, b.staion_id "
			  			+ "	FROM bus a, station b "
			  			+ "	WHERE a.station_id=b.id and "
			  			+ "	b.depart_station_city like '%"+dsc+"%' and b.arrive_station_city like '%"+asc+"%'";*/
			  System.out.println("query " + query);
			  st = conn.createStatement();
			  ResultSet  rs = st.executeQuery(query);


			  while(rs.next()){
			  al  = new ArrayList();
			  al.add(rs.getInt("id"));
			  al.add(rs.getString("name"));
			  al.add(rs.getString("seat_number"));
			  al.add(rs.getString("departure_date"));
			  al.add(rs.getString("departure_time"));
			  al.add(rs.getString("depart_station_city"));
			  al.add(rs.getString("arrive_station_city"));
			  al.add(rs.getString("seat_price"));
			  al.add(rs.getString("is_payed"));
			  System.out.println("al :: "+al);
			  emp_list.add(al);
			  }

			  request.setAttribute("empList",emp_list);
			  
			 System.out.println("empList " + emp_list);

			  // out.println("emp_list " + emp_list);

			
			  RequestDispatcher dispatcher = 
					  getServletContext().getRequestDispatcher("/TicketSearch.jsp");
			  dispatcher.forward(request,response);
			  conn.close();
			  System.out.println("Disconnected from database");
			  } catch (Exception e) {
			  e.printStackTrace();
			  }
			  }
}
