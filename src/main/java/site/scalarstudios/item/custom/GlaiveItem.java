package site.scalarstudios.item.custom;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import site.scalarstudios.Antiquity;

public class GlaiveItem extends Item {
    private static final Identifier REACH_MODIFIER_ID = Identifier.fromNamespaceAndPath(Antiquity.MODID, "glaive_reach");

    public GlaiveItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, float reachBonus, Item.Properties properties) {
        super(properties.sword(material, attackDamageBaseline, attackSpeedBaseline).attributes(createAttributes(material, attackDamageBaseline, attackSpeedBaseline, reachBonus)));
    }

    private static ItemAttributeModifiers createAttributes(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, float reachBonus) {
        return ItemAttributeModifiers.builder()
            .add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamageBaseline + material.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            ).add(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeedBaseline, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            ).add(
                Attributes.ENTITY_INTERACTION_RANGE,
                new AttributeModifier(REACH_MODIFIER_ID, reachBonus, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            ).build();
    }

    @Override
    public boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {
        return itemAbility == ItemAbilities.SWORD_SWEEP;
    }
}