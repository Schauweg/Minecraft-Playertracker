package com.schauweg.playertracker;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerListObject {
	private EntityPlayer player;
	private boolean checked;
	
	public EntityPlayer getPlayer() {
		return player;
	}
	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
	
}
