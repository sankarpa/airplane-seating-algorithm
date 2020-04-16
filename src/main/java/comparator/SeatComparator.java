package  comparator;

import models.Seat;

import java.util.Comparator;


public class SeatComparator implements Comparator<Seat> {
    public int compare(Seat seat1, Seat seat2) {
        if (seat1.getSeatType().compareTo(seat2.getSeatType()) == 0) {
            return seat1.getRow().compareTo(seat2.getRow());
        } else {
            return seat1.getSeatType().compareTo(seat2.getSeatType());
        }
    }
}
