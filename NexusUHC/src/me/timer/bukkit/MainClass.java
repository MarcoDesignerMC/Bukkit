package me.timer.bukkit;

import static org.bukkit.ChatColor.*;

import java.util.logging.Logger;

import me.timer.bukkit.Options.Languages;
import me.timer.bukkit.Options.Languages.Lang;
import me.timer.bukkit.Runnables.CountDown;
import me.timer.bukkit.Runnables.Start;
import me.timer.bukkit.Util.GlobalVariables;
import me.timer.bukkit.Util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

	CountDown cd;
	Start start;
	GlobalVariables var;
	Languages str;

	public static Object prefisso = YELLOW + "Ne" + BLUE + "xu" + RED + "s"
			+ GREEN + "UHC" + GRAY + " >> ";

	public final Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfFile = this.getDescription();

	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		var = new GlobalVariables();
		str = new Languages();
		log.info("UHCTimer has been enabled");
	}

	@Override
	public void onDisable() {
		getServer().getScheduler().cancelAllTasks();
		log.info("UHCTimer has been disabled");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player p = (Player) sender;
		/*
		 * 
		 * HA I PERMESSI E HA SCRITTO TIMER
		 */
		if (sender.hasPermission("canStartTimer")
				&& commandLabel.equalsIgnoreCase("timer")
				&& sender instanceof Player) {

			// NON HA SCRITTO NULLA
			if (args.length == 0) {
				p.sendMessage(prefisso
						+ (GREEN + "Usage: /timer <start/stop>/<lang>"));
				// HA SCRITTO START
			} else if (args[0].equalsIgnoreCase("start")) {
				startCount();

				// HA SCRITTO STOP
			} else if (args[0].equalsIgnoreCase("stop")) {
				Start.counter = 0;
				Bukkit.broadcastMessage(prefisso + ""
						+ str.getStrings(var.getLanguage(), str.fermata));
				Util.playSound(Sound.ITEM_BREAK, 1, 0);
				getServer().getScheduler().cancelAllTasks();
			} else if (args[0].equalsIgnoreCase("lang")
					|| args[0].equalsIgnoreCase("language")) {
				if (args.length == 1) {
					p.sendMessage(prefisso + "" + GREEN
							+ "Usage: /timer lang <IT/EN>");
				} else if (args[1].equalsIgnoreCase("it")) {
					p.sendMessage(prefisso + "" + YELLOW
							+ (ITALIC + "Lingua settata in Italiano."));
					var.setLanguage(Lang.IT);
				} else if (args[1].equalsIgnoreCase("en")) {
					var.setLanguage(Lang.EN);
					p.sendMessage(prefisso + "" + YELLOW
							+ (ITALIC + "Language changed to English."));
				}
			}
			/*
			 * 
			 * NON HA I PERMESSI
			 */
		} else if (!p.hasPermission("canStartTimer")) {
			p.sendMessage("" + str.noPermissions(p, var.getLanguage()));
		}
		return false;
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