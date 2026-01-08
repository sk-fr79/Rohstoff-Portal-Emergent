package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;


public class ADK_LIST_DATASEARCH extends E2_DataSearch
{

	public ADK_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_ADRESSKLASSE_DEF","ID_ADRESSKLASSE_DEF",E2_MODULNAME_ENUM.MODUL.ADRESSKLASSE_DEF_LIST.get_callKey());
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_ADRESSKLASSE_DEF","ID",true);
		this.addSearchDef("KURZBEZEICHNUNG","Kurzbezeichnung",false);
		this.addSearchDef("BEZEICHNUNG","Bezeichnung",false);
		this.addSearchDef("BESCHREIBUNG","Beschreibung",false);
		
		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF FROM "+bibE2.cTO()+".JT_ADRESSKLASSE_DEF WHERE TO_CHAR(JT_ADRESSKLASSE_DEF."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF FROM "+bibE2.cTO()+".JT_ADRESSKLASSE_DEF WHERE UPPER(JT_ADRESSKLASSE_DEF."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF FROM "+bibE2.cTO()+".JT_ADRESSKLASSE_DEF,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF="+cRefTableName+".ID_ADRESSKLASSE_DEF AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF FROM "+bibE2.cTO()+".JT_ADRESSKLASSE_DEF,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF="+cRefTableName+".ID_ADRESSKLASSE_DEF AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
