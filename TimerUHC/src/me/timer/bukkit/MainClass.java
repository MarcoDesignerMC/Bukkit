package me.timer.bukkit;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.fusesource.jansi.Ansi.Color;

public final class MainClass extends JavaPlugin {

	public final Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfFile = this.getDescription();

	@Override
	public void onEnable() {
		this.getCommand("timer").setExecutor(new StartTimer());

		this.log.info(pdfFile.getName() + " has been" + Color.YELLOW
				+ " enabled");
	}

	public void onDisable() {
		this.log.info(pdfFile.getName() + " has been" + Color.RED
				+ " disabled");
	}

}
