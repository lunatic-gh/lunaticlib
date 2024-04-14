package me.lunatic.lunaticlibfabric.ui.option;

import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;

public abstract class AbstractOption {

    private String key;
    protected final ClickAction clickAction;

    protected AbstractOption(String key, ClickAction clickAction) {
        this.key = key;
        this.clickAction = clickAction;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ClickableWidget createWidget(int x, int y, int width, int height) {
        return new PressableWidget(x, y, width, height, Text.literal(this.key)) {
            @Override
            public void onPress() {
                AbstractOption.this.clickAction.onClick();
            }

            @Override
            protected void appendClickableNarrations(NarrationMessageBuilder builder) {
            }
        };
    }

    public interface ClickAction {
        void onClick();
    }
}
