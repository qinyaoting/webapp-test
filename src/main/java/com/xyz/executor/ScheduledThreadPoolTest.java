package com.xyz.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
	
	private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
    static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd dd:mm:ss");  
  
    /** 
     * main 
     *  
     * @param args 
     */  
    public static void main(String[] args) {  
    	
    	final Runnable beeper = new Runnable() {  
            int i=0;  
            public void run() {  
                System.out.println("beep:"+(i++)+"----"+sf.format(new Date(System.currentTimeMillis())));  
                //throw new RuntimeException();  
                  
                //任务耗时2秒  
                try {  
                    Thread.sleep(2000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }
            }  
        };  
  
        int startTime = 3;  
        int each = 5;  
        System.out.println("任务将于"+startTime+"秒后开始，每"+each+"秒执行1次");  
          
        /** 
         * 3秒后开始执行run()方法，间隔5秒重复执行，
         * 如果run方法中执行时间超过5秒，比如6秒,那间隔时间是6秒后才执行run
         * 执行遇到异常，则后续执行都会被取消
         * 通过执行程序的取消或终止方法来终止该任务
         */  
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, startTime, each, TimeUnit.SECONDS);  
          
          
        /** 
         *  
         * 3秒后开始执行run()方法，
         * 如果run方法中执行时间是2秒，5+2秒后再次执行run
         * 执行遇到异常，则后续执行都会被取消
         * 通过执行程序的取消或终止方法来终止该任务 
         *  
         */  
        //final ScheduledFuture<?> beeperHandle = scheduler.scheduleWithFixedDelay(beeper, startTime, each, TimeUnit.SECONDS); 
          
        /** 
         * 创建并执行在给定延迟后启用的一次性操作。 
         * 这里用于在N时间后取消任务 
         */  
        scheduler.schedule(new Runnable() {  
            public void run() {  
                System.out.println("取消任务");  
                beeperHandle.cancel(true);  
            }  
        }, 60, TimeUnit.SECONDS); 
    }

}
