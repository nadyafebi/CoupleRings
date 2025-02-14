package me.meeu.couplerings;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CoupleRings.MOD_ID)
public class CoupleRings {
    public static final String MOD_ID = "couplerings";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CoupleRings(IEventBus modEventBus, ModContainer modContainer) {
    }
}
