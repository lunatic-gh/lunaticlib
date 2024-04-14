package me.lunatic.lunaticlibfabric.ui.option.impl;

import me.lunatic.lunaticlibfabric.ui.option.AbstractOption;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;

import java.util.function.Function;

public class BooleanOption extends AbstractOption {

    private boolean value;
    private final Function<Boolean, String> valueText;

    public BooleanOption(String key, Function<Boolean, String> valueText, boolean defaultValue, ClickAction clickAction) {
        super(key, clickAction);
        this.value = defaultValue;
        this.valueText = valueText;
    }

    public BooleanOption(String key, boolean defaultValue, ClickAction clickAction) {
        this(key, val -> val ? "On" : "Off", defaultValue, clickAction);
    }

    @Override
    public PressableWidget createWidget(int x, int y, int width, int height) {
        return new PressableWidget(x, y, width, height, Text.literal(this.getKey()).append(": ").append(this.valueText.apply(this.value))) {
            @Override
            public void onPress() {
                value = !value;
                clickAction.onClick();
                this.setMessage(Text.literal(getKey()).append(": ").append(valueText.apply(value)));
            }

            @Override
            protected void appendClickableNarrations(NarrationMessageBuilder builder) {
            }
        };
    }
}
