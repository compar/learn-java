package cn.compar.demo.String;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@State(Scope.Thread)
@Warmup(iterations = 4, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 8, time = 1)
public class EmptyStringEquals {

	@Param({ "", "nonEmptyString" })
	private String strParams;

	@Benchmark
	public boolean nonNullAndIsEmpty() {
		return strParams != null && strParams.isEmpty();
	}

	@Benchmark
	public boolean equalsPost() {
		return strParams != null && strParams.equals("");
	}

	@Benchmark
	public boolean preEquals() {
		return "".equals(strParams);
	}

	public static void main(String[] args) throws RunnerException {
		 Options options = new OptionsBuilder().include(EmptyStringEquals.class.getSimpleName()).build();
		new Runner(options).run();
	}
}
