package nameless.classicraft.tier;

import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum DaggerTiers implements Tier {
    WOOD(0, 59, 4.0F, 0.0F, 15,
            () -> Ingredient.of(ItemTags.PLANKS)),
    STONE(1, 131, 4.0F, 1.0F, 5,
            () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)),
    IRON(2, 250, 4.0F, 2.0F, 14,
            () -> Ingredient.of(Items.IRON_INGOT)),
    GOLD(0, 32, 4.0F, 0.0F, 22,
            () -> Ingredient.of(Items.GOLD_INGOT)),
    DIAMOND(3, 1561, 4.0F, 3.0F, 10,
            () -> Ingredient.of(Items.DIAMOND)),
    NETHERITE(4, 2031, 4.0F, 4.0F, 15,
            () -> Ingredient.of(Items.NETHERITE_INGOT));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    DaggerTiers(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
        this.level = pLevel;
        this.uses = pUses;
        this.speed = pSpeed;
        this.damage = pDamage;
        this.enchantmentValue = pEnchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}

