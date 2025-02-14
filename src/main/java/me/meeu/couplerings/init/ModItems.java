package me.meeu.couplerings.init;

import me.meeu.couplerings.CoupleRings;
import me.meeu.couplerings.item.CoupleRing;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM,
            CoupleRings.MOD_ID);

    public static final DeferredHolder<Item, CoupleRing> COUPLE_RING = REGISTRY.register("couple_ring",
            CoupleRing::new);
}
