package me.lunatic.lunaticlibfabric.ui.option.impl;

import me.lunatic.lunaticlibfabric.ui.option.AbstractOption;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class IntegerSliderOption extends AbstractOption<Integer> {

    private final Integer min;
    private final Integer max;

    public IntegerSliderOption(String key, Integer min, Integer max, Integer defaultValue, ValueChangeCallback<Integer> valueChangeCallback) {
        super(key, String::valueOf, defaultValue, valueChangeCallback);
        this.min = min;
        this.max = max;
    }

    @Override
    public void setValue(Integer value) {
        if (value < min || value > max) {
            return;
        }
        super.setValue(value);
    }


    @Override
    public ClickableWidget createWidget(int x, int y, int width, int height) {
        return new SliderWidget(x, y, width, height, Text.literal(this.getKey()).append(": ").append(this.getValueText().apply(this.getValue())), this.toProgress(this.value)) {
            @Override
            protected void updateMessage() {
                this.setMessage(Text.literal(getKey()).append(": ").append(getValueText().apply(getValue())));
            }

            @Override
            public boolean keyPressed(int code, int scanCode, int modifiers) {
                if (code == GLFW.GLFW_KEY_LEFT && this.sliderFocused) {
                    this.setValue(toProgress(toValue(this.value) - 1));
                    return true;
                }
                if (code == GLFW.GLFW_KEY_RIGHT && this.sliderFocused) {
                    this.setValue(toProgress(toValue(this.value) + 1));
                    return true;
                }
                return super.keyPressed(code, scanCode, modifiers);
            }


            @Override
            protected void setValueFromMouse(double mouseX) {
                double d = max - min;
                this.setValue(Math.round((((mouseX - (double) (this.getX() + 4)) / (double) (this.width - 8)) - min) * d) / d + min);
            }

            @Override
            protected void applyValue() {
                int i = toValue(this.value);
                if (i != getValue()) {
                    IntegerSliderOption.this.setValue(i);
                }
            }
        };
    }

    private double toProgress(int value) {
        return MathHelper.map(value, min, max, 0.0D, 1.0D);
    }

    private int toValue(double progress) {
        return (int) MathHelper.map(progress, 0.0D, 1.0D, min, max);
    }
}
