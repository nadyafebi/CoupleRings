package me.meeu.couplerings;

import me.meeu.couplerings.init.ModCreativeModeTabs;
import me.meeu.couplerings.init.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(CoupleRings.MOD_ID)
public class CoupleRings {
    public static final String MOD_ID = "couplerings";

    public CoupleRings(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.REGISTRY.register(modEventBus);
        ModCreativeModeTabs.REGISTRY.register(modEventBus);
    }
}
