package me.meeu.couplerings.item;

import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import me.meeu.couplerings.component.PlayerComponent;
import me.meeu.couplerings.init.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CoupleRing extends Item {
    public CoupleRing() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@NotNull Level level,
            @NotNull Player player,
            @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (!level.isClientSide) {
            if (!stack.has(ModDataComponents.PLAYER_ONE)) {
                stack.set(ModDataComponents.PLAYER_ONE, new PlayerComponent(player.getStringUUID()));
                return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
            } else if (!stack.has(ModDataComponents.PLAYER_TWO)) {
                String player1Uuid = stack.get(ModDataComponents.PLAYER_ONE).uuid();
                if (!player.getStringUUID().equals(player1Uuid)) {
                    stack.set(ModDataComponents.PLAYER_TWO, new PlayerComponent(player.getStringUUID()));
                    return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
                }
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
            @NotNull List<Component> tooltipComponents,
            @NotNull TooltipFlag tooltipFlag) {
        String tooltip = "Not Set";

        Level level = context.level();
        if (level == null)
            return;

        if (stack.has(ModDataComponents.PLAYER_ONE)) {
            String playerUuid = stack.get(ModDataComponents.PLAYER_ONE).uuid();
            Player player = level.getPlayerByUUID(UUID.fromString(playerUuid));
            String playerName = player != null ? player.getName().getString() : playerUuid;
            tooltip = playerName;
        }

        if (stack.has(ModDataComponents.PLAYER_TWO)) {
            String playerUuid = stack.get(ModDataComponents.PLAYER_ONE).uuid();
            Player player = level.getPlayerByUUID(UUID.fromString(playerUuid));
            String playerName = player != null ? player.getName().getString() : playerUuid;
            tooltip += " & " + playerName;
        }

        tooltipComponents.add(Component.literal(tooltip).withStyle(ChatFormatting.ITALIC, ChatFormatting.LIGHT_PURPLE));
    }
}
