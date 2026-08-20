package service;

import dao.ReservationDAO;
import model.Reservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationService() {
        reservationDAO = new ReservationDAO();
    }

    // CHECK ROOM AVAILABILITY
    public boolean checkRoomAvailability(
            int roomId,
            LocalDate checkIn,
            LocalDate checkOut) {

        if (checkIn == null || checkOut == null) {
            return false;
        }

        if (!checkOut.isAfter(checkIn)) {
            return false;
        }

        return reservationDAO.isRoomAvailable(
                roomId,
                checkIn,
                checkOut
        );
    }


    // CALCULATE NUMBER OF DAYS
    public long calculateDays(
            LocalDate checkIn,
            LocalDate checkOut) {

        if (checkIn == null || checkOut == null) {
            return 0;
        }

        if (!checkOut.isAfter(checkIn)) {
            return 0;
        }

        return ChronoUnit.DAYS.between(
                checkIn,
                checkOut
        );
    }


    // CALCULATE TOTAL COST
    public double calculateTotalCost(
            double roomPrice,
            LocalDate checkIn,
            LocalDate checkOut) {

        long days = calculateDays(checkIn, checkOut);

        return roomPrice * days;
    }


    // MAKE RESERVATION
    public boolean makeReservation(
            int guestId,
            int roomId,
            double roomPrice,
            LocalDate checkIn,
            LocalDate checkOut) {

        // Validate dates
        if (checkIn == null || checkOut == null) {
            System.out.println("Invalid dates!");
            return false;
        }

        if (!checkOut.isAfter(checkIn)) {
            System.out.println(
                    "Check-out date must be after check-in date!"
            );
            return false;
        }

        // Check room availability
        boolean available =
                checkRoomAvailability(
                        roomId,
                        checkIn,
                        checkOut
                );

        if (!available) {
            System.out.println("Room is not available!");
            return false;
        }

        // Calculate cost
        double totalCost =
                calculateTotalCost(
                        roomPrice,
                        checkIn,
                        checkOut
                );

        // Create reservation
        Reservation reservation =
                new Reservation(
                        guestId,
                        roomId,
                        checkIn,
                        checkOut,
                        totalCost,
                        "Booked"
                );

        // Save reservation
        reservationDAO.addReservation(reservation);

        System.out.println(
                "Reservation created successfully!"
        );

        System.out.println(
                "Total Cost: " + totalCost
        );

        return true;
    }
}