package gui;

import dao.RoomDAO;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomPanel extends JFrame {

    private JTextField idField;
    private JTextField hotelIdField;
    private JTextField roomNumberField;
    private JTextField roomTypeField;
    private JTextField priceField;
    private JTextField statusField;

    private JTable roomTable;
    private DefaultTableModel tableModel;

    private RoomDAO roomDAO;

    public RoomPanel() {

        roomDAO = new RoomDAO();

        setTitle("Room Management");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createGUI();

        loadRooms();
    }

    private void createGUI() {

        setLayout(new BorderLayout(10, 10));

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "ROOM MANAGEMENT",
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
                new GridLayout(6, 2, 10, 10)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 10, 30
                )
        );


        idField = new JTextField();
        hotelIdField = new JTextField();
        roomNumberField = new JTextField();
        roomTypeField = new JTextField();
        priceField = new JTextField();
        statusField = new JTextField();


        formPanel.add(new JLabel("Room ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Hotel ID:"));
        formPanel.add(hotelIdField);

        formPanel.add(new JLabel("Room Number:"));
        formPanel.add(roomNumberField);

        formPanel.add(new JLabel("Room Type:"));
        formPanel.add(roomTypeField);

        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);

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
                "Hotel ID",
                "Room Number",
                "Room Type",
                "Price",
                "Status"
        };

        tableModel =
                new DefaultTableModel(columns, 0);

        roomTable = new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(roomTable);

        add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =========================
        // ADD
        // =========================

        addButton.addActionListener(e -> {

            try {

                if (hotelIdField.getText().isEmpty()
                        || roomNumberField.getText().isEmpty()
                        || roomTypeField.getText().isEmpty()
                        || priceField.getText().isEmpty()
                        || statusField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill all fields!"
                    );

                    return;
                }


                int hotelId =
                        Integer.parseInt(
                                hotelIdField.getText()
                        );

                String roomNumber =
                        roomNumberField.getText();

                String roomType =
                        roomTypeField.getText();

                double price =
                        Double.parseDouble(
                                priceField.getText()
                        );

                String status =
                        statusField.getText();


                Room room = new Room(
                        hotelId,
                        roomNumber,
                        roomType,
                        price,
                        status
                );


                roomDAO.addRoom(room);


                JOptionPane.showMessageDialog(
                        this,
                        "Room added successfully!"
                );


                clearFields();

                loadRooms();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hotel ID and Price must be valid numbers!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });


        // =========================
        // UPDATE
        // =========================

        updateButton.addActionListener(e -> {

            try {

                if (idField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Select a room first!"
                    );

                    return;
                }


                int id =
                        Integer.parseInt(
                                idField.getText()
                        );

                int hotelId =
                        Integer.parseInt(
                                hotelIdField.getText()
                        );

                String roomNumber =
                        roomNumberField.getText();

                String roomType =
                        roomTypeField.getText();

                double price =
                        Double.parseDouble(
                                priceField.getText()
                        );

                String status =
                        statusField.getText();


                Room room = new Room(
                        id,
                        hotelId,
                        roomNumber,
                        roomType,
                        price,
                        status
                );


                roomDAO.updateRoom(room);


                JOptionPane.showMessageDialog(
                        this,
                        "Room updated successfully!"
                );


                clearFields();

                loadRooms();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });


        // =========================
        // DELETE
        // =========================

        deleteButton.addActionListener(e -> {

            try {

                if (idField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Select a room first!"
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
                                "Delete this room?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );


                if (choice ==
                        JOptionPane.YES_OPTION) {

                    roomDAO.deleteRoom(id);


                    JOptionPane.showMessageDialog(
                            this,
                            "Room deleted successfully!"
                    );


                    clearFields();

                    loadRooms();
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });


        // =========================
        // CLEAR
        // =========================

        clearButton.addActionListener(e -> {

            clearFields();

        });


        // =========================
        // TABLE CLICK
        // =========================

        roomTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                roomTable.getSelectedRow();

                        if (row >= 0) {

                            idField.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            hotelIdField.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            roomNumberField.setText(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            roomTypeField.setText(
                                    tableModel
                                            .getValueAt(row, 3)
                                            .toString()
                            );

                            priceField.setText(
                                    tableModel
                                            .getValueAt(row, 4)
                                            .toString()
                            );

                            statusField.setText(
                                    tableModel
                                            .getValueAt(row, 5)
                                            .toString()
                            );
                        }
                    }
                });
    }


    // =========================
    // LOAD ROOMS
    // =========================

    private void loadRooms() {

        tableModel.setRowCount(0);

        List<Room> rooms =
                roomDAO.getAllRooms();


        for (Room room : rooms) {

            tableModel.addRow(
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
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        idField.setText("");
        hotelIdField.setText("");
        roomNumberField.setText("");
        roomTypeField.setText("");
        priceField.setText("");
        statusField.setText("");

        roomTable.clearSelection();
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            RoomPanel frame =
                    new RoomPanel();

            frame.setVisible(true);
        });
    }
}