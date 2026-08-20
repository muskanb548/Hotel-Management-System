package gui;

import dao.GuestDAO;
import dao.HotelDAO;
import dao.ReservationDAO;
import dao.RoomDAO;

import model.Guest;
import model.Hotel;
import model.Reservation;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RetrieveDataPanel extends JFrame {

    private JTable hotelTable;
    private JTable roomTable;
    private JTable guestTable;
    private JTable reservationTable;

    private DefaultTableModel hotelModel;
    private DefaultTableModel roomModel;
    private DefaultTableModel guestModel;
    private DefaultTableModel reservationModel;

    private HotelDAO hotelDAO;
    private RoomDAO roomDAO;
    private GuestDAO guestDAO;
    private ReservationDAO reservationDAO;


    public RetrieveDataPanel() {

        hotelDAO = new HotelDAO();
        roomDAO = new RoomDAO();
        guestDAO = new GuestDAO();
        reservationDAO = new ReservationDAO();

        setTitle("Retrieve Data");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createGUI();

        loadAllData();
    }


    private void createGUI() {

        setLayout(new BorderLayout(10, 10));


        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "RETRIEVE DATA",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(titleLabel, BorderLayout.NORTH);


        // =========================
        // TAB PANEL
        // =========================

        JTabbedPane tabbedPane = new JTabbedPane();


        // =========================
        // HOTEL TABLE
        // =========================

        String[] hotelColumns = {
                "Hotel ID",
                "Name",
                "Location",
                "Amenities"
        };

        hotelModel =
                new DefaultTableModel(hotelColumns, 0);

        hotelTable =
                new JTable(hotelModel);

        tabbedPane.addTab(
                "Hotels",
                new JScrollPane(hotelTable)
        );


        // =========================
        // ROOM TABLE
        // =========================

        String[] roomColumns = {
                "Room ID",
                "Hotel ID",
                "Room Number",
                "Room Type",
                "Price",
                "Status"
        };

        roomModel =
                new DefaultTableModel(roomColumns, 0);

        roomTable =
                new JTable(roomModel);

        tabbedPane.addTab(
                "Rooms",
                new JScrollPane(roomTable)
        );


        // =========================
        // GUEST TABLE
        // =========================

        String[] guestColumns = {
                "Guest ID",
                "Name",
                "Email",
                "Phone"
        };

        guestModel =
                new DefaultTableModel(guestColumns, 0);

        guestTable =
                new JTable(guestModel);

        tabbedPane.addTab(
                "Guests",
                new JScrollPane(guestTable)
        );


        // =========================
        // RESERVATION TABLE
        // =========================

        String[] reservationColumns = {
                "Reservation ID",
                "Guest ID",
                "Room ID",
                "Check-in",
                "Check-out",
                "Total Cost",
                "Status"
        };

        reservationModel =
                new DefaultTableModel(
                        reservationColumns, 0
                );

        reservationTable =
                new JTable(reservationModel);

        tabbedPane.addTab(
                "Reservations",
                new JScrollPane(reservationTable)
        );


        add(
                tabbedPane,
                BorderLayout.CENTER
        );


        // =========================
        // REFRESH BUTTON
        // =========================

        JButton refreshButton =
                new JButton("Refresh Data");

        refreshButton.addActionListener(e -> {

            loadAllData();

            JOptionPane.showMessageDialog(
                    this,
                    "Data refreshed successfully!"
            );
        });


        JPanel bottomPanel =
                new JPanel();

        bottomPanel.add(refreshButton);

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }


    // =========================
    // LOAD ALL DATA
    // =========================

    private void loadAllData() {

        loadHotels();

        loadRooms();

        loadGuests();

        loadReservations();
    }


    // =========================
    // LOAD HOTELS
    // =========================

    private void loadHotels() {

        hotelModel.setRowCount(0);

        List<Hotel> hotels =
                hotelDAO.getAllHotels();

        for (Hotel hotel : hotels) {

            hotelModel.addRow(
                    new Object[]{
                            hotel.getHotelId(),
                            hotel.getName(),
                            hotel.getLocation(),
                            hotel.getAmenities()
                    }
            );
        }
    }


    // =========================
    // LOAD ROOMS
    // =========================

    private void loadRooms() {

        roomModel.setRowCount(0);

        List<Room> rooms =
                roomDAO.getAllRooms();

        for (Room room : rooms) {

            roomModel.addRow(
                    new Object[]{
                            room.getRoomId(),
                            room.getHotelId(),
                            room.getRoomNumber(),
                            room.getRoomType(),
                            room.getPrice(),
                            room.getStatus()
                    }
            );
        }
    }


    // =========================
    // LOAD GUESTS
    // =========================

    private void loadGuests() {

        guestModel.setRowCount(0);

        List<Guest> guests =
                guestDAO.getAllGuests();

        for (Guest guest : guests) {

            guestModel.addRow(
                    new Object[]{
                            guest.getGuestId(),
                            guest.getName(),
                            guest.getEmail(),
                            guest.getPhone()
                    }
            );
        }
    }


    // =========================
    // LOAD RESERVATIONS
    // =========================

    private void loadReservations() {

        reservationModel.setRowCount(0);

        List<Reservation> reservations =
                reservationDAO.getAllReservations();

        for (Reservation reservation : reservations) {

            reservationModel.addRow(
                    new Object[]{
                            reservation.getReservationId(),
                            reservation.getGuestId(),
                            reservation.getRoomId(),
                            reservation.getCheckIn(),
                            reservation.getCheckOut(),
                            reservation.getTotalCost(),
                            reservation.getStatus()
                    }
            );
        }
    }


 

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            RetrieveDataPanel frame =
                    new RetrieveDataPanel();

            frame.setVisible(true);

        });
    }
}