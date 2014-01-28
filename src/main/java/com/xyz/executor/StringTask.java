package com.xyz.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class StringTask implements Callable<String> {
	
	static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd dd:mm:ss"); 
	
	
	public String call() {
		// Long operations
		String result = "Run " + sf.format(new Date(System.currentTimeMillis()));
		System.out.println(result);
		//任务耗时2秒  
        try {  
            Thread.sleep(2000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }
		return result;
	}
}
