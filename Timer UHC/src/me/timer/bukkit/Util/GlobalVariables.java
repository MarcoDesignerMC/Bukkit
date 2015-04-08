package me.timer.bukkit.Util;

import me.timer.bukkit.Options.Languages.Lang;

public class GlobalVariables {
	
	private static Lang Language = Lang.EN;

	public Lang getLanguage() {
		return Language;
	}

	public void setLanguage(Lang language) {
		Language = language;
	}
	
}
