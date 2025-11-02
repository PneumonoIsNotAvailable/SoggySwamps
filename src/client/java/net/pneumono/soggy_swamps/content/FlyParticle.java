package net.pneumono.soggy_swamps.content;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlyParticle extends SingleQuadParticle {
    protected double lastVelocityX;
    protected double lastVelocityY;
    protected double lastVelocityZ;

    protected FlyParticle(ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, TextureAtlasSprite sprite) {
        super(level, x, y, z, velocityX, velocityY, velocityZ, sprite);
        this.speedUpWhenYMotionIsBlocked = true;
        this.friction = 0.96F;
        this.quadSize *= 0.75F;
        this.yd *= 0.8F;
        this.xd *= 0.8F;
        this.zd *= 0.8F;
        updateLastVelocity();
    }

    @Override
    public @NotNull Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        if (!isAlive()) return;

        this.setAlpha(getAlpha(this.age));

        // Constants
        double speed = 0.01;
        double maxSpeed = 0.25;
        double maxChange = 0.5;
        double avoidanceStrength = 0.5;

        double velocityXChange = this.xd - this.lastVelocityX;
        double velocityYChange = this.yd - this.lastVelocityY;
        double velocityZChange = this.zd - this.lastVelocityZ;

        // Kill grounded flies
        if (
                blocksMovement(0, 0, 0)
        ) {
            this.remove();
            return;
        }

        // Get new "base" velocity using previous velocities
        double newVelocityX = Math.clamp(this.xd + Math.clamp(velocityXChange, -maxChange, maxChange), -maxSpeed, maxSpeed);
        double newVelocityY = Math.clamp(this.yd + Math.clamp(velocityYChange, -maxChange, maxChange), -maxSpeed, maxSpeed);
        double newVelocityZ = Math.clamp(this.zd + Math.clamp(velocityZChange, -maxChange, maxChange), -maxSpeed, maxSpeed);

        // Change velocity to avoid blocks
        if (blocksMovement(1, 0, 0)) {
            newVelocityX -= avoidanceStrength * (1.0 - (this.x - Math.floor(this.x)));
        } else if (blocksMovement(-1, 0, 0)) {
            newVelocityX += avoidanceStrength * (this.x - Math.floor(this.x));
        }

        if (blocksMovement(0, 1, 0)) {
            newVelocityY -= avoidanceStrength * (1.0 - (this.y - Math.floor(this.y)));
        } else if (blocksMovement(0, 1, 0)) {
            newVelocityY += avoidanceStrength * (this.y - Math.floor(this.y));
        }

        if (blocksMovement(0, 0, 1)) {
            newVelocityZ -= avoidanceStrength * (1.0 - (this.z - Math.floor(this.z)));
        } else if (blocksMovement(0, 0, 1)) {
            newVelocityZ += avoidanceStrength * (this.z - Math.floor(this.z));
        }

        // Set new velocity + random offset
        updateLastVelocity();
        this.xd = newVelocityX + speed * this.random.nextFloat() - (speed / 2);
        this.yd = newVelocityY + speed * this.random.nextFloat() - (speed / 2);
        this.zd = newVelocityZ + speed * this.random.nextFloat() - (speed / 2);
    }

    private boolean blocksMovement(double xOffset, double yOffset, double zOffset) {
        return !this.level.getBlockState(
                BlockPos.containing(this.x + xOffset, this.y + yOffset, this.z + zOffset)
        ).isPathfindable(PathComputationType.LAND);
    }

    private float getAlpha(float age) {
        float ageFloat = Mth.clamp(age / this.lifetime, 0.0F, 1.0F);
        return Mth.clamp(-pow4((ageFloat * 2) - 1) + 1, 0.0F, 1.0F);
    }

    private float pow4(float value) {
        return value  * value * value * value;
    }

    private void updateLastVelocity() {
        this.lastVelocityX = this.xd;
        this.lastVelocityY = this.yd;
        this.lastVelocityZ = this.zd;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            FlyParticle flyParticle = new FlyParticle(
                    level, x, y, z, 0.5 - level.random.nextDouble(), level.random.nextBoolean() ? velocityY : -velocityY, 0.5 - level.random.nextDouble(), this.spriteProvider.get(random)
            );
            flyParticle.setLifetime(level.random.nextIntBetweenInclusive(200, 300));
            flyParticle.scale(1.5F);
            flyParticle.setAlpha(0.0F);
            flyParticle.hasPhysics = false;
            return flyParticle;
        }
    }
}
