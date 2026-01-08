package rohstoff.Echo2BusinessLogic.FIBU;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_ENUMS.FIBU;
import panter.gmbh.indep.exceptions.myException;


public class FIBU_LIST_DATASEARCH extends E2_DataSearch
{

	public FIBU_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_FIBU","ID_FIBU",oNaviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE());
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_FIBU","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("BUCHUNGSINFO","Info Beleg",false);
		this.addSearchDef("BEARBEITERKUERZEL","Bearbeiterkürzel",false);
		this.addSearchDef("BUCHUNGSBLOCK_NR","Buchungsblock-Nr",false);
		this.addSearchDefFormated("ENDBETRAG_FREMD_WAEHRUNG","Endbetrag (in Kundenwährung)");
		this.addSearchDef("ID_ADRESSE","Adress-ID",false);
		
		String cQueryAdresse = "SELECT ID_FIBU FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_ADRESSE IN ("+
								" SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE (" +
										" UPPER(NVL(NAME1,' '))  like upper('%#WERT#%') OR  "+
										" UPPER(NVL(NAME2,' '))  like upper('%#WERT#%') OR  "+
										" UPPER(NVL(ORT,' '))  like upper('%#WERT#%')) AND ADRESSTYP=1 "+
		                       ")";
		
		this.add_SearchElement(cQueryAdresse,new MyE2_String("Name/Ort Adresse"));
		
		this.addSearchDef("ID_FIBU","Fibu-ID",false);
		this.addSearchDef("ID_VKOPF_RG","Rechungs/Gutschrift-ID",false);
		this.addSearchDef("SCHECKNUMMER","Schecknummer",false);
		this.addSearchDef("STORNOGRUND","Stornogrund",false);
		this.addSearchDef("STORNOKUERZEL","Benutzerkürzel für Storno",false);
		this.addSearchDefFormated("ZAHLUNGSBETRAG_BASIS_WAEHRUNG","Betrag incl. Skontoabzug");
		this.addSearchDef("ZAHLUNGSZIEL","Zahlungsziel",false);
		
		//2016-06-17: neue suchmoeglichkeit nach fremdbeleg
		this.addSearchDef(FIBU.fremdbelegnummer.fn(),"Fremdbelegnummer",false);


		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_FIBU.ID_FIBU FROM "+bibE2.cTO()+".JT_FIBU WHERE TO_CHAR(JT_FIBU."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_FIBU.ID_FIBU FROM "+bibE2.cTO()+".JT_FIBU WHERE UPPER(JT_FIBU."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	//2012-08-30: betragssuche mit formatierten suchstrings
	private void addSearchDefFormated(String cFieldName,String cInfoText) throws myException
	{
		String cSearch1 = "TO_CHAR(JT_FIBU."+cFieldName+",'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.''')='#WERT#'";
		String cSearch2 = "TO_CHAR(JT_FIBU."+cFieldName+",'fm999999990d00','NLS_NUMERIC_CHARACTERS = '',.''')='#WERT#'";
		
		String cSearch = "SELECT JT_FIBU.ID_FIBU FROM "+bibE2.cTO()+".JT_FIBU WHERE  ("+cSearch1+" OR "+cSearch2+")";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	


}
