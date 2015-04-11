package me.timer.bukkit.Options;

import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.ITALIC;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.YELLOW;

import java.util.HashMap;

import me.timer.bukkit.MainClass;
import me.timer.bukkit.Util.GlobalVariables;

import org.bukkit.entity.Player;

public class Languages {
	GlobalVariables var;
	MainClass main;

	public static enum Lang {
		IT, EN,
	}

	/*
	 * 
	 * VARIABLES
	 */
	public  HashMap<Lang, Object> inizio = new HashMap<Lang, Object>();
	public  HashMap<Lang, Object> minutiTrascorsi = new HashMap<Lang, Object>();
	public  HashMap<Lang, Object> fra10secondi = new HashMap<Lang, Object>();
	public  HashMap<Lang, Object> teletrasporto = new HashMap<Lang, Object>();
	public  HashMap<Lang, Object> fermata = new HashMap<Lang, Object>();

	public Languages(MainClass plugin) {
		main = plugin;
		var = new GlobalVariables(plugin);

		inizio.put(Lang.IT, RED + "La UHC è iniziata, vinca il team migliore.");
		inizio.put(Lang.EN, RED + "The UHC is started, the best team wins.");

		fermata.put(Lang.IT, YELLOW + (ITALIC + "La UHC è stata fermata."));
		fermata.put(Lang.EN, YELLOW + (ITALIC + "The UHC has been stopped."));
		String itMinuti_o_secondi = (!GlobalVariables.secondi) ? " minuti."
				: " secondi.";
		String enMinuti_o_secondi = (!GlobalVariables.secondi) ? " minutes"
				: " seconds.";
		minutiTrascorsi.put(Lang.IT, RED + "Sono passati " + var.getInterval()
				+ itMinuti_o_secondi);
		minutiTrascorsi.put(Lang.EN, RED + "" + var.getInterval()
				+ enMinuti_o_secondi + " have passed.");

		fra10secondi.put(Lang.IT, RED
				+ "Fra 10 secondi verrete trasportati tutti all'arena, "
				+ (BOLD + "tenetevi pronti."));
		fra10secondi.put(Lang.EN, RED
				+ "In 10 seconds you will be transported at the arena, "
				+ (BOLD + "get ready."));
		teletrasporto.put(Lang.IT,
				(RED + (BOLD + "Teletrasporto in corso, preparati a morire.")));
		teletrasporto.put(Lang.EN,
				(RED + (BOLD + "Teleport in progress, get ready to die.")));
	}

	public Object getStrings(Lang lang, HashMap<Lang, Object> hashmap) {
		return hashmap.get(lang);
	}

	public Object getStringCountdown(int part, Lang lang) {
		Object[] stringCount = { RED + "Mancano ", RED + " secondi.", RED + "",
				RED + " seconds." };

		int string = 0;
		switch (lang) {
		case IT:
			string = (part == 1) ? 0 : 1;
			break;
		case EN:
			string = (part == 1) ? 2 : 3;
			break;
		}
		return stringCount[string];
	}

	public Object noPermissions(Player p, Lang lang) {
		Object[] array = {
				RED + "Scusa " + p.getName()
						+ ", non hai accesso a questo comando.",
				RED
						+ "Sorry "
						+ p.getName()
						+ ", you don't have enough permission to perform this command." };
		int a = (lang == Lang.IT) ? 0 : 1;
		return array[a];
	}
}
