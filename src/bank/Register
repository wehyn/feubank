import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class RegistrationForm {
    // Define the directory where files will be saved
    private static final String SAVE_DIRECTORY = "registrations";
    
    public static void main(String[] args) {
        // Create the save directory if it doesn't exist
        new File(SAVE_DIRECTORY).mkdirs();

        // Frame
        JFrame frame = new JFrame("Registration Form");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(15, 2, 10, 10));

        // Labels and Input Fields
        JLabel lblAccountNumber = new JLabel("Account Number: (Auto Generated)");
        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtFirstName = new JTextField();
        JLabel lblMiddleName = new JLabel("Middle Name:");
        lblMiddleName.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtMiddleName = new JTextField();
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtLastName = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtEmail = new JTextField();
        JLabel lblBirthday = new JLabel("Birthday (DD-MM-YYYY):");
        lblBirthday.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtBirthday = new JTextField();
        JLabel lblCity = new JLabel("City:");
        lblCity.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtCity = new JTextField();
        JLabel lblProvince = new JLabel("Province:");
        lblProvince.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtProvince = new JTextField();
        JLabel lblTown = new JLabel("Town:");
        lblTown.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtTown = new JTextField();
        JLabel lblPostalCode = new JLabel("Postal Code:");
        lblPostalCode.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtPostalCode = new JTextField();
        JLabel lblAddressLine = new JLabel("Address Line:");
        lblAddressLine.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtAddressLine = new JTextField();
        JLabel lblPassword = new JLabel("Create Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
        JPasswordField txtPassword = new JPasswordField();
        JLabel lblConfirmPassword = new JLabel("Re-enter Password:");
        lblConfirmPassword.setFont(new Font("Arial", Font.BOLD, 15));
        JPasswordField txtConfirmPassword = new JPasswordField();
        JLabel lblPin = new JLabel("Create 4-digit PIN:");
        lblPin.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField txtPin = new JTextField();

        // Register Button
        JButton btnRegister = new JButton("REGISTER");

        // Add Components to Frame
        frame.add(lblAccountNumber);
        frame.add(new JLabel()); // Empty space
        frame.add(lblFirstName);
        frame.add(txtFirstName);
        frame.add(lblMiddleName);
        frame.add(txtMiddleName);
        frame.add(lblLastName);
        frame.add(txtLastName);
        frame.add(lblEmail);
        frame.add(txtEmail);
        frame.add(lblBirthday);
        frame.add(txtBirthday);
        frame.add(lblCity);
        frame.add(txtCity);
        frame.add(lblProvince);
        frame.add(txtProvince);
        frame.add(lblTown);
        frame.add(txtTown);
        frame.add(lblPostalCode);
        frame.add(txtPostalCode);
        frame.add(lblAddressLine);
        frame.add(txtAddressLine);
        frame.add(lblPassword);
        frame.add(txtPassword);
        frame.add(lblConfirmPassword);
        frame.add(txtConfirmPassword);
        frame.add(lblPin);
        frame.add(txtPin);
        frame.add(new JLabel()); // Empty space
        frame.add(btnRegister);

        // Button Action
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = txtFirstName.getText().trim();
                String middleName = txtMiddleName.getText().trim();
                String lastName = txtLastName.getText().trim();
                String email = txtEmail.getText().trim();
                String birthday = txtBirthday.getText().trim();
                String city = txtCity.getText().trim();
                String province = txtProvince.getText().trim();
                String town = txtTown.getText().trim();
                String postalCode = txtPostalCode.getText().trim();
                String addressLine = txtAddressLine.getText().trim();
                String password = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConfirmPassword.getPassword());
                String pin = txtPin.getText().trim();

                // Validation
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || 
                    password.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, 
                        "All mandatory fields must be filled!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, 
                        "Passwords do not match!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (pin.length() != 4 || !pin.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, 
                        "PIN must be a 4-digit number!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Generate account number
                    int accountSequence = getNextAccountNumber();
                    String accountNumber = String.format("2401 5796 %04d", accountSequence);
                    String fileName = SAVE_DIRECTORY + File.separator + "acc" + accountSequence + ".csv";

                    // Create the file
                    File file = new File(fileName);
                    boolean isNewFile = file.createNewFile();
                    
                    // Write user data to the file
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.append("Account Number,First Name,Middle Name,Last Name,Email,Birthday,City,Province,Town,Postal Code,Address Line,Password,PIN\n");
                        writer.append(accountNumber).append(",");
                        writer.append(firstName).append(",");
                        writer.append(middleName).append(",");
                        writer.append(lastName).append(",");
                        writer.append(email).append(",");
                        writer.append(birthday).append(",");
                        writer.append(city).append(",");
                        writer.append(province).append(",");
                        writer.append(town).append(",");
                        writer.append(postalCode).append(",");
                        writer.append(addressLine).append(",");
                        writer.append(password).append(",");
                        writer.append(pin).append("\n");
                        
                        writer.flush();
                    }

                    JOptionPane.showMessageDialog(frame, 
                        "Registration successful!\nAccount Number: " + accountNumber + "\nFile saved as " + fileName, 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);

                    // Clear Fields
                    txtFirstName.setText("");
                    txtMiddleName.setText("");
                    txtLastName.setText("");
                    txtEmail.setText("");
                    txtBirthday.setText("");
                    txtCity.setText("");
                    txtProvince.setText("");
                    txtTown.setText("");
                    txtPostalCode.setText("");
                    txtAddressLine.setText("");
                    txtPassword.setText("");
                    txtConfirmPassword.setText("");
                    txtPin.setText("");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Error saving data: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Show Frame
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    // Helper method to generate the next account number
    private static int getNextAccountNumber() {
        File dir = new File(SAVE_DIRECTORY);
        if (!dir.exists()) {
            return 1;
        }
        
        int maxNumber = 0;
        File[] files = dir.listFiles((d, name) -> name.startsWith("acc") && name.endsWith(".csv"));
        
        if (files != null) {
            for (File file : files) {
                try {
                    String numStr = file.getName().substring(3, file.getName().length() - 4);
                    int num = Integer.parseInt(numStr);
                    maxNumber = Math.max(maxNumber, num);
                } catch (NumberFormatException | StringIndexOutOfBoundsException ignored) {
                    // Skip files that don't match the expected format
                }
            }
        }
        
        return maxNumber + 1;
    }
}
