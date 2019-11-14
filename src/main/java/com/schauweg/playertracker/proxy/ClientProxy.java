package com.schauweg.playertracker.proxy;

import com.schauweg.playertracker.ClientEventHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent e) {
    	super.postInit(e);
    	
    	//TestDialog td = new TestDialog();
    }
}
