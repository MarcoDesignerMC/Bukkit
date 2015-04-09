package me.timer.bukkit.Util;

import me.timer.bukkit.MainClass;
import me.timer.bukkit.Options.Languages.Lang;

public class GlobalVariables {

	private Lang Language = Lang.EN;
	MainClass main;
	private String durationTime, Announcements;
	int num, interval, numAnn, divisore;
	boolean secondi;

	public GlobalVariables(MainClass plugin) {
		main = plugin;

		durationTime = plugin.getConfig().getString("total_time");
		Announcements = plugin.getConfig().getString("numberOfAnnouncements");
		numAnn = Integer.parseInt(Announcements);

	}

	public Lang getLanguage() {
		return Language;
	}

	public void setLanguage(Lang language) {
		Language = language;
	}

	/**************************************** OPTIONS CONFIGURATIONS **************************************************/
	public boolean isSecondi() {
		return secondi;
	}

	public int getNum() {

		if (durationTime.contains("m")) {
			String[] parts = durationTime.split("m");
			if (Integer.parseInt(parts[0]) >= 10)
				num = Integer.parseInt(parts[0]) * 60;
			else
				main.log.warning("Total time in config.yml is invalid.");

		} else if (durationTime.contains("h")) {
			String[] parts = durationTime.split("h");
			if (Integer.parseInt(parts[0]) <= 12)
				num = Integer.parseInt(parts[0]) * 60 * 60;
			else
				main.log.warning("Total time in config.yml is invalid.");
		} else {
			main.log.warning("Total time in config.yml is invalid.");
		}

		return num;
	}

	public int getInterval() {

		divisore = (durationTime.contains("h")) ? 60 : 1;
		interval = (getNum() / numAnn) / divisore;

		secondi = (divisore == 60) ? false : true;

		return interval;
	}

	public int getIntervalInSeconds() {

		interval = (getNum() / numAnn);

		return interval;
	}

	public int getNumAnn() {

		return numAnn;
	}
}
