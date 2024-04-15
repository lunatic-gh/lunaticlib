package me.lunatic.lunaticlibfabric.ui.option.impl;

import me.lunatic.lunaticlibfabric.ui.option.AbstractOption;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;

import java.util.function.Function;

public class BooleanCyclingOption extends AbstractOption<Boolean> {

    public BooleanCyclingOption(String key, Function<Boolean, String> valueText, boolean defaultValue, ValueChangeCallback<Boolean> valueChangeCallback) {
        super(key, valueText, defaultValue, valueChangeCallback);
    }

    public BooleanCyclingOption(String key, boolean defaultValue, ValueChangeCallback<Boolean> valueChangeCallback) {
        this(key, val -> val ? "On" : "Off", defaultValue, valueChangeCallback);
    }

    @Override
    public ClickableWidget createWidget(int x, int y, int width, int height) {
        return new PressableWidget(x, y, width, height, Text.literal(this.getKey()).append(": ").append(BooleanCyclingOption.this.getValueText().apply(BooleanCyclingOption.this.getValue()))) {
            @Override
            public void onPress() {
                boolean b = !BooleanCyclingOption.this.getValue();
                BooleanCyclingOption.this.setValue(b);
                this.setMessage(Text.literal(BooleanCyclingOption.this.getKey()).append(": ").append(BooleanCyclingOption.this.getValueText().apply(b)));
            }

            @Override
            protected void appendClickableNarrations(NarrationMessageBuilder builder) {
            }
        };
    }
}
