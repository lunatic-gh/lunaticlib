package me.lunatic.testmod.ui.screen;

import me.lunatic.lunaticlibfabric.ui.option.impl.BooleanOption;
import me.lunatic.lunaticlibfabric.ui.option.impl.IntegerOption;
import me.lunatic.lunaticlibfabric.ui.option.widget.OptionListWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class TestScreen extends Screen {

    public TestScreen() {
        super(Text.empty());
    }

    @Override
    protected void init() {
        OptionListWidget list = new OptionListWidget(24, 24, this.width - 48, this.height - 48);
        list.addSingleOption(new BooleanOption("Test-Option", true, () -> System.out.println("CLICKED")), OptionListWidget.OptionType.LARGE);
        list.addSingleOption(new IntegerOption(IntegerOption.Type.CYCLING, "Test-Option", 0, 10, 5, () -> System.out.println("CLICKED")), OptionListWidget.OptionType.LARGE);
        list.addSingleOption(new IntegerOption(IntegerOption.Type.SLIDER, "Test-Option", 0, 10, 5, () -> System.out.println("CLICKED")), OptionListWidget.OptionType.LARGE);
        this.addDrawableChild(list);
    }
}
