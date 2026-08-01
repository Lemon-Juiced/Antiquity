package site.scalarstudios.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class FermentingRecipe implements Recipe<SingleRecipeInput> {
    public static final MapCodec<FermentingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
                Recipe.CommonInfo.MAP_CODEC.forGetter(o -> o.commonInfo),
                Ingredient.CODEC.fieldOf("ingredient").forGetter(o -> o.ingredient),
                ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result),
                Codec.INT.optionalFieldOf("fermentingtime", 200).forGetter(o -> o.fermentingTime)
            )
            .apply(instance, FermentingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, FermentingRecipe> STREAM_CODEC = StreamCodec.composite(
        Recipe.CommonInfo.STREAM_CODEC,
        o -> o.commonInfo,
        Ingredient.CONTENTS_STREAM_CODEC,
        o -> o.ingredient,
        ItemStackTemplate.STREAM_CODEC,
        o -> o.result,
        ByteBufCodecs.VAR_INT,
        o -> o.fermentingTime,
        FermentingRecipe::new
    );

    public static final RecipeSerializer<FermentingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    private final Recipe.CommonInfo commonInfo;
    private final Ingredient ingredient;
    private final ItemStackTemplate result;
    private final int fermentingTime;
    private @Nullable PlacementInfo placementInfo;

    public FermentingRecipe(Recipe.CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result, int fermentingTime) {
        this.commonInfo = commonInfo;
        this.ingredient = ingredient;
        this.result = result;
        this.fermentingTime = fermentingTime;
    }

    public Ingredient ingredient() {
        return this.ingredient;
    }

    public int fermentingTime() {
        return this.fermentingTime;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return this.result.create();
    }

    @Override
    public boolean showNotification() {
        return this.commonInfo.showNotification();
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<FermentingRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<FermentingRecipe> getType() {
        return AntiquityRecipes.FERMENTING_TYPE.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.create(this.ingredient);
        }

        return this.placementInfo;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.FURNACE_MISC;
    }
}
