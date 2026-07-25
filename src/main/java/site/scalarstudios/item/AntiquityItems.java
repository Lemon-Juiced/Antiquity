package site.scalarstudios.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumables;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;
import site.scalarstudios.item.custom.GlaiveItem;
import site.scalarstudios.item.custom.GladiusItem;
import site.scalarstudios.item.custom.NauseousDrinkItem;

import java.util.function.Function;

public class AntiquityItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Antiquity.MODID);

    // Gladii
    public static final DeferredItem<GladiusItem> WOODEN_GLADIUS = registerItem("wooden_gladius", p -> new GladiusItem(ToolMaterial.WOOD, 2.5F, -2.0F, -0.5F, p));
    public static final DeferredItem<GladiusItem> STONE_GLADIUS = registerItem("stone_gladius", p -> new GladiusItem(ToolMaterial.STONE, 2.5F, -2.0F, -0.5F, p));
    public static final DeferredItem<GladiusItem> IRON_GLADIUS = registerItem("iron_gladius", p -> new GladiusItem(ToolMaterial.IRON, 2.5F, -2.0F, -0.5F, p));
    public static final DeferredItem<GladiusItem> COPPER_GLADIUS = registerItem("copper_gladius", p -> new GladiusItem(ToolMaterial.COPPER, 2.5F, -2.0F, -0.5F, p));
    public static final DeferredItem<GladiusItem> GOLDEN_GLADIUS = registerItem("golden_gladius", p -> new GladiusItem(ToolMaterial.GOLD, 2.5F, -2.0F, -0.5F, p));
    public static final DeferredItem<GladiusItem> DIAMOND_GLADIUS = registerItem("diamond_gladius", p -> new GladiusItem(ToolMaterial.DIAMOND, 2.5F, -2.0F, -0.5F, p));
    public static final DeferredItem<GladiusItem> NETHERITE_GLADIUS = registerItem("netherite_gladius", p -> new GladiusItem(ToolMaterial.NETHERITE, 2.5F, -2.0F, -0.5F, p));

    // Glaives
    public static final DeferredItem<GlaiveItem> WOODEN_GLAIVE = registerItem("wooden_glaive", p -> new GlaiveItem(ToolMaterial.WOOD, 3.0F, -3.1F, 1.0F, p));
    public static final DeferredItem<GlaiveItem> STONE_GLAIVE = registerItem("stone_glaive", p -> new GlaiveItem(ToolMaterial.STONE, 3.0F, -3.1F, 1.0F, p));
    public static final DeferredItem<GlaiveItem> IRON_GLAIVE = registerItem("iron_glaive", p -> new GlaiveItem(ToolMaterial.IRON, 3.0F, -3.1F, 1.0F, p));
    public static final DeferredItem<GlaiveItem> COPPER_GLAIVE = registerItem("copper_glaive", p -> new GlaiveItem(ToolMaterial.COPPER, 3.0F, -3.1F, 1.0F, p));
    public static final DeferredItem<GlaiveItem> GOLDEN_GLAIVE = registerItem("golden_glaive", p -> new GlaiveItem(ToolMaterial.GOLD, 3.0F, -3.1F, 1.0F, p));
    public static final DeferredItem<GlaiveItem> DIAMOND_GLAIVE = registerItem("diamond_glaive", p -> new GlaiveItem(ToolMaterial.DIAMOND, 3.0F, -3.1F, 1.0F, p));
    public static final DeferredItem<GlaiveItem> NETHERITE_GLAIVE = registerItem("netherite_glaive", p -> new GlaiveItem(ToolMaterial.NETHERITE, 3.0F, -3.1F, 1.0F, p));

    // Kiln Bricks
    public static final DeferredItem<Item> KILN_BRICK = registerItem("kiln_brick", Item::new);
    public static final DeferredItem<Item> KILN_CLAY = registerItem("kiln_clay", Item::new);

    // Drinks
    public static final DeferredItem<NauseousDrinkItem> MEAD = registerItem("mead", NauseousDrinkItem::new, new Item.Properties().stacksTo(16).component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK).usingConvertsTo(Items.GLASS_BOTTLE));
    public static final DeferredItem<NauseousDrinkItem> WINE = registerItem("wine", NauseousDrinkItem::new, new Item.Properties().stacksTo(16).component(DataComponents.CONSUMABLE, Consumables.DEFAULT_DRINK).usingConvertsTo(Items.GLASS_BOTTLE));

    // Food
    public static final DeferredItem<Item> GRAPES = registerItem(
        "grapes",
        Item::new,
        new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build())
    );

    private static <T extends Item> DeferredItem<T> registerItem(String name, Function<Item.Properties, T> function) {
        return ITEMS.registerItem(name, function);
    }

    private static <T extends Item> DeferredItem<T> registerItem(String name, Function<Item.Properties, T> function, Item.Properties properties) {
        return ITEMS.registerItem(name, function, () -> properties);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
