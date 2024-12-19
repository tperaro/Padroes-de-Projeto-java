package GUI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
    }

    private void initComponents() {

        imageAvatar1 = new ImageAvatar();

        setBackground(new java.awt.Color(157, 153, 255));

        imageAvatar1.setBorderSize(1);
        imageAvatar1.setBorderSpace(0);
        imageAvatar1.setImage(createUserIcon(42, new Color(255, 90, 90)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(844, Short.MAX_VALUE)
                                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private ImageIcon createUserIcon(int size, Color color) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        // Enable anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw circle for head
        g2.setColor(color);
        int headSize = size / 3;
        int headY = size / 4;
        g2.fillOval(size/2 - headSize/2, headY, headSize, headSize);

        // Draw body
        int bodyY = headY + headSize;
        int bodyWidth = size / 2;
        int bodyHeight = size / 3;
        g2.fillArc(size/2 - bodyWidth/2, bodyY, bodyWidth, bodyHeight, 0, 180);

        g2.dispose();
        return new ImageIcon(image);
    }

    private ImageAvatar imageAvatar1;
}
