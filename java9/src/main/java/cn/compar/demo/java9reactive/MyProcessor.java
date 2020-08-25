package cn.compar.demo.java9reactive;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class MyProcessor extends SubmissionPublisher<String> implements Processor<Integer, String>{
	   private Subscription  subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription=subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(Integer item) {
		System.out.println("处理到："+item);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.submit("处理过的"+item.toString());
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
		this.subscription.cancel();
	}

	@Override
	public void onComplete() {
		System.out.println("处理完成");
		this.close();
	}

}