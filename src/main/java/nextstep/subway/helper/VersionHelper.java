package nextstep.subway.helper;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class VersionHelper {
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    private String version;

    @PostConstruct
    public void init() {
        version = curVersion();
    }

    public String getVersion() {
        return version;
    }

    private static String curVersion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        return LocalDateTime.now().format(formatter);
    }
}
