import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GitWheels {

    private static final String CUSTOMER_USERNAME = "Customer";
    private static final String CUSTOMER_PASSWORD = "password";

    private static int customerRentalCount = 0;
    private static double currentRentalCost = 0;
    private static String carRented;
    private static String paymentMethod;
    private static String rentalStartDate;
    private static String rentalEndDate;

    private static final JLabel totalCustomersLabel = new JLabel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Git Wheels Car Rental Service System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new CardLayout());
            frame.setLocationRelativeTo(null);

            // Color Palette used
            Color backgroundColor = new Color(45, 61, 78);
            Color buttonColor = new Color(70, 130, 180);
            Color textColor = Color.WHITE; // White text for buttons
            Color textFieldColor = new Color(245, 245, 245);
            Color labelColor = new Color(255, 165, 0);

            CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();

            // Log In Panel
            ImageIcon logoIcon = new ImageIcon("logo.png");

            JPanel loginPanel = new JPanel();
            loginPanel.setBackground(new Color(45, 61, 78));
            loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

            JLabel logoLabel1 = new JLabel(logoIcon);
            logoLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginPanel.add(logoLabel1);

            JLabel loginTitleLabel = new JLabel("LOG IN");
            loginTitleLabel.setForeground(new Color(255, 165, 0));
            loginTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
            loginTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginPanel.add(loginTitleLabel);

            JLabel userLabel = new JLabel("Username:");
            userLabel.setForeground(Color.WHITE);
            userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            userLabel.setFont(new Font("Arial", Font.BOLD, 15));
            JTextField userField = new JTextField();
            userField.setMaximumSize(new Dimension(200, 25));
            userField.setBackground(new Color(245, 245, 245));
            userField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            userField.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel passLabel = new JLabel("Password:");
            passLabel.setForeground(Color.WHITE);
            passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            passLabel.setFont(new Font("Arial", Font.BOLD, 15));
            JPasswordField passField = new JPasswordField();
            passField.setMaximumSize(new Dimension(200, 25));
            passField.setBackground(new Color(245, 245, 245));
            passField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            passField.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton loginButton = new JButton("Log In");
            loginButton.setBackground(new Color(70, 130, 180));
            loginButton.setForeground(Color.WHITE); //
            loginButton.setFont(new Font("Arial", Font.BOLD, 15));
            loginButton.setFocusPainted(false);
            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBackground(new Color(255, 69, 0));
            cancelButton.setFont(new Font("Arial", Font.BOLD, 15));
            cancelButton.setForeground(Color.WHITE);
            cancelButton.setFocusPainted(false);
            cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            //Layout format of the log in panel
            loginPanel.add(Box.createVerticalStrut(10));
            loginPanel.add(loginTitleLabel);
            loginPanel.add(Box.createVerticalStrut(20));
            loginPanel.add(userLabel);
            loginPanel.add(Box.createVerticalStrut(5));
            loginPanel.add(userField);
            loginPanel.add(Box.createVerticalStrut(10));
            loginPanel.add(passLabel);
            loginPanel.add(Box.createVerticalStrut(5));
            loginPanel.add(passField);
            loginPanel.add(Box.createVerticalStrut(20));
            loginPanel.add(loginButton);
            loginPanel.add(Box.createVerticalStrut(10));
            loginPanel.add(cancelButton);

            frame.add(loginPanel, "LoginPanel");

            // Git Wheels Main Panel

            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(backgroundColor);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            frame.add(mainPanel, "MainPanel");

            // Logo
            logoLabel1 = new JLabel(logoIcon);
            logoLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);


            // Welcome to the Program Label
            JLabel programNameLabel = new JLabel("Welcome to Git Wheels Car Rental Service System!");
            programNameLabel.setForeground(labelColor);
            programNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            programNameLabel.setFont(new Font("Arial", Font.BOLD, 25));

            // Rent Car Question Label
            JLabel rentCarQuestionLabel = new JLabel("Do you want to rent a car?");
            rentCarQuestionLabel.setForeground(textColor);
            rentCarQuestionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            rentCarQuestionLabel.setFont(new Font("Arial", Font.BOLD, 20));


            // Button Panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(backgroundColor);
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton yesButton = new JButton("Yes");
            JButton noButton = new JButton("No");
            styleButton(yesButton, buttonColor, textColor);
            styleButton(noButton, buttonColor, textColor);

            //Layout format of the Git Wheels Main panel
            mainPanel.add(logoLabel1);
            mainPanel.add(Box.createVerticalStrut(10));
            mainPanel.add(programNameLabel);
            mainPanel.add(Box.createVerticalStrut(10));
            mainPanel.add(rentCarQuestionLabel);
            mainPanel.add(Box.createVerticalStrut(20));
            buttonPanel.add(yesButton);
            buttonPanel.add(noButton);
            mainPanel.add(buttonPanel);


            // Customer Information Panel
            JPanel customerInfoPanel = new JPanel(new GridLayout(8, 2, 5, 10));
            customerInfoPanel.setBackground(backgroundColor);
            JLabel nameLabel = new JLabel("Name: ");
            JLabel houseStreetLabel = new JLabel("House & Street No: ");
            JLabel barangayLabel = new JLabel("Barangay: ");
            JLabel municipalityLabel = new JLabel("Municipality: ");
            JLabel cityProvinceLabel = new JLabel("City/Province: ");
            JLabel contactLabel = new JLabel("Contact No: ");
            setLabelColor(textColor, nameLabel, houseStreetLabel, barangayLabel, municipalityLabel, cityProvinceLabel, contactLabel);
            JTextField nameField = new JTextField();
            JTextField houseStreetField = new JTextField();
            JTextField barangayField = new JTextField();
            JTextField municipalityField = new JTextField();
            JTextField cityProvinceField = new JTextField();
            JTextField contactField = new JTextField();
            setTextFieldStyle(textFieldColor, nameField, houseStreetField, barangayField, municipalityField, cityProvinceField, contactField);

            contactField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
            //Layout format of the Customer Info panel
            customerInfoPanel.add(nameLabel);
            customerInfoPanel.add(nameField);
            customerInfoPanel.add(houseStreetLabel);
            customerInfoPanel.add(houseStreetField);
            customerInfoPanel.add(barangayLabel);
            customerInfoPanel.add(barangayField);
            customerInfoPanel.add(municipalityLabel);
            customerInfoPanel.add(municipalityField);
            customerInfoPanel.add(cityProvinceLabel);
            customerInfoPanel.add(cityProvinceField);
            customerInfoPanel.add(contactLabel);
            customerInfoPanel.add(contactField);

            JButton nextButton = new JButton("Next");
            styleButton(nextButton, buttonColor, textColor);
            customerInfoPanel.add(new JLabel());
            customerInfoPanel.add(nextButton);
            frame.add(customerInfoPanel, "CustomerInfoPanel");

            // Car Choices Panel
            JPanel carChoicePanel = new JPanel(new BorderLayout());
            carChoicePanel.setBackground(backgroundColor);

            // Table Items
            String[] columnNames = {"Seats", "Description", "Price", "Photo"};
            Object[][] data = {
                    {"5", "Toyota Vios (SEDAN)", "1200 Php/day", new ImageIcon("Vios.jpg")},
                    {"7", "Toyota Fortuner (SUV)", "1600 Php/day", new ImageIcon("Fortuner.jpg")},
                    {"8", "Toyota Innova (MPV)", "1900 Php/day", new ImageIcon("Innova.jpg")},
                    {"5", "Toyota Hilux (PICK-UP)", "2000 Php/day", new ImageIcon("Hilux.jpg")},
                    {"15", "Toyota Commuter (VAN)", "2500 Php/day", new ImageIcon("Commuter.jpg")}
            };

            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public Class<?> getColumnClass(int column) {
                    return (column == 3) ? Icon.class : Object.class;
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable carTable = new JTable(model);
            carTable.setRowHeight(60);
            carTable.getColumnModel().getColumn(3).setPreferredWidth(100);

            JScrollPane scrollPane = new JScrollPane(carTable);
            carChoicePanel.add(scrollPane, BorderLayout.CENTER);

            JPanel inputPanel = new JPanel();
            inputPanel.setBackground(backgroundColor);
            JLabel rentalDaysLabel = new JLabel("How many days do you want to rent the car? (Max 5 days)");
            rentalDaysLabel.setForeground(textColor);
            JTextField rentalDaysField = new JTextField(5);
            rentalDaysField.setBackground(textFieldColor);
            rentalDaysField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            inputPanel.add(rentalDaysLabel);
            inputPanel.add(rentalDaysField);

            JButton calculateCostButton = new JButton("Calculate Cost");
            styleButton(calculateCostButton, buttonColor, textColor);
            inputPanel.add(calculateCostButton);

            JButton selectCarButton = new JButton("Select Car");
            styleButton(selectCarButton, buttonColor, textColor);
            inputPanel.add(selectCarButton);

            carChoicePanel.add(inputPanel, BorderLayout.SOUTH);
            frame.add(carChoicePanel, "CarChoicePanel");


            // Payment Options Panel
            JPanel paymentPanel = new JPanel();
            paymentPanel.setBackground(backgroundColor);
            paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

            JLabel selectPaymentLabel = new JLabel("Select Payment Method:");
            selectPaymentLabel.setForeground(textColor);
            selectPaymentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            paymentPanel.add(selectPaymentLabel);

            // Button Cash
            ButtonGroup paymentGroup = new ButtonGroup();
            JRadioButton cashButton = new JRadioButton("Cash at Pickup");
            cashButton.setFont(new Font("Arial", Font.BOLD, 16));
            cashButton.setBackground(buttonColor);
            cashButton.setForeground(textColor);

            // Button GCash
            JRadioButton gcashButton = new JRadioButton("GCash");
            gcashButton.setFont(new Font("Arial", Font.BOLD, 16));
            gcashButton.setBackground(buttonColor);
            gcashButton.setForeground(textColor);

            // Button Online Banking
            JRadioButton onlineBankingButton = new JRadioButton("Online Banking");
            onlineBankingButton.setFont(new Font("Arial", Font.BOLD, 16));
            onlineBankingButton.setBackground(buttonColor);
            onlineBankingButton.setForeground(textColor);

            // Layout format of the Payment Options
            paymentGroup.add(cashButton);
            paymentGroup.add(gcashButton);
            paymentGroup.add(onlineBankingButton);

            JPanel paymentOptionsPanel = new JPanel();
            paymentOptionsPanel.setBackground(backgroundColor);
            paymentOptionsPanel.setLayout(new GridLayout(1, 3, 10, 0));
            paymentOptionsPanel.add(cashButton); // Ensure cash button is added
            paymentOptionsPanel.add(gcashButton);
            paymentOptionsPanel.add(onlineBankingButton);
            paymentPanel.add(paymentOptionsPanel);

            // Cash Panel
            JPanel cashPanel = new JPanel(new FlowLayout());
            cashPanel.setBackground(backgroundColor);
            cashPanel.setVisible(false);
            JLabel cashLabel = new JLabel("Pay at Cashier Upon Pickup.");
            cashLabel.setFont(new Font("Arial", Font.BOLD, 20));
            cashLabel.setForeground(textColor);
            cashPanel.add(cashLabel);

            // GCash Panel
            JPanel gcashPanel = new JPanel(new FlowLayout());
            gcashPanel.setBackground(backgroundColor);
            gcashPanel.setVisible(false);
            JLabel gcashLabel = new JLabel("Pay at GCash Account: 09608991460");
            gcashLabel.setFont(new Font("Arial", Font.BOLD, 20));
            gcashLabel.setForeground(textColor);
            gcashPanel.add(gcashLabel);

            // Online Banking Panel
            JPanel onlineBankingPanel = new JPanel(new BorderLayout());
            onlineBankingPanel.setBackground(backgroundColor);
            onlineBankingPanel.setVisible(false);

            JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
            formPanel.setBackground(backgroundColor);

            JLabel bankLabel = new JLabel("Enter Online Banking Details:");
            bankLabel.setForeground(textColor);

            // Online Banking Fields
            JLabel cardHolderLabel = new JLabel("Cardholder Name:");
            cardHolderLabel.setForeground(textColor);
            JTextField cardHolderField = new JTextField(15);
            cardHolderField.setBackground(textFieldColor);
            cardHolderField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel cardNumberLabel = new JLabel("Card Number:");
            cardNumberLabel.setForeground(textColor);
            JTextField cardNumberField = new JTextField(15);
            cardNumberField.setBackground(textFieldColor);
            cardNumberField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel expirationDateLabel = new JLabel("Expiration Date (MM/YY):");
            expirationDateLabel.setForeground(textColor);
            JTextField expirationDateField = new JTextField(5);
            expirationDateField.setBackground(textFieldColor);
            expirationDateField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel cvvLabel = new JLabel("CVV:");
            cvvLabel.setForeground(textColor);
            JTextField cvvField = new JTextField(3);
            cvvField.setBackground(textFieldColor);
            cvvField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // Layout format of the Online Banking
            formPanel.add(cardHolderLabel);
            formPanel.add(cardHolderField);
            formPanel.add(cardNumberLabel);
            formPanel.add(cardNumberField);
            formPanel.add(expirationDateLabel);
            formPanel.add(expirationDateField);
            formPanel.add(cvvLabel);
            formPanel.add(cvvField);

            onlineBankingPanel.add(bankLabel, BorderLayout.NORTH);
            onlineBankingPanel.add(formPanel, BorderLayout.CENTER);

            paymentPanel.add(cashPanel);
            paymentPanel.add(gcashPanel);
            paymentPanel.add(onlineBankingPanel);

            // Proceed Button
            JButton proceedButton = new JButton("Proceed");
            styleButton(proceedButton, buttonColor, textColor);
            paymentPanel.add(proceedButton);
            // Cancel Button
            JButton cancelOrderButton = new JButton("Cancel Order");
            styleButton(cancelOrderButton, new Color(255, 69, 0), textColor);
            paymentPanel.add(cancelOrderButton);

            frame.add(paymentPanel, "PaymentPanel");

            // Receipt Panel
            ImageIcon logoIcon2 = new ImageIcon("logo.png");

            JPanel receiptPanel = new JPanel();
            receiptPanel.setBackground(backgroundColor);
            receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));

            JLabel logoLabel2 = new JLabel(logoIcon2);
            logoLabel2.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the logo
            receiptPanel.add(logoLabel2);

            JLabel receiptLabel = new JLabel("Receipt");
            receiptLabel.setForeground(labelColor);
            receiptLabel.setFont(new Font("Arial", Font.BOLD, 30));
            receiptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel carRentedLabel = new JLabel();
            carRentedLabel.setForeground(textColor);
            carRentedLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            carRentedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel rentalPeriodLabel = new JLabel();
            rentalPeriodLabel.setForeground(textColor);
            rentalPeriodLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            rentalPeriodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel paymentMethodLabelReceipt = new JLabel();
            paymentMethodLabelReceipt.setForeground(textColor);
            paymentMethodLabelReceipt.setFont(new Font("Arial", Font.PLAIN, 20));
            paymentMethodLabelReceipt.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel rentalCostLabel = new JLabel();
            rentalCostLabel.setForeground(labelColor);
            rentalCostLabel.setFont(new Font("Arial", Font.PLAIN, 25));
            rentalCostLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton confirmRentalButton = new JButton("Confirm Rental");
            styleButton(confirmRentalButton, buttonColor, textColor);
            confirmRentalButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton cancelRentalButton = new JButton("Cancel Rental");
            styleButton(cancelRentalButton, new Color(255, 69, 0), textColor);
            cancelRentalButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            //Layout format of the Receipt
            receiptPanel.add(logoLabel2);
            receiptPanel.add(Box.createVerticalStrut(15));
            receiptPanel.add(receiptLabel);
            receiptPanel.add(Box.createVerticalStrut(20));
            receiptPanel.add(carRentedLabel);
            receiptPanel.add(Box.createVerticalStrut(10));
            receiptPanel.add(rentalPeriodLabel);
            receiptPanel.add(Box.createVerticalStrut(10));
            receiptPanel.add(paymentMethodLabelReceipt);
            receiptPanel.add(Box.createVerticalStrut(10));
            receiptPanel.add(rentalCostLabel);
            receiptPanel.add(Box.createVerticalStrut(25));
            receiptPanel.add(confirmRentalButton);
            receiptPanel.add(Box.createVerticalStrut(20));
            receiptPanel.add(cancelRentalButton);

            frame.add(receiptPanel, "ReceiptPanel");

            // Action listeners for the Log In Panel
            loginButton.addActionListener(e -> {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                if (CUSTOMER_USERNAME.equals(username) && CUSTOMER_PASSWORD.equals(password)) {
                    cardLayout.show(frame.getContentPane(), "MainPanel");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            cancelButton.addActionListener(e -> {
                userField.setText("");
                passField.setText("");
            });

            // Action listeners for the Customer Info Panel
            yesButton.addActionListener(e -> cardLayout.show(frame.getContentPane(), "CustomerInfoPanel"));

            noButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Thank you for visiting Git Wheels! Goodbye!");
                System.exit(0);
            });

            nextButton.addActionListener(e -> {
                if (nameField.getText().isEmpty() || houseStreetField.getText().isEmpty() || barangayField.getText().isEmpty()
                        || municipalityField.getText().isEmpty() || cityProvinceField.getText().isEmpty() || contactField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    cardLayout.show(frame.getContentPane(), "CarChoicePanel");
                }
            });

            // Action listener for Calculate Cost button
            calculateCostButton.addActionListener(e -> {
                int selectedRow = carTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a car", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int rentalDays = Integer.parseInt(rentalDaysField.getText());
                        if (rentalDays < 1 || rentalDays > 5) {
                            JOptionPane.showMessageDialog(frame, "Please enter a valid number of days (1-5)", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            double carPrice = Double.parseDouble(carTable.getValueAt(selectedRow, 2).toString().replaceAll("[^0-9]", ""));
                            double rentalCost = carPrice * rentalDays;
                            JOptionPane.showMessageDialog(frame, "Total Rental Cost: " + rentalCost + " Php", "Rental Cost", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number of days", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Action listener and Function for Select Car
            selectCarButton.addActionListener(e -> {
                int selectedRow = carTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a car", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int rentalDays = Integer.parseInt(rentalDaysField.getText());
                        if (rentalDays < 1 || rentalDays > 5) {
                            JOptionPane.showMessageDialog(frame, "Please enter a valid number of days (1-5)", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String carDescription = carTable.getValueAt(selectedRow, 1).toString();
                            double carPrice = Double.parseDouble(carTable.getValueAt(selectedRow, 2).toString().replaceAll("[^0-9]", ""));
                            carRented = carDescription;
                            currentRentalCost = carPrice * rentalDays;
                            rentalStartDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DAY_OF_MONTH, rentalDays);
                            rentalEndDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                            cardLayout.show(frame.getContentPane(), "PaymentPanel");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number of days", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Action Listener and Function for Payment Method Selection
            cashButton.addActionListener(e -> {
                cashPanel.setVisible(true);
                gcashPanel.setVisible(false);
                onlineBankingPanel.setVisible(false);
            });

            gcashButton.addActionListener(e -> {
                cashPanel.setVisible(false);
                gcashPanel.setVisible(true);
                onlineBankingPanel.setVisible(false);
            });

            onlineBankingButton.addActionListener(e -> {
                cashPanel.setVisible(false);
                gcashPanel.setVisible(false);
                onlineBankingPanel.setVisible(true);
            });

            proceedButton.addActionListener(e -> {
                if (cashButton.isSelected()) {
                    paymentMethod = "Cash at Pickup";
                } else if (gcashButton.isSelected()) {
                    paymentMethod = "GCash";
                } else if (onlineBankingButton.isSelected()) {
                    if (cardHolderField.getText().isEmpty() || cardNumberField.getText().isEmpty()
                            || expirationDateField.getText().isEmpty() || cvvField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all online banking details", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    paymentMethod = "Online Banking";
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a payment method", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                carRentedLabel.setText("Car Rented: " + carRented);
                rentalPeriodLabel.setText("Rental Period: " + rentalStartDate + " to " + rentalEndDate);
                paymentMethodLabelReceipt.setText("Payment Method: " + paymentMethod);
                rentalCostLabel.setText("Rental Cost: " + currentRentalCost + " Php");

                customerRentalCount++;
                totalCustomersLabel.setText("Total Customers Rented: " + customerRentalCount);

                cardLayout.show(frame.getContentPane(), "ReceiptPanel");
            });

            //Action Listener and Function for Receipt Panel
            cancelOrderButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Your order has been canceled.");
                cardLayout.show(frame.getContentPane(), "MainPanel");
            });


            confirmRentalButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Your rental has been confirmed. Thank you!");
                cardLayout.show(frame.getContentPane(), "MainPanel");
            });

            cancelRentalButton.addActionListener(e -> {
                int response = JOptionPane.showOptionDialog(frame,
                        "Your rental has been canceled. Would you like to make a new rental or exit?",
                        "Rental Canceled",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Make New Rental", "Exit"},
                        "Make New Rental");

                if (response == JOptionPane.YES_OPTION) {
                    cardLayout.show(frame.getContentPane(), "MainPanel");
                } else if (response == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Thank you for visiting Git Wheels! Goodbye!");
                    System.exit(0);
                }
            });

            frame.setVisible(true);
        });
    }

    private static void styleButton(JButton button, Color backgroundColor, Color textColor) {
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
    }

    private static void setLabelColor(Color color, JLabel... labels) {
        for (JLabel label : labels) {
            label.setForeground(color);
        }
    }

    private static void setTextFieldStyle(Color backgroundColor, JTextField... textFields) {
        for (JTextField textField : textFields) {
            textField.setBackground(backgroundColor);
            textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }
    }
}



