package gui;

import dao.ReservationDAO;
import model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ReservationPanel extends JFrame {

    private JTextField idField;
    private JTextField guestIdField;
    private JTextField roomIdField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextField totalCostField;
    private JTextField statusField;

    private JTable reservationTable;
    private DefaultTableModel tableModel;

    private ReservationDAO reservationDAO;

    public ReservationPanel() {

        reservationDAO = new ReservationDAO();

        setTitle("Reservation Management");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createGUI();

        loadReservations();
    }

    private void createGUI() {

        setLayout(new BorderLayout(10, 10));

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "RESERVATION MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(titleLabel, BorderLayout.NORTH);


        // =========================
        // FORM
        // =========================

        JPanel formPanel = new JPanel(
                new GridLayout(7, 2, 10, 10)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 10, 30
                )
        );

        idField = new JTextField();
        guestIdField = new JTextField();
        roomIdField = new JTextField();
        checkInField = new JTextField();
        checkOutField = new JTextField();
        totalCostField = new JTextField();
        statusField = new JTextField();

        formPanel.add(new JLabel("Reservation ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Guest ID:"));
        formPanel.add(guestIdField);

        formPanel.add(new JLabel("Room ID:"));
        formPanel.add(roomIdField);

        formPanel.add(new JLabel("Check-in (YYYY-MM-DD):"));
        formPanel.add(checkInField);

        formPanel.add(new JLabel("Check-out (YYYY-MM-DD):"));
        formPanel.add(checkOutField);

        formPanel.add(new JLabel("Total Cost:"));
        formPanel.add(totalCostField);

        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusField);


        // =========================
        // BUTTONS
        // =========================

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);


        // =========================
        // TOP PANEL
        // =========================

        JPanel topPanel = new JPanel(
                new BorderLayout()
        );

        topPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(topPanel, BorderLayout.NORTH);


        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "ID",
                "Guest ID",
                "Room ID",
                "Check-in",
                "Check-out",
                "Total Cost",
                "Status"
        };

        tableModel =
                new DefaultTableModel(columns, 0);

        reservationTable =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(reservationTable);

        add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =========================
        // ADD BUTTON
        // =========================

        addButton.addActionListener(e -> {

            try {

                if (guestIdField.getText().isEmpty()
                        || roomIdField.getText().isEmpty()
                        || checkInField.getText().isEmpty()
                        || checkOutField.getText().isEmpty()
                        || totalCostField.getText().isEmpty()
                        || statusField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill all fields!"
                    );

                    return;
                }


                int guestId =
                        Integer.parseInt(
                                guestIdField.getText()
                        );

                int roomId =
                        Integer.parseInt(
                                roomIdField.getText()
                        );

                LocalDate checkIn =
                        LocalDate.parse(
                                checkInField.getText()
                        );

                LocalDate checkOut =
                        LocalDate.parse(
                                checkOutField.getText()
                        );

                double totalCost =
                        Double.parseDouble(
                                totalCostField.getText()
                        );

                String status =
                        statusField.getText();


                if (!checkOut.isAfter(checkIn)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Check-out date must be after check-in date!"
                    );

                    return;
                }


                Reservation reservation =
                        new Reservation(
                                guestId,
                                roomId,
                                checkIn,
                                checkOut,
                                totalCost,
                                status
                        );


                reservationDAO.addReservation(
                        reservation
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Reservation added successfully!"
                );


                clearFields();

                loadReservations();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Guest ID, Room ID and Total Cost must be valid numbers!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid date or other input!\n"
                                + "Use date format: YYYY-MM-DD"
                );
            }
        });


        // =========================
        // UPDATE BUTTON
        // =========================

        updateButton.addActionListener(e -> {

            try {

                if (idField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Select a reservation first!"
                    );

                    return;
                }


                int id =
                        Integer.parseInt(
                                idField.getText()
                        );

                int guestId =
                        Integer.parseInt(
                                guestIdField.getText()
                        );

                int roomId =
                        Integer.parseInt(
                                roomIdField.getText()
                        );

                LocalDate checkIn =
                        LocalDate.parse(
                                checkInField.getText()
                        );

                LocalDate checkOut =
                        LocalDate.parse(
                                checkOutField.getText()
                        );

                double totalCost =
                        Double.parseDouble(
                                totalCostField.getText()
                        );

                String status =
                        statusField.getText();


                if (!checkOut.isAfter(checkIn)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Check-out date must be after check-in date!"
                    );

                    return;
                }


                Reservation reservation =
                        new Reservation(
                                id,
                                guestId,
                                roomId,
                                checkIn,
                                checkOut,
                                totalCost,
                                status
                        );


                reservationDAO.updateReservation(
                        reservation
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Reservation updated successfully!"
                );


                clearFields();

                loadReservations();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });


        // =========================
        // DELETE BUTTON
        // =========================

        deleteButton.addActionListener(e -> {

            try {

                if (idField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Select a reservation first!"
                    );

                    return;
                }


                int id =
                        Integer.parseInt(
                                idField.getText()
                        );


                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Delete this reservation?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );


                if (choice ==
                        JOptionPane.YES_OPTION) {

                    reservationDAO.deleteReservation(
                            id
                    );


                    JOptionPane.showMessageDialog(
                            this,
                            "Reservation deleted successfully!"
                    );


                    clearFields();

                    loadReservations();
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });


        // =========================
        // CLEAR BUTTON
        // =========================

        clearButton.addActionListener(e -> {

            clearFields();

        });


        // =========================
        // TABLE CLICK
        // =========================

        reservationTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                reservationTable
                                        .getSelectedRow();

                        if (row >= 0) {

                            idField.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            guestIdField.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            roomIdField.setText(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            checkInField.setText(
                                    tableModel
                                            .getValueAt(row, 3)
                                            .toString()
                            );

                            checkOutField.setText(
                                    tableModel
                                            .getValueAt(row, 4)
                                            .toString()
                            );

                            totalCostField.setText(
                                    tableModel
                                            .getValueAt(row, 5)
                                            .toString()
                            );

                            statusField.setText(
                                    tableModel
                                            .getValueAt(row, 6)
                                            .toString()
                            );
                        }
                    }
                });
    }


    // =========================
    // LOAD RESERVATIONS
    // =========================

    private void loadReservations() {

        tableModel.setRowCount(0);

        List<Reservation> reservations =
                reservationDAO.getAllReservations();


        for (Reservation reservation :
                reservations) {

            tableModel.addRow(
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


    // =========================
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        idField.setText("");
        guestIdField.setText("");
        roomIdField.setText("");
        checkInField.setText("");
        checkOutField.setText("");
        totalCostField.setText("");
        statusField.setText("");

        reservationTable.clearSelection();
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ReservationPanel frame =
                    new ReservationPanel();

            frame.setVisible(true);
        });
    }
}