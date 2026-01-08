package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;


public class USER__LIST_DATASEARCH extends E2_DataSearch
{

	public USER__LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JD_USER","ID_USER",MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_USER","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("AUTCODE","AUTCODE",false);
		this.addSearchDef("EMAIL","EMAIL",false);
		this.addSearchDef("HAT_FAHRPLAN_BUTTON","HAT_FAHRPLAN_BUTTON",false);
		this.addSearchDef("ID_SPRACHE","ID_SPRACHE",false);
		this.addSearchDef("ID_USER","ID_USER",false);
		this.addSearchDef("ID_USERGROUP","ID_USERGROUP",false);
		this.addSearchDef("IST_FAHRER","IST_FAHRER",false);
		this.addSearchDef("IST_SUPERVISOR","IST_SUPERVISOR",false);
		this.addSearchDef("KUERZEL","KUERZEL",false);
		this.addSearchDef("LAUFZEIT_SESSION","LAUFZEIT_SESSION",false);
		this.addSearchDef("LISTEGESAMTLAENGE","LISTEGESAMTLAENGE",false);
		this.addSearchDef("LISTESEITELAENGE","LISTESEITELAENGE",false);
		this.addSearchDef("MAIL_SIGNATUR","MAIL_SIGNATUR",false);
		this.addSearchDef("NAME","NAME",false);
		this.addSearchDef("NAME1","NAME1",false);
		this.addSearchDef("NAME2","NAME2",false);
		this.addSearchDef("NAME3","NAME3",false);
		this.addSearchDef("PASSWORT","PASSWORT",false);
		this.addSearchDef("TELEFAX","TELEFAX",false);
		this.addSearchDef("TELEFON","TELEFON",false);
		this.addSearchDef("TEXTCOLLECTOR","TEXTCOLLECTOR",false);
		this.addSearchDef("TODO_SUPERVISOR","TODO_SUPERVISOR",false);
		this.addSearchDef("VORNAME","VORNAME",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JD_USER.ID_USER FROM "+bibE2.cTO()+".JD_USER WHERE TO_CHAR(JD_USER."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JD_USER.ID_USER FROM "+bibE2.cTO()+".JD_USER WHERE UPPER(JD_USER."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	


		
}
