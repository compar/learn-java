package compar.demo.java8fun;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import java.util.stream.Stream;

import javax.swing.text.DateFormatter;

import compar.demo.java8fun.mode.Album;
import compar.demo.java8fun.mode.Track;

/**
 * Hello world!
 *
 */
public class App {

	static ThreadLocal<DateFormatter> formatter = ThreadLocal
			.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

	public static void main(String[] args) throws ParseException {

		System.out.println(formatter.get().valueToString(new Date()));
		
		findLongTracks(SampleData.albums)
			.stream().forEach(System.out::println);
	}

	/**命令写法*/
	public static Set<String> findLongTracks(List<Album> albums) {
		Set<String> trackNames = new HashSet<>();
		for (Album album : albums) {
			for (Track track : album.getTrackList()) {
				if (track.getLength() > 60) {
					String name = track.getName();
					trackNames.add(name);
				}
			}
		}
		return trackNames;
	}
	
	/**函数写法*/
	public static Set<String> findLongTracks(Stream<Album> albums) {		
		return albums.flatMap(album-> album.getTrackList().stream())
					.filter(track->track.getLength()>60)
					 .map(track->track.getName())
					 .collect(toSet());

	}
}
