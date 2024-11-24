package bank.components;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class rightHomePage extends JPanel {
    private double zoomFactor = 0.45;
    public rightHomePage(){
        setBounds(250, 0, 350, 400);
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fill the background with white (optional)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Load the background image
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/0601res-feu-clip.jpg"))).getImage();

        // Set transparency (if needed)
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

        // Zoom the background image
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        // Calculate new size based on the zoom factor
        int zoomedWidth = (int) (imageWidth * zoomFactor);
        int zoomedHeight = (int) (imageHeight * zoomFactor);

        // Calculate the position so the image stays centered
        int x = ((getWidth() - zoomedWidth) / 2) - 200;
        int y = (getHeight() - zoomedHeight) / 2 - 75;

        // Draw the zoomed image at the calculated position
        g2d.drawImage(image, x, y, zoomedWidth, zoomedHeight, this);

        // Here, you can add additional code to draw other components like buttons, text, etc.
    }
}
