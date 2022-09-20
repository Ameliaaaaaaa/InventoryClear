package me.amelia.inventoryclear;

import me.amelia.inventoryclear.Commands.ClearCommand;
import me.amelia.inventoryclear.GUIs.ConfirmGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public ConfirmGUI confirmGUI;

    @Override
    public void onEnable() {
        this.setupConfig();

        this.confirmGUI = new ConfirmGUI(this);

        this.registerCommands();
    }

    @Override
    public void onDisable() {}

    public void setupConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    public void registerCommands() {
        this.getCommand("clear").setExecutor(new ClearCommand(this));
    }

    public ConfirmGUI getConfirmGUI() {
        return this.confirmGUI;
    }
}
