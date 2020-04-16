package printers;

import managers.SeatLayoutManager;
import models.Seat;
import models.Zone;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SeatLayoutPrinter {
    private final SeatLayoutManager seatLayoutManager;

    public SeatLayoutPrinter(SeatLayoutManager seatLayoutManager) {
        this.seatLayoutManager = seatLayoutManager;
    }

    public void printSeats() {
        IntStream.rangeClosed(1, getMaxRows()).forEach(row -> {
            seatLayoutManager.getZones().forEach(
                    zone -> {
                        List<Seat> seatsForZone = seatLayoutManager.getSeats().stream().filter(seat -> seat.getZone() == zone.getNumber() && seat.getRow() == row).collect(Collectors.toList());
                        printFilersIfRowNotFoundForZone(zone, seatsForZone);
                        printRows(seatsForZone);
                        System.out.print("\t");
                    }
            );
            System.out.print("\n");
        });
    }

    private void printRows(List<Seat> seatsForZone) {
        seatsForZone.forEach(
                seat -> {
                    if (seat.getPassengerId() != null) {
                        System.out.print(seat.getPassengerId());
                    } else {
                        System.out.print("x");
                    }
                    System.out.print("\t");
                }
        );
    }

    private void printFilersIfRowNotFoundForZone(Zone zone, List<Seat> seatsForZone) {
        if (seatsForZone.isEmpty()) {
            IntStream.range(0, zone.getTotalColumns()).forEach(column -> {
                System.out.print("\t");
            });
        }
    }

    private Integer getMaxRows() {
        return seatLayoutManager.getZones().stream().max(Comparator.comparingInt(Zone::getTotalRows)).get().getTotalRows();
    }
}