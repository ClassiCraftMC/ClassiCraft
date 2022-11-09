package nameless.classicraft.item;

import nameless.classicraft.ClassiCraftMod;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RanchuBucketItem extends MobBucketItem {

    public static final List<Runnable> CALLBACKS = new ArrayList<>();

    public RanchuBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Item item,
                            boolean hasTooltip, Item.Properties builder) {
        super(entityType, fluid, () -> SoundEvents.BUCKET_EMPTY_FISH, builder);
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> CALLBACKS.add(() ->
                ItemProperties.register(this, new ResourceLocation(ClassiCraftMod.MODID, "variant"),
                        (stack, world, player, i) -> stack.hasTag() ? stack.getTag().getInt("Variant") : 0)));
    }

}

