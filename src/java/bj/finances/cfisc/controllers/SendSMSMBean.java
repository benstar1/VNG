package bj.finances.cfisc.controllers;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import javax.faces.bean.ManagedBean;

import javax.net.ssl.HttpsURLConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
@ManagedBean(name = "SendSMSMBean")
public class SendSMSMBean {

    
	private final String USER_AGENT = "Mozilla/5.0";

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
   
     Long aleatoire() {
        Random randnum = new Random();

        long LOWER_RANGE = 0; //assign lower range value
        long UPPER_RANGE = 1000000; //assign upper range value

        return LOWER_RANGE + (long) (randnum.nextDouble() * (UPPER_RANGE - LOWER_RANGE));

    }
    
    // HTTP GET request
	void sendGet(String Exp, String Dest, String Message, Long pin) throws Exception {

            // https://rest.nexmo.com/sms/json?api_key=7643be3a&api_secret=7593629098d7c721&from=22997217745&to=22997217745&text=Hello+From+IFU+Platform
	
//            Long pin = aleatoire();
//            System.out.println("Pin = "+ pin);
            
            String url = "https://rest.nexmo.com/sms/json?api_key=7643be3a&api_secret=7593629098d7c721&from=";
                url=url+""+Exp;
                url = url+"&to="+Dest;
                url = url+"&text="+Message;
                url = url+""+pin;
                
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
 
        	void sendGet(String Exp, String Dest, String Message) throws Exception {

            // https://rest.nexmo.com/sms/json?api_key=7643be3a&api_secret=7593629098d7c721&from=22997217745&to=22997217745&text=Hello+From+IFU+Platform
	
//            Long pin = aleatoire();
//            System.out.println("Pin = "+ pin);
            
            String url = "https://rest.nexmo.com/sms/json?api_key=7643be3a&api_secret=7593629098d7c721&from=";
                url=url+""+Exp;
                url = url+"&to="+Dest;
                url = url+"&text="+Message;
                               
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
 
}
