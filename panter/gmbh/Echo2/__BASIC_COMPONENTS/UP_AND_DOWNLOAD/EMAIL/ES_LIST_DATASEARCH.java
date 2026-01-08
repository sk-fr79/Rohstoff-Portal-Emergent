package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class ES_LIST_DATASEARCH extends E2_DataSearch
{

	public ES_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_EMAIL_SEND","ID_EMAIL_SEND",E2_MODULNAMES.EMAIL_SEND_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_EMAIL_SEND","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("BETREFF","BETREFF",false);
		this.addSearchDef("ID_EMAIL_SEND","ID_EMAIL_SEND",false);
		this.addSearchDef("SENDER_ADRESS","SENDER_ADRESS",false);
		this.addSearchDef("TEXT","TEXT",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_EMAIL_SEND.ID_EMAIL_SEND FROM "+bibE2.cTO()+".JT_EMAIL_SEND WHERE TO_CHAR(JT_EMAIL_SEND."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_EMAIL_SEND.ID_EMAIL_SEND FROM "+bibE2.cTO()+".JT_EMAIL_SEND WHERE UPPER(JT_EMAIL_SEND."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_EMAIL_SEND.ID_EMAIL_SEND FROM "+bibE2.cTO()+".JT_EMAIL_SEND,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_EMAIL_SEND.ID_EMAIL_SEND="+cRefTableName+".ID_EMAIL_SEND AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_EMAIL_SEND.ID_EMAIL_SEND FROM "+bibE2.cTO()+".JT_EMAIL_SEND,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_EMAIL_SEND.ID_EMAIL_SEND="+cRefTableName+".ID_EMAIL_SEND AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
