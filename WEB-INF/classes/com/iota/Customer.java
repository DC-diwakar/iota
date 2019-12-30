package com.iota;
public class Customer
{
private String name;
private String email;
private String contact;
private String address;
private int pincode;
private boolean modeOfPayment;

public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setEmail(String email)
{
this.email=email;
}
public String getEmail()
{
return this.email;
}
public void setContact(String contact)
{
this.contact=contact;
}
public String getContact()
{
return this.contact;
}
public void setAddress(String address)
{
this.address=address;
}
public String getAddress()
{
return this.address;
}
public void setPinCode(int pincode)
{
this.pincode=pincode;
}
public int getPincode()
{
return this.pincode;
}
}
