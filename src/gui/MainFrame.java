package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JButton hotelButton;
    private JButton roomButton;
    private JButton guestButton;
    private JButton reservationButton;
    private JButton retrieveButton;
    
    public MainFrame() {

       
    	setTitle("Hotel Management System");
        setSize(600, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());


        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "HOTEL MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(titleLabel, BorderLayout.NORTH);


        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(
                new GridLayout(5, 1, 15, 15)
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 100, 40, 100
                )
        );
 
        hotelButton = new JButton("Add Hotel");
        roomButton = new JButton("Add Room");
        guestButton = new JButton("Add Guest");
        reservationButton = new JButton("Add Reservation");
        retrieveButton = new JButton("Retrieve Data");


        buttonPanel.add(hotelButton);
        buttonPanel.add(roomButton);
        buttonPanel.add(guestButton);
        buttonPanel.add(reservationButton);
        buttonPanel.add(retrieveButton);
       


        add(buttonPanel, BorderLayout.CENTER);


        // =========================
        // HOTEL BUTTON
        // =========================

        hotelButton.addActionListener(e -> {

            HotelPanel hotelPanel =
                    new HotelPanel();

            hotelPanel.setVisible(true);

        });


        // =========================
        // ROOM BUTTON
        // =========================

        roomButton.addActionListener(e -> {

            RoomPanel roomPanel =
                    new RoomPanel();

            roomPanel.setVisible(true);

        });


        // =========================
        // GUEST BUTTON
        // =========================

        guestButton.addActionListener(e -> {

            GuestPanel guestPanel =
                    new GuestPanel();

            guestPanel.setVisible(true);

        });


        // =========================
        // RESERVATION BUTTON
        // =========================

        reservationButton.addActionListener(e -> {

            ReservationPanel reservationPanel =
                    new ReservationPanel();

            reservationPanel.setVisible(true);

        });


        // =========================
        // EXIT BUTTON
        // =========================

        retrieveButton.addActionListener(e -> {

            RetrieveDataPanel panel =
                    new RetrieveDataPanel();

            panel.setVisible(true);

        });
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainFrame frame =
                    new MainFrame();

            frame.setVisible(true);

        });
    }
}