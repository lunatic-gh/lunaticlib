package me.lunatic.lunaticlibfabric.ui.option.impl;

import me.lunatic.lunaticlibfabric.ui.option.AbstractOption;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

public class IntegerOption extends AbstractOption {

    private final Type type;
    private int value;
    private final int min;
    private final int max;
    private final Function<Integer, String> valueText;

    public IntegerOption(Type type, String key, Function<Integer, String> valueText, int min, int max, int defaultValue, ClickAction onValueUpdate) {
        super(key, onValueUpdate);
        this.type = type;
        this.min = min;
        this.max = max;
        this.value = defaultValue;
        this.valueText = valueText;
    }

    public IntegerOption(Type type, String key, int min, int max, int defaultValue, ClickAction onValueUpdate) {
        this(type, key, String::valueOf, min, max, defaultValue, onValueUpdate);
    }

    @Override
    public ClickableWidget createWidget(int x, int y, int width, int height) {
        if (this.type == Type.CYCLING) {
            return new PressableWidget(x, y, width, height, Text.literal(this.getKey()).append(": ").append(valueText.apply(value))) {
                @Override
                public void onPress() {
                    if (value >= max) {
                        value = min;
                    } else {
                        value++;
                    }
                    clickAction.onClick();
                    this.setMessage(Text.literal(getKey()).append(": ").append(valueText.apply(value)));
                }

                @Override
                protected void appendClickableNarrations(NarrationMessageBuilder builder) {
                }
            };
        } else if (type == Type.SLIDER) {
            return new SliderWidget(x, y, width, height, Text.literal(this.getKey()).append(": ").append(valueText.apply(value)), MathHelper.map(value, min, max, 0.0D, 1.0D)) {
                @Override
                protected void updateMessage() {
                    int i = (int) MathHelper.map(value, 0.0D, 1.0D, min, max);
                    this.setMessage(Text.literal(getKey()).append(": ").append(String.valueOf(i)));
                }

                @Override
                protected void applyValue() {
                    int i = (int) MathHelper.map(value, 0.0D, 1.0D, min, max);
                    if (i != IntegerOption.this.value) {
                        IntegerOption.this.value = i;
                        IntegerOption.this.clickAction.onClick();

                    }
                }
            };
        }
        return null;
    }

    public enum Type {
        CYCLING, SLIDER
    }
}
