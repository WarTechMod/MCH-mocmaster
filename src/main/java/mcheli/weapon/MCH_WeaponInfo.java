package mcheli.weapon;

import java.util.ArrayList;
import java.util.List;
import mcheli.MCH_BaseInfo;
import mcheli.MCH_Color;
import mcheli.MCH_DamageFactor;
import mcheli.helicopter.MCH_EntityHeli;
import mcheli.plane.MCP_EntityPlane;
import mcheli.tank.MCH_EntityTank;
import mcheli.vehicle.MCH_EntityVehicle;
import mcheli.weapon.MCH_BulletModel;
import mcheli.weapon.MCH_Cartridge;
import mcheli.weapon.MCH_SightType;
import mcheli.wrapper.W_Item;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MCH_WeaponInfo extends MCH_BaseInfo {

   public final String name;
   public int nukeYield;
   public int chlorineYield;
   public String displayName;
   public String type;
   public int power;
   public float acceleration;
   public float accelerationInWater;
   public int explosion;
   public int explosionBlock;
   public int explosionInWater;
   public int explosionAltitude;
   public int delayFuse;
   public float bound;
   public int timeFuse;
   public boolean flaming;
   public MCH_SightType sight;
   public float[] zoom;
   public int delay;
   public int reloadTime;
   public int round;
   public int suppliedNum;
   public int maxAmmo;
   public List roundItems;
   public int soundDelay;
   public float soundVolume;
   public float soundPitch;
   public float soundPitchRandom;
   public int soundPattern;
   public int lockTime;
   public boolean ridableOnly;
   public float proximityFuseDist;
   public int rigidityTime;
   public float accuracy;
   public int bomblet;
   public int bombletSTime;
   public float bombletDiff;
   public int modeNum;
   public int fixMode;
   public int piercing;
   public int heatCount;
   public int maxHeatCount;
   public boolean isFAE;
   public boolean isGuidedTorpedo;
   public float gravity;
   public float gravityInWater;
   public float velocityInWater;
   public boolean destruct;
   public String trajectoryParticleName;
   public int trajectoryParticleStartTick;
   public boolean disableSmoke;
   public MCH_Cartridge cartridge;
   public MCH_Color color;
   public MCH_Color colorInWater;
   public String soundFileName;
   public float smokeSize;
   public int smokeNum;
   public int smokeMaxAge;
   public Item dispenseItem;
   public int dispenseDamege;
   public int dispenseRange;
   public int recoilBufCount;
   public int recoilBufCountSpeed;
   public float length;
   public float radius;
   public float angle;
   public boolean displayMortarDistance;
   public boolean fixCameraPitch;
   public float cameraRotationSpeedPitch;
   public int target;
   public int markTime;
   public float recoil;
   public String bulletModelName;
   public MCH_BulletModel bulletModel;
   public String bombletModelName;
   public MCH_BulletModel bombletModel;
   public MCH_DamageFactor damageFactor;
   public String group;
   public List listMuzzleFlash;
   public List listMuzzleFlashSmoke;
   public int wpYield;
    public int uranYield;


    public MCH_WeaponInfo(String name) {
      this.name = name;
      this.displayName = name;
      this.type = "";
      this.power = 0;
      this.acceleration = 1.0F;
      this.accelerationInWater = 1.0F;
      this.explosion = 0;
      this.explosionBlock = -1;
      this.explosionInWater = 0;
      this.explosionAltitude = 0;
      this.delayFuse = 0;
      this.timeFuse = 0;
      this.flaming = false;
      this.sight = MCH_SightType.NONE;
      this.zoom = new float[]{1.0F};
      this.delay = 10;
      this.reloadTime = 30;
      this.round = 0;
      this.suppliedNum = 1;
      this.roundItems = new ArrayList();
      this.maxAmmo = 0;
      this.soundDelay = 0;
      this.soundPattern = 0;
      this.soundVolume = 1.0F;
      this.soundPitch = 1.0F;
      this.soundPitchRandom = 0.1F;
      this.lockTime = 30;
      this.ridableOnly = false;
      this.proximityFuseDist = 0.0F;
      this.rigidityTime = 7;
      this.accuracy = 0.0F;
      this.bomblet = 0;
      this.bombletSTime = 10;
      this.bombletDiff = 0.3F;
      this.modeNum = 0;
      this.fixMode = 0;
      this.piercing = 0;
      this.heatCount = 0;
      this.maxHeatCount = 0;
      this.bulletModelName = "";
      this.bombletModelName = "";
      this.bulletModel = null;
      this.bombletModel = null;
      this.isFAE = false;
      this.isGuidedTorpedo = false;
      this.gravity = 0.0F;
      this.gravityInWater = 0.0F;
      this.velocityInWater = 0.999F;
      this.destruct = false;
      this.trajectoryParticleName = "explode";
      this.trajectoryParticleStartTick = 0;
      this.cartridge = null;
      this.disableSmoke = false;
      this.color = new MCH_Color();
      this.colorInWater = new MCH_Color();
      this.soundFileName = name + "_snd";
      this.smokeMaxAge = 100;
      this.smokeNum = 1;
      this.smokeSize = 2.0F;
      this.dispenseItem = null;
      this.dispenseDamege = 0;
      this.dispenseRange = 1;
      this.recoilBufCount = 2;
      this.recoilBufCountSpeed = 3;
      this.length = 0.0F;
      this.radius = 200.0F;
      this.target = 1;
      this.recoil = 0.0F;
      this.damageFactor = null;
      this.group = "";
      this.listMuzzleFlash = null;
      this.listMuzzleFlashSmoke = null;
      this.displayMortarDistance = false;
      this.fixCameraPitch = false;
      this.cameraRotationSpeedPitch = 1.0F;
      this.nukeYield=0;
      this.chlorineYield=0;
      this.wpYield=0;

   }

   public void checkData() {
      if(this.explosionBlock < 0) {
         this.explosionBlock = this.explosion;
      }

      if(this.fixMode >= this.modeNum) {
         this.fixMode = 0;
      }

      if(this.round <= 0) {
         this.round = this.maxAmmo;
      }

      if(this.round > this.maxAmmo) {
         this.round = this.maxAmmo;
      }

      if(this.explosion <= 0) {
         this.isFAE = false;
      }

      if(this.delayFuse <= 0) {
         this.bound = 0.0F;
      }

      if(this.isFAE) {
         this.explosionInWater = 0;
      }

      if(this.bomblet > 0 && this.bombletSTime < 1) {
         this.bombletSTime = 1;
      }

      if(this.destruct) {
         this.delay = 1000000;
      }

      this.angle = (float)(Math.atan2((double)this.radius, (double)this.length) * 180.0D / 3.141592653589793D);
   }

   public void loadItemData(String item, String data) {
      if(item.compareTo("displayname") == 0) {
         this.displayName = data;
      } else if(item.compareTo("type") == 0) {
         this.type = data.toLowerCase();
         if(this.type.equalsIgnoreCase("bomb") || this.type.equalsIgnoreCase("dispenser")) {
            this.gravity = -0.03F;
            this.gravityInWater = -0.03F;
         }
      } else if(item.compareTo("group") == 0) {
         this.group = data.toLowerCase().trim();
      } else if(item.compareTo("power") == 0) {
         this.power = this.toInt(data);
      } else if(item.equalsIgnoreCase("sound")) {
         this.soundFileName = data.toLowerCase().trim();
      } else if(item.compareTo("acceleration") == 0) {
         this.acceleration = this.toFloat(data, 0.0F, 100.0F);
      } else if(item.compareTo("accelerationinwater") == 0) {
         this.accelerationInWater = this.toFloat(data, 0.0F, 100.0F);
      } else if(item.compareTo("gravity") == 0) {
         this.gravity = this.toFloat(data, -50.0F, 50.0F);
      } else if(item.compareTo("gravityinwater") == 0) {
         this.gravityInWater = this.toFloat(data, -50.0F, 50.0F);
      } else if(item.equalsIgnoreCase("VelocityInWater")) {
         this.velocityInWater = this.toFloat(data);
      } else if(item.compareTo("explosion") == 0) {
         this.explosion = this.toInt(data, 0, 50);
      } else if(item.equalsIgnoreCase("explosionBlock")) {
         this.explosionBlock = this.toInt(data, 0, 50);
      } else if(item.compareTo("explosioninwater") == 0) {
         this.explosionInWater = this.toInt(data, 0, 50);
      } else if(item.equalsIgnoreCase("ExplosionAltitude")) {
         this.explosionAltitude = this.toInt(data, 0, 100);
      } else if(item.equalsIgnoreCase("TimeFuse")) {
         this.timeFuse = this.toInt(data, 0, 100000);
      } else if(item.equalsIgnoreCase("DelayFuse")) {
         this.delayFuse = this.toInt(data, 0, 100000);
      } else if(item.equalsIgnoreCase("Bound")) {
         this.bound = this.toFloat(data, 0.0F, 100000.0F);
      } else if(item.compareTo("flaming") == 0) {
         this.flaming = this.toBool(data);
      } else if(item.equalsIgnoreCase("DisplayMortarDistance")) {
         this.displayMortarDistance = this.toBool(data);
      } else if(item.equalsIgnoreCase("FixCameraPitch")) {
         this.fixCameraPitch = this.toBool(data);
      } else if(item.equalsIgnoreCase("CameraRotationSpeedPitch")) {
         this.cameraRotationSpeedPitch = this.toFloat(data, 0.0F, 100.0F);
      } else if(item.compareTo("sight") == 0) {
         data = data.toLowerCase();
         if(data.compareTo("movesight") == 0) {
            this.sight = MCH_SightType.ROCKET;
         }

         if(data.compareTo("missilesight") == 0) {
            this.sight = MCH_SightType.LOCK;
         }
      } else {
         String[] s;
         int c;
         if(item.equalsIgnoreCase("Zoom")) {
            s = this.splitParam(data);
            if(s.length > 0) {
               this.zoom = new float[s.length];

               for(c = 0; c < s.length; ++c) {
                  this.zoom[c] = this.toFloat(s[c], 0.1F, 10.0F);
               }
            }
         } else if(item.compareTo("delay") == 0) {
            this.delay = this.toInt(data, 0, 100000);
         } else if(item.equalsIgnoreCase("nukeYield")) {
            this.nukeYield = this.toInt(data, 0, 100000);
         } else if(item.equalsIgnoreCase("chlorineYield")) {
            this.chlorineYield = this.toInt(data, 0, 100000);
         } else if(item.equalsIgnoreCase("wpYield")) {
            this.wpYield = this.toInt(data, 0, 100000);
         } else if(item.equalsIgnoreCase("uranYield")) {
            this.uranYield = this.toInt(data, 0, 100000);
         } else if(item.compareTo("reloadtime") == 0) {
            this.reloadTime = this.toInt(data, 3, 1000);
         } else if(item.compareTo("round") == 0) {
            this.round = this.toInt(data, 1, 30000);
         } else if(item.equalsIgnoreCase("MaxAmmo")) {
            this.maxAmmo = this.toInt(data, 0, 30000);
         } else if(item.equalsIgnoreCase("SuppliedNum")) {
            this.suppliedNum = this.toInt(data, 1, 30000);
         } else if(item.equalsIgnoreCase("Item")) {
            s = data.split("\\s*,\\s*");
            if(s.length >= 2 && s[1].length() > 0 && this.roundItems.size() < 3) {
               c = this.toInt(s[0], 1, 64);
               if(c > 0) {
                  int className = s.length >= 3?this.toInt(s[2], 0, 100000000):0;
                  this.roundItems.add(new MCH_WeaponInfo.RoundItem(c, s[1].toLowerCase().trim(), className));
               }
            }
         } else if(item.compareTo("sounddelay") == 0) {
            this.soundDelay = this.toInt(data, 0, 1000);
         } else if(item.compareTo("soundpattern") != 0) {
            if(item.compareTo("soundvolume") == 0) {
               this.soundVolume = this.toFloat(data, 0.0F, 1000.0F);
            } else if(item.compareTo("soundpitch") == 0) {
               this.soundPitch = this.toFloat(data, 0.0F, 1.0F);
            } else if(item.equalsIgnoreCase("SoundPitchRandom")) {
               this.soundPitchRandom = this.toFloat(data, 0.0F, 1.0F);
            } else if(item.compareTo("locktime") == 0) {
               this.lockTime = this.toInt(data, 2, 1000);
            } else if(item.equalsIgnoreCase("RidableOnly")) {
               this.ridableOnly = this.toBool(data);
            } else if(item.compareTo("proximityfusedist") == 0) {
               this.proximityFuseDist = this.toFloat(data, 0.0F, 2000.0F);
            } else if(item.equalsIgnoreCase("RigidityTime")) {
               this.rigidityTime = this.toInt(data, 0, 1000000);
            } else if(item.compareTo("accuracy") == 0) {
               this.accuracy = this.toFloat(data, 0.0F, 1000.0F);
            } else if(item.compareTo("bomblet") == 0) {
               this.bomblet = this.toInt(data, 0, 1000);
            } else if(item.compareTo("bombletstime") == 0) {
               this.bombletSTime = this.toInt(data, 0, 1000);
            } else if(item.equalsIgnoreCase("BombletDiff")) {
               this.bombletDiff = this.toFloat(data, 0.0F, 1000.0F);
            } else if(item.equalsIgnoreCase("RecoilBufCount")) {
               s = this.splitParam(data);
               if(s.length >= 1) {
                  this.recoilBufCount = this.toInt(s[0], 1, 10000);
               }

               if(s.length >= 2 && this.recoilBufCount > 2) {
                  this.recoilBufCountSpeed = this.toInt(s[1], 1, 10000) - 1;
                  if(this.recoilBufCountSpeed > this.recoilBufCount / 2) {
                     this.recoilBufCountSpeed = this.recoilBufCount / 2;
                  }
               }
            } else if(item.compareTo("modenum") == 0) {
               this.modeNum = this.toInt(data, 0, 1000);
            } else if(item.equalsIgnoreCase("FixMode")) {
               this.fixMode = this.toInt(data, 0, 10);
            } else if(item.compareTo("piercing") == 0) {
               this.piercing = this.toInt(data, 0, 100000);
            } else if(item.compareTo("heatcount") == 0) {
               this.heatCount = this.toInt(data, 0, 100000);
            } else if(item.compareTo("maxheatcount") == 0) {
               this.maxHeatCount = this.toInt(data, 0, 100000);
            } else if(item.compareTo("modelbullet") == 0) {
               this.bulletModelName = data.toLowerCase().trim();
            } else if(item.equalsIgnoreCase("ModelBomblet")) {
               this.bombletModelName = data.toLowerCase().trim();
            } else if(item.compareTo("fae") == 0) {
               this.isFAE = this.toBool(data);
            } else if(item.compareTo("guidedtorpedo") == 0) {
               this.isGuidedTorpedo = this.toBool(data);
            } else if(item.compareTo("destruct") == 0) {
               this.destruct = this.toBool(data);
            } else if(item.equalsIgnoreCase("AddMuzzleFlash")) {
               s = this.splitParam(data);
               if(s.length >= 7) {
                  if(this.listMuzzleFlash == null) {
                     this.listMuzzleFlash = new ArrayList();
                  }

                  this.listMuzzleFlash.add(new MCH_WeaponInfo.MuzzleFlash(this.toFloat(s[0]), this.toFloat(s[1]), 0.0F, this.toInt(s[2]), this.toFloat(s[3]) / 255.0F, this.toFloat(s[4]) / 255.0F, this.toFloat(s[5]) / 255.0F, this.toFloat(s[6]) / 255.0F, 1));
               }
            } else if(item.equalsIgnoreCase("AddMuzzleFlashSmoke")) {
               s = this.splitParam(data);
               if(s.length >= 9) {
                  if(this.listMuzzleFlashSmoke == null) {
                     this.listMuzzleFlashSmoke = new ArrayList();
                  }

                  this.listMuzzleFlashSmoke.add(new MCH_WeaponInfo.MuzzleFlash(this.toFloat(s[0]), this.toFloat(s[2]), this.toFloat(s[3]), this.toInt(s[4]), this.toFloat(s[5]) / 255.0F, this.toFloat(s[6]) / 255.0F, this.toFloat(s[7]) / 255.0F, this.toFloat(s[8]) / 255.0F, this.toInt(s[1], 1, 1000)));
               }
            } else if(item.equalsIgnoreCase("TrajectoryParticle")) {
               this.trajectoryParticleName = data.toLowerCase().trim();
               if(this.trajectoryParticleName.equalsIgnoreCase("none")) {
                  this.trajectoryParticleName = "";
               }
            } else if(item.equalsIgnoreCase("TrajectoryParticleStartTick")) {
               this.trajectoryParticleStartTick = this.toInt(data, 0, 10000);
            } else if(item.equalsIgnoreCase("DisableSmoke")) {
               this.disableSmoke = this.toBool(data);
            } else {
               float var10;
               if(item.equalsIgnoreCase("SetCartridge")) {
                  s = data.split("\\s*,\\s*");
                  if(s.length > 0 && s[0].length() > 0) {
                     var10 = s.length >= 2?this.toFloat(s[1]):0.0F;
                     float var11 = s.length >= 3?this.toFloat(s[2]):0.0F;
                     float pt = s.length >= 4?this.toFloat(s[3]):0.0F;
                     float sc = s.length >= 5?this.toFloat(s[4]):1.0F;
                     float gr = s.length >= 6?this.toFloat(s[5]):-0.04F;
                     float bo = s.length >= 7?this.toFloat(s[6]):0.5F;
                     this.cartridge = new MCH_Cartridge(s[0].toLowerCase(), var10, var11, pt, bo, gr, sc);
                  }
               } else if(!item.equalsIgnoreCase("BulletColorInWater") && !item.equalsIgnoreCase("BulletColor") && !item.equalsIgnoreCase("SmokeColor")) {
                  if(item.equalsIgnoreCase("SmokeSize")) {
                     this.smokeSize = this.toFloat(data, 0.0F, 100.0F);
                  } else if(item.equalsIgnoreCase("SmokeNum")) {
                     this.smokeNum = this.toInt(data, 1, 100);
                  } else if(item.equalsIgnoreCase("SmokeMaxAge")) {
                     this.smokeMaxAge = this.toInt(data, 2, 1000);
                  } else if(item.equalsIgnoreCase("DispenseItem")) {
                     s = data.split("\\s*,\\s*");
                     if(s.length >= 2) {
                        this.dispenseDamege = this.toInt(s[1], 0, 100000000);
                     }

                     this.dispenseItem = W_Item.getItemByName(s[0]);
                  } else if(item.equalsIgnoreCase("DispenseRange")) {
                     this.dispenseRange = this.toInt(data, 1, 100);
                  } else if(item.equalsIgnoreCase("Length")) {
                     this.length = (float)this.toInt(data, 1, 300);
                  } else if(item.equalsIgnoreCase("Radius")) {
                     this.radius = (float)this.toInt(data, 1, 1000);
                  } else if(item.equalsIgnoreCase("Target")) {
                     if(data.indexOf("block") >= 0) {
                        this.target = 64;
                     } else {
                        this.target = 0;
                        this.target |= data.indexOf("planes") >= 0?32:0;
                        this.target |= data.indexOf("helicopters") >= 0?16:0;
                        this.target |= data.indexOf("vehicles") >= 0?8:0;
                        this.target |= data.indexOf("tanks") >= 0?8:0;
                        this.target |= data.indexOf("players") >= 0?4:0;
                        this.target |= data.indexOf("monsters") >= 0?2:0;
                        this.target |= data.indexOf("others") >= 0?1:0;
                     }
                  } else if(item.equalsIgnoreCase("MarkTime")) {
                     this.markTime = this.toInt(data, 1, 30000) + 1;
                  } else if(item.equalsIgnoreCase("Recoil")) {
                     this.recoil = this.toFloat(data, 0.0F, 100.0F);
                  } else if(item.equalsIgnoreCase("DamageFactor")) {
                     s = this.splitParam(data);
                     if(s.length >= 2) {
                        Class var13 = null;
                        String var14 = s[0].toLowerCase();
                        if(var14.equals("player")) {
                           var13 = EntityPlayer.class;
                        } else if(!var14.equals("heli") && !var14.equals("helicopter")) {
                           if(var14.equals("plane")) {
                              var13 = MCP_EntityPlane.class;
                           } else if(var14.equals("tank")) {
                              var13 = MCH_EntityTank.class;
                           } else if(var14.equals("vehicle")) {
                              var13 = MCH_EntityVehicle.class;
                           }
                        } else {
                           var13 = MCH_EntityHeli.class;
                        }

                        if(var13 != null) {
                           if(this.damageFactor == null) {
                              this.damageFactor = new MCH_DamageFactor();
                           }

                           this.damageFactor.add(var13, this.toFloat(s[1], 0.0F, 1000000.0F));
                        }
                     }
                  }
               } else {
                  s = data.split("\\s*,\\s*");
                  if(s.length >= 4) {
                     var10 = 0.003921569F;
                     MCH_Color var12 = new MCH_Color(0.003921569F * (float)this.toInt(s[0], 0, 255), 0.003921569F * (float)this.toInt(s[1], 0, 255), 0.003921569F * (float)this.toInt(s[2], 0, 255), 0.003921569F * (float)this.toInt(s[3], 0, 255));
                     if(item.equalsIgnoreCase("BulletColorInWater")) {
                        this.colorInWater = var12;
                     } else {
                        this.color = var12;
                     }
                  }
               }
            }
         }
      }

   }

   public float getDamageFactor(Entity e) {
      return this.damageFactor != null?this.damageFactor.getDamageFactor(e):1.0F;
   }

   public String getWeaponTypeName() {
      return this.type.equalsIgnoreCase("MachineGun1")?"MachineGun":(this.type.equalsIgnoreCase("MachineGun2")?"MachineGun":(this.type.equalsIgnoreCase("Torpedo")?"Torpedo":(this.type.equalsIgnoreCase("CAS")?"CAS":(this.type.equalsIgnoreCase("Rocket")?"Rocket":(this.type.equalsIgnoreCase("ASMissile")?"AS Missile":(this.type.equalsIgnoreCase("AAMissile")?"AA Missile":(this.type.equalsIgnoreCase("TVMissile")?"TV Missile":(this.type.equalsIgnoreCase("ATMissile")?"AT Missile":(this.type.equalsIgnoreCase("Bomb")?"Bomb":(this.type.equalsIgnoreCase("MkRocket")?"Mk Rocket":(this.type.equalsIgnoreCase("Dummy")?"Dummy":(this.type.equalsIgnoreCase("Smoke")?"Smoke":(this.type.equalsIgnoreCase("Smoke")?"Smoke":(this.type.equalsIgnoreCase("Dispenser")?"Dispenser":(this.type.equalsIgnoreCase("TargetingPod")?"Targeting Pod":"")))))))))))))));
   }

   public class RoundItem {

      public final int num;
      public final String itemName;
      public final int damage;
      public ItemStack itemStack;


      public RoundItem(int n, String name, int damage) {
         this.num = n;
         this.itemName = name;
         this.damage = damage;
      }
   }

   public class MuzzleFlash {

      public final float dist;
      public final float size;
      public final float range;
      public final int age;
      public final float a;
      public final float r;
      public final float g;
      public final float b;
      public final int num;


      public MuzzleFlash(float dist, float size, float range, int age, float a, float r, float g, float b, int num) {
         this.dist = dist;
         this.size = size;
         this.range = range;
         this.age = age;
         this.a = a;
         this.r = r;
         this.g = g;
         this.b = b;
         this.num = num;
      }
   }
}
