function validateCustomer()
{
var customerName=$("#customer_name").val();
var mobileNumber=$("#contact_number").val();
var pincode=$("#address_pincode").val();
var address=$("#customer_address").val();
var email=$("#customer_email").val();
var quantity=$("#product_quantity").val();
if(customerName=='' || mobileNumber=='' || pincode=='' || address=='' || email=='' || quantity=='')
{
alert("All fields are mandatory");
return;
}
}
function placeOrder()
{

$.post('http://iotahub.in/StudentServlet',   // url
       { myData: 'This is my data.' }, // data to be submit
       function(data, status) {// success callback
                alert("hello");
});

}
