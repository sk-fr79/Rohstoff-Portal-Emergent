package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_LIST_DATASEARCH extends E2_DataSearch
{
	private String cWhereZusatz = null;


	public BSFP_LIST_DATASEARCH(E2_NavigationList 	oNaviList, String MODULE_KENNER) throws myException
	{
		super( "JT_VPOS_RG","ID_VPOS_RG", MODULE_KENNER);
		
		this.cWhereZusatz = "JT_VPOS_RG.ID_VKOPF_RG IS NULL ";
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);

		
		this.addSearchDef("ID_ADRESSE",					"Adress-ID",		true);
		this.addSearchDef("ANR1",						"ANR1",				false);
		this.addSearchDef("ANR2",						"ANR2",				false);
		this.addSearchDef("ARTBEZ1",					"Artikelbez. 1",	false);
		this.addSearchDef("ARTBEZ2",					"Artikelbez. 2",	false);
		this.addSearchDef("ID_ARTIKEL",					"ID Sorte",			true);
		this.addSearchDef("EINHEITKURZ",				"Mengeneinheit",	false);
		this.addSearchDef("EINHEIT_PREIS_KURZ",			"Mengeneinheit",	false);
		this.addSearchDef("BESTELLNUMMER",				"Bestellnummer",		false);
		this.addSearchDef("ZAHLUNGSBEDINGUNGEN",		"Zahlungsbedingungen",false);
		this.addSearchDef("LIEFERBEDINGUNGEN",			"Lieferbedingungen",false);
		this.addSearchDef("ID_VPOS_TPA_FUHRE_ZUGEORD",	"ID-Fuhre",false);
		this.addSearchDef("ID_VPOS_RG",					"Positions-ID",		true);
		this.addSearchDef("ID_VKOPF_RG",				"Vorgangs-ID",		true);
		
		
		
		
		this.addSearchDefInAdresse("NAME1",			"Name 1",false);
		this.addSearchDefInAdresse("NAME2",			"Name 2",false);
		this.addSearchDefInAdresse("NAME3",			"Name 3",false);
		this.addSearchDefInAdresse("STRASSE",		"Strasse",false);
		this.addSearchDefInAdresse("PLZ",			"PLZ",false);
		this.addSearchDefInAdresse("ORT",			"Ort",false);
		this.addSearchDefInAdresse("ORTZUSATZ",		"Ort-Zusatz",false);
		
		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_VPOS_RG.ID_VPOS_RG FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE TO_CHAR(JT_VPOS_RG."+cFieldName+") ='#WERT#' AND "+this.cWhereZusatz;
		else
			cSearch = "SELECT JT_VPOS_RG.ID_VPOS_RG FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE UPPER(JT_VPOS_RG."+cFieldName+") like upper('%#WERT#%') AND "+this.cWhereZusatz;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	
	private void addSearchDefInAdresse(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_VPOS_RG.ID_VPOS_RG FROM "+bibE2.cTO()+".JT_VPOS_RG,"+bibE2.cTO()+".JT_ADRESSE WHERE " +
					" JT_VPOS_RG.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE AND TO_CHAR(JT_ADRESSE."+cFieldName+") ='#WERT#' AND "+this.cWhereZusatz;
		else
			cSearch = "SELECT JT_VPOS_RG.ID_VPOS_RG FROM "+bibE2.cTO()+".JT_VPOS_RG,"+bibE2.cTO()+".JT_ADRESSE WHERE " +
					" JT_VPOS_RG.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE AND UPPER(JT_ADRESSE."+cFieldName+") like upper('%#WERT#%') AND "+this.cWhereZusatz;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

	
}
