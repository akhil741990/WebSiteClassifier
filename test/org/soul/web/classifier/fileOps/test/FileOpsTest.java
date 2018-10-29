package org.soul.web.classifier.fileOps.test;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.soul.web.classifier.datacollector.WebPageDataCollector;
import org.soul.web.classifier.file.ops.FileOperations;

public class FileOpsTest {

	public static void main(String args[]) throws Exception {
		
		List<String>  websites = FileOperations.getWebSiteList("/home/apillai44/soul/WebSiteClassifier/data/websites/ecommerce.txt");
		
		
		String url = "https://www.flipkart.com";
		String websiteName = url.split("www.")[1].split("\\.")[0];
		String httpbBody = WebPageDataCollector.getWebPageContent("https://www.flipkart.com");
		Document doc = Jsoup.parse(httpbBody);
		
		String text = doc.body().text(); 
		FileOperations.writeUsingOutputStream(text, "/home/apillai44/soul/WebSiteClassifier/data/ecommerce/"+websiteName+".txt");
		
		
	}
}
