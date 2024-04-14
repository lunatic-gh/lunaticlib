package me.lunatic.lunaticlibfabric.ui.option.widget;

import me.lunatic.lunaticlibfabric.ui.option.AbstractOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.Nullable;

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

    public void addSingleOption(AbstractOption option, OptionType optionType) {
        super.addEntry(new Entry(option, null, optionType));
    }

    public void addDoubleOption(AbstractOption firstOption, AbstractOption secondOption) {
        super.addEntry(new Entry(firstOption, secondOption, OptionType.SMALL_LEFT));
    }

    public class Entry extends ElementListWidget.Entry<Entry> {
        private final Pair<ClickableWidget, ClickableWidget> widgets;

        public Entry(AbstractOption firstOption, @Nullable AbstractOption secondOption, OptionType optionType) {
            int w = (OptionListWidget.this.getWidth() - 14) / 2;
            int x0 = OptionListWidget.this.getX() + 2;
            int x1 = x0 + w + 4;
            this.widgets = new Pair<>(firstOption.createWidget((optionType == OptionType.SMALL_LEFT || optionType == OptionType.LARGE) ? x0 : x1, 0, optionType != OptionType.LARGE ? w : OptionListWidget.this.getWidth() - 14, 20), secondOption == null ? null : secondOption.createWidget(x1, 0, w, 20));
        }

        @Override
        public List<? extends Selectable> selectableChildren() {
            return widgets.getRight() == null ? List.of(widgets.getLeft()) : List.of(widgets.getLeft(), widgets.getRight());
        }

        @Override
        public List<? extends Element> children() {
            return widgets.getRight() == null ? List.of(widgets.getLeft()) : List.of(widgets.getLeft(), widgets.getRight());
        }

        @Override
        public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            ClickableWidget w = this.widgets.getLeft();
            ClickableWidget w1 = this.widgets.getRight();
            w.setY(y);
            w.render(context, mouseX, mouseY, tickDelta);
            if (w1 != null) {
                w1.setY(y);
                w1.render(context, mouseX, mouseY, tickDelta);
            }
        }
    }

    public enum OptionType {
        SMALL_LEFT, SMALL_RIGHT, LARGE
    }
}
