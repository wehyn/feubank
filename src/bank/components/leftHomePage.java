package bank.components;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class leftHomePage extends JPanel {
    public leftHomePage() {
        setBounds(0, 0, 250, 400);
        setLayout(null);

        // Load the Prosto One font
        try {
            Font prostoFont = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(getClass().getResourceAsStream("/resources/fonts/ProstoOne-Regular.ttf")));
            prostoFont = prostoFont.deriveFont(Font.PLAIN, 72); // Derive the font with desired style and size

            // FEU text
            JLabel titleLabel = new JLabel("FEU");
            titleLabel.setForeground(new Color(244, 226, 124));
            titleLabel.setFont(prostoFont); // Set the Prosto One font
            titleLabel.setBounds(30, 100, 200, 60);
            add(titleLabel);

            // "Online" text
            JLabel onlineLabel = new JLabel("Online");
            onlineLabel.setForeground(Color.WHITE);
            onlineLabel.setFont(prostoFont.deriveFont(Font.PLAIN, 24)); // Apply Prosto One font (smaller size)
            onlineLabel.setBounds(30, 160, 100, 30);
            add(onlineLabel);

            // "No Waiting, Just Earning" text
            JLabel sloganLabel = new JLabel("<html><div style='text-align: center;'>No Waiting, Just Earning</div></html>");
            sloganLabel.setForeground(Color.WHITE);
            sloganLabel.setFont(prostoFont.deriveFont(Font.ITALIC, 16)); // Apply Prosto One font (italic)
            sloganLabel.setBounds(30, 190, 200, 50);
            add(sloganLabel);

        } catch (Exception e) {
            System.out.println("test");
            e.printStackTrace();
            // Fallback to default font if loading fails
            JLabel titleLabel = new JLabel("FEU");
            titleLabel.setForeground(new Color(244, 226, 124));
            titleLabel.setFont(new Font("Arial", Font.BOLD, 72));
            titleLabel.setBounds(30, 100, 200, 60);
            add(titleLabel);

            // "Online" text
            JLabel onlineLabel = new JLabel("Online");
            onlineLabel.setForeground(Color.WHITE);
            onlineLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            onlineLabel.setBounds(30, 160, 100, 30);
            add(onlineLabel);

            // "No Waiting, Just Earning" text
            JLabel sloganLabel = new JLabel("<html><div style='text-align: center;'>No Waiting, Just Earning</div></html>");
            sloganLabel.setForeground(Color.WHITE);
            sloganLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            sloganLabel.setBounds(30, 190, 200, 50);
            add(sloganLabel);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        Color color1 = new Color(27, 80, 69); // Dark green
        Color color2 = new Color(62, 182, 122); // Light green
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
