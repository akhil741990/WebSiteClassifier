package org.soul.web.classifier.datacollector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.soul.web.classifier.file.ops.FileOperations;

public class WebPageDataCollector {

	
	public  static void fetchData(List<String> websites, String path){
		for(String url : websites){
			try {
				generateMLdata(url,path);
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
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0");

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
 
		//System.out.println("Response : "+ response.toString());
		return response.toString();
	}	
	
	public static void main(String args[]) throws Exception{
	
		ArrayList<String> websites = new ArrayList<>();
		websites.add("https://www.flipkart.com");
		websites.add("https://www.amazon.com");
		websites.add("https://www.infibeam.com");
		websites.add("https://www.ebay.com/");
		websites.add("https://www.walmart.com/");
		websites.add("https://www.target.com/");
		websites.add("https://www.overstock.com/");
		websites.add("https://www.newegg.com/");
		WebPageDataCollector.fetchData(websites, "/home/apillai44/soul/WebSiteClassifier/data/ecommerce/");
	}
	
	private static void persistFetchedContent(String httpBody, String path) {
		Document doc = Jsoup.parse(httpBody);
		String text = doc.body().text(); 
		FileOperations.writeUsingOutputStream(text, path);
	}
	
	private static void generateMLdata(String url, String path) {
		try {
			String httpBody = getWebPageContent(url);
			String websiteName = url.split("www.")[1].split("\\.")[0];
			persistFetchedContent(httpBody, path + websiteName + ".txt");
		} catch (Exception e) {
			System.out.println("Exception :"+e.getMessage());
		}
	}
	
}
