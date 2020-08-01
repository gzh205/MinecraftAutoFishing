package com.example.examplemod.gui;

import com.example.examplemod.FishingCore.AutoFishing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.lang.reflect.Field;

public class OptionsWnd extends Screen {

    public CheckboxButton checkNightVision;
    Field checked;
    public static OptionsWnd inst;

    public static OptionsWnd getInstance() {
        if (OptionsWnd.inst == null) {
            OptionsWnd.inst = new OptionsWnd(new StringTextComponent("settings"));
        }
        return OptionsWnd.inst;
    }

    @Override
    public void init() {
        if (getMinecraft() != null && getMinecraft().world != null) {
            PlayerEntity player = getMinecraft().player;
            if (getMinecraft().player != null) {
                this.renderBackground();
                this.checkNightVision = new CheckboxButton(20, 50, 150, 20, "enable night vision", false);
                this.addButton(this.checkNightVision);

                try {
                    this.checked = CheckboxButton.class.getDeclaredField("field_212943_a");
                    this.checked.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    AutoFishing.LOGGER.info(e.getMessage());
                }
            }
        }
    }

    public boolean isChecked(CheckboxButton check) {
        if(check==null) return false;
        boolean result = false;
        try {
            result = checked.getBoolean(check);
        }catch(IllegalAccessException e){
            AutoFishing.LOGGER.info(e.getMessage());
        }
        return result;
    }


    private OptionsWnd(ITextComponent titleIn) {
        super(titleIn);
        this.init();
    }

    public void showWnd() {
        this.minecraft.displayGuiScreen(this);
    }

    public void onClose() {
        this.minecraft.displayGuiScreen(null);
    }

}
