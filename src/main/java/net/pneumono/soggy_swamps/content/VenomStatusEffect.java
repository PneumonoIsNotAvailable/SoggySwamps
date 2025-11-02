package net.pneumono.soggy_swamps.content;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class VenomStatusEffect extends MobEffect {
    // Nothing needs to go here, but I figured extending the class would be better than using access wideners
    public VenomStatusEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
}
