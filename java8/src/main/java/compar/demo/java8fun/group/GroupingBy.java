package compar.demo.java8fun.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**自定义收集器*/
public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K,List<T>>>{
	
	private static final Set<Characteristics> characteristics = Collections.emptySet();
	
	
	public static<T, K> Collector<T, Map<K, List<T>>, Map<K,List<T>>> groupingBy(Function<? super T, ? extends K> keyFun) {
			return new GroupingBy<T, K>(keyFun);
	}

	private Function<? super T, ? extends K> keyFun;
	
	//private List<T> list = new ArrayList();
	
	public GroupingBy(Function<? super T, ? extends K> keyFun){
		this.keyFun=keyFun;
	}
	
	@Override
	public Supplier<Map<K, List<T>>> supplier() {
		return TreeMap<K, List<T>>::new;
	}

	@Override
	public BiConsumer<Map<K, List<T>>, T> accumulator() {
		return (map,obj)->{
			K key = keyFun.apply(obj);
			List<T> list = map.computeIfAbsent(key, k->new ArrayList<T>());
			list.add(obj);			
		};
	}

	@Override
	public BinaryOperator<Map<K, List<T>>> combiner() {		
		return (map1,map2) ->{
			   map2.forEach((key,value)->{
				   		map1.merge(key, value, (leftvalue,rightvalue)->{
				   				leftvalue.addAll(rightvalue);
				   						return leftvalue;
				   		});
			   });
			   return map1;
		};
	}

	@Override
	public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {	
		return (map)->map;
	}

	@Override
	public Set<Characteristics> characteristics() {		
		return characteristics;
	}
	
}
