package com.iota;
import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;
import com.paytm.pg.merchant.*;
public class NotifyCustomer
{
public void notifyViaSMS(String linkId,String mobileNumber)
{
JSONObject paytmParams=new JSONObject();
/* body parameters */
JSONObject body = new JSONObject();

/* Find your MID in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
body.put("mid", "YOUR_MID_HERE");

/* Enter link id here */
body.put("linkId", linkId);

/* if true the SMS would be sent on customer mobile number */
body.put("sendSms", "true");

/* initialize an object for notifyContact */
JSONObject notifyContact = new JSONObject();

/* enter customer mobile number where SMS would be sent */
notifyContact.put("customerMobile", "9174954181");

/* put notifyContact object in body */
body.put("notifyContact", notifyContact);

/**
* Generate checksum by parameters we have in body
* You can get Checksum JAR from https://developer.paytm.com/docs/v1/payment-gateway/#code
* Find your Merchant Key in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys
*/
try{

String checksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("BTr0sZJ!kR7tXtjv", body.toString());

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
URL url = new URL("https://securegw-stage.paytm.in/link/resendNotification");

/* for Production */
// URL url = new URL("https://securegw.paytm.in/link/resendNotification");

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
        System.out.append("Response: " + responseData);
    }
    // System.out.append("Request: " + post_data);
    responseReader.close();
} catch (Exception exception) {
    exception.printStackTrace();
}



}
}
