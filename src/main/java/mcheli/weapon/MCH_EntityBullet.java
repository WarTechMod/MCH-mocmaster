package mcheli.weapon;

import java.util.ArrayList;
import java.util.List;

import com.hbm.main.MainRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mcheli.MCH_Config;
import mcheli.MCH_Lib;
import mcheli.MCH_MOD;
import mcheli.weapon.MCH_BulletModel;
import mcheli.weapon.MCH_DefaultBulletModels;
import mcheli.weapon.MCH_EntityBaseBullet;
import mcheli.wrapper.W_MovingObjectPosition;
import mcheli.wrapper.W_WorldFunc;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

public class MCH_EntityBullet extends MCH_EntityBaseBullet {

   private ForgeChunkManager.Ticket loaderTicket;


   public MCH_EntityBullet(World par1World) {
      super(par1World);
   }

   public MCH_EntityBullet(World par1World, double pX, double pY, double pZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
      super(par1World, pX, pY, pZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
   }

   @Override
   protected void entityInit() {
      super.entityInit();
      this.init(ForgeChunkManager.requestTicket(MainRegistry.instance, worldObj, ForgeChunkManager.Type.ENTITY));
   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.isDead && !super.worldObj.isRemote && this.getCountOnUpdate() > 1 && this.getInfo() != null && super.explosionPower > 0) {
         float pDist = this.getInfo().proximityFuseDist;
         if((double)pDist > 0.1D) {
            ++pDist;
            float rng = pDist + MathHelper.abs(this.getInfo().acceleration);
            List list = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand((double)rng, (double)rng, (double)rng));

            for(int i = 0; i < list.size(); ++i) {
               Entity entity1 = (Entity)list.get(i);
               if(this.canBeCollidedEntity(entity1) && entity1.getDistanceSqToEntity(this) < (double)(pDist * pDist)) {
                  MCH_Lib.DbgLog(super.worldObj, "MCH_EntityBullet.onUpdate:proximityFuse:" + entity1, new Object[0]);
                  super.posX = (entity1.posX + super.posX) / 2.0D;
                  super.posY = (entity1.posY + super.posY) / 2.0D;
                  super.posZ = (entity1.posZ + super.posZ) / 2.0D;
                  MovingObjectPosition mop = W_MovingObjectPosition.newMOP((int)super.posX, (int)super.posY, (int)super.posZ, 0, W_WorldFunc.getWorldVec3EntityPos(this), false);
                  this.onImpact(mop, 1.0F);
                  break;
               }
            }
         }
         loadNeighboringChunks((int) Math.floor(posX / 16), (int) Math.floor(posZ / 16));
      }
      if(this.ticksExisted>300){
         this.setDead();
      }
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean isInRangeToRenderDist(double distance) {
      return true;
   }

   protected void onUpdateCollided() {
      double mx = super.motionX * super.accelerationFactor;
      double my = super.motionY * super.accelerationFactor;
      double mz = super.motionZ * super.accelerationFactor;
      float damageFactor = 1.0F;
      MovingObjectPosition m = null;

      Vec3 vec3;
      Vec3 vec31;
      for(int entity = 0; entity < 5; ++entity) {
         vec3 = W_WorldFunc.getWorldVec3(super.worldObj, super.posX, super.posY, super.posZ);
         vec31 = W_WorldFunc.getWorldVec3(super.worldObj, super.posX + mx, super.posY + my, super.posZ + mz);
         m = W_WorldFunc.clip(super.worldObj, vec3, vec31);
         boolean list = false;
         if(super.shootingEntity != null && W_MovingObjectPosition.isHitTypeTile(m)) {
            Block d0 = W_WorldFunc.getBlock(super.worldObj, m.blockX, m.blockY, m.blockZ);
            MCH_Config var10000 = MCH_MOD.config;
            if(MCH_Config.bulletBreakableBlocks.contains(d0)) {
               W_WorldFunc.destroyBlock(super.worldObj, m.blockX, m.blockY, m.blockZ, true);
               list = true;
            }
         }

         if(!list) {
            break;
         }
      }

      vec3 = W_WorldFunc.getWorldVec3(super.worldObj, super.posX, super.posY, super.posZ);
      vec31 = W_WorldFunc.getWorldVec3(super.worldObj, super.posX + mx, super.posY + my, super.posZ + mz);
      if(this.getInfo().delayFuse > 0) {
         if(m != null) {
            this.boundBullet(m.sideHit);
            if(super.delayFuse == 0) {
               super.delayFuse = this.getInfo().delayFuse;
            }
         }

      } else {
         if(m != null) {
            vec31 = W_WorldFunc.getWorldVec3(super.worldObj, m.hitVec.xCoord, m.hitVec.yCoord, m.hitVec.zCoord);
         }

         Entity var22 = null;
         List var23 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.addCoord(mx, my, mz).expand(21.0D, 21.0D, 21.0D));
         double var24 = 0.0D;

         for(int j = 0; j < var23.size(); ++j) {
            Entity entity1 = (Entity)var23.get(j);
            if(this.canBeCollidedEntity(entity1)) {
               float f = 0.3F;
               AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
               MovingObjectPosition m1 = axisalignedbb.calculateIntercept(vec3, vec31);
               if(m1 != null) {
                  double d1 = vec3.distanceTo(m1.hitVec);
                  if(d1 < var24 || var24 == 0.0D) {
                     var22 = entity1;
                     var24 = d1;
                  }
               }
            }
         }

         if(var22 != null) {
            m = new MovingObjectPosition(var22);
         }

         if(m != null) {
            this.onImpact(m, damageFactor);
         }

      }
   }

   public MCH_BulletModel getDefaultBulletModel() {
      return MCH_DefaultBulletModels.Bullet;
   }

   public void init(ForgeChunkManager.Ticket ticket) {
      if(!worldObj.isRemote) {

         if(ticket != null) {

            if(loaderTicket == null) {

               loaderTicket = ticket;
               loaderTicket.bindEntity(this);
               loaderTicket.getModData();
            }

            ForgeChunkManager.forceChunk(loaderTicket, new ChunkCoordIntPair(chunkCoordX, chunkCoordZ));
         }
      }
   }

   List<ChunkCoordIntPair> loadedChunks = new ArrayList<ChunkCoordIntPair>();

   public void loadNeighboringChunks(int newChunkX, int newChunkZ) {
      if(!worldObj.isRemote && loaderTicket != null) {

         clearChunkLoader();

         loadedChunks.clear();
         loadedChunks.add(new ChunkCoordIntPair(newChunkX, newChunkZ));
         //loadedChunks.add(new ChunkCoordIntPair(newChunkX + (int) Math.floor((this.posX + this.motionX * this.motionMult()) / 16D), newChunkZ + (int) Math.floor((this.posZ + this.motionZ * this.motionMult()) / 16D)));

         for(ChunkCoordIntPair chunk : loadedChunks) {
            ForgeChunkManager.forceChunk(loaderTicket, chunk);
         }
      }
   }

   public void clearChunkLoader() {
      if(!worldObj.isRemote && loaderTicket != null) {
         for(ChunkCoordIntPair chunk : loadedChunks) {
            ForgeChunkManager.unforceChunk(loaderTicket, chunk);
         }
      }
   }
}
