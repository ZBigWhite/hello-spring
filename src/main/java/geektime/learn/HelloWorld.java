/*
 * Copyright (C), 2002-2021, 苏宁易购电子商务有限公司
 * FileName: HelloWorld.java
 * Author:   20018848
 * Date:     2021/8/4 20:33
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package geektime.learn;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:<br>
 *
 * @author 20018848
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HelloWorld {
    public static class MyTask1 implements Runnable{
        private int i;

        MyTask1(int i){
            this.i=i;
        }

        @Override
        public void run() {
            System.out.println("任务跑到" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static  void main(String args[]){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 50, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                System.out.println("创建线程"+t);
                return  t;
            }
        });

        for (int i = 0;i<=10;i++){
            threadPoolExecutor.submit(new MyTask1(i));
        }
        threadPoolExecutor.shutdown();
    }

}