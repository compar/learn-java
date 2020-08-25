package compar.demo.java8fun.cmd;

import java.util.function.Consumer;

public class HelloCmd {
		public void hello(Command<Object> t) {
			t.execute("hello");
			t.execute("world");
		}
		
		public static void main(String[] args) {
			HelloCmd cmd = new HelloCmd();
			cmd.hello(System.out::println);
			
			Consumer<String> consumer = System.out::println; 
			Command<String> command = System.out::println; 
			
			Command<String> command2 = HelloCmd::ttt;
			
			System.out.println(consumer == command);
		}
		
		
		public static void  ttt(String s) {
			
		}
}
