package site.scalarstudios.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class NauseousDrinkItem extends Item {
    private static final int NAUSEA_DURATION_TICKS = 300;
    private static final int POISON_DURATION_TICKS = 150;
    private static final int RESISTANCE_DURATION_TICKS = 300;
    // Nausea V (amplifier 5) is the cap - drinking further past this causes alcohol poisoning instead.
    private static final int MAX_NAUSEA_AMPLIFIER = 5;

    public NauseousDrinkItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide()) {
            MobEffectInstance existingNausea = entity.getEffect(MobEffects.NAUSEA);
            int nauseaAmplifier = existingNausea != null ? existingNausea.getAmplifier() : -1;

            if (nauseaAmplifier >= MAX_NAUSEA_AMPLIFIER) {
                MobEffectInstance existingPoison = entity.getEffect(MobEffects.POISON);
                int poisonAmplifier = existingPoison != null ? existingPoison.getAmplifier() + 1 : 0;
                entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, NAUSEA_DURATION_TICKS, MAX_NAUSEA_AMPLIFIER));
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, POISON_DURATION_TICKS, poisonAmplifier));
            } else {
                entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, NAUSEA_DURATION_TICKS, nauseaAmplifier + 1));
            }

            entity.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, RESISTANCE_DURATION_TICKS));
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
