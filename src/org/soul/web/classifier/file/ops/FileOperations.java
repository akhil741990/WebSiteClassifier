package org.soul.web.classifier.file.ops;

import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
}
