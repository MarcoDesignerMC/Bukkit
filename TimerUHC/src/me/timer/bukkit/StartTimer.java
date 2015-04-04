package me.timer.bukkit;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartTimer implements CommandExecutor {

	boolean counting;
	long timer = 0L;

	Object prefisso = Color.YELLOW + "Ne" + Color.BLUE + "xu" + Color.RED + "s"
			+ Color.GREEN + "UHC" + Color.GRAY + " >> ";

	public Object frasi = Color.ORANGE + "Sono passati 20 minuti.";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
// HA I PERMESSI E HA SCRITTO TIMER
		if (sender.hasPermission("canStartTimer")
				&& commandLabel.equalsIgnoreCase("timer")) {

	// NON HA SCRITTO NULLA
			if (args.length == 0) {
				sender.sendMessage(prefisso
						+ "Comando: /timer <start/stop>");

	// HA SCRITTO START
			} else if (args[0] == "start") {
				Bukkit.broadcastMessage(prefisso.toString() + 
						Color.CYAN + "La " + 
						Color.BLUE + "UHC" + 
						Color.CYAN + " è"  + 
						Color.RED  + " iniziata");
				counting = true;

	// HA SCRITTO STOP
			} else if (args[0] == "stop") {
				counting = false;
				timer = 0L;
			}

	// QUANDO IL TIMER E' ATTIVO
			while (counting) {
				Bukkit.broadcastMessage(prefisso.toString() + timer);
				timer++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (timer % 1200 == 0) {
					Bukkit.broadcastMessage(prefisso.toString() + frasi.toString());
				}
			}
	// TERMINA
			if (timer >= 7200) {
				Bukkit.broadcastMessage(prefisso.toString() + Color.RED
						+ "Teletrasporto all'arena deathmatch in 30 secondi...");
				counting = false;
			}

		} else if (!sender.hasPermission("canStartTimer")) {
			sender.sendMessage(Color.RED + "Scusa "
					+ sender.getName().toString()
					+ ", non hai accesso a questo comando.");
		}

		return false;
	}
}