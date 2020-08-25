package cn.compar.demo.java9reactive;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class Flowdemo {
	
	public static void main(String[] args) {
		//发布者
		SubmissionPublisher<Integer> publisher = new SubmissionPublisher<Integer>();
		
		//订阅者
		Subscriber<String> subscriber = new Subscriber<String>() {
			
			private Subscription subscription;

			@Override
			public void onSubscribe(Subscription subscription) {
				//保存契约或预约
				this.subscription = subscription;
				//请求一条数据
				this.subscription.request(1);
			}
			
			@Override
			public void onNext(String item) {
				//处理一条
				System.out.println("接收到："+item);
				//然后再请求一条
				this.subscription.request(1);
			}
			
			@Override
			public void onError(Throwable throwable) {
				throwable.printStackTrace();
				//出现异常终止契约，不再接受数据。
				this.subscription.cancel();
			}
			
			@Override
			public void onComplete() {
				//处理完成（ 发布者关闭）
				System.out.println("完成！");
			}
		};
		
		//中间处理者
		MyProcessor  processor = new MyProcessor();
		//发布者绑定处理者
		publisher.subscribe(processor);
		
		
		//处理者者绑定订阅者
		processor.subscribe(subscriber);
		//publisher.subscribe(subscriber);
		int data = 111;
		//发布数据
		for (int i = 0; i < 1000; i++) {
			System.out.println("生产数据！" +i);
			publisher.submit(i);
		}
		publisher.submit(222);
		publisher.submit(333);
		//发布者关闭
		publisher.close();
		
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
