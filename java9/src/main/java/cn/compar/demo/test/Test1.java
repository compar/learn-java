package cn.compar.demo.test;

/**
 * 为了便于观察 内存GC
 * 启动把内存改小点：  -Xmx10m -Xms10m
 * @author hongzy
 *
 */
public class Test1 {
	public static void main(String[] args) throws InterruptedException {
			while(true) {
				Thread.sleep(1000	);
				System.out.println(123);
			}
	}
}
