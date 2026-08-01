package site.scalarstudios.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.Ingredient;
import site.scalarstudios.block.AntiquityBlocks;

public class KilnRecipe extends AbstractCookingRecipe {
    public static final MapCodec<KilnRecipe> MAP_CODEC = cookingMapCodec(KilnRecipe::new, 200);
    public static final StreamCodec<RegistryFriendlyByteBuf, KilnRecipe> STREAM_CODEC = cookingStreamCodec(KilnRecipe::new);
    public static final RecipeSerializer<KilnRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    public KilnRecipe(Recipe.CommonInfo commonInfo, AbstractCookingRecipe.CookingBookInfo bookInfo, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime) {
        super(commonInfo, bookInfo, ingredient, result, experience, cookingTime);
    }

    @Override
    protected Item furnaceIcon() {
        return AntiquityBlocks.KILN.get().asItem();
    }

    @Override
    public RecipeSerializer<KilnRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<KilnRecipe> getType() {
        return AntiquityRecipes.KILN_TYPE.get();
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.FURNACE_MISC;
    }
}
