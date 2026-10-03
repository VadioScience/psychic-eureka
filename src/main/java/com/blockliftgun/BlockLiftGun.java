package com.blockliftgun;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blockliftgun.item.ModItems;

@Mod("blockliftgun")
public class BlockLiftGun {
    public static final String MOD_ID = "blockliftgun";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public BlockLiftGun(ModContainer container, IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        
        ModItems.ITEMS.register(modEventBus);
        
        LOGGER.info("Block Lift Gun мод загружен!");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup завершена");
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Client setup завершена");
    }
}
