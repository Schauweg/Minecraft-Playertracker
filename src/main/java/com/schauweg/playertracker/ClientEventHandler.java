package com.schauweg.playertracker;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {
	
	TrackerWindow trackerWindow;
	EntityPlayer clientPlayer;
	List<PlayerListObject> players;
	
	@SubscribeEvent
	public void entityTick(LivingUpdateEvent event){
		clientPlayer = Minecraft.getMinecraft().thePlayer;
		if (event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entity;
			if(player.worldObj.isRemote && !player.equals(clientPlayer)) {
				boolean insert = true;
				for(PlayerListObject p : players) {
					EntityPlayer ep = p.getPlayer();
					if(player.getUniqueID().equals(ep.getUniqueID())) {
						p.setPlayer(player);
						p.setChecked(true);
						insert = false;
						break;
					}
				}
				if(insert) {
					PlayerListObject plo = new PlayerListObject();
					plo.setPlayer(player);
					plo.setChecked(true);
					players.add(plo);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
	    if (event.phase == TickEvent.Phase.END && players != null && trackerWindow != null && clientPlayer != null) {
	    	for(int i = 0; i < players.size(); i++) {
				PlayerListObject plo = players.get(i);
				if(!plo.isChecked()) {
					players.remove(i);
				} else {
					plo.setChecked(false);
				}
			}
			trackerWindow.updatePlayer(players, clientPlayer);
	    }
	}
	
	@SubscribeEvent
	public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		players = new ArrayList<PlayerListObject>();
		trackerWindow = new TrackerWindow();
		trackerWindow.setVisible(true);
	}

	@SubscribeEvent
	public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
		trackerWindow.dispose();
		players = null;
		clientPlayer = null;
	}
}
