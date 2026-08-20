package dao;

import model.Guest;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    // ADD GUEST
    public void addGuest(Guest guest) {

        String sql = "INSERT INTO guests (name, email, phone) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getEmail());
            ps.setString(3, guest.getPhone());

            ps.executeUpdate();

            System.out.println("Guest added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW ALL GUESTS
    public List<Guest> getAllGuests() {

        List<Guest> guests = new ArrayList<>();

        String sql = "SELECT * FROM guests";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Guest guest = new Guest(
                        rs.getInt("guest_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );

                guests.add(guest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }

    // UPDATE GUEST
    public void updateGuest(Guest guest) {

        String sql = "UPDATE guests SET name=?, email=?, phone=? WHERE guest_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getEmail());
            ps.setString(3, guest.getPhone());
            ps.setInt(4, guest.getGuestId());

            ps.executeUpdate();

            System.out.println("Guest updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE GUEST
    public void deleteGuest(int guestId) {

        String sql = "DELETE FROM guests WHERE guest_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, guestId);

            ps.executeUpdate();

            System.out.println("Guest deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FIND GUEST BY ID
    public Guest getGuestById(int guestId) {

        String sql = "SELECT * FROM guests WHERE guest_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, guestId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Guest(
                            rs.getInt("guest_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}