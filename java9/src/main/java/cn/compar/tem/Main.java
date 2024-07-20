package cn.compar.tem;


import java.util.concurrent.Flow;


public class Main {


    public static void main(String[] args) {


        getTemperatures("New York")
                .subscribe(new TempSubscriber());


    }


    //TODO ...
    private static Flow.Publisher<TempInfo> getTemperatures(String town){
        return  subscriber ->{
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            subscriber.onSubscribe(new TempSubscription(processor,town) );
        };
    }

}
