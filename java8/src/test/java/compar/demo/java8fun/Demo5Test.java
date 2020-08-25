package compar.demo.java8fun;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import compar.demo.java8fun.group.GroupingBy;
import junit.framework.TestCase;

public class Demo5Test extends TestCase {
	  Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
			   "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
	//找出最长的名字  
	   public void testLongest() {
		   			String name = names.max(Comparator.comparing(String::length))
		   					.get();
		   			assertEquals(name, "Stuart Sutcliffe");
	   }
	   
	   public void testLongestByCollect() {
		   Optional<String> name2 = names.collect(Collectors.maxBy(Comparator.comparing(String::length)));
  			
  			assertEquals(name2.get(), "Stuart Sutcliffe");
	   }
	   /**计算每个单词出现的次数*/
	   public void testCount() {
		   Stream<String> names = Stream.of("John", "Paul", "George", "John",
				   "Paul", "John");
		   
		   Map<String, Long> counts = names.collect(Collectors.groupingBy(name->name,Collectors.counting()));
		   System.out.println(counts);
	   }
	   /**自定义收集器*/
	   public void testGroup() {
		   Stream<String> names = Stream.of("John", "Paul", "George", "John",
				   "Paul", "John").parallel();
		   Map<Object, List<String>> counts = names.collect(GroupingBy.groupingBy(name->name));
		   System.out.println("自定义收集器:"+counts);
	   }
	   
	   /**计算斐波那契数列*/
	 public void testFibonacciSequence() {
		
		 map.put(0, 0l);
		 map.put(1, 1l);
		 fibonacci(15);
		 System.out.println(map);
		 
		 

	   }
	 Map<Integer,Long> map = new HashMap<>();
	 public long fibonacci(int x){
		 		 System.out.println(x+":"+Integer.valueOf(x).hashCode());
		 	  return map.computeIfAbsent(x, n-> {
		 		   return fibonacci(n-1)+fibonacci(n-2);
		 	  });
	 }
}
