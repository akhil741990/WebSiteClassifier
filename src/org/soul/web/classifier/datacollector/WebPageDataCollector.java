package org.soul.web.classifier.datacollector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebPageDataCollector {

	
	public  void fetchData(List<String> websites){
		for(String url : websites){
			try {
				getWebPageContent(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static String getWebPageContent(String url) throws Exception{
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

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
 
		System.out.println("Response : "+ response.toString());
		return response.toString();
	}	
	
	public static void main(String args[]) throws Exception{
		String httpbBody = getWebPageContent("https://www.flipkart.com");
		Document doc = Jsoup.parse(httpbBody);
		System.out.println("Body :");
	}
}
