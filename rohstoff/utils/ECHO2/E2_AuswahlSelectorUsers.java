package rohstoff.utils.ECHO2;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR;
import panter.gmbh.Echo2.components.E2_AuswahlSelector;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * Selektion aller aktiven Benutzer des Mandanten in einer links->rechts-Auswahl-Listbox
 * Die Auswahl wird durch anwählen des Eintrags gesetzt oder gelöscht.
 *
 */
public class E2_AuswahlSelectorUsers extends E2_AuswahlSelector
{

	public E2_AuswahlSelectorUsers(Extent SpaltenBreite, Extent SpaltenHoehe,Component ComponentMitte) throws myException
	{
		//super( bibDB.EinzelAbfrageInArrayFormatiert("SELECT  NVL(NAME1,'')||' '||NVL(VORNAME,'')||' ('||NVL(NAME,'-')||')', ID_USER FROM  "+bibE2.cTO()+".JD_USER  Where ID_MANDANT="+bibALL.get_ID_MANDANT()+ " AND NVL(AKTIV,'N')='Y' ORDER BY NAME1"), new String[0][2], SpaltenBreite, SpaltenHoehe, ComponentMitte,true);
		
		super(new USER_SELECTOR_GENERATOR(true).get_AktiveBenutzer(false, null) , new String[0][2], SpaltenBreite, SpaltenHoehe, ComponentMitte,true);
	}
	
	
	public E2_AuswahlSelectorUsers(USER_SELECTOR_GENERATOR oUserGenerator_formattedIDs, Extent SpaltenBreite, Extent SpaltenHoehe,Component ComponentMitte) throws myException
	{
		super(oUserGenerator_formattedIDs.get_AktiveBenutzer(false, null) , new String[0][2], SpaltenBreite, SpaltenHoehe, ComponentMitte,true);
	}

}
