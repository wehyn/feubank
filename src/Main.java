import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("FEU Online Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setResizable(false);

        // Left panel (green background)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0, 128, 64)); // Green color
        leftPanel.setBounds(0, 0, 250, 400);
        leftPanel.setLayout(null);

        // FEU text
        JLabel titleLabel = new JLabel("FEU");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(90, 100, 100, 40);
        leftPanel.add(titleLabel);

        // "Online" text
        JLabel onlineLabel = new JLabel("Online");
        onlineLabel.setForeground(Color.WHITE);
        onlineLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        onlineLabel.setBounds(90, 140, 100, 30);
        leftPanel.add(onlineLabel);

        // "No Waiting, Just Earning" text
        JLabel sloganLabel = new JLabel("No Waiting, Just Earning");
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        sloganLabel.setBounds(40, 180, 200, 30);
        leftPanel.add(sloganLabel);

        frame.add(leftPanel);

        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBounds(250, 0, 350, 400);
        rightPanel.setLayout(null);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(50, 100, 100, 25);
        rightPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 130, 250, 30);
        usernameField.setBackground(Color.WHITE);
        rightPanel.add(usernameField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(50, 170, 100, 25);
        rightPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 200, 250, 30);
        rightPanel.add(passwordField);

        ImageIcon openEyeIcon = new ImageIcon(Main.class.getResource("/resources/notvisible.png"));
        ImageIcon closedEyeIcon = new ImageIcon(Main.class.getResource("/resources/visible-2.png"));

        // Eye button to toggle password visibility
        JButton toggleButton = new JButton(openEyeIcon); // You can use an image icon here
        toggleButton.setBounds(310, 200, 30, 30);
        toggleButton.setBorderPainted(false);
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
        loginButton.setBounds(50, 250, 250, 40);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(username, password)){
                    System.out.println("Successfully Logged In");
                }

            }
        });
        rightPanel.add(loginButton);

        // Forgot password label
        JLabel forgotPasswordLabel = new JLabel("FORGOT PASSWORD");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPasswordLabel.setForeground(Color.GRAY);
        forgotPasswordLabel.setBounds(50, 300, 150, 20);
        rightPanel.add(forgotPasswordLabel);

        // Enroll now label
        JLabel enrollNowLabel = new JLabel("NO ACCOUNT? ENROLL NOW");
        enrollNowLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        enrollNowLabel.setForeground(Color.GRAY);
        enrollNowLabel.setBounds(50, 320, 200, 20);
        rightPanel.add(enrollNowLabel);

        frame.add(rightPanel);

        // Set frame visible
        frame.setVisible(true);
    }

    private static boolean authenticateUser(String username, String password) {
        // Authenticate User in the
        System.out.println(username);
        System.out.println(password);

        return false;


    }

    private static boolean transferMoney(String accountNumber, double amount){
        return false;
    }

    private static void getBalance(){
        return;
    }

}
