package me.marco.pluginTimer;

import java.awt.Color;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

	public static Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile description = this.getDescription();

	@Override
	public void onEnable() {
		this.getCommand("canStartTimer").setExecutor(new StartTimer());
		MainClass.log.info(description.getName() + " has been" + Color.YELLOW
				+ " enabled");
	}

	@Override
	public void onDisable() {
		MainClass.log.info(description.getName() + " has been" + Color.RED
				+ " disabled");
	}

}
