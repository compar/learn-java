package cn.compar.demo.forkjoin;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;



public class ForkJoinSumCalculator extends RecursiveTask<Long>{

    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 100_100;  //将任务分解为子任务的阈值大小

    public ForkJoinSumCalculator(long [] numbers){
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long [] numbers,int start,int end){
        this.numbers = numbers;
        this.start=start;
        this.end=end;
    }


    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) { 
            long r = computeSequentially();
            System.out.println(Thread.currentThread().getId()+":  "+start+","+end+","+r);
            return r; 
        } 

        ForkJoinSumCalculator leftTask  = new ForkJoinSumCalculator(numbers, start, start+length/2);
        leftTask.fork();         //利用 ForkJoinPool的另一个线程异步地执行新创建的子任务
        ForkJoinSumCalculator rightTask  = new ForkJoinSumCalculator(numbers, start+length/2, end);//创建一个子任务为数组的后一半求和
        Long rightResult = rightTask.compute(); //同步执行第二个子任务，有可能进行进一步的递归划分,

        Long leftResult = leftTask.join();    //读取第一个子任务的结果，如果尚未完成就等待， 
        return leftResult+rightResult;
    }

    private long computeSequentially() { 
        long sum = 0; 
        for (int i = start; i < end; i++) { 
        sum += numbers[i]; 
        } 
        return sum; 
        } 


    public static void main(String[] args) {
       long result = forkJoinSum(100_000_000);
        System.out.println(result);
        


    }    

    public static long forkJoinSum(long n) { 
        int availableProcessors = Runtime.getRuntime().availableProcessors(); 
        System.out.println("可用的cup数：" + availableProcessors);

        long[] numbers = LongStream.rangeClosed(1, n).toArray(); 
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers); 
        return new ForkJoinPool().invoke(task);   //请注意在实际应用时，使用多个 ForkJoinPool 是没有什么意义的。正是出于这个原因，
                                                  //一般来说把它实例化一次，然后把实例保存在静态字段中，使之成为单例，这样就可以在软件中任何部分方便地重用了
    } 
    
}
