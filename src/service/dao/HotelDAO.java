package dao;

import model.Hotel;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    // ADD HOTEL
    public void addHotel(Hotel hotel) {

        String sql = "INSERT INTO hotels (name, location, amenities) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getLocation());
            ps.setString(3, hotel.getAmenities());

            ps.executeUpdate();

            System.out.println("Hotel added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW ALL HOTELS
    public List<Hotel> getAllHotels() {

        List<Hotel> hotels = new ArrayList<>();

        String sql = "SELECT * FROM hotels";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Hotel hotel = new Hotel(
                        rs.getInt("hotel_id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("amenities")
                );

                hotels.add(hotel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    // UPDATE HOTEL
    public void updateHotel(Hotel hotel) {

        String sql = "UPDATE hotels SET name=?, location=?, amenities=? WHERE hotel_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getLocation());
            ps.setString(3, hotel.getAmenities());
            ps.setInt(4, hotel.getHotelId());

            ps.executeUpdate();

            System.out.println("Hotel updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE HOTEL
    public void deleteHotel(int hotelId) {

        String sql = "DELETE FROM hotels WHERE hotel_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hotelId);

            ps.executeUpdate();

            System.out.println("Hotel deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}