package panter.gmbh.Echo2.components.DB.MaskSearchField;


import panter.gmbh.indep.exceptions.myException;

public abstract class XX_MaskActionAfterFound
{
	
	/**
	 * 
	 * @param cMaskValue     			gefundener datenbankwert
	 * @param oSearchField   			die eigene Suchkomponente
	 * @param bAfterAction   			wenn die methode gestartet wird nach einem Button, dann ist dies true, beim fuellen der maske false
	 * @param bIS_PrimaryCall           wenn eine suche eine andere Suche (ebenfalls mit folgeaktionen) ausfuehrt, dann ist bei der ersten der Wert true, bei der zweiten false
	 * @throws myException
	 */
	public abstract void doMaskSettingsAfterValueWrittenInMaskField(
			String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException;

	
	
	
}
