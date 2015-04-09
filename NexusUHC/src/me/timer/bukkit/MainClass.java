package me.timer.bukkit;

import static org.bukkit.ChatColor.*;

import java.util.logging.Logger;

import me.timer.bukkit.Commands.TimerCommand;
import me.timer.bukkit.Options.Languages;
import me.timer.bukkit.Runnables.CountDown;
import me.timer.bukkit.Runnables.Start;
import me.timer.bukkit.Util.GlobalVariables;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

	CountDown cd;
	Start start;
	GlobalVariables var;
	Languages str;

	public Object prefisso = YELLOW + "Ne" + BLUE + "xu" + RED + "s"
			+ GREEN + "UHC" + GRAY + " >> ";

	public final Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfFile = this.getDescription();

	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		this.getCommand("timer").setExecutor(new TimerCommand(this));
		
		var = new GlobalVariables(this);
		str = new Languages(this);
		log.info("UHCTimer has been enabled");
	}

	@Override
	public void onDisable() {
		getServer().getScheduler().cancelAllTasks();
		log.info("UHCTimer has been disabled");
	}

	public void startCount() {
		start = new Start(this);
		Bukkit.broadcastMessage(prefisso + ""
				+ str.getStrings(var.getLanguage(), str.inizio));
		start.runTaskTimer(this, 0, var.getIntervalInSeconds() * 20L);
	}

	public void startFinalCountDown() {
		cd = new CountDown(this);
		cd.runTaskTimer(this, 0, 20L);
	}

}