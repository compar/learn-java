package compar.demo.java8fun;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import compar.demo.java8fun.mode.Track;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testMap() {

		List<String> collected = Stream.of("a", "b", "hello").map(String::toUpperCase).collect(Collectors.toList());
		assertEquals(Arrays.asList("A", "B", "HELLO"), collected);
	}

	public void testFilter() {
		List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
				.filter(value -> Character.isDigit(value.charAt(0))).collect(Collectors.toList());
		assertEquals(Arrays.asList("1abc"), beginningWithNumbers);
	}

	public void testReduce() {
		int count = Stream.of(1, 2, 3)
				.reduce(0, Integer::sum);
				assertEquals(6, count);
	}
	
	public void testFlatMap() {
		List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
				.flatMap(List::stream)
				.collect(Collectors.toList());
				assertEquals(Arrays.asList(1, 2, 3, 4), together);
	}
}
