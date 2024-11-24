package bank;

import bank.components.leftHomePage;
import bank.components.rightHomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Application {
    public void createLoginFrame() {
        // Create the frame
        JFrame frame = new JFrame("FEU Online Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Left panel
        JPanel leftPanel = new leftHomePage();
        frame.add(leftPanel);

        // Right panel
        JPanel rightPanel = new rightHomePage();
        frame.add(rightPanel);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setBounds(20, 60, 100, 25);
        usernameLabel.setForeground(Color.decode("#49454F"));
        rightPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(20, 90, 260, 30);
        usernameField.setBackground(new Color(255, 255, 255)); //
        usernameField.setOpaque(true);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true)); // Rounded border

        rightPanel.add(usernameField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(Color.decode("#49454F"));
        passwordLabel.setBounds(20, 130, 100, 25);


        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 160, 260, 30);
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setOpaque(true);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true)); // Rounded border

        rightPanel.add(passwordLabel);
        rightPanel.add(passwordField);

        // Load icons
        ImageIcon openEyeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/notvisible.png")));
        ImageIcon closedEyeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/visible-2.png")));

        // Eye button to toggle password visibility
        JButton toggleButton = new JButton(openEyeIcon);
        toggleButton.setBounds(280, 160, 30, 30);
        toggleButton.setBorderPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getEchoChar() != '\u0000') {
                    passwordField.setEchoChar('\u0000'); // Show password
                    toggleButton.setIcon(closedEyeIcon);
                } else {
                    passwordField.setEchoChar((Character) UIManager.get("PasswordField.echoChar")); // Hide password
                    toggleButton.setIcon(openEyeIcon);
                }
            }
        });
        rightPanel.add(toggleButton);

        // Login button
        JButton loginButton = new JButton("LOG IN");
        loginButton.setBounds(20, 210, 260, 40);
        loginButton.setBackground(new Color(30, 30, 30));
        loginButton.setOpaque(true);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 1, true)); // Rounded border
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(username, password)) {
                    // Login successful, open home page
                    frame.setVisible(false);  // Hide login frame
                    createHomePage();        // Show home page
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        rightPanel.add(loginButton);

        // Forgot password label
        JLabel forgotPasswordLabel = new JLabel("FORGOT PASSWORD");
        forgotPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        forgotPasswordLabel.setForeground(Color.decode("747070"));
        forgotPasswordLabel.setBounds(75, 260, 150, 20);
        rightPanel.add(forgotPasswordLabel);

        // Enroll now label
        JLabel enrollNowLabel = new JLabel("NO ACCOUNT? ENROLL NOW");
        enrollNowLabel.setFont(new Font("Arial", Font.BOLD, 12));
        enrollNowLabel.setForeground(Color.decode("747070"));
        enrollNowLabel.setBounds(55, 300, 200, 20);
        enrollNowLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                createRegisterPage();
            }
        });
        rightPanel.add(enrollNowLabel);

        frame.add(rightPanel);

        // Set frame visible
        frame.setVisible(true);
    }

    private boolean authenticateUser(String username, String password) {
        // Implement actual authentication logic here
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        // Dummy authentication (Always returns true for demonstration)
        return "user".equals(username) && "password".equals(password);
    }

    private void createHomePage() {
        JFrame homeFrame = new JFrame("FEU Home Page");
        homeFrame.setSize(600, 400);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to FEU Online");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(150, 150, 300, 40);
        homePanel.add(welcomeLabel);

        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.setBounds(250, 200, 100, 40);
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true)); // Rounded border
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> {
            // Log out and return to login page
            homeFrame.dispose();
            createLoginFrame();
        });
        homePanel.add(logoutButton);

        homeFrame.add(homePanel);
        homeFrame.setVisible(true);
    }

    private void createRegisterPage() {
        JFrame registerFrame = new JFrame("FEU Register Page");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(600, 400);
        registerFrame.setLayout(null);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);
        registerPanel.setBounds(0, 0, 600, 400);

        JLabel registerLabel = new JLabel("Register to FEU Online");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        registerLabel.setBounds(150, 20, 300, 40);
        registerPanel.add(registerLabel);

        // Panel for First Name, Middle Name, Last Name
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Horizontal layout with gaps
        namePanel.setBounds(20, 70, 560, 30); // Adjust the width as needed

        // First Name
        namePanel.add(createLabel("First Name", 0, 0)); // Positioning within the panel is handled by FlowLayout
        JTextField firstNameField = createTextField(0, 0);
        namePanel.add(firstNameField);

        // Middle Name
        namePanel.add(createLabel("Middle Name", 0, 0));
        JTextField middleNameField = createTextField(0, 0);
        namePanel.add(middleNameField);

        // Last Name
        namePanel.add(createLabel("Last Name", 0, 0));
        JTextField lastNameField = createTextField(0, 0);
        namePanel.add(lastNameField);

        registerPanel.add(namePanel);

        // Email
        registerPanel.add(createLabel("Email", 20, 120));
        JTextField emailField = createTextField(20, 150);
        registerPanel.add(emailField);

        // Password
        registerPanel.add(createLabel("Password", 20, 190));
        JPasswordField passwordField = createPasswordField(20, 220);
        registerPanel.add(passwordField);

        // Confirm Password
        registerPanel.add(createLabel("Confirm Password", 20, 260));
        JPasswordField confirmPasswordField = createPasswordField(20, 290);
        registerPanel.add(confirmPasswordField);

        // Register button
        JButton registerButton = new JButton("REGISTER");
        registerButton.setBounds(20, 330, 150, 40);
        registerButton.setBackground(Color.RED);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setOpaque(true);
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String middleName = middleNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(registerFrame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // dummy registration logic
                System.out.println("Registered First Name: " + firstName);
                System.out.println("Registered Middle Name: " + middleName);
                System.out.println("Registered Last Name: " + lastName);
                System.out.println("Registered Email: " + email);
                System.out.println("Registered Password: " + password);
                JOptionPane.showMessageDialog(registerFrame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Show success frame
                JFrame successFrame = new JFrame("Registration Successful");
                successFrame.setSize(400, 200);
                successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                successFrame.setLayout(null);

                JPanel successPanel = new JPanel();
                successPanel.setLayout(null);
                successPanel.setBackground(Color.decode("#C4FFA9"));

                JLabel successLabel = new JLabel("Registration Successful!");
                successLabel.setFont(new Font("Arial", Font.BOLD, 24));
                successLabel.setBounds(50, 50, 300, 40);
                successLabel.setForeground(Color.BLACK);
                successPanel.add(successLabel);

                JButton loginButton = new JButton("GO BACK TO LOGIN");
                loginButton.setBounds(100, 100, 200, 40);
                loginButton.setBackground(Color.BLACK);
                loginButton.setForeground(Color.WHITE);
                loginButton.setFont(new Font("Arial", Font.BOLD, 14));
                loginButton.setOpaque(true);
                loginButton.setContentAreaFilled(false);
                loginButton.setBorderPainted(false);
                loginButton.addActionListener(e2 -> {
                    successFrame.setVisible(false);
                    registerFrame.dispose(); // Close the registration frame
                    createLoginFrame(); // Go back to log in screen
                });
                successPanel.add(loginButton);

                successFrame.add(successPanel);
                successFrame.setVisible(true);
            }
        });
        registerPanel.add(registerButton);

        registerFrame.add(registerPanel);
        registerFrame.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBounds(x, y, 200, 25);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 30));
        textField.setBackground(new Color(240, 240, 240));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        return textField;
    }

    private JPasswordField createPasswordField(int x, int y) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, 250, 30);
        passwordField.setBackground(new Color(240, 240, 240));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        return passwordField;
    }
}
