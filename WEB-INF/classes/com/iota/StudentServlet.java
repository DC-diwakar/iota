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
        printWriter.print("<html>");
        printWriter.print("<body>");
        printWriter.print("<h1>Student Resistration Form Data</h1>");
        printWriter.print("<p> firstName :: " + name + "</p>");
        printWriter.print("<p> mobileNumber :: " + mobileNumber + "</p>");
        printWriter.print("<p> quantity :: " + quantity + "</p>");
        printWriter.print("</body>");
        printWriter.print("</html>");
        printWriter.close();
    }
}
