package cn.compar.demo.stream07;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value=2,jvmArgs= {"-Xms4G","-Xmx4G"}) //采用4Gb的堆，执行基准测试两次获得可靠的结果
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
public class ParallelStreamBenchmark {
	private static final long N = 10_000_000L;
	
	@Benchmark
	public long sequentialSum() {
		return LongStream.iterate(1L, i->i+1)
				.limit(N)
				.reduce(0L, Long::sum);
	}
	
	@Benchmark
	public long parallelSum() {
		return LongStream.iterate(1L, i->i+1)
				.limit(N)
				.parallel()
				.reduce(0L, Long::sum);
	}
	
	@TearDown(Level.Invocation)  //尽量在每次基准测试迭代结束后都进行一次垃圾回收
	public void tearDown() {
		System.gc();
	}
	
	public static void main(String[] args) throws RunnerException {
		 Options options = new OptionsBuilder().include(ParallelStreamBenchmark.class.getSimpleName()).build();
		new Runner(options).run();
	}
}
