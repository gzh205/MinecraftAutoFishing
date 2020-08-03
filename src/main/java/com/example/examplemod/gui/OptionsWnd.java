package com.example.examplemod.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class OptionsWnd extends Screen {

    public static CheckNightVision checkNightVision = new CheckNightVision(20, 20, 150, 20, "enable night vision", false);
    public static CheckBox checkAutoFishing = new CheckBox(20, 50, 150, 20, "enable auto fishing", false);;
    public static OptionsWnd inst;

    public static OptionsWnd getInstance() {
        if (OptionsWnd.inst == null) {
            OptionsWnd.inst = new OptionsWnd(new StringTextComponent("settings"));
        }
        return OptionsWnd.inst;
    }

    @Override
    public void func_231160_c_() {
        if (getMinecraft() != null && getMinecraft().world != null) {
            PlayerEntity player = getMinecraft().player;
            if (getMinecraft().player != null) {
                this.func_230480_a_(checkNightVision);
                this.func_230480_a_(checkAutoFishing);
            }
        }
    }

    private OptionsWnd(ITextComponent titleIn) {
        super(titleIn);
        this.func_231160_c_();
    }

    public void showWnd() {
        this.field_230706_i_.displayGuiScreen(this);
    }

    public void onClose() {
        this.field_230706_i_.displayGuiScreen(null);
    }
}
