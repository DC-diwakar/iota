package com.iota;
import java.io.IOException;
import java.io.PrintWriter;

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
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        CustomerDAO customerDAO=new CustomerDAO();
	Customer customer=new Customer();
	customer.setName(name);
	customer.setEmail(email);
	customer.setContact(mobileNumber);
	customer.setPincode(pincode);
	customer.setQuantity(quantity);
	boolean status=customerDAO.addCustomer(customer);
	if(status) printWriter.println("SUCCESSFULL");
	else printWriter.println("FAILED");
	printWriter.close();
    }
}
