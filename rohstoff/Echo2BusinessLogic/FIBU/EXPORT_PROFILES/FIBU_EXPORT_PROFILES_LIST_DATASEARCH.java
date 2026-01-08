package rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class FIBU_EXPORT_PROFILES_LIST_DATASEARCH extends E2_DataSearch
{

	public FIBU_EXPORT_PROFILES_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_DATEV_PROFILE","ID_DATEV_PROFILE",E2_MODULNAMES.NAME_FIBU_EXPORT_PROFILES_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_DATEV_PROFILE","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("DATEV_BERATERNUMMER","DATEV_BERATERNUMMER",false);
		this.addSearchDef("DATEV_GESCHAEFTSJAHRESBEGINN","DATEV_GESCHAEFTSJAHRESBEGINN",false);
		this.addSearchDef("DATEV_MANDANTNUMMER","DATEV_MANDANTNUMMER",false);
		this.addSearchDef("ID_DATEV_PROFILE","ID_DATEV_PROFILE",false);
		this.addSearchDef("ID_DRUCKER","ID_DRUCKER",false);
		this.addSearchDef("ID_USER","ID_USER",false);

		/*
		this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
		this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
		this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
		this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);
		*/		

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_DATEV_PROFILE.ID_DATEV_PROFILE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE WHERE TO_CHAR(JT_DATEV_PROFILE."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_DATEV_PROFILE.ID_DATEV_PROFILE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE WHERE UPPER(JT_DATEV_PROFILE."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_DATEV_PROFILE.ID_DATEV_PROFILE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_DATEV_PROFILE.ID_DATEV_PROFILE="+cRefTableName+".ID_DATEV_PROFILE AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_DATEV_PROFILE.ID_DATEV_PROFILE FROM "+bibE2.cTO()+".JT_DATEV_PROFILE,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_DATEV_PROFILE.ID_DATEV_PROFILE="+cRefTableName+".ID_DATEV_PROFILE AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
