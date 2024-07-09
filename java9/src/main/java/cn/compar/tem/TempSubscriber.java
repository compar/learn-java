package cn.compar.tem;


import java.util.concurrent.Flow;

public class TempSubscriber implements Flow.Subscriber<TempInfo> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;  //保存一个订阅
        subscription.request(1);  //发出第一个请求
    }

    @Override
    public void onNext(TempInfo item) {
        
        System.out.println(item.toString());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("完成！");
    }
}
