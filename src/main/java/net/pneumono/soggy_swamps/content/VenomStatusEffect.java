package net.pneumono.soggy_swamps.content;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class VenomStatusEffect extends StatusEffect {
    // Nothing needs to go here, but I figured extending the class would be better than using access wideners
    public VenomStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
}
