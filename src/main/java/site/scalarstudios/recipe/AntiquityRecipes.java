package site.scalarstudios.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;

public class AntiquityRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Antiquity.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Antiquity.MODID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<KilnRecipe>> KILN_TYPE = RECIPE_TYPES.register("kiln",
        () -> new RecipeType<KilnRecipe>() {
            @Override
            public String toString() {
                return "antiquity:kiln";
            }
        }
    );

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<KilnRecipe>> KILN_SERIALIZER = RECIPE_SERIALIZERS.register("kiln", () -> KilnRecipe.SERIALIZER);

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
