package com.schauweg.playertracker.playermarker;

import net.minecraft.entity.player.EntityPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PlayerMarker extends JPanel {

    private static final long serialVersionUID = 1L;

    private String xCoord;
    private String yCoord;
    private String zCoord;
    private String playerName;
    private int fontsize = 9;
    double relY;

    public PlayerMarker(EntityPlayer player, double relY) {
        playerName = player.getName();
        xCoord = round(player.posX);
        yCoord = round(player.posY);
        zCoord = round(player.posZ);
        this.relY = relY;

        setOpaque(false);
//		setBackground(Color.green);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{10, 2, 10, 0};
        gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel name = new JLabel(playerName);
        name.setFont(new Font("Tahoma", Font.PLAIN, 9));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_name = new GridBagConstraints();
        gbc_name.anchor = GridBagConstraints.NORTH;
        gbc_name.fill = GridBagConstraints.HORIZONTAL;
        gbc_name.insets = new Insets(0, 0, 5, 0);
        gbc_name.gridx = 0;
        gbc_name.gridy = 0;
        add(name, gbc_name);

        JLabel coords = new JLabel(xCoord+" "+yCoord+" "+zCoord);
        coords.setFont(new Font("Tahoma", Font.PLAIN, 8));
        coords.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_coords = new GridBagConstraints();
        gbc_coords.anchor = GridBagConstraints.NORTH;
        gbc_coords.fill = GridBagConstraints.HORIZONTAL;
        gbc_coords.gridx = 0;
        gbc_coords.gridy = 2;
        add(coords, gbc_coords);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = getPreferredSize();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int r = 4;
        int x = (d.width/2)-(r/2);
        int y = (d.height/2)-(r/2);

        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, r, r);

        if(relY > 0) {
            g2d.setColor(new Color(156, 59, 30));
        } else if(relY < 0) {
            g2d.setColor(new Color(30, 69, 156));
        } else {
            g2d.setColor(new Color(24, 135, 28));
        }

        g2d.fill(circle);
        g2d.draw(circle);
        repaint();
    }

    private String round(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String s =  df.format(d);
        if(!s.contains(",")) {
            return s+",00";
        } else {
            return s;
        }
    }


}
