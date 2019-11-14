package com.schauweg.playertracker;

import java.util.List;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.schauweg.playertracker.playermarker.BasePanel;
import com.schauweg.playertracker.playermarker.PlayerMarker;
import net.minecraft.entity.player.EntityPlayer;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class TrackerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private int size = 400;

	
	public TrackerWindow() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setAutoRequestFocus(false);
		setSize(size, size);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void updatePlayer(List<PlayerListObject> players, EntityPlayer clientPlayer) {
		BasePanel panel = new BasePanel(clientPlayer, size);
		getContentPane().removeAll();
		
		for(int i = 0; i < players.size(); i++) {
			EntityPlayer player = players.get(i).getPlayer();
			PlayerMarker playerPanel = new PlayerMarker(player, getRealtiveCoords(clientPlayer.posY, player.posY));

			double x = getRealtiveCoords(clientPlayer.chasingPosX, player.posX);
			double z = getRealtiveCoords(clientPlayer.chasingPosZ, player.posZ);

			x += (panel.getWidth()/2)-(playerPanel.getWidth()/2)-(playerPanel.getPreferredSize().width/2);
			z += (panel.getHeight()/2)-(playerPanel.getHeight()/2)-(playerPanel.getPreferredSize().height/2);

			playerPanel.setSize(playerPanel.getPreferredSize());
			playerPanel.setLocation((int)x, (int)z);
			panel.add(playerPanel);
		}
		getContentPane().add(panel);
		getContentPane().revalidate();
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
	
	private double getRealtiveCoords(double clientPlayer, double player) {
		return player - clientPlayer;
	}
	
}
