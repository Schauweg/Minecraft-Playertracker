package com.schauweg.testmod;

import java.util.List;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.minecraft.entity.player.EntityPlayer;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class TestDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JCheckBox chckbxRelativeCoords;
	private GridBagConstraints gbc_chckbxRelativeCoords;
	
	public TestDialog() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setAutoRequestFocus(false);
		setSize(335, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		
		chckbxRelativeCoords = new JCheckBox("relative Coords");
		chckbxRelativeCoords.setSelected(true);
		gbc_chckbxRelativeCoords = new GridBagConstraints();
		gbc_chckbxRelativeCoords.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxRelativeCoords.anchor = GridBagConstraints.EAST;
		gbc_chckbxRelativeCoords.gridwidth = 3;
		gbc_chckbxRelativeCoords.gridx = 1;
		gbc_chckbxRelativeCoords.gridy = 0;
		setVisible(true);
	}
	
	public void updatePlayer(List<PlayerListObject> players, EntityPlayer clientPlayer) {
		JPanel panel = new JPanel();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 70, 70, 70};
		gridBagLayout.rowHeights = new int[]{0, 0};
		panel.setLayout(gridBagLayout);
		
		JLabel lblCount = new JLabel("Player Count: "+players.size());
		GridBagConstraints gbc_lblCount = new GridBagConstraints();
		gbc_lblCount.insets = new Insets(0, 0, 0, 5);
		gbc_lblCount.anchor = GridBagConstraints.WEST;
		gbc_lblCount.gridx = 0;
		gbc_lblCount.gridy = 0;
		panel.add(lblCount, gbc_lblCount);

		panel.add(chckbxRelativeCoords, gbc_chckbxRelativeCoords);
		
		for(int i = 0; i < players.size(); i++) {
			EntityPlayer player = players.get(i).getPlayer();
			int index = i+1;
			
			JLabel name = new JLabel(player.getName());
//			JLabel name = new JLabel("WWWWWWWWWWWWWWWW"); //Limit Testing
			GridBagConstraints gbc_Name = new GridBagConstraints();
			gbc_Name.insets = new Insets(0, 0, 0, 5);
			gbc_Name.anchor = GridBagConstraints.WEST;
			gbc_Name.gridx = 0;
			gbc_Name.gridy = index;
			panel.add(name, gbc_Name);
			
			String x;
			if(chckbxRelativeCoords.isSelected()) {
				x = round(getRealtiveCoords(clientPlayer.posX, player.posX));
			} else {
				x = round(player.posX);
			}
			
			JLabel lblX = new JLabel("X: " + x);
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 0, 5);
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.gridx = 1;
			gbc_lblX.gridy = index;
			panel.add(lblX, gbc_lblX);
			
			String y;
			if(chckbxRelativeCoords.isSelected()) {
				y = round(getRealtiveCoords(clientPlayer.posY, player.posY));
			} else {
				y = round(player.posY);
			}
			
			JLabel lblY = new JLabel("Y: " + y);
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 0, 5);
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.gridx = 2;
			gbc_lblY.gridy = index;
			panel.add(lblY, gbc_lblY);
			
			String z;
			if(chckbxRelativeCoords.isSelected()) {
				z = round(getRealtiveCoords(clientPlayer.posZ, player.posZ));
			} else {
				z = round(player.posZ);
			}
			
			JLabel lblZ = new JLabel("Z: " + z);
			GridBagConstraints gbc_lblZ = new GridBagConstraints();
			gbc_lblZ.insets = new Insets(0, 0, 0, 5);
			gbc_lblZ.anchor = GridBagConstraints.EAST;
			gbc_lblZ.gridx = 3;
			gbc_lblZ.gridy = index;
			panel.add(lblZ, gbc_lblZ);			
		}
		scrollPane.setViewportView(panel);
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
