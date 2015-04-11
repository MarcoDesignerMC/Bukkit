package me.timer.bukkit.Runnables;

import me.timer.bukkit.MainClass;
import me.timer.bukkit.Options.Languages;
import me.timer.bukkit.Util.GlobalVariables;
import me.timer.bukkit.Util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class Start extends BukkitRunnable {

	private int counter = 0;

	MainClass plugin;
	GlobalVariables var;
	Languages str;
	CountDown cd;

	public Start(MainClass plugin) {
		this.plugin = plugin;
		var = new GlobalVariables(plugin);
		str = new Languages(plugin);
	}

	@Override
	public void run() {
		if (counter < var.getNumAnn() && counter > 0) {
			Bukkit.broadcastMessage(plugin.prefisso + ""
					+ str.getStrings(GlobalVariables.Language, str.minutiTrascorsi));
			Util.playSound(Sound.NOTE_PLING, 0.6f, 2);

		} else if (counter >= var.getNumAnn()) {
			Bukkit.broadcastMessage(plugin.prefisso + ""
					+ str.getStrings(GlobalVariables.Language, str.fra10secondi));
			Util.playSound(Sound.AMBIENCE_THUNDER, 1, 0);
			if (counter >= var.getNumAnn())
				cd = new CountDown(plugin);
				cd.runTaskTimer(plugin, 0, 20L);
			this.cancel();
		}
		counter++;
	}

	public int getCounter() {
		return counter;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
}
