package dao;

import model.Room;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    // ADD ROOM
    public void addRoom(Room room) {

        String sql = "INSERT INTO rooms " +
                     "(hotel_id, room_number, room_type, price, status) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, room.getHotelId());
            ps.setString(2, room.getRoomNumber());
            ps.setString(3, room.getRoomType());
            ps.setDouble(4, room.getPrice());
            ps.setString(5, room.getStatus());

            ps.executeUpdate();

            System.out.println("Room added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW ALL ROOMS
    public List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();

        String sql = "SELECT * FROM rooms";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Room room = new Room(
                        rs.getInt("room_id"),
                        rs.getInt("hotel_id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );

                rooms.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // UPDATE ROOM
    public void updateRoom(Room room) {

        String sql = "UPDATE rooms SET " +
                     "hotel_id=?, room_number=?, room_type=?, price=?, status=? " +
                     "WHERE room_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, room.getHotelId());
            ps.setString(2, room.getRoomNumber());
            ps.setString(3, room.getRoomType());
            ps.setDouble(4, room.getPrice());
            ps.setString(5, room.getStatus());
            ps.setInt(6, room.getRoomId());

            ps.executeUpdate();

            System.out.println("Room updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE ROOM
    public void deleteRoom(int roomId) {

        String sql = "DELETE FROM rooms WHERE room_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            ps.executeUpdate();

            System.out.println("Room deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FIND ROOM BY ID
    public Room getRoomById(int roomId) {

        String sql = "SELECT * FROM rooms WHERE room_id=?";

        try (Connection con = DatabaseConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Room(
                            rs.getInt("room_id"),
                            rs.getInt("hotel_id"),
                            rs.getString("room_number"),
                            rs.getString("room_type"),
                            rs.getDouble("price"),
                            rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}