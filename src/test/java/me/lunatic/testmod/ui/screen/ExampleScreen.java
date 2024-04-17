package me.lunatic.testmod.ui.screen;

import me.lunatic.lunaticlibfabric.ui.option.impl.BooleanCyclingOption;
import me.lunatic.lunaticlibfabric.ui.option.widget.OptionListWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ExampleScreen extends Screen {

    public ExampleScreen() {
        super(Text.empty());
    }

    @Override
    protected void init() {
        OptionListWidget list = new OptionListWidget(0, 0, this.width, this.height);
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        list.addSingleOption(new BooleanCyclingOption("Test-Option", true, val -> System.out.println("CLICKED")));
        this.addDrawableChild(list);
    }
}
