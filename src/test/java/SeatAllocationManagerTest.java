import managers.SeatAllocationManager;
import managers.SeatLayoutManager;
import models.Seat;
import models.SeatType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SeatAllocationManagerTest {

    private  List<Seat> seats;

    @Before
    public void setUp() throws Exception {
        Integer[][] configuration = {{3, 2}};

        SeatLayoutManager seatLayoutManager = new SeatLayoutManager(configuration);
        seats = seatLayoutManager.getSeats();
    }

    @Test
    public void allocateSeatsForSixPassengerWhenSixSeats() {

        SeatAllocationManager allocationManager = new SeatAllocationManager(6, seats);
        allocationManager.allocateSeats();

        Integer emptySeats = seats.stream().filter(seat -> seat.isOccupied() == false ).collect(Collectors.toList()).size();

        Assert.assertEquals(0, emptySeats.intValue());

        Assert.assertEquals(3, seats.get(0).getPassengerId().intValue());
        Assert.assertEquals(SeatType.WINDOW, seats.get(0).getSeatType());

        Assert.assertEquals(5, seats.get(1).getPassengerId().intValue());
        Assert.assertEquals(SeatType.CENTER, seats.get(1).getSeatType());

        Assert.assertEquals(1, seats.get(2).getPassengerId().intValue());
        Assert.assertEquals(SeatType.AISLE, seats.get(2).getSeatType());

        Assert.assertEquals(4, seats.get(3).getPassengerId().intValue());
        Assert.assertEquals(SeatType.WINDOW, seats.get(3).getSeatType());

        Assert.assertEquals(6, seats.get(4).getPassengerId().intValue());
        Assert.assertEquals(SeatType.CENTER, seats.get(4).getSeatType());

        Assert.assertEquals(2, seats.get(5).getPassengerId().intValue());
        Assert.assertEquals(SeatType.AISLE, seats.get(5).getSeatType());
    }

    @Test
    public void allocateSeatsForOnePassengerWhenSixSeats() {

        SeatAllocationManager allocationManager = new SeatAllocationManager(1, seats);
        allocationManager.allocateSeats();

        Integer emptySeats = seats.stream().filter(seat -> seat.isOccupied() == false ).collect(Collectors.toList()).size();

        Assert.assertEquals(5, emptySeats.intValue());

        Assert.assertEquals(1, seats.get(2).getPassengerId().intValue());
        Assert.assertEquals(SeatType.AISLE, seats.get(2).getSeatType());
    }

    @Test
    public void allocateSeatsForThreePassengerWhenSixSeats() {
        SeatAllocationManager allocationManager = new SeatAllocationManager(3, seats);
        allocationManager.allocateSeats();

        Integer emptySeats = seats.stream().filter(seat -> seat.isOccupied() == false ).collect(Collectors.toList()).size();

        Assert.assertEquals(3, emptySeats.intValue());

        Assert.assertEquals(3, seats.get(0).getPassengerId().intValue());
        Assert.assertEquals(SeatType.WINDOW, seats.get(0).getSeatType());

        Assert.assertNull( seats.get(1).getPassengerId());
        Assert.assertEquals(SeatType.CENTER, seats.get(1).getSeatType());

        Assert.assertEquals(1, seats.get(2).getPassengerId().intValue());
        Assert.assertEquals(SeatType.AISLE, seats.get(2).getSeatType());

        Assert.assertNull(seats.get(3).getPassengerId());
        Assert.assertEquals(SeatType.WINDOW, seats.get(3).getSeatType());

        Assert.assertNull( seats.get(4).getPassengerId());
        Assert.assertEquals(SeatType.CENTER, seats.get(4).getSeatType());

        Assert.assertEquals(2, seats.get(5).getPassengerId().intValue());
        Assert.assertEquals(SeatType.AISLE, seats.get(5).getSeatType());
    }

    @Test(expected = RuntimeException.class)
    public void allocateSeatsForSevenPassengerWhenSixSeats() {
        SeatAllocationManager allocationManager = new SeatAllocationManager(7, seats);

        allocationManager.allocateSeats();
    }
}