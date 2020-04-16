import managers.SeatLayoutManager;
import models.Seat;
import models.SeatType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SeatLayoutManagerTest {

    @Test
    public void createSeatLayoutForTwoRowAndThreeColumns() {
        Integer[][] configuration = {{3, 2}};

        SeatLayoutManager seatLayoutManager = new SeatLayoutManager(configuration);
        List<Seat> seats = seatLayoutManager.getSeats();

        Assert.assertEquals(6, seats.size());

//        assert seats in row one
        Assert.assertEquals(SeatType.WINDOW, seats.get(0).getSeatType());
        Assert.assertEquals(1, seats.get(0).getRow().intValue());
        Assert.assertEquals(SeatType.CENTER, seats.get(1).getSeatType());
        Assert.assertEquals(1, seats.get(1).getRow().intValue());
        Assert.assertEquals(SeatType.AISLE, seats.get(2).getSeatType());
        Assert.assertEquals(1, seats.get(2).getRow().intValue());


//        assert seats in row two
        Assert.assertEquals(SeatType.WINDOW, seats.get(3).getSeatType());
        Assert.assertEquals(2, seats.get(3).getRow().intValue());
        Assert.assertEquals(SeatType.CENTER, seats.get(4).getSeatType());
        Assert.assertEquals(2, seats.get(4).getRow().intValue());
        Assert.assertEquals(SeatType.AISLE, seats.get(5).getSeatType());
        Assert.assertEquals(2, seats.get(5).getRow().intValue());
    }

    @Test
    public void createSeatLayoutForCargoPlane(){

        Integer[][] configuration = {{0,0}};
        SeatLayoutManager seatLayoutManager = new SeatLayoutManager(configuration);
        List<Seat> seats = seatLayoutManager.getSeats();
        Assert.assertEquals(0, seats.size());
    }
}