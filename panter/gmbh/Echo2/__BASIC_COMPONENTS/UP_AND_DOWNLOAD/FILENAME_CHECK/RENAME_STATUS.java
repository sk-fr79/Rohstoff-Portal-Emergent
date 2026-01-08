package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_ResourceIcon;

public enum RENAME_STATUS{
	UNCORRECT("#--#Uncorrected",  new Color(128,128,128), E2_ResourceIcon.get_RI("empty.png")),
	OK("Korrigiert", 	new Color(0,128,0), 	E2_ResourceIcon.get_RI("led_green.png")),
	KO("Problem", 		new Color(255,0,0), 	E2_ResourceIcon.get_RI("led_red.png")),
	WARNING("Datei", 		new Color(255,0,0), 	E2_ResourceIcon.get_RI("led_orange.png"));
	
	private String lblText;
	private Color textColor;
	private E2_ResourceIcon icon;
	
	private RENAME_STATUS(String text, Color col,E2_ResourceIcon icon) {
		this.lblText = text;
		this.textColor = col;
		this.icon = icon;
	}

	public String getLblText() {
		return lblText;
	}

	public Color getTextColor() {
		return textColor;
	}

	public E2_ResourceIcon getIcon() {
		return icon;
	}
}