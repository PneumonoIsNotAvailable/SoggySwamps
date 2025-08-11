package net.pneumono.soggy_swamps.content;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class FlyParticle extends SpriteBillboardParticle {
    protected double lastVelocityX;
    protected double lastVelocityY;
    protected double lastVelocityZ;

    protected FlyParticle(ClientWorld clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(clientWorld, x, y, z, velocityX, velocityY, velocityZ);
        this.ascending = true;
        this.velocityMultiplier = 0.96F;
        this.scale *= 0.75F;
        this.velocityY *= 0.8F;
        this.velocityX *= 0.8F;
        this.velocityZ *= 0.8F;
        updateLastVelocity();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
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

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            FlyParticle flyParticle = new FlyParticle(
                    clientWorld, d, e, f, 0.5 - clientWorld.random.nextDouble(), clientWorld.random.nextBoolean() ? h : -h, 0.5 - clientWorld.random.nextDouble()
            );
            flyParticle.setMaxAge(clientWorld.random.nextBetween(200, 300));
            flyParticle.scale(1.5F);
            flyParticle.setSprite(this.spriteProvider);
            flyParticle.setAlpha(0.0F);
            flyParticle.collidesWithWorld = false;
            return flyParticle;
        }
    }
}
