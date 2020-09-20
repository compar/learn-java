package compar.demo.java8fun;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import compar.demo.java8fun.group.PrimeNumberCollector;
import junit.framework.TestCase;

public class PrimeTest extends TestCase {
	public void testPrime() {
		long fastest = Long.MAX_VALUE;
		Map<Boolean, List<Integer>> primes = Collections.EMPTY_MAP;
		for (int i = 0; i < 10;i++) {
			long start = System.nanoTime();
			//primes = PrimeWithCustomCollector(1_000_000);    //自定义比较快，但是不能并行。  但是takWhile方法不是原生的，可能也有影响。
			primes = PartitionPrimes(1_000_000);    //可开启并行，更快。
			long duration = (System.nanoTime() - start )/1_000_000;
			if(duration <fastest) fastest = duration;
		}
		System.out.println("fastest execution done in "+fastest+" msecs,count="+ primes.get(true).size());	
	}
	
	public Map<Boolean,List<Integer>>  PrimeWithCustomCollector(int n){
		return IntStream.rangeClosed(2, n).boxed()
				//.parallel()
				.collect(new PrimeNumberCollector());
	}
	
	public Map<Boolean,List<Integer>>  PartitionPrimes(int n){
		return IntStream.rangeClosed(2, n).boxed()
				.parallel()  //并行
				.collect(Collectors.partitioningBy(i -> PrimeNumberCollector.isPrime(i)));
	}
}
