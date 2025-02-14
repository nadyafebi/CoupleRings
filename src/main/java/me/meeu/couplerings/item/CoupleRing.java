package me.meeu.couplerings.item;

import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        if (level.isClientSide)
            return super.use(level, player, usedHand);

        if (!stack.has(ModDataComponents.PLAYER_ONE)) {
            stack.set(ModDataComponents.PLAYER_ONE, new PlayerComponent(player.getStringUUID()));
            return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
        }

        if (!stack.has(ModDataComponents.PLAYER_TWO)) {
            String player1Uuid = stack.get(ModDataComponents.PLAYER_ONE).uuid();
            if (!player.getStringUUID().equals(player1Uuid)) {
                String player2Uuid = player.getStringUUID();
                stack.set(ModDataComponents.PLAYER_TWO, new PlayerComponent(player2Uuid));

                Player player1 = getPlayer(level, player1Uuid);
                Player player2 = getPlayer(level, player2Uuid);

                player1.sendSystemMessage(
                        Component.literal(String.format("Congrats! %s said yes!", getPlayerName(player2)))
                                .withStyle(ChatFormatting.LIGHT_PURPLE));
                player2.sendSystemMessage(
                        Component.literal(String.format("Congrats! Be happy with %s!", getPlayerName(player1)))
                                .withStyle(ChatFormatting.LIGHT_PURPLE));

                return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
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
        if (level == null || !level.isClientSide)
            return;

        if (stack.has(ModDataComponents.PLAYER_ONE)) {
            String playerUuid = stack.get(ModDataComponents.PLAYER_ONE).uuid();
            tooltip = getPlayerName(level, playerUuid);
        }

        if (stack.has(ModDataComponents.PLAYER_TWO)) {
            String playerUuid = stack.get(ModDataComponents.PLAYER_ONE).uuid();
            tooltip += " & " + getPlayerName(level, playerUuid);
        }

        tooltipComponents.add(Component.literal(tooltip).withStyle(ChatFormatting.ITALIC, ChatFormatting.LIGHT_PURPLE));
    }

    public void onPickup(Player player, ItemStack stack) {
        Level level = player.level();
        if (level.isClientSide)
            return;

        if (!stack.has(ModDataComponents.PLAYER_ONE) || stack.has(ModDataComponents.PLAYER_TWO))
            return;

        String player1Uuid = stack.get(ModDataComponents.PLAYER_ONE).uuid();
        String player2Uuid = player.getStringUUID();

        if (player1Uuid.equals(player2Uuid))
            return;

        String player1Name = getPlayerName(level, player1Uuid);
        String player2Name = player.getName().getString();

        player.sendSystemMessage(
                Component.literal(String.format("%s: %s, will you marry me?", player1Name, player2Name))
                        .withStyle(ChatFormatting.LIGHT_PURPLE));
        player.sendSystemMessage(Component.literal("(Right click to accept.)").withStyle(ChatFormatting.ITALIC,
                ChatFormatting.DARK_GRAY));
    }

    private @Nullable Player getPlayer(Level level, String uuid) {
        return level.getPlayerByUUID(UUID.fromString(uuid));
    }

    private String getPlayerName(Level level, String uuid) {
        Player player = getPlayer(level, uuid);
        return player != null ? player.getName().getString() : uuid;
    }

    private String getPlayerName(Player player) {
        return player.getName().getString();
    }
}
