package com.example.examplemod.FishingCore;

import com.example.examplemod.gui.OptionsWnd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

public class AutoFishing {
    public static Minecraft mc = Minecraft.getInstance();
    public static final Logger LOGGER = LogManager.getLogger();
    //public ArrayList<FishingBobberEntity> list;
    public KeyBinding keyCode;
    public Method rightClick;
    public Field status;
    public Field fishingDelay;
    public boolean hooked;

    public AutoFishing() {
        //this.list = new ArrayList<>();
        MinecraftForge.EVENT_BUS.register(this);
        keyCode = new KeyBinding("fishing", GLFW.GLFW_KEY_H, "fishing.config.troggle");
        ClientRegistry.registerKeyBinding(keyCode);
        OptionsWnd.getInstance();
        Method[] methods = mc.getClass().getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals("func_147121_ag")) {//rightClickMouse
                this.rightClick = m;
                this.rightClick.setAccessible(true);
            }
        }
        Field[] fields = FishingBobberEntity.class.getDeclaredFields();
        for (Field f : fields) {
            if (f.getName().equals("field_190627_av")) {//currentState
                this.status = f;
                this.status.setAccessible(true);
            }
            if (f.getName().equals("field_146040_ay")) {//ticksCaughtDelay
                this.fishingDelay = f;
                this.fishingDelay.setAccessible(true);
            }
        }
        if (this.rightClick == null) {
            LOGGER.info("================ do not found method func_147121_ag===================");
        }
        if (this.status == null) {
            LOGGER.info("======================= do not found field field_190627_av========================");
        }
        if (this.fishingDelay == null) {
            LOGGER.info("======================= do not found field field_146040_ay========================");
        }
        hooked = false;
    }

    public void onHKeyPressed() {
        mc.displayGuiScreen(OptionsWnd.getInstance());
    }

    @SubscribeEvent
    public void onSplash(net.minecraftforge.client.event.sound.PlaySoundEvent event) throws IllegalAccessException {
        if (event.getName().equals("entity.fishing_bobber.splash")) {
            if (mc.player.fishingBobber != null) {
                if (status.get(mc.player.fishingBobber).toString().equals("BOBBING")) {
                    if (fishingDelay.getInt(mc.player.fishingBobber) <= 0 && !hooked) {
                        hooked = true;
                        LOGGER.info("======================fish get=======================");
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    rightClick.invoke(mc);
                                    Thread.sleep(500);
                                    if (!(mc.player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof FishingRodItem)) {
                                        for (ItemStack s : mc.player.inventory.mainInventory) {
                                            if (s.getItem() instanceof FishingRodItem) {
                                                mc.player.setHeldItem(Hand.MAIN_HAND, s);
                                            }
                                        }
                                    }
                                    rightClick.invoke(mc);
                                    Thread.sleep(500);
                                    hooked = false;
                                } catch (IllegalAccessException e) {
                                    LOGGER.info(e.getStackTrace());
                                } catch (InterruptedException e) {
                                    LOGGER.info(e.getStackTrace());
                                } catch (InvocationTargetException e) {
                                    LOGGER.info(e.getStackTrace());
                                }
                            }
                        }, 500);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void Tick(TickEvent event) {
        if (event != null && mc.world != null && mc.player != null) {
            if (keyCode != null && keyCode.isPressed()) {
                LOGGER.info("======================H key pressed=======================");
                onHKeyPressed();
            }
        }
    }

}
