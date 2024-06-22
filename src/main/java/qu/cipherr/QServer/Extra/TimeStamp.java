package qu.cipherr.QServer.Extra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStamp {
    private static final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getTimestamp() {
        return LocalDateTime.now().format(dtFormatter);
    }
}
