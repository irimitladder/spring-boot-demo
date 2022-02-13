package irimi.springbootdemo;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Helper {

    public static final ZoneId ROMAN_TIME_ZONE_ID = ZoneId.of("Europe/Rome");
    public static final DateTimeFormatter DATE_AND_TIME_STRING_FORMATTER = DateTimeFormatter.ofPattern("E, d LLL yyyy HH:mm:ss");

    public static String normalize(String string) {
        if (string != null) {
            string = string.trim();
            string = string.replaceAll("[\\x{0000}-\\x{0019}]+", " ");
            string = string.replaceAll("[^a-zA-Z \\-']+", "");
            if (string.isEmpty())
                return null;
        }
        return string;
    }
}
