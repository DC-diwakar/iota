function validateCustomer()
{
alert($("#customer_name").val());
$.post('http://iotahub.in/StudentServlet',   // url
       { myData: 'This is my data.' }, // data to be submit
       function(data, status, jqXHR) {// success callback
                alert("hello");
        });


}
function placeOrder()
{
}
