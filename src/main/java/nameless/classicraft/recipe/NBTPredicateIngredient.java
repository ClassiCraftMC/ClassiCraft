package nameless.classicraft.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nameless.classicraft.ClassiCraftMod;
import nameless.classicraft.util.Helpers;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public final class NBTPredicateIngredient extends Ingredient {

    @SubscribeEvent
    public static void registerRecipeSerializers(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, (recipe) -> {
            CraftingHelper.register(Helpers.identifier("nbt_includes"),
                    NBTPredicateIngredient.Serializer.INSTANCE);

        });
        ClassiCraftMod.LOGGER.info("Successfully added NBT Predicate Ingredient Deserializer");
    }

    private final ItemStack stack;
    private final NbtPredicate predicate;

    public NBTPredicateIngredient(ItemStack stack) {
        super(Stream.of(new Ingredient.ItemValue(stack)));

        this.stack = stack;

        CompoundTag shareTag = stack.getShareTag();
        this.predicate = shareTag == null ? NbtPredicate.ANY : new NbtPredicate(shareTag);
    }

    @Override
    public boolean test(@Nullable ItemStack stack) {
        if (stack == null)
            return false;

        return this.stack.getItem() == stack.getItem() && this.stack.getDamageValue() == stack.getDamageValue() && this.predicate.matches(stack);
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("type", CraftingHelper.getID(NBTPredicateIngredient.Serializer.INSTANCE).toString());
        json.addProperty("item", this.stack.getItem().getDescriptionId().toString());
        json.addProperty("count", this.stack.getCount());

        CompoundTag shareTag = this.stack.getShareTag();
        if (shareTag != null) json.addProperty("nbt", shareTag.toString());

        return json;
    }

    public static final class Serializer implements IIngredientSerializer<NBTPredicateIngredient> {
        public static final NBTPredicateIngredient.Serializer INSTANCE = new NBTPredicateIngredient.Serializer();

        @Override
        public NBTPredicateIngredient parse(FriendlyByteBuf buffer) {
            return new NBTPredicateIngredient(buffer.readItem());
        }

        @Override
        public NBTPredicateIngredient parse(JsonObject json) {
            ItemStack stack = CraftingHelper.getItemStack(json, true);

            return new NBTPredicateIngredient(stack);
        }

        @Override
        public void write(FriendlyByteBuf buffer, NBTPredicateIngredient ingredient) {
            buffer.writeItem(ingredient.stack);
        }
    }
}