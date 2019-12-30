package com.iota;
import java.sql.*;
public class CustomerDAO
{
public boolean addCustomer(Customer customer)
{
try
{
Connection c;
Class.forName("com.mysql.jdbc.Driver");
c=DriverManager.getConnection("jdbc:mysql://localhost:3306/iota_ny_sale","iota","iota@3210");
PreparedStatement ps=c.prepareStatement("insert into customer(name,mobile_number,email,address,pincode,quantity) values(?,?,?,?,?,?)");  
ps.setString(1,customer.getName());
ps.setString(2,customer.getContact());
ps.setString(3,customer.getEmail());  
ps.setString(4,customer.getAddress());
ps.setString(5,customer.getPincode()); 
ps.setInt(6,customer.getQuantity());
int i=ps.executeUpdate();  
ps.close();
c.close();
}catch(ClassNotFoundException e)
{
System.out.println(e.getMessage());
}catch(SQLException sqe)
{
System.out.println("SQL Exception"+sqe.getMessage());
return false;
}
return true;
} 
}
