package me.timer.bukkit.Runnables;

import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.YELLOW;
import me.timer.bukkit.MainClass;
import me.timer.bukkit.Listener.MainListener;
import me.timer.bukkit.Options.Languages;
import me.timer.bukkit.Util.GlobalVariables;
import me.timer.bukkit.Util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDown extends BukkitRunnable {

	int count = 10;

	MainClass plugin;
	GlobalVariables var;
	Languages str;
	Start start;
	MainListener listener;

	// MainListener listener;

	public CountDown(MainClass plugin) {
		this.plugin = plugin;
		var = new GlobalVariables(plugin);
		str = new Languages(plugin);
		listener = new MainListener(plugin);
	}

	@Override
	public void run() {
		if (count > 0) {
			if (count % 5 == 0 && count > 0) {

				Bukkit.broadcastMessage(plugin.prefisso + ""
						+ str.getStringCountdown(1, GlobalVariables.Language)
						+ YELLOW + count
						+ str.getStringCountdown(2, GlobalVariables.Language));
			}
			if (count <= 3) {
				Bukkit.broadcastMessage(plugin.prefisso
						+ str.getStringCountdown(1, GlobalVariables.Language)
								.toString()
						+ BOLD
						+ count
						+ str.getStringCountdown(2, GlobalVariables.Language)
								.toString());

				Util.playSound(Sound.NOTE_PLING, 0.6f, 2);
			}

		} else {
			Bukkit.broadcastMessage(plugin.prefisso
					+ ""
					+ str.getStrings(GlobalVariables.Language,
							str.teletrasporto));
			Util.playSound(Sound.BLAZE_DEATH, 1, 0);
			start = new Start(plugin);
			start.setCounter(0);
			/*
			 * if (Bukkit.getOnlinePlayers().size() == 1) { MainListener.inGame
			 * = false; MainListener.end = true; MainListener.starting = false;
			 * 
			 * Bukkit.broadcastMessage(plugin.prefisso +
			 * str.getStrings(GlobalVariables.Language,
			 * str.UHCfinita).toString() + MainListener.winner); for (int i = 0;
			 * i < listener.bannedIPs.getItemCount(); i++) {
			 * Bukkit.unbanIP(listener.bannedIPs.getItem(i)); } }
			 */
			this.cancel();

		}
		count--;
	}
}
