package me.lunatic.lunaticlibfabric.ui.option.widget;

import me.lunatic.lunaticlibfabric.ui.option.AbstractOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;

import java.util.List;

public class OptionListWidget extends ElementListWidget<OptionListWidget.Entry> {

    public OptionListWidget(int x, int y, int width, int height) {
        super(MinecraftClient.getInstance(), 0, 0, 0, 24);
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setRenderBackground(false);
    }

    @Override
    public int getRowWidth() {
        return this.width;
    }

    @Override
    protected int getScrollbarPositionX() {
        return (this.getX() + this.getWidth()) - 6;
    }

    public void addSingleOption(AbstractOption<?> option) {
        super.addEntry(new Entry(option));
    }

    //TODO: For the love of god, i cannot figure out why it doesnt properly scale with 2 options... maybe it's a division issue with specific resolutions?
    //public void addDoubleOption(AbstractOption<?> firstOption, AbstractOption<?> secondOption) {
    //    super.addEntry(new Entry(firstOption, secondOption, SingleOptionType.SMALL_LEFT));
    //}

    public class Entry extends ElementListWidget.Entry<Entry> {
        private final ClickableWidget widget;

        public Entry(AbstractOption<?> option) {
            int w = getWidth() - 10;
            int centerX = getX() + (w / 2);
            this.widget = option.createWidget(centerX - (w / 2) + 2, 0, w, 20);
        }

        @Override
        public List<? extends Selectable> selectableChildren() {
            return List.of(widget);
        }

        @Override
        public List<? extends Element> children() {
            return List.of(widget);
        }

        @Override
        public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            widget.setY(y);
            widget.render(context, mouseX, mouseY, tickDelta);
        }
    }
}
