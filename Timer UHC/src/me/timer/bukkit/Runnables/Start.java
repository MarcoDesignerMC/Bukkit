package me.timer.bukkit.Runnables;

import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.RED;
import me.timer.bukkit.MainClass;
import me.timer.bukkit.Util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class Start extends BukkitRunnable {

	public static int counter = 0;
	MainClass plugin;
	int spazioDiTempo = 4; // 20 * 60;

	CountDown cd;

	@Override
	public void run() {
		if (counter < 6 && counter > 0) {
			Bukkit.broadcastMessage(MainClass.prefisso
					+ (RED + "Sono passati 20 minuti."));
			Util.playSound(Sound.NOTE_PLING, 0.6f, 2);

		} else if (counter >= 6) {
			Bukkit.broadcastMessage(MainClass.prefisso
					+ (RED
							+ "Fra 10 secondi verrete trasportati tutti all'arena, " + (BOLD + "tenetevi pronti.")));
			Util.playSound(Sound.AMBIENCE_THUNDER, 1, 0);
			if (Start.counter >= 6)
				plugin.startFinalCountDown();
			this.cancel();
		}
		counter++;
	}

	public Start(MainClass plugin) {
		this.plugin = plugin;
	}
}
