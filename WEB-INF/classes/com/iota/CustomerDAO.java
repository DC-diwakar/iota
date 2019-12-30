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
c=DriverManager.getConnection("jdbc:mysql://localhost:3306/iota_calendar_sale","iota","iota@3210");
PreparedStatement ps=c.prepareStatement("insert into customer values(?,?,?,?)");  
ps.setString(1,customer.getName());
ps.setString(2,customer.getContact());  
ps.setString(3,customer.getAddress());
ps.setInt(4,customer.getPincode()); 
int i=ps.executeUpdate();  
s.close();
c.close();
}catch(ClassNotFoundException e)
{
System.out.println(e.getMessage());
}catch(SQLException sqe)
{
return "FAILED"+sqe.getMessage();
}
return TRUE;
} 
}
