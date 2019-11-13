package com.schauweg.testmod.proxy;

import com.schauweg.testmod.ClientEventHandler;
import com.schauweg.testmod.TestDialog;

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