package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Seat {
    private SeatType seatType;
    private boolean isOccupied;
    private Integer zone;
    private Integer row;
    private Integer column;
    private Integer seatNumber;
    private Integer passengerId;

    public boolean isOccupied() {
        return passengerId != null;
    }
}
