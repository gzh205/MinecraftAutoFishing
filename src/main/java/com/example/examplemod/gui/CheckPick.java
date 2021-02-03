package com.example.examplemod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.StringTextComponent;

import java.util.Timer;
import java.util.TimerTask;

public class CheckPick extends CheckboxButton {
    protected boolean enable;
    public CheckPick(int p_i51140_1_, int p_i51140_2_, int p_i51140_3_, int p_i51140_4_, String p_i51140_5_, boolean p_i51140_6_) {
        super(p_i51140_1_, p_i51140_2_, p_i51140_3_, p_i51140_4_, new StringTextComponent(p_i51140_5_), p_i51140_6_);
        this.enable = false;
    }

    @Override
    public void func_230930_b_(){//onPress
        super.func_230930_b_();
        enable = !enable;
        if(enable){
            new Timer().schedule(new TimerTask() {
                Minecraft mc = Minecraft.getInstance();
                @Override
                public void run() {
                    if(!enable)
                        this.cancel();
                    PlayerEntity player = mc.player;
                    mc.playerController.onPlayerDamageBlock(new BlockPos(player.getPosX(),player.getPosY(),player.getPosZ()), Direction.DOWN);
                }
            },500,500);
        }
    }
}
