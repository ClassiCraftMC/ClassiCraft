package org.bedracket.classicraft.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.bedracket.classicraft.ClassiCraftMod;
import org.quiltmc.qsl.block.content.registry.api.BlockContentRegistries;
import org.quiltmc.qsl.block.content.registry.api.FlammableBlockEntry;
import org.quiltmc.qsl.lifecycle.api.event.ServerWorldTickEvents;
import org.quiltmc.qsl.registry.attachment.api.RegistryEntryAttachment;

import java.util.Optional;

public class ModFlammableContentsQuilt {

    public static void registerFlammableContents() {
        ServerWorldTickEvents.START.register((server, world) -> {
            assertValues(ModBlocks.CHARCOAL_BLOCK.get(),
                    BlockContentRegistries.FLAMMABLE,
                    new FlammableBlockEntry(5, 60));
        });

    }

    private static <T> void assertValues(Block block, RegistryEntryAttachment<Block, T> attachment, T value) {
        Optional<T> entry = attachment.get(block);
        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
        if (entry.isEmpty()) {
            throw new AssertionError("No entry present for " + id);
        }

        if (!entry.get().equals(value)) {
            throw new AssertionError("Value incorrect for " + id);
        }

        ClassiCraftMod.LOGGER.info("Registry flammability " + id + " passed for ClassiCraft for " + attachment.id());
    }
}
