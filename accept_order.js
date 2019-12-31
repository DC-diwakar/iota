var customerId='';
function addOrderId()
{
var orderId=$("#order_id").val();
if(orderId=='')
{
alert("Invalid order Id");
return;
}
 $.ajax({
  type: "POST",
  url: "mapOrderId",
  data: {customerId:customerId,orderId:orderId},
  success: function(data,status){
     alert("successful");
//   $("#orderIdDiv").hide();
     window.location.href='http://iotahub.in';
  },
  error: function(){
                $('#orderIdDiv').hide();
                alert("error");
        }
});


}

function validateCustomer()
{
customerId='';
var customerName=$("#customer_name").val();
var mobileNumber=$("#contact_number").val();
var pincode=$("#address_pincode").val();
var address=$("#customer_address").val();
var email=$("#customer_email").val();
var quantity=$("#product_quantity").val();
form_validate();
$('#customerCart').submit(function(e) {e.preventDefault(); });
if($('#customerCart').valid()) {

 $.ajax({
  type: "POST",
  url: "StudentServlet",
  data: {customerName:customerName,mobileNumber:mobileNumber,pincode:pincode,address:address,email:email,quantity:quantity  },
  success: function(data,status){
//     $("#orderIdDiv").show();
     $("#paytmDiv").show();
     $("paytmDiv").html("");
     $("#paytmDiv").append(data.merchantHtml);
     customerId=data.customerId;	 
 },
  error: function(){
		$('#paytmDiv').hide();
//		$('#orderIdDiv').hide();
		alert("error");
	}
}); 

}

}
function placeOrder()
{
$.post('http://iotahub.in/StudentServlet',
       { data : 'This is my data.', }, // data to be submit
       function(data, status) {// success callback
                alert("hello");
});

}
function form_validate() {
$('#customerCart').validate({
  rules: {
    customer_name: 'required',
    customer_address: 'required',
    address_pincode: 'required',
    customer_email: {
      required: true,
      email: true,
    },
    contact_number: {
      required: true,
      minlength: 10,
      maxlength: 10
    }
  },
  messages: {
    customer_name: 'This field is required',
    customer_address: 'This field is required',
    customer_email: 'Enter a valid email',
    contact_number: {
      minlength: '10 digit number required',
      maxlength: '10 digit number required'
    }
  }
});
}
