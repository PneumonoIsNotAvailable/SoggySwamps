package net.pneumono.soggy_swamps.content;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class FlyParticle extends BillboardParticle {
    protected double lastVelocityX;
    protected double lastVelocityY;
    protected double lastVelocityZ;

    protected FlyParticle(ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Sprite sprite) {
        super(clientWorld, x, y, z, velocityX, velocityY, velocityZ, sprite);
        this.ascending = true;
        this.velocityMultiplier = 0.96F;
        this.scale *= 0.75F;
        this.velocityY *= 0.8F;
        this.velocityX *= 0.8F;
        this.velocityZ *= 0.8F;
        updateLastVelocity();
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.PARTICLE_ATLAS_TRANSLUCENT;
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

        double velocityXChange = this.velocityX - this.lastVelocityX;
        double velocityYChange = this.velocityY - this.lastVelocityY;
        double velocityZChange = this.velocityZ - this.lastVelocityZ;

        // Kill grounded flies
        if (
                blocksMovement(0, 0, 0)
        ) {
            this.markDead();
            return;
        }

        // Get new "base" velocity using previous velocities
        double newVelocityX = Math.clamp(this.velocityX + Math.clamp(velocityXChange, -maxChange, maxChange), -maxSpeed, maxSpeed);
        double newVelocityY = Math.clamp(this.velocityY + Math.clamp(velocityYChange, -maxChange, maxChange), -maxSpeed, maxSpeed);
        double newVelocityZ = Math.clamp(this.velocityZ + Math.clamp(velocityZChange, -maxChange, maxChange), -maxSpeed, maxSpeed);

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
        this.velocityX = newVelocityX + speed * this.random.nextFloat() - (speed / 2);
        this.velocityY = newVelocityY + speed * this.random.nextFloat() - (speed / 2);
        this.velocityZ = newVelocityZ + speed * this.random.nextFloat() - (speed / 2);
    }

    private boolean blocksMovement(double xOffset, double yOffset, double zOffset) {
        return !this.world.getBlockState(
                BlockPos.ofFloored(this.x + xOffset, this.y + yOffset, this.z + zOffset)
        ).canPathfindThrough(NavigationType.LAND);
    }

    private float getAlpha(float age) {
        float ageFloat = MathHelper.clamp(age / this.maxAge, 0.0F, 1.0F);
        return MathHelper.clamp(-pow4((ageFloat * 2) - 1) + 1, 0.0F, 1.0F);
    }

    private float pow4(float value) {
        return value  * value * value * value;
    }

    private void updateLastVelocity() {
        this.lastVelocityX = this.velocityX;
        this.lastVelocityY = this.velocityY;
        this.lastVelocityZ = this.velocityZ;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            FlyParticle flyParticle = new FlyParticle(
                    world, x, y, z, 0.5 - world.random.nextDouble(), world.random.nextBoolean() ? velocityY : -velocityY, 0.5 - world.random.nextDouble(), this.spriteProvider.getSprite(random)
            );
            flyParticle.setMaxAge(world.random.nextBetween(200, 300));
            flyParticle.scale(1.5F);
            flyParticle.setAlpha(0.0F);
            flyParticle.collidesWithWorld = false;
            return flyParticle;
        }
    }
}
