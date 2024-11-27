package bank;

import bank.components.leftHomePage;
import bank.components.rightHomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.LoggingPermission;
import javax.swing.*;

public class Application {

    // Stores the users
    private Authentication authentication;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Application() {
        authentication = new Authentication();
    }

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

                if (authentication.authenticate(username, password)) {
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

    public void createHomePage() {
            JFrame frame = new JFrame("Banking Dashboard");
            frame.setSize(1000, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
        
            // Create CardLayout for main content
            CardLayout cardLayout = new CardLayout();
            JPanel mainContentCards = new JPanel(cardLayout);
            mainContentCards.setBackground(Color.WHITE);
        
            // Sidebar
            JPanel sidebar = new JPanel();
            sidebar.setBackground(Color.decode("#1B5045"));
            sidebar.setPreferredSize(new Dimension(180, frame.getHeight()));
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        
            // Logo and title
            JLabel logoLabel = new JLabel("FEU", SwingConstants.LEFT);
            logoLabel.setForeground(Color.decode("#F4E27C"));
            logoLabel.setFont(new Font("Arial", Font.BOLD, 18));
            logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
            JLabel onlineLabel = new JLabel("Online", SwingConstants.LEFT);
            onlineLabel.setForeground(Color.WHITE);
            onlineLabel.setFont(new Font("Arial", Font.BOLD, 18));
            onlineLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            onlineLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        
            sidebar.add(Box.createVerticalStrut(20)); 
            sidebar.add(logoLabel);
            sidebar.add(onlineLabel);
        
            // Create navigation items with panels
            String[] navItems = {"Home", "Load", "Transfer", "Loan", "Bills", "Inbox"};
            JPanel[] navPanels = new JPanel[navItems.length];
            
            for (int i = 0; i < navItems.length; i++) {
                navPanels[i] = new JPanel(new BorderLayout());
                navPanels[i].setOpaque(false);
                
                // Create label
                JLabel navLabel = new JLabel(navItems[i], SwingConstants.LEFT);
                navLabel.setForeground(Color.WHITE);
                navLabel.setFont(new Font("Arial", Font.BOLD, 14));
                navLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); //

                navPanels[i].setMinimumSize(new Dimension(180, 3000));
                navPanels[i].setMaximumSize(new Dimension(180, 3000));

                final int index = i;
                navPanels[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        // Reset all backgrounds
                        for (JPanel panel : navPanels) {
                            panel.setBackground(new Color(27, 80, 69));
                            panel.setOpaque(false);
                        }
                        // Highlight selected
                        navPanels[index].setBackground(new Color(62, 182, 122));
                        navPanels[index].setOpaque(true);
                        // Switch content
                        cardLayout.show(mainContentCards, navItems[index].toLowerCase());
                    }
                });

                navPanels[i].add(navLabel, BorderLayout.CENTER);
                sidebar.add(navPanels[i]);
                
                // Create and add content panel
                JPanel contentPanel = createContentPanel(navItems[i].toLowerCase());
                mainContentCards.add(contentPanel, navItems[i].toLowerCase());
            }
            // Highlight the first item
            navPanels[0].setBackground(new Color(62, 182, 122));
            navPanels[0].setOpaque(true);
            cardLayout.show(mainContentCards, "home");
        
            // Add glue to push remaining items to bottom
            sidebar.add(Box.createVerticalGlue());
        
            // Add user info at bottom
            String name = capitalizeFirstLetter(authentication.getLoggedInAccount().firstName) + " " + 
                         capitalizeFirstLetter(authentication.getLoggedInAccount().lastName);
            JLabel userProfile = new JLabel(name, SwingConstants.LEFT);
            userProfile.setForeground(Color.WHITE);
            userProfile.setFont(new Font("Arial", Font.BOLD, 14));
            userProfile.setAlignmentX(Component.LEFT_ALIGNMENT);
            userProfile.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            sidebar.add(userProfile);

            // Add components to frame
            frame.add(sidebar, BorderLayout.WEST);
            frame.add(mainContentCards, BorderLayout.CENTER);
            
            frame.setVisible(true);
        }


    // Add the cards panel to the frame
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    private JPanel createContentPanel(String type) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        return switch (type) {
            case "home" -> createHomeContent();
            case "load" -> createLoadContent();
            //case "transfer" -> createTransferPage();
            //case "bills" -> createBillsPage();
            default -> panel;
        };
    }

    private JPanel createHomeContent(){

        BankAccountClass.UserAccount user = authentication.getLoggedInAccount();

        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(Color.WHITE);
        
        // Top Panel for Welcome and Cards
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
    
        // Welcome User
        String name = capitalizeFirstLetter(user.firstName) + " " +
                     capitalizeFirstLetter(user.lastName);
        JLabel welcomeLabel = new JLabel("Welcome, " + name, SwingConstants.LEFT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topPanel.add(welcomeLabel, BorderLayout.NORTH);
    
        // Card Panel (Account Summary)
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        cardPanel.setBackground(Color.WHITE);
    
        // Savings Account Card
        JPanel savingsCard = new JPanel(new BorderLayout());
        savingsCard.setPreferredSize(new Dimension(250, 150));
        savingsCard.setBackground(Color.decode("#FBE470"));
        savingsCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    
        JLabel savingsLabel = new JLabel("Savings Account", SwingConstants.LEFT);
        savingsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        savingsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        savingsCard.add(savingsLabel, BorderLayout.NORTH);
    
        JLabel balanceLabel = new JLabel("P " + user.balance, SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        savingsCard.add(balanceLabel, BorderLayout.CENTER);
    
        cardPanel.add(savingsCard);
        topPanel.add(cardPanel, BorderLayout.CENTER);
    
        // Add top panel to home panel
        homePanel.add(topPanel, BorderLayout.NORTH);
    
        // Transactions Panel
        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
        transactionsPanel.setBackground(Color.decode("#F5F5F5"));
        transactionsPanel.setBorder(BorderFactory.createTitledBorder("Transactions"));

        ArrayList<BankAccountClass.Transaction> userTransactions = user.getTransactions();

        for (BankAccountClass.Transaction transaction : userTransactions) {
            JLabel transactionLabel = new JLabel(transaction.details + ": "+ transaction.amount);
            transactionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            transactionLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
            transactionsPanel.add(transactionLabel);
        }
    
        homePanel.add(transactionsPanel, BorderLayout.CENTER);
    
        return homePanel;
    }

    //private JPanel createTransferPage(){
    //}

    //private JPanel createBillsPage(){
    //}


    private JPanel createLoadContent() {
        JPanel loadPanel = new JPanel(null);
        loadPanel.setBackground(Color.WHITE);
        loadPanel.setPreferredSize(new Dimension(800, 700));
    
        // Left Panel (White)
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 0, 400, 700);
    
        // Left panel components
        JLabel titleLabel = new JLabel("Buy Load");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(40, 40, 200, 30);
        leftPanel.add(titleLabel);
    
        JLabel serviceProviderLabel = new JLabel("Select Service Provider:");
        serviceProviderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        serviceProviderLabel.setBounds(40, 90, 200, 25);
        leftPanel.add(serviceProviderLabel);
    
        String[] serviceProviders = {"Globe", "Smart", "TNT", "TM", "DITO"};
        JComboBox<String> serviceProviderComboBox = new JComboBox<>(serviceProviders);
        serviceProviderComboBox.setBounds(40, 120, 300, 35);
        leftPanel.add(serviceProviderComboBox);
    
        JLabel methodLabel = new JLabel("Payment Method:");
        methodLabel.setFont(new Font("Arial", Font.BOLD, 14));
        methodLabel.setBounds(40, 170, 150, 25);
        leftPanel.add(methodLabel);
    
        String[] methods = {"Current", "Savings", "Credit"};
        JComboBox<String> methodComboBox = new JComboBox<>(methods);
        methodComboBox.setBounds(40, 200, 300, 35);
        leftPanel.add(methodComboBox);
    
        // Right Panel (Yellow)
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(new Color(244, 226, 124));
        rightPanel.setBounds(400, 0, 400, 700);
    
        JLabel phoneLabel = new JLabel("Buy Load for");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        phoneLabel.setBounds(40, 40, 200, 30);
        rightPanel.add(phoneLabel);
    
        JTextField numberField = new JTextField();
        numberField.setBounds(40, 80, 300, 35);
        numberField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        rightPanel.add(numberField);
    
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        amountLabel.setBounds(40, 130, 150, 25);
        rightPanel.add(amountLabel);
    
        JTextField amountField = new JTextField();
        amountField.setBounds(40, 160, 300, 35);
        amountField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        rightPanel.add(amountField);
    
        JButton loadButton = new JButton("LOAD MONEY");
        loadButton.setBounds(40, 220, 300, 40);
        loadButton.setBackground(new Color(30, 30, 30));
        loadButton.setForeground(Color.WHITE);
        loadButton.setFont(new Font("Arial", Font.BOLD, 14));
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);
        loadButton.addActionListener(e -> {
            String amount = amountField.getText();
            String method = (String) methodComboBox.getSelectedItem();
            JOptionPane.showMessageDialog(null, 
                "Loading " + amount + " via " + method, 
                "Buy Load", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        rightPanel.add(loadButton);
    
        // Add panels to main panel
        loadPanel.add(leftPanel);
        loadPanel.add(rightPanel);
    
        return loadPanel;
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