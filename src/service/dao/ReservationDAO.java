package dao;

import model.Reservation;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // ADD RESERVATION
    public void addReservation(Reservation reservation) {

        String sql = "INSERT INTO reservations " +
                "(guest_id, room_id, check_in, check_out, total_cost, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservation.getGuestId());
            ps.setInt(2, reservation.getRoomId());
            ps.setDate(3, java.sql.Date.valueOf(reservation.getCheckIn()));
            ps.setDate(4, java.sql.Date.valueOf(reservation.getCheckOut()));
            ps.setDouble(5, reservation.getTotalCost());
            ps.setString(6, reservation.getStatus());

            ps.executeUpdate();

            System.out.println("Reservation added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // VIEW ALL RESERVATIONS
    public List<Reservation> getAllReservations() {

        List<Reservation> reservations = new ArrayList<>();

        String sql = "SELECT * FROM reservations";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("guest_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in").toLocalDate(),
                        rs.getDate("check_out").toLocalDate(),
                        rs.getDouble("total_cost"),
                        rs.getString("status")
                );

                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }


    // CANCEL RESERVATION
    public void cancelReservation(int reservationId) {

        String sql = "UPDATE reservations SET status=? WHERE reservation_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "Cancelled");
            ps.setInt(2, reservationId);

            ps.executeUpdate();

            System.out.println("Reservation cancelled successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // UPDATE RESERVATION
    public void updateReservation(Reservation reservation) {

        String sql = "UPDATE reservations SET " +
                "guest_id=?, room_id=?, check_in=?, check_out=?, " +
                "total_cost=?, status=? " +
                "WHERE reservation_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservation.getGuestId());
            ps.setInt(2, reservation.getRoomId());
            ps.setDate(3, java.sql.Date.valueOf(reservation.getCheckIn()));
            ps.setDate(4, java.sql.Date.valueOf(reservation.getCheckOut()));
            ps.setDouble(5, reservation.getTotalCost());
            ps.setString(6, reservation.getStatus());
            ps.setInt(7, reservation.getReservationId());

            ps.executeUpdate();

            System.out.println("Reservation updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE RESERVATION
    public void deleteReservation(int reservationId) {

        String sql = "DELETE FROM reservations WHERE reservation_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            ps.executeUpdate();

            System.out.println("Reservation deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // CHECK ROOM AVAILABILITY
    public boolean isRoomAvailable(
            int roomId,
            java.time.LocalDate checkIn,
            java.time.LocalDate checkOut) {

        String sql =
                "SELECT COUNT(*) FROM reservations " +
                "WHERE room_id=? " +
                "AND status='Booked' " +
                "AND check_in < ? " +
                "AND check_out > ?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.setDate(2, java.sql.Date.valueOf(checkOut));
            ps.setDate(3, java.sql.Date.valueOf(checkIn));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}