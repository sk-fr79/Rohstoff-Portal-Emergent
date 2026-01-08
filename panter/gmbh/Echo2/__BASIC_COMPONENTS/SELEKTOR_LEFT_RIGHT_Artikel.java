package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.E2_AuswahlSelector;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * Selektion aller aktiven Benutzer des Mandanten in einer links->rechts-Auswahl-Listbox
 * Die Auswahl wird durch anwählen des Eintrags gesetzt oder gelöscht.
 *
 */
public class SELEKTOR_LEFT_RIGHT_Artikel extends E2_AuswahlSelector
{

	public SELEKTOR_LEFT_RIGHT_Artikel(Extent SpaltenBreite, Extent SpaltenHoehe,Component ComponentMitte) throws myException
	{
		super(new String[0][2] , new String[0][2], SpaltenBreite, SpaltenHoehe, ComponentMitte,true);
		
		String cQuery = "SELECT "+_DB.ARTIKEL$ANR1+"||' ('||"+_DB.ARTIKEL$ARTBEZ1+"||')',"+_DB.ARTIKEL$ID_ARTIKEL+" FROM "+bibE2.cTO()+"."+_DB.ARTIKEL+" WHERE NVL("+_DB.ARTIKEL$AKTIV+",'N')='Y' ORDER BY ANR1 ";
			
		this.get_oSelQuelle().set_oDataToView(new dataToView(bibDB.EinzelAbfrageInArray(cQuery, "-"), false, bibE2.get_CurrSession()), false);
		
	}

}
