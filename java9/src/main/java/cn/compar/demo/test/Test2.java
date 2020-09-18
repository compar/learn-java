package cn.compar.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 *  为了内存溢出会产生dump, 启动加参数
 *   -Xmx10m -Xms10m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author hongzy
 *
 */
public class Test2 {
	public static void main(String[] args) throws InterruptedException {
			List list = new ArrayList<>(); 
			int i=0;
			while(true) {
				list.add(i++);
			}
	}
}
