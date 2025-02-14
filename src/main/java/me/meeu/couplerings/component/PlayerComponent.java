package me.meeu.couplerings.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record PlayerComponent(String uuid) {
    public static final Codec<PlayerComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("uuid").forGetter(
                    PlayerComponent::uuid))
            .apply(instance, PlayerComponent::new));

    public static final StreamCodec<ByteBuf, PlayerComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            PlayerComponent::uuid,
            PlayerComponent::new);
}
