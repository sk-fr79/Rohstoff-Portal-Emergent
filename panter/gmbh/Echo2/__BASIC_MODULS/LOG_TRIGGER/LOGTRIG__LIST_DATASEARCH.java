package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class LOGTRIG__LIST_DATASEARCH extends E2_DataSearch
{

	public LOGTRIG__LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JD_TRIGGER_DEF","ID_TRIGGER_DEF",E2_MODULNAMES.NAME_MODUL_LOG_TRIGGER_DEF_LISTE);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_TRIGGER_DEF","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("BESCHREIBUNG","BESCHREIBUNG",false);
		this.addSearchDef("ERZEUGT_AM","ERZEUGT_AM",false);
		this.addSearchDef("ERZEUGT_VON","ERZEUGT_VON",false);
		this.addSearchDef("ID_TRIGGER_DEF","ID_TRIGGER_DEF",false);
		this.addSearchDef("SPALTE","SPALTE",false);
		this.addSearchDef("TABELLE","TABELLE",false);
		this.addSearchDef("TRIGG_NAME","TRIGG_NAME",false);

		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JD_TRIGGER_DEF.ID_TRIGGER_DEF FROM "+bibE2.cTO()+".JD_TRIGGER_DEF WHERE TO_CHAR(JD_TRIGGER_DEF."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JD_TRIGGER_DEF.ID_TRIGGER_DEF FROM "+bibE2.cTO()+".JD_TRIGGER_DEF WHERE UPPER(JD_TRIGGER_DEF."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JD_TRIGGER_DEF.ID_TRIGGER_DEF FROM "+bibE2.cTO()+".JD_TRIGGER_DEF,"+bibE2.cTO()+"."+cRefTableName+" WHERE JD_TRIGGER_DEF.ID_TRIGGER_DEF="+cRefTableName+".ID_TRIGGER_DEF AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JD_TRIGGER_DEF.ID_TRIGGER_DEF FROM "+bibE2.cTO()+".JD_TRIGGER_DEF,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JD_TRIGGER_DEF.ID_TRIGGER_DEF="+cRefTableName+".ID_TRIGGER_DEF AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
