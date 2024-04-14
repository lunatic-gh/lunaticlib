package me.lunatic.testmod.mixin;

import me.lunatic.testmod.ui.screen.TestScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {

    public MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Test-Screen"), button -> this.client.setScreen(new TestScreen())).dimensions(4, this.height - 24, 75, 20).build());
    }
}
