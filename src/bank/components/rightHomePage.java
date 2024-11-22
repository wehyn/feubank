package bank.components;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class rightHomePage extends JPanel {
    public rightHomePage(){
        setBounds(250, 0, 350, 400);
        setLayout(null);

        JPanel rightPanel = new JPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fill the background with white
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the image with transparency
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/0601res-feu-clip.jpg"))).getImage();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
