package me.lunatic.lunaticlibfabric.ui.option;

import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;

import java.util.function.Function;

/**
 * The Base of all options.
 * If you want to create a custom option, you want to extend this, and override the method {@link #createWidget(int, int, int, int)} to create a widget that fits your option type.
 * If you don't do this, it will create a basic button widget, similar to vanilla options.
 * In some cases, you also want to override {@link #setValue(Object)}, for ex. when you want to only change the value when some condition is met.
 *
 * @param <T> The Type of the Option-Value
 */
public abstract class AbstractOption<T> {

    private final String key;
    protected T value;
    protected final ValueChangeCallback<T> valueChangeCallback;
    private final Function<T, String> valueText;

    protected AbstractOption(String key, Function<T, String> valueText, T defaultValue, ValueChangeCallback<T> valueChangeCallback) {
        this.key = key;
        this.value = defaultValue;
        this.valueChangeCallback = valueChangeCallback;
        this.valueText = valueText;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        this.valueChangeCallback.onValueChange(value);
    }

    public Function<T, String> getValueText() {
        return valueText;
    }

    public ClickableWidget createWidget(int x, int y, int width, int height) {
        return new PressableWidget(x, y, width, height, Text.literal(this.key)) {
            @Override
            public void onPress() {
                AbstractOption.this.valueChangeCallback.onValueChange(value);
            }

            @Override
            protected void appendClickableNarrations(NarrationMessageBuilder builder) {
            }
        };
    }

    public interface ValueChangeCallback<T> {
        void onValueChange(T value);
    }
}
