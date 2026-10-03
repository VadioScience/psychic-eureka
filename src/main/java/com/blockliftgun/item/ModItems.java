package com.blockliftgun.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryObject;
import net.minecraft.core.registries.Registries;
import com.blockliftgun.BlockLiftGun;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, BlockLiftGun.MOD_ID);

    public static final RegistryObject<Item> BLOCK_LIFT_GUN = ITEMS.register("block_lift_gun",
            () -> new BlockLiftGunItem(new Item.Properties().stacksTo(1)));
}
