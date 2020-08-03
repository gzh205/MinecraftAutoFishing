package com.example.examplemod.FishingCore;

import net.minecraft.client.gui.widget.button.CheckboxButton;

public class CheckBox extends CheckboxButton {
    public boolean enable;

    public CheckBox(int p_i51140_1_, int p_i51140_2_, int p_i51140_3_, int p_i51140_4_, String p_i51140_5_, boolean p_i51140_6_) {
        super(p_i51140_1_, p_i51140_2_, p_i51140_3_, p_i51140_4_, p_i51140_5_, p_i51140_6_);
        enable = false;
    }
    @Override
    public void onPress(){
        super.onPress();
        this.enable = !enable;
    }
}
