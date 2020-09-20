package compar.demo.java8fun.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class PrimeNumberCollector implements Collector<Integer, Map<Boolean,List<Integer>>, Map<Boolean,List<Integer>>> {

	/**java8模拟java9的 Stream的takeWhile方法*/
	public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
		int i =0;
		for (A item:list) {
			if( !p.test(item)) {
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}
	
	/**判断是否质数,只计算小于平方根的*/
	public static boolean isPrime(int num) {
		int candidateRoot = (int) Math.sqrt((double)num);
		return IntStream.rangeClosed(2, candidateRoot)
				.noneMatch(i -> num % i == 0 );
	}
	
	/**判断是否质数,再优化,只计算已经计算的质数*/
	public boolean isPrime(List<Integer> primes,int num) {
		int candidateRoot = (int) Math.sqrt(num);
		return takeWhile(primes,i -> i <= candidateRoot)
				.stream()
				.noneMatch(p-> num % p ==0);
	}
	
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return ()->new HashMap<Boolean, List<Integer>>() {{
				put(true, new ArrayList<Integer>());
				put(false, new ArrayList<Integer>());
		}};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {		
		return (map,num)->{
			map.get(isPrime(map.get(true),num))
			.add(num);
		};
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {		
		return (map1,map2)->{
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {		
		return Function.identity();
	}

	@Override
	/* 这个算法需要依赖前面的计算结果，所以有顺序， 不能并行执行， 只符合IDENTITY*/
	public Set<Characteristics> characteristics() {		
		return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));		
	}

}
