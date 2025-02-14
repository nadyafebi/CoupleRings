package me.meeu.couplerings.init;

import me.meeu.couplerings.CoupleRings;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, CoupleRings.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB = REGISTRY
            .register("couplerings_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.couplerings"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.COUPLE_RING.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.COUPLE_RING.get());
                    }).build());
}
