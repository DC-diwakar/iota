package com.iota;
import java.io.*;
import java.net.*;
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;
import com.paytm.pg.merchant.*;
public class GeneratePaymentLink
{
public JSONObject generateLink(Customer customer)
{
/* initialize an object */
JSONObject paytmParams = new JSONObject();

/* body parameters */
JSONObject body = new JSONObject();
JSONObject customerContact=new JSONObject();

/* Find your MID in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
//body.put("mid", "RugdjG44197832525788");
body.put("mid","edtYcP19717760789682"); // prod
/* Possible value are "GENERIC", "FIXED", "INVOICE" */
body.put("linkType", "FIXED");

/* Enter your choice of payment link description here, special characters are not allowed */
body.put("linkDescription", "iotahub payment");

/* Enter your choice of payment link name here, special characters and spaces are not allowed */
body.put("linkName", "iotahubcalendarpaymentlink");
body.put("sendSMS","true");
body.put("amount",customer.getQuantity()*155);
body.put("statusCallbackUrl","iotahub.in/StudentServlet");
customerContact.put("customerName",customer.getName());
customerContact.put("customerMobile",customer.getContact());
body.put("customerContact",customerContact);
/**
 * Generate checksum by parameters we have in body
 * You can get Checksum JAR from https://developer.paytm.com/docs/v1/payment-gateway/#code
 * Find your Merchant Key in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys
 */
try {

String checksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("8kxUggPM3SZMQ!@j", body.toString());

/* head parameters */
JSONObject head = new JSONObject();

/* This will be AES */
head.put("tokenType", "AES");

/* put generated checksum value here */
head.put("signature", checksum);

/* prepare JSON string for request */
paytmParams.put("body", body);
paytmParams.put("head", head);
String post_data = paytmParams.toString();

/* for Staging */
//URL url = new URL("https://securegw-stage.paytm.in/link/create");
JSONObject rspp=null;
JSONObject linkObject=null;
JSONParser parser = new JSONParser();
/* for Production */
URL url = new URL("https://securegw.paytm.in/link/create");

	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	connection.setRequestMethod("POST");
	connection.setRequestProperty("Content-Type", "application/json");
	connection.setDoOutput(true);

	DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
	requestWriter.writeBytes(post_data);
	requestWriter.close();
	String responseData = "";
	InputStream is = connection.getInputStream();
	BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
	if ((responseData = responseReader.readLine()) != null) {
		rspp= (JSONObject) parser.parse(responseData);
		//rspp = new JSONObject(responseData.toString());
		System.out.println("Response: " + responseData+"\n");
	}
	if(rspp!=null){
	linkObject=(JSONObject)rspp.get("body");
	System.out.println(linkObject.get("linkId"));


	//System.out.println((JSONObject)rspp.get("body").get("linkId"));
//	NotifyCustomer nf=new NotifyCustomer();
//	nf.notifyViaSMS(objj.get("linkId").toString(),"9174954181");

	}
	// System.out.append("Request: " + post_data);
	responseReader.close();
	return linkObject;
} catch (Exception exception) {
	exception.printStackTrace();
}
finally{
return null; 
}
}

public static void main(String gg[])
{
Customer cst=new Customer();
cst.setName("DC");
cst.setContact("9174954181");
cst.setQuantity(1);
GeneratePaymentLink gpl=new GeneratePaymentLink();
gpl.generateLink(cst);
}
}
