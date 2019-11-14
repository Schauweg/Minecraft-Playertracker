package com.schauweg.playertracker.playermarker;

import net.minecraft.entity.player.EntityPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BasePanel extends JPanel {

    private int size;
    private float yaw;

    public BasePanel(EntityPlayer player, int size) {
        this.size = size;
        yaw = (player.rotationYaw)% 360;
        yaw = -yaw;

        setSize(size, size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int d = 4;
        int lineLength = 5;
        int x = size/2;
        int y = size/2;
        double angle = yaw * Math.PI / 180;
        int endX = (int) (x + lineLength * Math.sin(angle));
        int endY = (int) (y + lineLength * Math.cos(angle));

        Ellipse2D.Double circle = new Ellipse2D.Double(x-(d/2), y-(d/2), d, d);

        g2d.setColor(Color.orange);
        g2d.fill(circle);
        g2d.draw(circle);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x, y, endX, endY);
        repaint();
    }
}
