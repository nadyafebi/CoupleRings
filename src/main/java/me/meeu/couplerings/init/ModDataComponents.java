package me.meeu.couplerings.init;

import me.meeu.couplerings.CoupleRings;
import me.meeu.couplerings.component.PlayerComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModDataComponents {
    public static final DeferredRegister.DataComponents REGISTRY = DeferredRegister
            .createDataComponents(Registries.DATA_COMPONENT_TYPE, CoupleRings.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PlayerComponent>> PLAYER_ONE = REGISTRY
            .registerComponentType(
                    "player_one",
                    builder -> builder
                            .persistent(PlayerComponent.CODEC)
                            .networkSynchronized(PlayerComponent.STREAM_CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PlayerComponent>> PLAYER_TWO = REGISTRY
            .registerComponentType(
                    "player_two",
                    builder -> builder
                            .persistent(PlayerComponent.CODEC)
                            .networkSynchronized(PlayerComponent.STREAM_CODEC));
}
