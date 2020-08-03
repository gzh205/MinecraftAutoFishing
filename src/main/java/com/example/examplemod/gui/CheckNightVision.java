package com.example.examplemod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.text.StringTextComponent;

public class CheckNightVision extends CheckboxButton {
    protected boolean enable;
    public CheckNightVision(int p_i51140_1_, int p_i51140_2_, int p_i51140_3_, int p_i51140_4_, String p_i51140_5_, boolean p_i51140_6_) {
        super(p_i51140_1_, p_i51140_2_, p_i51140_3_, p_i51140_4_, new StringTextComponent(p_i51140_5_), p_i51140_6_);
        this.enable = false;
    }

    @Override
    public void func_230930_b_(){//onPress
        super.func_230930_b_();
        enable = !enable;
        if(enable) Minecraft.getInstance().gameSettings.gamma = 255;
        else Minecraft.getInstance().gameSettings.gamma = 1;
    }
}
