package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class MMD_LIST_DATASEARCH extends E2_DataSearch
{

	public MMD_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_MAIL_AUS_MASK","ID_MAIL_AUS_MASK",E2_MODULNAMES.NAME_MODUL_MAIL_AUS_MASK_DEF__LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_MAIL_AUS_MASK","ID",true);

		//hier kommen die Felder	
		this.addSearchDef(_DB.MAIL_AUS_MASK$BASISTABELLE,"Tabelle",false);
		this.addSearchDef(_DB.MAIL_AUS_MASK$BETREFF,"Betreff",false);
		this.addSearchDef(_DB.MAIL_AUS_MASK$ID_MAIL_AUS_MASK,"ID",true);
		this.addSearchDef(_DB.MAIL_AUS_MASK$MAILTEXT,"Mailtext",false);
		this.addSearchDef(_DB.MAIL_AUS_MASK$MODULKENNER,"Kenner des Moduls",false);
		this.addSearchDef(_DB.MAIL_AUS_MASK$BUTTONKENNER,"Berechtigungskennung",false);
		this.addSearchDef(_DB.MAIL_AUS_MASK$SICHERHEITSABFRAGE_VOR_START,"Sicherheitsabfrage",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_MAIL_AUS_MASK.ID_MAIL_AUS_MASK FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK WHERE TO_CHAR(JT_MAIL_AUS_MASK."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_MAIL_AUS_MASK.ID_MAIL_AUS_MASK FROM "+bibE2.cTO()+".JT_MAIL_AUS_MASK WHERE UPPER(JT_MAIL_AUS_MASK."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	



		
}
