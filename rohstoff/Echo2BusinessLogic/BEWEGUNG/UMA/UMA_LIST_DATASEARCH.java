package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class UMA_LIST_DATASEARCH extends E2_DataSearch
{

	public UMA_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_UMA_KONTRAKT","ID_UMA_KONTRAKT", E2_MODULNAMES.NAME_MODUL_UMA_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);

//		String cSQL_SorteAusgang = "SELECT JT_UMA_KONTRAKT.ID_UMA_KONTRAKT FROM "
//			+bibE2.cTO()+".JT_UMA_KONTRAKT" +
//			" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ON (JT_UMA_KONTRAKT.ID_ARTIKEL_BEZ_AUSGANG=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ) "+
//			" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL) "+
//			" WHERE " +
//			"(" +
//			"UPPER(JT_ARTIKEL_BEZ.ARTBEZ1) LIKE UPPER('%#WERT#%') OR " +
//			"UPPER(JT_ARTIKEL_BEZ.ANR2) LIKE UPPER('%#WERT#%') OR " +
//			"UPPER(JT_ARTIKEL.ANR1) LIKE UPPER('%#WERT#%'))";
//		
//		String cSQL_SorteZiel = "SELECT JT_UMA_KONTRAKT.ID_UMA_KONTRAKT FROM "
//			+bibE2.cTO()+".JT_UMA_KONTRAKT" +
//			" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ON (JT_UMA_KONTRAKT.ID_ARTIKEL_BEZ_ZIEL=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ) "+
//			" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL) "+
//			" WHERE " +
//			"(" +
//			"UPPER(JT_ARTIKEL_BEZ.ARTBEZ1) LIKE UPPER('%#WERT#%') OR " +
//			"UPPER(JT_ARTIKEL_BEZ.ANR2) LIKE UPPER('%#WERT#%') OR " +
//			"UPPER(JT_ARTIKEL.ANR1) LIKE UPPER('%#WERT#%'))";
//		
		
//definition der sorten via tochtertabellen
		
		String cSQL_SorteAusgang = "SELECT "+_DB.UMA_KON_ARTB_LIEF+"."+_DB.UMA_KON_ARTB_LIEF$ID_UMA_KON_ARTB_LIEF+" FROM "
				+bibE2.cTO()+"."+_DB.UMA_KON_ARTB_LIEF +
				" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ON ("+_DB.UMA_KON_ARTB_LIEF+"."+_DB.UMA_KON_ARTB_LIEF$ID_ARTIKEL_BEZ+"=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ) "+
				" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL) "+
				" WHERE " +
				"(" +
				"UPPER(JT_ARTIKEL_BEZ.ARTBEZ1) LIKE UPPER('%#WERT#%') OR " +
				"UPPER(JT_ARTIKEL_BEZ.ANR2) LIKE UPPER('%#WERT#%') OR " +
				"UPPER(JT_ARTIKEL.ANR1) LIKE UPPER('%#WERT#%'))";
			
			String cSQL_SorteZiel = "SELECT "+_DB.UMA_KON_ARTB_RUECKLIEF+"."+_DB.UMA_KON_ARTB_RUECKLIEF$ID_UMA_KON_ARTB_RUECKLIEF+" FROM "
				+bibE2.cTO()+"."+_DB.UMA_KON_ARTB_RUECKLIEF +
				" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ON ("+_DB.UMA_KON_ARTB_RUECKLIEF+"."+_DB.UMA_KON_ARTB_RUECKLIEF$ID_ARTIKEL_BEZ+"=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ) "+
				" INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL) "+
				" WHERE " +
				"(" +
				"UPPER(JT_ARTIKEL_BEZ.ARTBEZ1) LIKE UPPER('%#WERT#%') OR " +
				"UPPER(JT_ARTIKEL_BEZ.ANR2) LIKE UPPER('%#WERT#%') OR " +
				"UPPER(JT_ARTIKEL.ANR1) LIKE UPPER('%#WERT#%'))";
			
		
		String cSQL_Betreuer = "SELECT JT_UMA_KONTRAKT.ID_UMA_KONTRAKT FROM "
			+bibE2.cTO()+".JT_UMA_KONTRAKT" +
			" INNER JOIN "+bibE2.cTO()+".JD_USER ON (JT_UMA_KONTRAKT.ID_USER_BETREUER=JD_USER.ID_USER) "+
			" WHERE " +
			"(" +
			"UPPER(JD_USER.VORNAME) LIKE UPPER('%#WERT#%') OR " +
			"UPPER(JD_USER.NAME1) LIKE UPPER('%#WERT#%'))";
		
		String cSQL_Firma = "SELECT JT_UMA_KONTRAKT.ID_UMA_KONTRAKT FROM "
			+bibE2.cTO()+".JT_UMA_KONTRAKT" +
			" INNER JOIN "+bibE2.cTO()+".JT_ADRESSE ON (JT_UMA_KONTRAKT.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE) "+
			" WHERE " +
			"(" +
			"TO_CHAR(JT_ADRESSE.ID_ADRESSE) LIKE UPPER('%#WERT#%') OR " +
			"UPPER(JT_ADRESSE.VORNAME) LIKE UPPER('%#WERT#%') OR " +
			"UPPER(JT_ADRESSE.NAME1) LIKE UPPER('%#WERT#%') OR " +
			"UPPER(JT_ADRESSE.NAME2) LIKE UPPER('%#WERT#%') OR " +
			"UPPER(JT_ADRESSE.STRASSE) LIKE UPPER('%#WERT#%') OR " +
			"UPPER(JT_ADRESSE.PLZ) LIKE UPPER('%#WERT#%') OR " +
			"UPPER(JT_ADRESSE.ORT) LIKE UPPER('%#WERT#%'))";
		

		//hier kommen die Felder	
		this.addSearchDef("ID_UMA_KONTRAKT",			"UMA-Kontrakt-ID",false);
		this.addSearchDef("BEMERKUNGEN",				"Bemerkung",false);
		this.add_DatumsSuchFeld("JT_UMA_KONTRAKT", 		"DATUM_VERTRAG", new MyE2_String("Vertragsdatum"));
		this.add_SearchElement(cSQL_Firma, 				new MyE2_String("Firma/Kunde"));
		this.add_SearchElement(cSQL_SorteAusgang, 		new MyE2_String("Ausgangssorte"));
		this.add_SearchElement(cSQL_SorteZiel, 			new MyE2_String("Zielsorte"));
		this.add_SearchElement(cSQL_Betreuer, 			new MyE2_String("Betreuer"));

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_UMA_KONTRAKT.ID_UMA_KONTRAKT FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE TO_CHAR(JT_UMA_KONTRAKT."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_UMA_KONTRAKT.ID_UMA_KONTRAKT FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT WHERE UPPER(JT_UMA_KONTRAKT."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_UMA_KONTRAKT.ID_UMA_KONTRAKT FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_UMA_KONTRAKT.ID_UMA_KONTRAKT="+cRefTableName+".ID_UMA_KONTRAKT AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_UMA_KONTRAKT.ID_UMA_KONTRAKT FROM "+bibE2.cTO()+".JT_UMA_KONTRAKT,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_UMA_KONTRAKT.ID_UMA_KONTRAKT="+cRefTableName+".ID_UMA_KONTRAKT AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
