import models.Seat;
import org.junit.Assert;
import org.junit.Test;

public class SeatTest {

    @Test
    public void isOccupiedWhenThereIsNoPassenger() {
        Seat seat = Seat.builder().passengerId(1).build();
        Assert.assertTrue(seat.isOccupied());
    }

    @Test
    public void isOccupiedWhenThereIsPassenger() {
        Seat seat = Seat.builder().build();
        Assert.assertFalse(seat.isOccupied());
    }

}