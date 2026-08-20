package gui;

import dao.GuestDAO;
import model.Guest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GuestPanel extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    private JTable guestTable;
    private DefaultTableModel tableModel;

    private GuestDAO guestDAO;

    public GuestPanel() {

        guestDAO = new GuestDAO();

        setTitle("Guest Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createGUI();

        loadGuests();
    }

    private void createGUI() {

        setLayout(new BorderLayout(10, 10));

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "GUEST MANAGEMENT",
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
                new GridLayout(4, 2, 10, 10)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 10, 30
                )
        );

        idField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();

        formPanel.add(new JLabel("Guest ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);


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
                "Name",
                "Email",
                "Phone"
        };

        tableModel =
                new DefaultTableModel(columns, 0);

        guestTable = new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(guestTable);

        add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =========================
        // ADD BUTTON
        // =========================

        addButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText().trim();

                String email =
                        emailField.getText().trim();

                String phone =
                        phoneField.getText().trim();


                if (name.isEmpty()
                        || email.isEmpty()
                        || phone.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill all fields!"
                    );

                    return;
                }


                Guest guest = new Guest(
                        name,
                        email,
                        phone
                );


                guestDAO.addGuest(guest);


                JOptionPane.showMessageDialog(
                        this,
                        "Guest added successfully!"
                );


                clearFields();

                loadGuests();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
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
                            "Select a guest first!"
                    );

                    return;
                }


                int id =
                        Integer.parseInt(
                                idField.getText()
                        );


                Guest guest = new Guest(
                        id,
                        nameField.getText().trim(),
                        emailField.getText().trim(),
                        phoneField.getText().trim()
                );


                guestDAO.updateGuest(guest);


                JOptionPane.showMessageDialog(
                        this,
                        "Guest updated successfully!"
                );


                clearFields();

                loadGuests();

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
                            "Select a guest first!"
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
                                "Delete this guest?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );


                if (choice ==
                        JOptionPane.YES_OPTION) {

                    guestDAO.deleteGuest(id);


                    JOptionPane.showMessageDialog(
                            this,
                            "Guest deleted successfully!"
                    );


                    clearFields();

                    loadGuests();
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

        guestTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                guestTable.getSelectedRow();

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

                            emailField.setText(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            phoneField.setText(
                                    tableModel
                                            .getValueAt(row, 3)
                                            .toString()
                            );
                        }
                    }
                });
    }


    // =========================
    // LOAD GUESTS
    // =========================

    private void loadGuests() {

        tableModel.setRowCount(0);

        List<Guest> guests =
                guestDAO.getAllGuests();


        for (Guest guest : guests) {

            tableModel.addRow(
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
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");

        guestTable.clearSelection();
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            GuestPanel frame =
                    new GuestPanel();

            frame.setVisible(true);
        });
    }
}
