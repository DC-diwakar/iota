package com.iota;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("customerName");
        String mobileNumber = req.getParameter("mobileNumber");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
	String pincode= req.getParameter("pincode");
	int quantity=Integer.parseInt(req.getParameter("quantity"));
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        CustomerDAO customerDAO=new CustomerDAO();
	Customer customer=new Customer();
	customer.setName(name);
	customer.setEmail(email);
	customer.setContact(mobileNumber);
	customer.setAddress(address);
	customer.setPincode(pincode);
	customer.setQuantity(quantity);
	boolean status=customerDAO.addCustomer(customer);
	if(status)
	{
	GeneratePaymentLink gpl=new GeneratePaymentLink();
	JSONObject response=gpl.generateLink(customer);	
	if(response!=null)
	{
	response.put("customerId",customer.getId());
	printWriter.println(response.toString());	
	}else
	{
	resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error message");
	printWriter.println("FAILED");
	}
	}
	else {
	resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Error message");
	printWriter.println("FAILED");
	}
	printWriter.close();
    }
}
