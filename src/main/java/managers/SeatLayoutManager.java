package managers;

import models.Seat;
import models.SeatType;
import models.Zone;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SeatLayoutManager {

    private Integer[][] configuration;
    private Integer totalNumberOfZones;
    private List<Zone> zones;
    private List<Seat> seats = new ArrayList<Seat>();


    public SeatLayoutManager(Integer[][] configuration) {
        this.configuration = configuration;
        this.totalNumberOfZones = configuration.length;
        this.zones = createZones();
        this.seats = createSeatLayout();
    }

    private List<Zone> createZones() {
        List<Zone> zones = new ArrayList<Zone>();

        IntStream.range(0, totalNumberOfZones).forEach(
                zoneIndex ->{
                    zones.add(Zone.builder().number(zoneIndex + 1).
                            totalColumns(configuration[zoneIndex][0]).
                            totalRows(configuration[zoneIndex][1]).
                            build());
                }
        );

        return zones;
    }

    private List<Seat> createSeatLayout() {
        zones.forEach(zone -> {
            IntStream.rangeClosed(1, zone.getTotalRows()).forEach(row -> {
                IntStream.rangeClosed(1, zone.getTotalColumns()).forEach(column -> {
                    Seat seat = Seat.builder()
                            .row(row)
                            .column(column)
                            .zone(zone.getNumber())
                            .seatType(figureOutSeatType(row, column, zone))
                            .build();
                    seats.add(seat);
                });
            });
        });
        return seats;
    }

    private SeatType figureOutSeatType(Integer row, Integer column, Zone zone) {
        if (zone.getNumber() == 1 && column == 1) {
            return SeatType.WINDOW;
        }
        if (zone.getTotalRows() == totalNumberOfZones && column == zone.getTotalColumns()) {
            return SeatType.WINDOW;
        }
        if (column == 1 || column == zone.getTotalColumns()) {
            return SeatType.AISLE;
        }
        return SeatType.CENTER;
    }

    public List<Zone> getZones() {
        return this.zones;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }
}
