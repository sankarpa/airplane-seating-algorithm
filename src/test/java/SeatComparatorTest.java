import comparator.SeatComparator;
import models.Seat;
import models.SeatType;
import org.junit.Assert;
import org.junit.Test;

public class SeatComparatorTest {

    @Test
    public void compareWithAisleSeatAndWindowSeatInSameRow() {
        Seat aisle = Seat.builder().seatType(SeatType.AISLE).row(1).build();
        Seat window = Seat.builder().seatType(SeatType.WINDOW).row(1).build();

        SeatComparator seatComparator = new SeatComparator();
        Assert.assertEquals(-1, seatComparator.compare(aisle,window));
    }

    @Test
    public void compareWithAisleSeatAndAisleSeatInSameRow() {
        Seat aisle1 = Seat.builder().seatType(SeatType.AISLE).row(1).build();
        Seat aisle2 = Seat.builder().seatType(SeatType.AISLE).row(1).build();

        SeatComparator seatComparator = new SeatComparator();
        Assert.assertEquals(0, seatComparator.compare(aisle1,aisle2));

    }

    @Test
    public void compareWithAisleSeatAndAisleSeatInDifferentRow() {
        Seat aisle1 = Seat.builder().seatType(SeatType.AISLE).row(1).build();
        Seat aisle2 = Seat.builder().seatType(SeatType.AISLE).row(2).build();

        SeatComparator seatComparator = new SeatComparator();
        Assert.assertEquals(-1, seatComparator.compare(aisle1,aisle2));
    }

    @Test
    public void compareWithAisleSeatAndCenterSeatInSameRow() {
        Seat aisle = Seat.builder().seatType(SeatType.CENTER).row(1).build();
        Seat center = Seat.builder().seatType(SeatType.AISLE).row(1).build();

        SeatComparator seatComparator = new SeatComparator();
        Assert.assertEquals(2, seatComparator.compare(aisle,center));
    }

    @Test
    public void compareWithWindowSeatAndCenterSeatInSameRow() {
        Seat window = Seat.builder().seatType(SeatType.WINDOW).row(1).build();
        Seat center = Seat.builder().seatType(SeatType.CENTER).row(1).build();

        SeatComparator seatComparator = new SeatComparator();
        Assert.assertEquals(-1, seatComparator.compare(window,center));
    }
}