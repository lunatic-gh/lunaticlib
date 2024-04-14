package me.lunatic.lunaticlibfabric.ui.screen;

import me.lunatic.lunaticlibfabric.ui.option.AbstractOption;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationScreen extends Screen {

    private final List<AbstractOption> options;

    public ConfigurationScreen(Text title) {
        super(title);
        this.options = new ArrayList<>();
    }

    @Override
    protected void init() {
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        this.renderWidgets(context, mouseX, mouseY, delta);
    }

    private void renderWidgets(DrawContext context, int mouseX, int mouseY, float delta) {
        this.drawables.forEach(drawable -> drawable.render(context, mouseX, mouseY, delta));
    }
}