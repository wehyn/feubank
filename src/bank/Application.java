package bank;

import bank.components.leftHomePage;
import bank.components.rightHomePage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.LoggingPermission;
import javax.swing.*;

public class Application {

    // Stores the users
    private Authentication authentication;

    private CardLayout cardLayout;
    private JPanel mainContentCards;

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
                    try {
                        createHomePage();        // Show home page
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        rightPanel.add(loginButton);

        // Forgot password label
        /*JLabel forgotPasswordLabel = new JLabel("FORGOT PASSWORD");
        forgotPasswordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        forgotPasswordLabel.setForeground(Color.decode("747070"));
        forgotPasswordLabel.setBounds(75, 260, 150, 20);
        rightPanel.add(forgotPasswordLabel);*/

        // Enroll now label
        JLabel enrollNowLabel = new JLabel("NO ACCOUNT? ENROLL NOW");
        enrollNowLabel.setFont(new Font("Arial", Font.BOLD, 12));
        enrollNowLabel.setForeground(Color.decode("747070"));
        enrollNowLabel.setBounds(55, 300, 200, 20);
        enrollNowLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    createRegisterPage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        rightPanel.add(enrollNowLabel);

        frame.add(rightPanel);

        // Set frame visible
        frame.setVisible(true);
    }

    public void createHomePage() throws IOException, FontFormatException {
        JFrame frame = new JFrame("Banking Dashboard");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create CardLayout for main content
        cardLayout = new CardLayout();
        mainContentCards = new JPanel(cardLayout);
        mainContentCards.setBackground(Color.WHITE);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.decode("#1B5045"));
        sidebar.setPreferredSize(new Dimension(180, frame.getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        Font prostoFont = Font.createFont(Font.TRUETYPE_FONT,
                Objects.requireNonNull(getClass().getResourceAsStream("/resources/fonts/ProstoOne-Regular.ttf")));
        prostoFont = prostoFont.deriveFont(Font.PLAIN, 20); // Derive the font with desired style and size

        // Logo and title
        JLabel logoLabel = new JLabel("FEU", SwingConstants.LEFT);
        logoLabel.setForeground(Color.decode("#F4E27C"));
        logoLabel.setFont(prostoFont);
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JLabel onlineLabel = new JLabel("Online", SwingConstants.LEFT);
        onlineLabel.setForeground(Color.WHITE);
        onlineLabel.setFont(new Font("Arial", Font.BOLD, 18));
        onlineLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        onlineLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(logoLabel);
        sidebar.add(onlineLabel);

        // Create navigation items with panels
        String[] navItems = {"Home", "Load", "Transfer", "Loan"};
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
        userProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Switch to the profile panel
                cardLayout.show(mainContentCards, "profile");
            }
        });
        sidebar.add(userProfile);

        JPanel profilePanel = createProfileContent(frame);
        mainContentCards.add(profilePanel, "profile");

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
            case "transfer" -> createTransferPage();
            case "loan" -> createloanPage();
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
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Card Panel (Account Summary)
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components

        // Savings Account Card
        JPanel savingsCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundImage = ImageIO.read(getClass().getResourceAsStream("/resources/0601res-feu-clip.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        savingsCard.setLayout(new BorderLayout());
        savingsCard.setPreferredSize(new Dimension(350, 200));

        JLabel savingsLabel = new JLabel("Savings Account", SwingConstants.LEFT);
        savingsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        savingsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        savingsCard.add(savingsLabel, BorderLayout.NORTH);

        //Blackpanel inside the savingscard
        JPanel blackPanel = new JPanel (new BorderLayout());
        blackPanel.setPreferredSize(new Dimension(350, 50));
        blackPanel.setBackground(Color.BLACK);
        savingsCard.add(blackPanel, BorderLayout.SOUTH);

        JLabel balanceLabel = new JLabel("P " + user.balance, SwingConstants.LEFT);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        blackPanel.add(balanceLabel, BorderLayout.WEST);

        String maskedAccountNumber = "•••• " + user.accountNumber.substring(user.accountNumber.length() - 4);
        JLabel accountNumber = new JLabel(maskedAccountNumber, SwingConstants.RIGHT);
        accountNumber.setFont(new Font("Arial", Font.BOLD, 20));
        accountNumber.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        accountNumber.setForeground(Color.WHITE);
        blackPanel.add(accountNumber,BorderLayout.EAST);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2; // Spans two rows
        cardPanel.add(savingsCard, gbc);

        JPanel creditBalance = new JPanel(new BorderLayout());
        creditBalance.setPreferredSize(new Dimension(150, 200));
        creditBalance.setBackground(new Color(245,245,245));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;

        JLabel balanceText = new JLabel("Credit Balance");
        balanceText.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLabel.setForeground(Color.BLACK);
        creditBalance.add(balanceText,BorderLayout.WEST);

        JLabel balanceAmount = new JLabel("P" + user.getBalance());
        balanceAmount.setFont(new Font("Arial", Font.BOLD, 14));
        creditBalance.add(balanceAmount, BorderLayout.WEST);
        cardPanel.add(creditBalance, gbc);

        JPanel creditLimit = new JPanel(new BorderLayout());
        creditLimit.setPreferredSize(new Dimension(200, 90));
        creditLimit.setBackground(Color.GRAY);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1; // Single row
        cardPanel.add(creditLimit, gbc);

        JPanel creditDebt = new JPanel(new BorderLayout());
        creditDebt.setPreferredSize(new Dimension(200, 90));
        creditDebt.setBackground(Color.GRAY);
        gbc.gridx = 2;
        gbc.gridy = 1;
        cardPanel.add(creditDebt, gbc);

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

    private JPanel createTransferPage(){

        BankAccountClass.UserAccount user = authentication.getLoggedInAccount();

        JPanel transferPanel = new JPanel(null);
        transferPanel.setBackground(Color.WHITE);
        transferPanel.setPreferredSize(new Dimension(800, 700));

        // Left Panel (White)
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 0, 400, 700);

        JLabel titleLabel = new JLabel("Transfer Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(40, 40, 200, 30);
        leftPanel.add(titleLabel);

        JLabel chooseAccLabel = new JLabel("Choose which account to transfer:");
        chooseAccLabel.setFont(new Font("Arial", Font.BOLD, 14));
        chooseAccLabel.setBounds(40, 90, 300, 25);
        leftPanel.add(chooseAccLabel);

        String[] methods = {"FEU Account Transfer", "Other Bank Account Transfer"};
        JComboBox<String> methodComboBox = new JComboBox<>(methods);
        methodComboBox.setBounds(40, 120, 300, 35);
        leftPanel.add(methodComboBox);

        JLabel accountNameLabel = new JLabel("Account Name:");
        accountNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        accountNameLabel.setBounds(40, 170, 200, 25);
        leftPanel.add(accountNameLabel);

        JTextField accountNameField = new JTextField();
        accountNameField.setBackground(new Color(255, 255, 255));
        accountNameField.setOpaque(true);
        accountNameField.setBounds(40, 200, 300, 35);
        accountNameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        leftPanel.add(accountNameField);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setFont(new Font("Arial", Font.BOLD, 14));
        accountNumberLabel.setBounds(40, 250, 150, 25);
        leftPanel.add(accountNumberLabel);

        JTextField accountNumberField = new JTextField();
        accountNumberField.setBackground(new Color(255, 255, 255));
        accountNumberField.setOpaque(true);
        accountNumberField.setBounds(40, 280, 300, 35);
        accountNumberField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        leftPanel.add(accountNumberField);

        // Right Panel (Yellow)
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(new Color(244, 226, 124));
        rightPanel.setBounds(400, 0, 400, 700);

        JLabel availAmountLabel = new JLabel("Available Amount");
        availAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        availAmountLabel.setBounds(40, 40, 200, 30);
        rightPanel.add(availAmountLabel);

        JLabel balanceLabel = new JLabel("P " + user.balance);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 48));
        balanceLabel.setBounds(40, 80, 200, 48);
        rightPanel.add(balanceLabel);

        JLabel enterAmountLabel = new JLabel("Enter Amount:");
        enterAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        enterAmountLabel.setBounds(40, 170, 150, 25);
        rightPanel.add(enterAmountLabel);

        JTextField amountField = new JTextField();
        amountField.setBackground(new Color(255, 255, 255));
        amountField.setOpaque(true);
        amountField.setBounds(40, 200, 300, 35);
        amountField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        rightPanel.add(amountField);

        JLabel feeLabel = new JLabel("Fee:");
        feeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        feeLabel.setBounds(40, 250, 150, 25);
        rightPanel.add(feeLabel);

        JLabel feeAmountLabel = new JLabel("P 0.0");
        feeAmountLabel.setFont(new Font("Arial", Font.BOLD, 24));
        feeAmountLabel.setBounds(40, 270, 150, 25);
        rightPanel.add(feeAmountLabel);

        JLabel feeNoteLabel = new JLabel("Note: No transfer fee for FEU Bank Account");
        feeNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        feeNoteLabel.setBounds(40, 300, 400, 25);
        rightPanel.add(feeNoteLabel);

        JButton ProceedButton = new JButton("PROCEED");
        ProceedButton.setBounds(40, 330, 300, 40);
        ProceedButton.setBackground(new Color(30, 30, 30));
        ProceedButton.setForeground(Color.WHITE);
        ProceedButton.setFont(new Font("Arial", Font.BOLD, 14));
        ProceedButton.setOpaque(true);
        ProceedButton.setBorderPainted(false);
        ProceedButton.addActionListener(e -> {
            String accName = accountNameField.getText();
            String accNum = accountNumberField.getText();
            String amount = amountField.getText();
            JOptionPane.showMessageDialog(null,
                    "Transferred P " + amount + " to " + accName + ", " + accNum,
                    "SUCCESS",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        rightPanel.add(ProceedButton);

        transferPanel.add(leftPanel);
        transferPanel.add(rightPanel);

        return transferPanel;
    }

    private JPanel createloanPage() {
        BankAccountClass.UserAccount user = authentication.getLoggedInAccount();
    
        JPanel loanPanel = new JPanel(null);
        loanPanel.setBackground(Color.WHITE);
        loanPanel.setPreferredSize(new Dimension(800, 700));
    
        // Left Panel (White)
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 0, 400, 700);
    
        // Title Label
        JLabel titleLabel = new JLabel("Loan Request");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(40, 40, 200, 30);
        leftPanel.add(titleLabel);
    
        // Loan Amount Label
        JLabel loanLabel = new JLabel("Enter Loan Amount (₱):");
        loanLabel.setFont(new Font("Arial", Font.BOLD, 14));
        loanLabel.setBounds(40, 100, 200, 25);
        leftPanel.add(loanLabel);
    
        // Loan Amount Text Field
        JTextField loanAmountField = new JTextField(15);
        loanAmountField.setBounds(40, 130, 300, 35);
        loanAmountField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        leftPanel.add(loanAmountField);
    
        // Right Panel (Yellow)
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(new Color(244, 226, 124));
        rightPanel.setBounds(400, 0, 400, 700);
    
        // Submit Button
        JButton submitButton = new JButton("Submit Loan Request");
        submitButton.setBounds(40, 350, 300, 40);
        submitButton.setBackground(new Color(30, 30, 30));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loanAmount = loanAmountField.getText();
                
                // Validate input
                try {
                    double amount = Double.parseDouble(loanAmount);
                    if ( amount < 1000 || amount > 100000) {
                        JOptionPane.showMessageDialog(loanPanel, 
                            "Loan amount must be between ₱1,000 and ₱100,000.",
                            "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Create PIN Dialog
                    JDialog pinDialog = new JDialog((Frame) null, "Enter PIN", true);
                    pinDialog.setLayout(new BorderLayout());
                    pinDialog.setBackground(Color.WHITE);
                    pinDialog.setSize(300, 150);
                    pinDialog.setLocationRelativeTo(null);
    
                    JLabel pinLabel = new JLabel("Enter your PIN to confirm loan request:");
                    pinLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    pinLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    pinDialog.add(pinLabel, BorderLayout.NORTH);
    
                    JPasswordField pinField = new JPasswordField();
                    pinField.setFont(new Font("Arial", Font.BOLD, 14));
                    pinField.setDocument(new LimitedDocument(4));
                    pinDialog.add(pinField, BorderLayout.CENTER);
    
                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    JButton confirmButton = new JButton("Confirm");
                    JButton cancelButton = new JButton("Cancel");
    
                    buttonPanel.add(cancelButton);
                    buttonPanel.add(confirmButton);
                    pinDialog.add(buttonPanel, BorderLayout.SOUTH);
    
                    confirmButton.addActionListener(pinEvent -> {
                        String pin = new String(pinField.getPassword());
                        if (pin.isEmpty()) {
                            JOptionPane.showMessageDialog(pinDialog, "PIN cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Create loan confirmation frame
                            JOptionPane.showMessageDialog(pinDialog, 
                                "Your loan request for $" + amount + " has been submitted.\n" +
                                "You will receive an email notification about the status.", 
                                "Loan Request Submitted", 
                                JOptionPane.INFORMATION_MESSAGE);
                            pinDialog.dispose();
                        }
                    });
    
                    cancelButton.addActionListener(pinEvent -> pinDialog.dispose());
    
                    pinDialog.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(loanPanel, 
                        "Please enter a valid number.", 
                        "Invalid Input", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        rightPanel.add(submitButton);
    
        // Additional loan information
        JLabel infoLabel = new JLabel("<html>Loan Request Guidelines:<br>" +
            "• Minimum loan amount: ₱1,000<br>" +
            "• Maximum loan amount: ₱100,000<br>" +
            "• Interest rates vary based on amount and credit score<br>" +
            "• Approval subject to credit check</html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setBounds(40, 200, 300, 150);
        rightPanel.add(infoLabel);
    
        // Add panels to main panel
        loanPanel.add(leftPanel);
        loanPanel.add(rightPanel);
    
        return loanPanel;
    }

    private JPanel createProfileContent(JFrame currentFrame){
        BankAccountClass.UserAccount user = authentication.getLoggedInAccount();

        JPanel profilePanel = new JPanel(null);
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setPreferredSize(new Dimension(800, 700));
        profilePanel.setOpaque(false);

        JPanel topPanel = new JPanel(null);
        topPanel.setBackground(new Color(244, 226, 124));
        topPanel.setBounds(0, 0, 800, 100);

        // Profile Panel Components
        JLabel titleLabel = new JLabel("Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(40, 40, 200, 30);
        topPanel.add(titleLabel);

        JPanel lowerPanel = new JPanel(null);
        lowerPanel.setBackground(Color.WHITE);
        lowerPanel.setBounds(0, 100, 800,600);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBounds(40, 80, 200, 25);

        JLabel userLabel = new JLabel("Username: ");
        userLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userLabel.setBounds(40, 120, 200, 25);

        JLabel birthdayLabel = new JLabel("Birthday: ");
        birthdayLabel.setFont(new Font("Arial", Font.BOLD, 20));
        birthdayLabel.setBounds(40, 160, 200, 25);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addressLabel.setBounds(40, 200, 200, 25);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setBounds(40, 240, 200, 25);
        lowerPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(user.password);
        passwordField.setBounds(150, 240, 200, 25);
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setOpaque(true);
        lowerPanel.add(passwordField);

        ImageIcon openEyeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/notvisible.png")));
        ImageIcon closedEyeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/visible-2.png")));
    
        // Eye button to toggle password visibility
        JButton toggleButton = new JButton(closedEyeIcon);
        toggleButton.setBounds(360, 240, 30, 30);
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

        JLabel AccountNumber = new JLabel("Account Number: ");
        AccountNumber.setFont(new Font("Arial", Font.BOLD, 20));
        AccountNumber.setBounds(40, 280, 200, 25);

        JLabel pinLabel = new JLabel("PIN: ");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pinLabel.setBounds(40, 320, 200, 25);

        JPasswordField pinField = new JPasswordField(user.pin);
        pinField.setBounds(150, 320, 200, 25);
        pinField.setBackground(new Color(255, 255, 255));
        pinField.setOpaque(true);
        lowerPanel.add(pinField);
    
        // Eye button to toggle password visibility
        JButton toggleButton2 = new JButton(closedEyeIcon);
        toggleButton2.setBounds(360, 320, 30, 30);
        toggleButton2.setBorderPainted(false);
        toggleButton2.setContentAreaFilled(false);
        toggleButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pinField.getEchoChar() != '\u0000') {
                    pinField.setEchoChar('\u0000'); // Show password
                    toggleButton2.setIcon(closedEyeIcon);
                } else {
                    pinField.setEchoChar((Character) UIManager.get("PasswordField.echoChar")); // Hide password
                    toggleButton2.setIcon(openEyeIcon);
                }
            }
        });

        JButton loginButton = new JButton("LOG OUT");
        loginButton.setBounds(100, 400, 260, 40);
        loginButton.setBackground(new Color(30, 30, 30));
        loginButton.setOpaque(true);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 1, true)); // Rounded border
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    currentFrame.dispose();
                    createLoginFrame();
            }
        });
        lowerPanel.add(loginButton);
        
        lowerPanel.add(toggleButton);
        lowerPanel.add(nameLabel);
        lowerPanel.add(userLabel);
        lowerPanel.add(birthdayLabel);
        lowerPanel.add(addressLabel);
        lowerPanel.add(passwordLabel);
        lowerPanel.add(AccountNumber);
        lowerPanel.add(pinLabel);
        lowerPanel.add(toggleButton2);


        profilePanel.add(topPanel);
        profilePanel.add(lowerPanel);

        return profilePanel;
    }

    private JPanel createLoadContent() {

        BankAccountClass.UserAccount user = authentication.getLoggedInAccount();

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

        JPanel balancePanel = new JPanel();
        balancePanel.setBackground(new Color(235,235,235));
        balancePanel.setBounds(40, 240, 300, 50);
        leftPanel.add(balancePanel);

        JLabel amountAvail = new JLabel("Amount Available:\n");
        amountAvail.setFont(new Font("Arial", Font.BOLD, 14));
        amountAvail.setBounds(40, 240, 150, 25);
        balancePanel.add(amountAvail);

        JLabel balance = new JLabel("P" + user.balance);
        balance.setFont(new Font("Arial", Font.BOLD, 18));
        balance.setBounds(40, 270, 150, 25);
        balancePanel.add(balance);

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

        JLabel feeLabel = new JLabel("Transaction Fee: ");
        feeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        feeLabel.setBounds(40, 210, 150, 25);
        rightPanel.add(feeLabel);

        JLabel feeText = new JLabel("P");
        feeText.setFont(new Font("Arial", Font.BOLD, 14));
        feeText.setBounds(40, 240, 150, 25);
        rightPanel.add(feeText);

        JLabel feeNote = new JLabel("<html>Note: Transaction fee varies from <br> service provider</html>\"");
        feeNote.setFont(new Font("Arial", Font.ITALIC, 14));
        feeNote.setBounds(40, 280, 300, 50);
        rightPanel.add(feeNote);

        JButton loadButton = new JButton("LOAD MONEY");
        loadButton.setBounds(40, 350, 300, 40);
        loadButton.setBackground(new Color(30, 30, 30));
        loadButton.setForeground(Color.WHITE);
        loadButton.setFont(new Font("Arial", Font.BOLD, 14));
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);
        loadButton.addActionListener(e -> {
            // Create the dialog
            JDialog pinDialog = new JDialog((Frame) null, "Enter PIN", true);
            pinDialog.setLayout(new BorderLayout());
            pinDialog.setBackground(Color.BLACK);
            pinDialog.setSize(300, 150);
            pinDialog.setLocationRelativeTo(null); // Center on screen

            // Create a label for instructions
            JLabel pinLabel = new JLabel("Enter your PIN:");
            pinLabel.setFont(new Font("Arial", Font.BOLD, 14));
            pinLabel.setForeground(Color.BLACK);
            pinLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            pinDialog.add(pinLabel, BorderLayout.NORTH);

            // Create a PIN input field
            JPasswordField pinField = new JPasswordField();
            pinField.setFont(new Font("Arial", Font.BOLD, 14));
            pinField.setDocument(new LimitedDocument(4));
            pinDialog.add(pinField, BorderLayout.CENTER);

            // Create a button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton confirmButton = new JButton("Confirm");
            JButton cancelButton = new JButton("Cancel");

            buttonPanel.add(cancelButton);
            buttonPanel.add(confirmButton);
            pinDialog.add(buttonPanel, BorderLayout.SOUTH);

            // Add functionality
            confirmButton.addActionListener(event -> {

                String number = numberField.getText();
                String amount = amountField.getText();
                String serviceProvider = (String) serviceProviderComboBox.getSelectedItem();

                String pin = new String(pinField.getPassword());
                if (pin.isEmpty()) {
                    JOptionPane.showMessageDialog(pinDialog, "PIN cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pinDialog, "PIN entered: " + pin, "Success", JOptionPane.INFORMATION_MESSAGE);
                    pinDialog.dispose();

                    user.buyLoad(number, Double.parseDouble(amount), serviceProvider);
                    balance.setText("P" + user.balance);
                }
            });

            cancelButton.addActionListener(event -> pinDialog.dispose()); // Close dialog

            // Show the dialog
            pinDialog.setVisible(true);
        });
        rightPanel.add(loadButton);

        // Add panels to main panel
        loadPanel.add(leftPanel);
        loadPanel.add(rightPanel);

        return loadPanel;
    }

    private void createRegisterPage() throws IOException, FontFormatException {
        JFrame registerFrame = new JFrame("FEU Register Page");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(700, 600);
        registerFrame.setBackground(Color.decode("#1B5045"));
        registerFrame.setLayout(null); // Use null layout for manual positioning

        JPanel registerPanel = new JPanel(null);
        registerPanel.setBackground(Color.WHITE);
        registerPanel.setBounds(0, 0, 800, 700);

        // Left Panel (White background)
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0, 0, 550, 600); // 600px width for the left side to accommodate all fields

        // Left panel components
        JLabel registerLabel = new JLabel("Register to FEU Online");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        registerLabel.setBounds(40, 40, 300, 30); // Position the label
        leftPanel.add(registerLabel);

        // First Name Field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(40, 100, 100, 25);
        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(150, 100, 300, 30);
        leftPanel.add(firstNameLabel);
        leftPanel.add(firstNameField);

        // Middle Name Field
        JLabel middleNameLabel = new JLabel("Middle Name:");
        middleNameLabel.setBounds(40, 140, 100, 25);
        JTextField middleNameField = new JTextField();
        middleNameField.setBounds(150, 140, 300, 30);
        leftPanel.add(middleNameLabel);
        leftPanel.add(middleNameField);

        // Last Name Field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(40, 180, 100, 25);
        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 180, 300, 30);
        leftPanel.add(lastNameLabel);
        leftPanel.add(lastNameField);

        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(40, 220, 100, 25);
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 220, 300, 30);
        leftPanel.add(emailLabel);
        leftPanel.add(emailField);

        // Address Field
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(40, 260, 100, 25);
        JTextField addressField = new JTextField();
        addressField.setBounds(150, 260, 300, 30);
        leftPanel.add(addressLabel);
        leftPanel.add(addressField);

        // Birthday Section (Day, Month, Year)
        JLabel birthdayLabel = new JLabel("Birthday:");
        birthdayLabel.setBounds(40, 300, 100, 25);
        leftPanel.add(birthdayLabel);

        // Day Dropdown
        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setBounds(150, 300, 50, 25);
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        JComboBox<String> dayComboBox = new JComboBox<>(days);
        dayComboBox.setBounds(200, 300, 50, 30);

        // Month Dropdown
        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setBounds(260, 300, 50, 25);
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        JComboBox<String> monthComboBox = new JComboBox<>(months);
        monthComboBox.setBounds(310, 300, 70, 30);

        // Year Dropdown (Range from 1900 to current year)
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(390, 300, 50, 25);
        int currentYear = LocalDate.now().getYear();
        String[] years = new String[currentYear - 1899];
        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        JComboBox<String> yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(440, 300, 70, 30);

        leftPanel.add(dayLabel);
        leftPanel.add(dayComboBox);
        leftPanel.add(monthLabel);
        leftPanel.add(monthComboBox);
        leftPanel.add(yearLabel);
        leftPanel.add(yearComboBox);

        // Password Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 340, 100, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 340, 300, 30);
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordField);

        // Confirm Password Field
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(40, 380, 150, 25);
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 380, 300, 30);
        leftPanel.add(confirmPasswordLabel);
        leftPanel.add(confirmPasswordField);

        // PIN Field
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setBounds(40, 420, 100, 25);
        JPasswordField pinField = new JPasswordField();
        pinField.setBounds(150, 420, 300, 30);
        pinField.setDocument(new LimitedDocument(4)); // Limiting to 4 digits for PIN
        leftPanel.add(pinLabel);
        leftPanel.add(pinField);

        // Register Button
        JButton registerButton = new JButton("REGISTER");
        registerButton.setBounds(100, 480, 300, 40);
        registerButton.setBackground(new Color(30, 30, 30));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(e -> {
            // Add action listener code here
            String firstName = firstNameField.getText();
            String middleName = middleNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String birthday = dayComboBox.getSelectedItem() + " " + monthComboBox.getSelectedItem() + " " + yearComboBox.getSelectedItem();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String pin = new String(pinField.getPassword());

            BankAccountClass.UserAccount newUser = new BankAccountClass.UserAccount(firstName, middleName, lastName, email, birthday, address, 0, password, pin);
            authentication.addUser(newUser);
            registerFrame.dispose();

        });
        leftPanel.add(registerButton);

        // Right Panel (Green background, adjusted to 200px width)
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.decode("#1B5045")); // Yellow background or green
        rightPanel.setBounds(300, 0, 400, 700); // Adjusted right panel width to 200px

        Font prostoFont = Font.createFont(Font.TRUETYPE_FONT,
                Objects.requireNonNull(getClass().getResourceAsStream("/resources/fonts/ProstoOne-Regular.ttf")));
        prostoFont = prostoFont.deriveFont(Font.PLAIN, 32); // Derive the font with desired style and size

        JLabel titleLabel = new JLabel("FEU");
        titleLabel.setForeground(new Color(244, 226, 124));
        titleLabel.setFont(prostoFont); // Set the Prosto One font
        titleLabel.setBounds(280, 450, 100, 60);
        rightPanel.add(titleLabel);

        // "Online" text
        JLabel onlineLabel = new JLabel("Online");
        onlineLabel.setForeground(Color.WHITE);
        onlineLabel.setFont(prostoFont.deriveFont(Font.PLAIN, 16)); // Apply Prosto One font (smaller size)
        onlineLabel.setBounds(280, 490, 100, 30);
        rightPanel.add(onlineLabel);
        // Add the left panel and right panel to the main panel
        registerPanel.add(leftPanel);
        registerPanel.add(rightPanel);

        // Add the main panel to the frame
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
