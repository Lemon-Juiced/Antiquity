package site.scalarstudios.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.recipe.AntiquityRecipes;
import site.scalarstudios.recipe.FermentingRecipe;

public class ClayVesselRecipeCategory implements IRecipeCategory<RecipeHolder<FermentingRecipe>> {
    public static final IRecipeHolderType<FermentingRecipe> TYPE = IRecipeHolderType.create(AntiquityRecipes.FERMENTING_TYPE.get());

    private static final int WIDTH = 122;
    private static final int HEIGHT = 36;

    private final IDrawable icon;

    public ClayVesselRecipeCategory(IGuiHelper guiHelper) {
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(AntiquityBlocks.CLAY_VESSEL.get()));
    }

    @Override
    public IRecipeHolderType<FermentingRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.antiquity.clay_vessel");
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<FermentingRecipe> recipeHolder, IFocusGroup focusGroup) {
        FermentingRecipe recipe = recipeHolder.value();

        builder.addSlot(RecipeIngredientRole.INPUT, 21, 10)
            .setStandardSlotBackground()
            .add(recipe.ingredient());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 81, 10)
            .setOutputSlotBackground()
            .add(recipe.assemble(new SingleRecipeInput(ItemStack.EMPTY)));
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<FermentingRecipe> recipeHolder, IFocusGroup focusGroup) {
        FermentingRecipe recipe = recipeHolder.value();
        builder.addAnimatedRecipeArrow(recipe.fermentingTime()).setPosition(44, 9);
    }
}
