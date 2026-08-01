package site.scalarstudios.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import site.scalarstudios.Antiquity;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.menu.AntiquityMenuTypes;
import site.scalarstudios.menu.ClayVesselMenu;
import site.scalarstudios.menu.KilnMenu;
import site.scalarstudios.recipe.FermentingRecipe;
import site.scalarstudios.recipe.KilnRecipe;

import java.util.List;

@JeiPlugin
public class AntiquityJeiPlugin implements IModPlugin {
    private static final Identifier UID = Identifier.fromNamespaceAndPath(Antiquity.MODID, "jei_plugin");

    @Override
    public Identifier getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
            new KilnRecipeCategory(registration.getJeiHelpers().getGuiHelper()),
            new ClayVesselRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerRecipes(IRecipeRegistration registration) {
        IntegratedServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server == null) {
            return;
        }

        RecipeManager recipeManager = server.getRecipeManager();

        List<RecipeHolder<KilnRecipe>> kilnRecipes = recipeManager.getRecipes()
            .stream()
            .filter(holder -> holder.value() instanceof KilnRecipe)
            .map(holder -> (RecipeHolder<KilnRecipe>) (RecipeHolder<?>) holder)
            .toList();
        registration.addRecipes(KilnRecipeCategory.TYPE, kilnRecipes);

        List<RecipeHolder<FermentingRecipe>> fermentingRecipes = recipeManager.getRecipes()
            .stream()
            .filter(holder -> holder.value() instanceof FermentingRecipe)
            .map(holder -> (RecipeHolder<FermentingRecipe>) (RecipeHolder<?>) holder)
            .toList();
        registration.addRecipes(ClayVesselRecipeCategory.TYPE, fermentingRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(KilnRecipeCategory.TYPE, AntiquityBlocks.KILN.get());
        registration.addCraftingStation(ClayVesselRecipeCategory.TYPE, AntiquityBlocks.CLAY_VESSEL.get(), AntiquityBlocks.FERMENTING_BARREL.get());
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(KilnMenu.class, AntiquityMenuTypes.KILN.get(), KilnRecipeCategory.TYPE, 0, 2, 3, 36);
        registration.addRecipeTransferHandler(ClayVesselMenu.class, AntiquityMenuTypes.CLAY_VESSEL.get(), ClayVesselRecipeCategory.TYPE, 0, 1, 2, 36);
    }
}
