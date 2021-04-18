package nextstep.subway.helper;


import com.github.jknack.handlebars.Options;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class CustomHandlebarHelper {
    private final VersionHelper versionHelper;

    public CustomHandlebarHelper(VersionHelper versionHelper) {
        this.versionHelper = versionHelper;
    }

    public String staticUrls(String path, Options options) {
        return String.format("/resources/%s%s", versionHelper.getVersion(), path);
    }
}
