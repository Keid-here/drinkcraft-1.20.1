package com.keid.drinkcraft.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class FishCurseEffect extends StatusEffect {
    public FishCurseEffect(){
        // category: StatusEffectCategory - describes if the effect is helpful (BENEFICIAL), harmful (HARMFUL) or useless (NEUTRAL)
        // color: int - Color is the color assigned to the effect (in RGB)
        super(StatusEffectCategory.HARMFUL, 0xe9b8b3);
    }

    // Called every tick to check if the effect can be applied or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the effect every tick
        return true;
    }

    // Called when the effect is applied
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            if (!entity.isSubmergedInWater()) {
                entity.setAir(entity.getAir() - 5);
                if (entity.getAir() <= 0) {
                    entity.damage(entity.getDamageSources().drown(), 1.0f);
                }
            }else if(entity.isSubmergedInWater()){
                entity.setAir(entity.getMaxAir());
            }

        }
    }
}
