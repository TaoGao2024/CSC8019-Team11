package com.team11.castleproj;

import com.team11.castleproj.Repository.CastleInfoRepository;
import com.team11.castleproj.Repository.RouteStopRepository;
import com.team11.castleproj.Repository.ScheduleInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CastleProjTest {

    @Autowired
    private CastleInfoRepository castleRepo;
    @Autowired
    private ScheduleInfoRepository scheduleRepo;
    @Autowired
    private RouteStopRepository routeStopRepo;

    @Test
    public void testCastleQueries() {
        String castleName = "alnwick";
        var castle = castleRepo.findByName(castleName);

        System.out.println("Castle test run");
        assertThat(castle).isNotNull();
        assertThat(castle.getName()).isEqualTo(castleName);
        assertThat(castle.getDescription()).containsIgnoringCase(castleName);
        assertThat(castle.getEntryFee()).isGreaterThanOrEqualTo(0);
        assertThat(castle.getOpenTime()).isBefore(castle.getCloseTime());
    }

    @Test
    public void testScheduleQueries() {
        // test all 4 castles
        for (int i = 1; i <= 4; i++) {
            // test finding schedules on existing data
            var outbound = scheduleRepo.findOutboundSchedules(LocalTime.of(9, 0), LocalTime.of(11, 0), "c00" + i, "Weekday");
            var returnOption = scheduleRepo.findReturnSchedules(LocalTime.of(14, 0), LocalTime.of(16, 0), "c00" + i, "Weekday");

            // routes in this time range should exist
            assertThat(!outbound.isEmpty()).isNotNull();
            assertThat(!returnOption.isEmpty()).isNotNull();

            // times of returned routes are in queried range
            assertThat(outbound.getFirst().getDepartTime()).isBetween(LocalTime.of(9, 0), LocalTime.of(11, 0));
            assertThat(returnOption.getFirst().getDepartTime()).isBetween(LocalTime.of(14, 0), LocalTime.of(16, 0));

            // test stops for the first set of route ids
            boolean hasTransfer = i > 2;
            testStopQueries(outbound.getFirst().getRouteId(), returnOption.getFirst().getRouteId(), hasTransfer);
        }
    }

    /**
     * Test finding associated stops for the outbound and return routes
     * of a generated round trip
     *
     * @param outboundRouteId route id of a given outbound route
     * @param returnRouteId   route id of a given return route
     * @param hasTransfer     whether route has transfer
     */
    public void testStopQueries(String outboundRouteId, String returnRouteId, boolean hasTransfer) {
        var outboundStops = routeStopRepo.findRouteStopsByRouteId(outboundRouteId);
        var returnStops = routeStopRepo.findRouteStopsByRouteId(returnRouteId);

        if (!hasTransfer) {
            // all routes should have a departure and arrival stop
            assertThat(outboundStops.size()).isEqualTo(2);
        } else {
            // routes with transfer should have additional stops
            assertThat(outboundStops.size()).isGreaterThan(2);
            assertThat(returnStops.size()).isGreaterThan(2);
        }

        // journey's start and end stop should be Haymarket or Eldon square
        String startStop = outboundStops.getFirst().getStopInfo().getName();
        String endStop = returnStops.getLast().getStopInfo().getName();
        assertThat(startStop.startsWith("Haymarket") || startStop.startsWith("Eldon Square")).isTrue();
        assertThat(endStop.startsWith("Haymarket") || endStop.startsWith("Eldon Square")).isTrue();

        // walking instructions to the castle should exist for all castle stops
        String castleStop = outboundStops.getLast().getStopInfo().getInstructions();
        assertThat(castleStop).isNotNull();
    }
}
