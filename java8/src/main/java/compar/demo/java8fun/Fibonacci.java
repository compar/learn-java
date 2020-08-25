package compar.demo.java8fun;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author hongzy
 *
 */
public class Fibonacci {

	private final Map<Integer, Long> cache;

	public Fibonacci() {
		cache = new TreeMap<>();
		cache.put(0, 0L);
		cache.put(1, 1L);
	}

	public long fibonacci(int x) {
		return cache.computeIfAbsent(x, n ->   fibonacci(n - 1) + fibonacci(n - 2));
	}

	public void show() {
		System.out.println(cache);
	}

	public static void main(String[] args) {
		Fibonacci f = new Fibonacci();
		f.fibonacci(100);
		f.show();
	}
}
