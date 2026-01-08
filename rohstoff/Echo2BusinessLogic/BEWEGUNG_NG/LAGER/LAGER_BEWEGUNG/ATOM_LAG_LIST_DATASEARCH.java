package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class ATOM_LAG_LIST_DATASEARCH extends E2_DataSearch
{

	public ATOM_LAG_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_BEWEGUNG_STATION","ID_BEWEGUNG_STATION",E2_MODULNAMES.NAME_MODUL_ATOM_LAGER_BEWEGUNG_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_BEWEGUNG_STATION","ID",true);

		//hier kommen die Felder der Station
		
		this.addSearchDef("ID_ADRESSE","AdressID des Kundenlagers",true);
		this.addSearchDef("ID_ADRESSE_BESITZER","AdressID des Besitzers",false);
		this.addSearchDef("ID_WIEGEKARTE","ID Wiegekarten",true);
		this.addSearchDef("WIEGEKARTENKENNER","Wiegekartenkenner",false);
		this.addSearchDef("NAME1","Name1",false);
		this.addSearchDef("NAME2","Name2",false);
		this.addSearchDef("NAME3","Name3",false);
		this.addSearchDef("STRASSE","Strasse",false);
		this.addSearchDef("HAUSNUMMER","Hausnummer",false);
		this.addSearchDef("PLZ","Plz",false);
		this.addSearchDef("ORT","Ort",false);
		this.addSearchDef("BESTELLNUMMER","Bestellnummer",false);

		this.addSearchDefGegenseite("S1.NAME1||S1.NAME2||S1.NAME3","Kunde",false);
		this.addSearchDefGegenseite("S1.ORT","Kunden-Ort",false);
		
		
		this.addSearchDefAtom("ID_ARTIKEL","ID Artikel",true);
		this.addSearchDefAtom("ID_ARTIKEL_BEZ","ID Artikelbezeichnung",true);
		this.addSearchDefAtom("ID_BEWEGUNG","ID Bewegung",true);
		this.addSearchDefAtom("ID_BEWEGUNG_VEKTOR","ID Bewegungsvektor",true);
		this.addSearchDefAtom("ID_BEWEGUNG_ATOM","ID Bewegungsatom",true);
		
		this.addSearchDefBewegung("ID_VPOS_TPA_FUHRE", "ID Fuhre", true);
		
		this.addSearchDefAtom("NATIONALER_ABFALL_CODE","Nationaler Abfallcode",false);
		this.addSearchDefAtom("NOTIFIKATION_NR","NotifikationNr",false);
		this.addSearchDefAtom("ORDNUNGSNUMMER_CMR","Ordnungsnummer CMR",false);
		this.addSearchDefAtom("POSTENNUMMER","Postennummer",false);


		/*
		this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
		this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
		this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
		this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);
		*/		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private String sSearchBlockGlobal =    "  AND  JT_BEWEGUNG_STATION.ID_ADRESSE IN  " +
											"  (SELECT LA.ID_ADRESSE_LIEFER     " +
											"   FROM " + bibE2.cTO() +".JT_LIEFERADRESSE LA" +
											"  INNER JOIN " + bibE2.cTO() + ".JT_ADRESSE ADRL ON LA.ID_ADRESSE_LIEFER = ADRL.ID_ADRESSE" +
											"  WHERE LA.ID_ADRESSE_BASIS = " +
											"  ( SELECT M.EIGENE_ADRESS_ID FROM  " + bibE2.cTO() +".JD_MANDANT M WHERE M.ID_MANDANT = " + bibALL.get_ID_MANDANT() + " )" + 
											"        AND ( ADRL.SONDERLAGER is null OR trim(ADRL.SONDERLAGER) = trim('STRECKE') )" +
											"  UNION" +
											"  ( SELECT M.EIGENE_ADRESS_ID FROM  " + bibE2.cTO() +".JD_MANDANT M WHERE M.ID_MANDANT =  " + bibALL.get_ID_MANDANT() + " )" +
											"  )" ;
	
	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE TO_CHAR(JT_BEWEGUNG_STATION."+cFieldName+")='#WERT#'" + sSearchBlockGlobal;
		else
			cSearch = "SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE UPPER(JT_BEWEGUNG_STATION."+cFieldName+") like upper('%#WERT#%')" + sSearchBlockGlobal;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefGegenseite(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = " SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION "
					+ " INNER JOIN "+bibE2.cTO()+".JT_BEWEGUNG_ATOM A ON A.ID_BEWEGUNG_ATOM = JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM "
					+ " INNER JOIN "+bibE2.cTO()+".JT_BEWEGUNG_STATION S1 ON A.ID_BEWEGUNG_ATOM = S1.ID_BEWEGUNG_ATOM AND S1.MENGENVORZEICHEN = -1 * JT_BEWEGUNG_STATION.MENGENVORZEICHEN"
					+ " WHERE TO_CHAR("+ cFieldName + ") = '#WERT#'" + sSearchBlockGlobal;
		else
			cSearch = " SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION "
					+ " INNER JOIN "+bibE2.cTO()+".JT_BEWEGUNG_ATOM A ON A.ID_BEWEGUNG_ATOM = JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM "
					+ " INNER JOIN "+bibE2.cTO()+".JT_BEWEGUNG_STATION S1 ON A.ID_BEWEGUNG_ATOM = S1.ID_BEWEGUNG_ATOM AND S1.MENGENVORZEICHEN = -1 * JT_BEWEGUNG_STATION.MENGENVORZEICHEN"
					+ " WHERE UPPER("+ cFieldName + ") LIKE UPPER('%#WERT#%') " + sSearchBlockGlobal;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

	
	private void addSearchDefAtom(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = " SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION " +
					  " INNER JOIN JT_BEWEGUNG_ATOM on JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM = JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM " +
					  " WHERE TO_CHAR(JT_BEWEGUNG_ATOM."+cFieldName+")='#WERT#'" + sSearchBlockGlobal;
		else
			cSearch = " SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION " +
			  " INNER JOIN JT_BEWEGUNG_ATOM on JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM = JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM " +
			  " WHERE UPPER(JT_BEWEGUNG_ATOM."+cFieldName+") like upper('%#WERT#%')" + sSearchBlockGlobal;
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

	private void addSearchDefBewegung (String cFieldName, String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = " SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION " +
					  " INNER JOIN JT_BEWEGUNG_ATOM on JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM = JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM " +
					  " INNER JOIN JT_BEWEGUNG on JT_BEWEGUNG.ID_BEWEGUNG = JT_BEWEGUNG_ATOM.ID_BEWEGUNG " +
					  " WHERE TO_CHAR(JT_BEWEGUNG."+cFieldName+")='#WERT#'" + sSearchBlockGlobal;
		else
			cSearch = " SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION " +
			  " INNER JOIN JT_BEWEGUNG_ATOM on JT_BEWEGUNG_STATION.ID_BEWEGUNG_ATOM = JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM " +
			  " INNER JOIN JT_BEWEGUNG on JT_BEWEGUNG.ID_BEWEGUNG = JT_BEWEGUNG_ATOM.ID_BEWEGUNG " +
			  " WHERE UPPER(JT_BEWEGUNG."+cFieldName+") like upper('%#WERT#%')" + sSearchBlockGlobal;
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	
	
	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM="+cRefTableName+".ID_BEWEGUNG_ATOM AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM="+cRefTableName+".ID_BEWEGUNG_ATOM AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
