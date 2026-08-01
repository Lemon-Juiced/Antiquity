package site.scalarstudios.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.recipe.AntiquityRecipes;
import site.scalarstudios.recipe.KilnRecipe;

import java.util.List;

public class KilnRecipeCategory implements IRecipeCategory<RecipeHolder<KilnRecipe>> {
    public static final IRecipeHolderType<KilnRecipe> TYPE = IRecipeHolderType.create(AntiquityRecipes.KILN_TYPE.get());

    private static final int WIDTH = 82;
    private static final int HEIGHT = 56;

    private final IDrawable icon;

    public KilnRecipeCategory(IGuiHelper guiHelper) {
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(AntiquityBlocks.KILN.get()));
    }

    @Override
    public IRecipeHolderType<KilnRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.antiquity.kiln");
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<KilnRecipe> recipeHolder, IFocusGroup focusGroup) {
        KilnRecipe recipe = recipeHolder.value();

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
            .setStandardSlotBackground()
            .add(recipe.input());

        builder.addSlot(RecipeIngredientRole.INPUT, 1, 37)
            .setStandardSlotBackground()
            .addItemStacks(fuelItemStacks());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 60, 19)
            .setOutputSlotBackground()
            .add(recipe.assemble(new SingleRecipeInput(ItemStack.EMPTY)));
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RecipeHolder<KilnRecipe> recipeHolder, IFocusGroup focusGroup) {
        KilnRecipe recipe = recipeHolder.value();
        builder.addAnimatedRecipeArrow(recipe.cookingTime()).setPosition(25, 19);
        builder.addAnimatedRecipeFlame(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD).setPosition(3, 19);
    }

    private static List<ItemStack> fuelItemStacks() {
        if (Minecraft.getInstance().level == null) {
            return List.of();
        }

        return Minecraft.getInstance().level.fuelValues().fuelItems().stream().map(ItemStack::new).toList();
    }
}
