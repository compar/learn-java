package compar.demo.java8fun;

import java.util.List;
import java.util.function.BiFunction;

import compar.demo.java8fun.mode.Artist;

public class Demo537 {
	public static void main(String[] args) {

		List<Artist> artists = SampleData.getThreeArtists();
		StringCombiner combined =
				artists.stream()
				.map(Artist::getName)
				.reduce(new StringCombiner(", ", "[", "]"),
						StringCombiner::add,
						StringCombiner::merge);

				String result = combined.toString();
		System.out.println(result);
		
		BiFunction<StringCombiner,String,StringCombiner> aa  = StringCombiner::add;
		
		//BiFun
		BiFunction<Demo537,String,String > bb = Demo537::add;
		

		System.out.println(bb.apply(new Demo537(), "111"));
		
	}

	
	public  String add(String a) {
		return this.toString()+a;
	}
}
