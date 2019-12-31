package com.iota;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
@WebServlet("/getAllCustomer")
public class GetAllCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try
	{
	Connection c;
	Class.forName("com.mysql.jdbc.Driver");
	c=DriverManager.getConnection("jdbc:mysql://localhost:3306/iota_ny_sale","iota","iota@3210");
	Statement s=c.createStatement();
	String sql="select * from customer";
	ResultSet rs = s.executeQuery(sql);
	resp.setContentType("application/html");
        PrintWriter pw = resp.getWriter();
      //STEP 5: Extract data from result set
	pw.println("<html><body><table><thead>");
	pw.println("<tr><td>customerId</td><td>name</td><td>mobile number</td><td>email</td><td>address</td><td>pincode</td><td>quantity</td></tr></thead><tbody>");
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("customer_id");
         String name = rs.getString("name");
         String mobile_number = rs.getString("mobile_number");
	 String email=rs.getString("email");
	 String address=rs.getString("address");
	 String pincode=rs.getString("pincode");
	 int quantity=rs.getInt("quantity");
	
         pw.println("<tr><td>"+id+"</td><td>"+name+"</td><td>"+mobile_number+"</td><td>"+email+"</td><td>"+address+"</td><td>"+pincode+"</td><td>"+quantity+"</td></tr>");
      	   
      }
      pw.println("</tbody></table></body></html>");
      rs.close();
      s.close();
      c.close();
      pw.close();	
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
}
