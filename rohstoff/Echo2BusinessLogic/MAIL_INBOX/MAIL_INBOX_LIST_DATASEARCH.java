package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;


public class MAIL_INBOX_LIST_DATASEARCH extends E2_DataSearch
{

	public MAIL_INBOX_LIST_DATASEARCH(E2_NavigationList oNaviList, String cMODULE_KENNER) throws myException
	{
		super("JT_EMAIL_INBOX","ID_EMAIL_INBOX",cMODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_EMAIL_INBOX","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("DATE_RECEIVED","Empfangsdatum",false);
		this.addSearchDef("DATE_SEND","Sendedatum",false);
		this.addSearchDef("MAIL_FROM","Absender",false);
		this.addSearchDef("MAIL_TO","Empfänger",false);
		this.addSearchDef("MAIL_CC","CC-Felde",false);
		this.addSearchDef("SUBJECT","Betreff",false);
		this.addSearchDef("MESSAGE_TEXT","Text",false);
		this.addSearchDef("ID_ADRESSE_ZUGEORDNET","ID der zugeordneten Adresse",false);
		this.addSearchDef("ID_EMAIL_INBOX","ID",false);
		
		this.addSearchDefLookupTable(RECORD_ADRESSE.TABLENAME,RECORD_ADRESSE.FIELD__NAME1,"ID_ADRESSE", "ID_ADRESSE_ZUGEORDNET",  "Adresse Name 1", false);
		this.addSearchDefLookupTable(RECORD_ADRESSE.TABLENAME,RECORD_ADRESSE.FIELD__NAME2,"ID_ADRESSE", "ID_ADRESSE_ZUGEORDNET",  "Adresse Name 2", false);
		this.addSearchDefLookupTable(RECORD_ADRESSE.TABLENAME,RECORD_ADRESSE.FIELD__NAME3,"ID_ADRESSE", "ID_ADRESSE_ZUGEORDNET",  "Adresse Name 3", false);
		this.addSearchDefLookupTable(RECORD_ADRESSE.TABLENAME,RECORD_ADRESSE.FIELD__VORNAME,"ID_ADRESSE", "ID_ADRESSE_ZUGEORDNET",  "Adresse Vorname", false);
		this.addSearchDefLookupTable(RECORD_ADRESSE.TABLENAME,RECORD_ADRESSE.FIELD__PLZ,"ID_ADRESSE", "ID_ADRESSE_ZUGEORDNET",  "Adresse PLZ", false);
		this.addSearchDefLookupTable(RECORD_ADRESSE.TABLENAME,RECORD_ADRESSE.FIELD__ORT,"ID_ADRESSE", "ID_ADRESSE_ZUGEORDNET",  "Adresse PLZ", false);
		this.addSearchDefLookupTable(RECORD_ADRESSE.TABLENAME,RECORD_ADRESSE.FIELD__STRASSE,"ID_ADRESSE", "ID_ADRESSE_ZUGEORDNET",  "Adresse Straße", false);


		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_EMAIL_INBOX.ID_EMAIL_INBOX FROM "+bibE2.cTO()+".JT_EMAIL_INBOX WHERE TO_CHAR(JT_EMAIL_INBOX."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_EMAIL_INBOX.ID_EMAIL_INBOX FROM "+bibE2.cTO()+".JT_EMAIL_INBOX WHERE UPPER(JT_EMAIL_INBOX."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_EMAIL_INBOX.ID_EMAIL_INBOX FROM "+bibE2.cTO()+".JT_EMAIL_INBOX,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_EMAIL_INBOX.ID_EMAIL_INBOX="+cRefTableName+".ID_EMAIL_INBOX AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_EMAIL_INBOX.ID_EMAIL_INBOX FROM "+bibE2.cTO()+".JT_EMAIL_INBOX,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_EMAIL_INBOX.ID_EMAIL_INBOX="+cRefTableName+".ID_EMAIL_INBOX AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

	
	private void addSearchDefLookupTable(String cLookupTableName, String cFieldName, String cLookupId, String cFieldId, String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_EMAIL_INBOX.ID_EMAIL_INBOX FROM "+bibE2.cTO()+".JT_EMAIL_INBOX,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_EMAIL_INBOX." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND TO_CHAR(" + cLookupTableName+"."+cFieldName+") = '#WERT#'";
		else
			cSearch = "SELECT JT_EMAIL_INBOX.ID_EMAIL_INBOX FROM "+bibE2.cTO()+".JT_EMAIL_INBOX,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_EMAIL_INBOX." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND UPPER(" + cLookupTableName+"."+cFieldName+") like Upper('%#WERT#%')";
	
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
