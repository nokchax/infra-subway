package nextstep.subway.map.application;

import nextstep.subway.line.application.LineService;
import nextstep.subway.map.dto.PathResponse;
import nextstep.subway.station.application.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MapService {
    private static final Logger log = LoggerFactory.getLogger(MapService.class);
    private final PathService pathService;

    public MapService(PathService pathService) {
        this.pathService = pathService;
    }

    public PathResponse findPath(Long source, Long target) {
        log.info("Find shortest path : from {} -> to {}", source, target);
        return pathService.findPath(source, target);
    }
}
