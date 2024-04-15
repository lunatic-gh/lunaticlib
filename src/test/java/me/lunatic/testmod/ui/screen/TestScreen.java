package me.lunatic.testmod.ui.screen;

import me.lunatic.lunaticlibfabric.ui.option.impl.BooleanCyclingOption;
import me.lunatic.lunaticlibfabric.ui.option.impl.DoubleSliderOption;
import me.lunatic.lunaticlibfabric.ui.option.impl.IntegerSliderOption;
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
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")), OptionListWidget.OptionType.LARGE);
        list.addSingleOption(new IntegerSliderOption("Test-Option", 0, 10, 5, val -> System.out.println("CLICKED")), OptionListWidget.OptionType.LARGE);
        list.addSingleOption(new DoubleSliderOption("Test-Option", 0.0D, 10.0D, 0.5D, 5.0D, val -> System.out.println("CLICKED")), OptionListWidget.OptionType.LARGE);
        this.addDrawableChild(list);
    }
}
