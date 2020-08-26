package compar.demo.java8fun;


import java.util.Scanner;
import java.util.stream.Stream;

public class DemoStream {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);

		Stream<Integer> generate = Stream.generate(()->{

				 int b = input.nextInt();
				 return b;

		});
		generate.forEach((x)-> {
			  System.out.println(x);
		});
		
		input.close();

	}
}
