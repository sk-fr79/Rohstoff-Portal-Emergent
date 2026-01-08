package rohstoff.Echo2BusinessLogic.BANKENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;

public class BANK_LIST_DATASEARCH extends E2_DataSearch
{

	public BANK_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_ADRESSE","ID_ADRESSE", MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_ADRESSE","ID",true);
		
		this.addSearchDef("NAME1","NAME1",false);
		this.addSearchDef("NAME2","NAME2",false);
		this.addSearchDef("NAME3","NAME3",false);
		this.addSearchDef("ORT","ORT",false);
		this.addSearchDef("PLZ","PLZ",false);
		this.addSearchDef("STRASSE","STRASSE",false);
		this.addSearchDef("BEMERKUNGEN","Bemerkungen Adresse",false);
		
		this.addSearchDefRefTable("JT_BANKENSTAMM","BANKLEITZAHL","BANKLEITZAHL",false);
		this.addSearchDefRefTable("JT_BANKENSTAMM","SWIFTCODE","SWIFTCODE",false);
		this.addSearchDefRefTable("JT_BANKENSTAMM","IBAN_NR","IBAN_NR",false);
		this.addSearchDefRefTable("JT_BANKENSTAMM","BEMERKUNGEN","Bemerkungen Bank",false);
		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_BANK+" AND TO_CHAR(JT_ADRESSE."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_BANK+" AND UPPER(JT_ADRESSE."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_ADRESSE.ID_ADRESSE="+cRefTableName+".ID_ADRESSE AND JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_BANK+" AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_ADRESSE.ID_ADRESSE="+cRefTableName+".ID_ADRESSE AND  JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_BANK+" AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	
	
}
