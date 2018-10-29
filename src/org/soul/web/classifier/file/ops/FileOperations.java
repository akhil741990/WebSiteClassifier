package org.soul.web.classifier.file.ops;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class FileOperations {

	public static void writeUsingOutputStream(String data,String path) {
        
		System.out.println("Data is written to path :"+path);
		OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static List<String> getWebSiteList(String path){
		BufferedReader reader;
		List<String> websites = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(path));
			
			String line = reader.readLine();
			while(line!=null) {
				System.out.println("website :"+line);
				websites.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return websites;
	}
	
}
