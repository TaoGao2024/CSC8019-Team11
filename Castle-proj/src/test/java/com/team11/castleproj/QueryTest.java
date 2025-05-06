//package com.team11.castleproj;
//
//import com.team11.castleproj.Repository.CastleInfoRepository;
//import com.team11.castleproj.Repository.RouteStopRepository;
//import com.team11.castleproj.Repository.ScheduleInfoRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import java.time.LocalTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class QueryTest {
//
//    @Autowired
//    private CastleInfoRepository castleRepo;
//    private ScheduleInfoRepository scheduleRepo;
//    private RouteStopRepository routeStopRepo;
//
//    @Test
//    public void testCastleQueries() {
//        String castleName = "alnwick";
//        var castle = castleRepo.findByName(castleName);
//
//        assertThat(castle).isNotNull();
//        assertThat(castle.getDescription()).containsIgnoringCase(castleName);
//    }
//
//    @Test
//    public void testScheduleQueries() {
//        // test all 4 castles
//        for (int i = 1; i <= 4; i++){
//            // Find schedules on existing data
//            var outbound = scheduleRepo.findOutboundSchedules(LocalTime.of(9, 0), LocalTime.of(11, 0), "c00" + i);
//            var returnOption = scheduleRepo.findReturnSchedules(LocalTime.of(14, 0), LocalTime.of(16, 0), "c00" + i);
//            assertThat(!outbound.isEmpty()).isNotNull();
//            assertThat(!returnOption.isEmpty()).isNotNull();
//
//            // Find associated stops for a generated route
//            var outboundStops = routeStopRepo.findRouteStopsByRouteId(outbound.getFirst().getRouteId());
//            var returnStops = routeStopRepo.findRouteStopsByRouteId(returnOption.getFirst().getRouteId());
//            assertThat(outboundStops.size() >= 2).isTrue();
//            assertThat(returnStops.size() >= 2).isTrue();
//            String stopName = outboundStops.get(0).getStopInfo().getName();
//            assertThat(stopName.equals("Haymarket Bus Station") || stopName.equals("Eldon Square"));
//        }
//    }
//}
