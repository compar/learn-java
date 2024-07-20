package compar.demo.mapstruct;



import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


import org.mapstruct.Named;

@Named("InstantStringMapper")
public class InstantStringMapper {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);

    @Named("instantByString")
    public Instant timestampByString(String strParam) {
        if(strParam == null) {
            return null;
        }     
        return LocalDateTime.parse(strParam,formatter).toInstant(ZoneOffset.UTC);

    }

    @Named("stringByInstant")
    public String stringByTimestamp(Instant timestamp) {
        return formatter.format(timestamp);
    }
}
