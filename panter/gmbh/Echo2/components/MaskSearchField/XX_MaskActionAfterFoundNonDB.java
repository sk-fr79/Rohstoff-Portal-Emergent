package panter.gmbh.Echo2.components.MaskSearchField;

import panter.gmbh.indep.exceptions.myException;

public abstract class XX_MaskActionAfterFoundNonDB
{
	
	/*
	 * bAfterAction wird nur dann true, wenn ein knopf gedruckt wird,
	 * beim laden der maske wird bAfterAction=false
	 */
	public abstract void doMaskSettingsAfterValueWrittenInMaskField(
			String cMaskValue, MyE2_MaskSearchField oSearchField, boolean bAfterAction) throws myException;
	
}
