package com.xyz.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService threadPool = Executors.newFixedThreadPool(4);

		/*for (int i = 0; i < 10; i++) {
			pool.submit(new StringTask());
		}
		
		//JVM 并不会去关闭这些线程
		//但你必须手工的关闭线程池来结束所有池中的线程：
		pool.shutdown();
		//可以使用 shutdownNow() 的方法来强制关闭线程池
		// 注意：执行中的线程也会被中断，所有尚未被执行的任务也将不会再执行
		//===========================================================
		 
		List<Future<String>> futures = new ArrayList<Future<String>>(10);
		 
		for(int i = 0; i < 10; i++){
		   futures.add(pool.submit(new StringTask()));
		}
		 
		for(Future<String> future : futures){
		   String result = future.get();
		 
		   //Compute the result
		}
		 
		//pool.shutdown();
		//如果第一个任务耗费非常长的时间来执行，然后其他的任务都早于它结束，那么当前线程就无法在第一个任务结束之前获得执行结果*/
		
		
		/*CompletionService<String> service = new ExecutorCompletionService<String>(threadPool);
		 
		for(int i = 0; i < 10; i++){
			service.submit(new StringTask());
		}
		 
		for(int i = 0; i < 10; i++){
		   String result = service.take().get();
		   System.out.println("         RS:"  + result);
		   //Compute the result
		}
		 
		threadPool.shutdown();*/
		
		
		
		//http://heipark.iteye.com/blog/1393847
		// 可以看到task被提交都了LinkedBlockingQueue中。这里有个问题，如果任务列表很大，一定会把内存撑爆，如何解决？看下面：
		
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(3);  
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());  
          
        for (int i = 0; i < 10; i++) {  
            final int index = i;  
            System.out.println("task: " + (index+1));  
            Runnable run = new Runnable() {  
                @Override  
                public void run() {  
                    System.out.println("thread start" + (index+1));  
                    try {  
                        Thread.sleep(Long.MAX_VALUE);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                    System.out.println("thread end" + (index+1));  
                }  
            };  
            executor.execute(run);  
        } 
	}

	

}


