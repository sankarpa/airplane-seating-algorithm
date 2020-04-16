package managers;

import comparator.SeatComparator;
import models.Seat;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SeatAllocationManager {

    private Integer totalNumberOfPassengers;
    private List<Seat> sortedSeats;

    public SeatAllocationManager(Integer totalNumberOfPassengers, List<Seat> seats) {
        this.totalNumberOfPassengers = totalNumberOfPassengers;
        this.sortedSeats = sort(seats);
    }

    private List<Seat> sort(List<Seat> seats) {
        List<Seat> sortedList = seats.stream().sorted(new SeatComparator()::compare).collect(Collectors.toList());

        return sortedList;
    }

    public void allocateSeats() {
        try {
            IntStream.rangeClosed(1, totalNumberOfPassengers).forEach(passenger -> {
                nextAvailableSeat().setPassengerId(passenger);
            });
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Seats are exhausted. Terminating with error %s\", e.getMessage()");
        }
    }

    private Seat nextAvailableSeat() {
        return sortedSeats.stream().filter(seat -> seat.isOccupied() == false).findFirst().get();
    }
}
