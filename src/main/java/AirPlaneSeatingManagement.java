import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.SeatAllocationManager;
import managers.SeatLayoutManager;
import models.Seat;
import org.json.JSONException;
import printers.SeatLayoutPrinter;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AirPlaneSeatingManagement {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the seat configuration of the airplane");
            String seatConfigString = sc.nextLine();
            Integer[][] seatConfig = deriveSeatConfigFromString(seatConfigString);
            System.out.println("Entered seatlayout:" + Arrays.deepToString(seatConfig));

            System.out.println("Enter number of passengers for reservation");
            Integer totalPassenger = sc.nextInt();

            SeatLayoutManager seatLayoutManager = new SeatLayoutManager(seatConfig);
            SeatLayoutPrinter printer = new SeatLayoutPrinter(seatLayoutManager);
            List<Seat> seats = seatLayoutManager.getSeats();
            SeatAllocationManager manager = new SeatAllocationManager(totalPassenger, seats);

            if (seats.size() < totalPassenger) {
                System.out.printf("Only %s seats are available. %s passengers will be left out , Please try with less passenger numbers", seats.size(), (totalPassenger - seats.size()));
                return;
            }
            manager.allocateSeats();
            printer.printSeats();
        } catch (JSONException e) {
            System.out.println("Please enter a valid 2D array of seat configuration to proceed");

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer for total number of passenger");
        } catch (Exception e) {
            System.out.println("Someting went wrong! Please check the stack trace below!");
            e.printStackTrace();
        }

    }

    private static Integer[][] deriveSeatConfigFromString(String seatConfigString) throws JSONException {
        Gson gson = new GsonBuilder().create();
        Integer[][] seatConfig = gson.fromJson(seatConfigString, Integer[][].class);

        return seatConfig;
    }
}
