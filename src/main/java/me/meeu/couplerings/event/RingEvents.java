package me.meeu.couplerings.event;

import me.meeu.couplerings.CoupleRings;
import me.meeu.couplerings.item.CoupleRing;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

@EventBusSubscriber(modid = CoupleRings.MOD_ID)
public class RingEvents {
    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Post event) {
        ItemStack stack = event.getOriginalStack();
        if (stack.getItem() instanceof CoupleRing) {
            CoupleRing ring = (CoupleRing) stack.getItem();
            ring.onPickup(event.getPlayer(), stack);
        }
    }
}
