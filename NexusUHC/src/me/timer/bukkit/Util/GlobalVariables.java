package me.timer.bukkit.Util;

import me.timer.bukkit.MainClass;
import me.timer.bukkit.Options.Languages.Lang;

public class GlobalVariables {

	public static Lang Language = Lang.EN;
	MainClass main;
	private String durationTime, Announcements;
	int num, interval, numAnn, divisore;
	public static boolean secondi;

	public GlobalVariables(MainClass plugin) {
		main = plugin;

		durationTime = plugin.getConfig().getString("total_time");
		Announcements = plugin.getConfig().getString("numberOfAnnouncements");
		numAnn = Integer.parseInt(Announcements);

	}

	/**************************************** OPTIONS CONFIGURATIONS **************************************************/
	/*public boolean isSecondi() {
		return secondi;
	}*/

	public int getNum() {

		if (durationTime.contains("m")) {
			String[] parts = durationTime.split("m");
			if (Integer.parseInt(parts[0]) >= 10)
				num = Integer.parseInt(parts[0]) * 60;
			else {
				main.log.warning("Total time in config.yml is invalid.");
				main.getPluginLoader().disablePlugin(main);
			}
		} else if (durationTime.contains("h")) {
			String[] parts = durationTime.split("h");
			if (Integer.parseInt(parts[0]) <= 12)
				num = Integer.parseInt(parts[0]) * 60 * 60;
			else {
				main.log.warning("Total time in config.yml is invalid.");
				main.getPluginLoader().disablePlugin(main);

			}
		} else {
			main.log.warning("Total time in config.yml is invalid.");
			main.getPluginLoader().disablePlugin(main);

		}

		return num;
	}

	public int getInterval() {

		divisore = (getNum() / numAnn < 1) ? 60 : 1;
		interval = (getNum() / numAnn) / divisore;

		secondi = (divisore == 60) ? false : true;

		return Math.round(interval * 10) / 10;
	}

	public int getIntervalInSeconds() {

		interval = (getNum() / numAnn);

		return interval;
	}

	public int getNumAnn() {

		return numAnn;
	}
}
