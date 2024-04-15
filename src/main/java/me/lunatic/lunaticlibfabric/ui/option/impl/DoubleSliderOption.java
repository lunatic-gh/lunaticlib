package me.lunatic.lunaticlibfabric.ui.option.impl;

import me.lunatic.lunaticlibfabric.ui.option.AbstractOption;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class DoubleSliderOption extends AbstractOption<Double> {

    private final Double min;
    private final Double max;
    private final Double step;

    public DoubleSliderOption(String key, Double min, Double max, Double step, Double defaultValue, ValueChangeCallback<Double> valueChangeCallback) {
        super(key, String::valueOf, defaultValue, valueChangeCallback);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public void setValue(Double value) {
        if (value < min && value > max) {
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
                double d = toValue(this.value);
                if (code == GLFW.GLFW_KEY_LEFT && this.sliderFocused) {
                    this.setValue(toProgress(d - step));
                    return true;
                }
                if (code == GLFW.GLFW_KEY_RIGHT && this.sliderFocused) {
                    this.setValue(toProgress(d + step));
                    return true;
                }
                return super.keyPressed(code, scanCode, modifiers);
            }

            @Override
            protected void setValueFromMouse(double mouseX) {
                double d = (max - min) / step;
                this.setValue(Math.round((((mouseX - (double) (this.getX() + 4)) / (double) (this.width - 8)) - min) * d) / d + min);
            }

            @Override
            protected void applyValue() {
                double d = toValue(this.value);
                if (d != getValue()) {
                    DoubleSliderOption.this.setValue(d);
                }
            }
        };
    }

    private double toProgress(double value) {
        return MathHelper.map(value, min, max, 0.0D, 1.0D);
    }

    private double toValue(double progress) {
        return MathHelper.map(progress, 0.0D, 1.0D, min, max);
    }
}
