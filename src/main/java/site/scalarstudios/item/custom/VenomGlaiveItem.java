package site.scalarstudios.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public class VenomGlaiveItem extends GlaiveItem {
    private static final int POISON_DURATION_TICKS = 100;
    private static final int POISON_AMPLIFIER = 0;

    public VenomGlaiveItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, float reachBonus, Item.Properties properties) {
        super(material, attackDamageBaseline, attackSpeedBaseline, reachBonus, properties);
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        super.hurtEnemy(itemStack, mob, attacker);
        if (!mob.level().isClientSide()) {
            mob.addEffect(new MobEffectInstance(MobEffects.POISON, POISON_DURATION_TICKS, POISON_AMPLIFIER), attacker);
        }
    }
}
