package me.timer.bukkit.Util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Util {

	public static void playSound(Sound s,float volume, float pitch){
		for(Player p : Bukkit.getOnlinePlayers()){
			p.playSound(p.getLocation(), s, volume, pitch);
		}
	}
	
}
