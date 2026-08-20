package gui;

import dao.HotelDAO;
import model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HotelPanel extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField locationField;
    private JTextField amenitiesField;

    private JTable hotelTable;
    private DefaultTableModel tableModel;

    private HotelDAO hotelDAO;

    public HotelPanel() {

        hotelDAO = new HotelDAO();

        setTitle("Hotel Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createGUI();

        loadHotels();
    }

    private void createGUI() {

        setLayout(new BorderLayout(10, 10));

        // -------------------------
        // TITLE
        // -------------------------

        JLabel titleLabel = new JLabel(
                "HOTEL MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(titleLabel, BorderLayout.NORTH);


        // -------------------------
        // FORM PANEL
        // -------------------------

        JPanel formPanel = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 10, 30
                )
        );


        idField = new JTextField();
        nameField = new JTextField();
        locationField = new JTextField();
        amenitiesField = new JTextField();


        formPanel.add(new JLabel("Hotel ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Location:"));
        formPanel.add(locationField);

        formPanel.add(new JLabel("Amenities:"));
        formPanel.add(amenitiesField);


        // -------------------------
        // BUTTON PANEL
        // -------------------------

        JPanel buttonPanel = new JPanel(
                new FlowLayout()
        );

        JButton addButton =
                new JButton("Add");

        JButton updateButton =
                new JButton("Update");

        JButton deleteButton =
                new JButton("Delete");

        JButton clearButton =
                new JButton("Clear");


        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);


        // -------------------------
        // TOP PANEL
        // -------------------------

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


        // -------------------------
        // TABLE
        // -------------------------

        String[] columns = {
                "ID",
                "Name",
                "Location",
                "Amenities"
        };

        tableModel =
                new DefaultTableModel(columns, 0);

        hotelTable =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(hotelTable);

        add(
                scrollPane,
                BorderLayout.CENTER
        );


        // -------------------------
        // ADD BUTTON
        // -------------------------

        addButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText();

                String location =
                        locationField.getText();

                String amenities =
                        amenitiesField.getText();


                if (name.isEmpty() ||
                    location.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Name and Location are required!"
                    );

                    return;
                }


                Hotel hotel =
                        new Hotel(
                                name,
                                location,
                                amenities
                        );

                hotelDAO.addHotel(hotel);


                JOptionPane.showMessageDialog(
                        this,
                        "Hotel added successfully!"
                );


                clearFields();

                loadHotels();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });


        // -------------------------
        // UPDATE BUTTON
        // -------------------------

        updateButton.addActionListener(e -> {

            try {

                if (idField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Enter Hotel ID!"
                    );

                    return;
                }


                int id =
                        Integer.parseInt(
                                idField.getText()
                        );


                Hotel hotel =
                        new Hotel(
                                id,
                                nameField.getText(),
                                locationField.getText(),
                                amenitiesField.getText()
                        );


                hotelDAO.updateHotel(hotel);


                JOptionPane.showMessageDialog(
                        this,
                        "Hotel updated successfully!"
                );


                clearFields();

                loadHotels();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });


        // -------------------------
        // DELETE BUTTON
        // -------------------------

        deleteButton.addActionListener(e -> {

            try {

                if (idField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Enter Hotel ID!"
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
                                "Delete this hotel?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );


                if (choice ==
                        JOptionPane.YES_OPTION) {

                    hotelDAO.deleteHotel(id);

                    JOptionPane.showMessageDialog(
                            this,
                            "Hotel deleted successfully!"
                    );

                    clearFields();

                    loadHotels();
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );
            }
        });


        // -------------------------
        // CLEAR BUTTON
        // -------------------------

        clearButton.addActionListener(e -> {

            clearFields();

        });


        // -------------------------
        // TABLE CLICK
        // -------------------------

        hotelTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                hotelTable.getSelectedRow();

                        if (row >= 0) {

                            idField.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            nameField.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            locationField.setText(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            amenitiesField.setText(
                                    tableModel
                                            .getValueAt(row, 3)
                                            .toString()
                            );
                        }
                    }
                });
    }


    // -------------------------
    // LOAD HOTELS
    // -------------------------

    private void loadHotels() {

        tableModel.setRowCount(0);

        List<Hotel> hotels =
                hotelDAO.getAllHotels();


        for (Hotel hotel : hotels) {

            tableModel.addRow(
                    new Object[]{
                            hotel.getHotelId(),
                            hotel.getName(),
                            hotel.getLocation(),
                            hotel.getAmenities()
                    }
            );
        }
    }


    // -------------------------
    // CLEAR FIELDS
    // -------------------------

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        amenitiesField.setText("");

        hotelTable.clearSelection();
    }


    // -------------------------
    // MAIN METHOD FOR TESTING
    // -------------------------

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HotelPanel frame =
                    new HotelPanel();

            frame.setVisible(true);
        });
    }
}