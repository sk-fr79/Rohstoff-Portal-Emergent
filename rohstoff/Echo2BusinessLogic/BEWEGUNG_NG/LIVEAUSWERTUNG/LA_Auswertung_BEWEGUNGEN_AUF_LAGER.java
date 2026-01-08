package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP_ext4ReadOnly;
import panter.gmbh.indep.exceptions.myException;

public class LA_Auswertung_BEWEGUNGEN_AUF_LAGER  extends XX_ClassSammler4AuswerteZentrale
{
	
	public LA_Auswertung_BEWEGUNGEN_AUF_LAGER() throws myException
	{
	}
	
	
	public class ComponentMAP extends E2_ComponentMAP
	{
		private HashMapSimpleListen  hmSpaltenDef = new HashMapSimpleListen();

		
		public ComponentMAP() throws myException
		{
			super(new LA_SQL_FIELDMAP());
			
			this.add_Component(LA_CONST.NAME_OF_CHECKBOX_IN_LIST,   new E2_CheckBoxForList(20),new MyE2_String("?"));
			this.add_Component(LA_CONST.NAME_OF_LISTMARKER_IN_LIST, new E2_ButtonListMarker(),new MyE2_String("?"));

			//checkboxen
			this.hmSpaltenDef.ADD("ID_ARTIKEL",			"ID-Sorte",	20,"Sorten-ID (Feldname: ID_ARTIKEL)");
			this.hmSpaltenDef.ADD("ANR1",				"ANR1",		20,"ANR1 der Sorte dieser Zeile");
			this.hmSpaltenDef.ADD("JAHR_MONAT_LESBAR",	"Monat",	100,"Monat der betrachteten Gruppensumme");
			
			this.hmSpaltenDef.ADD("SUMME_EINGANG_EK",			"EK-Mge",		100,"Eingangsmenge aus Einkäufen");
			this.hmSpaltenDef.ADD("SUMME_ABZUG_EINGANG_EK",		"EK-Mge(-)",	100,"Abzüge aus Eingangsmenge aus Einkäufen");
			this.hmSpaltenDef.ADD("SUMME_AUSGANG_VK",			"VK-Mge",		100,"Ausgangsmenge zu Verkäufen");
			this.hmSpaltenDef.ADD("SUMME_ABZUG_AUSGANG_VK",		"VK-Mge (-)",	100,"Kann abgerechnet werden");
			this.hmSpaltenDef.ADD("SUMME_EINGANG_MK",			"MK (+)",		100,"Mengenkorrektur Zubuchung");
			this.hmSpaltenDef.ADD("SUMME_AUSGANG_MK",			"MK (-)",		100,"Mengenkorrektur Abbuchung");
			this.hmSpaltenDef.ADD("SUMME_EINGANG_KH",			"KH (+) ",		100,"Handkorrektur Zubuchung");
			this.hmSpaltenDef.ADD("SUMME_AUSGANG_KH",			"KH (-)",		100,"Handkorrektur Abbuchung");
			this.hmSpaltenDef.ADD("SUMME_EINGANG_UH",			"HU (+)",		100,"Umbuchung Hand Zubuchung");
			this.hmSpaltenDef.ADD("SUMME_AUSGANG_UH",			"HU (-)",		100,"Umbuchung Hand Abbuchung");
			this.hmSpaltenDef.ADD("SUMME_EINGANG_LL",			"LL (+)",		100,"Lager-Lager-Zubuchung");
			this.hmSpaltenDef.ADD("SUMME_AUSGANG_LL",			"LL (-)",		100,"Lager-Lager-Abbuchung");
			this.hmSpaltenDef.ADD("SUMME_EINGANG_SW",			"SW (+)",		100,"Zugang aufgrund Sortenwechsel");
			this.hmSpaltenDef.ADD("SUMME_AUSGANG_SW",			"SW (-)",		100,"Abgang aufgrund Sortenwechsel");
			this.hmSpaltenDef.ADD("SUMME_EINGANG_MI",			"Mixed (+)",	100,"Lagerzugang aufgrund Mixed-Anteil alter Fuhren");
			this.hmSpaltenDef.ADD("SUMME_AUSGANG_MI",			"Mixed (-)",	100,"Lagerabgang aufgrund Mixed-Anteil alter Fuhren");
			this.hmSpaltenDef.ADD("JAHR_MONAT",					"Datum(Sort)",	100,"Datumsfeld für Sortierung (intern)");
			this.hmSpaltenDef.ADD("ID_BASIS",					"ID_BASIS",		100,"ID der Tabelle");

			
			//jetzt die Spaltendefs durchscannen, welche felder als checkboxen zu sehen sind
			SQLFieldMAP  		oSQLFieldMAP = 	this.get_oSQLFieldMAP();
			Vector<String>  	vAuflistung = 	this.hmSpaltenDef.get_vSorter();
			
			for (String cFeld: vAuflistung)
			{
				SimpleListenSpalte oSpalte = hmSpaltenDef.get(cFeld);
				oSpalte.init(oSQLFieldMAP);
				this.add_Component(oSpalte.get_Component(), oSpalte.get_oTitleString(),true,true );
			}

			
			this.get__DBComp("JAHR_MONAT_LESBAR").EXT_DB().set_cSortAusdruckFuerSortbuttonUP("JAHR_MONAT ASC");
			this.get__DBComp("JAHR_MONAT_LESBAR").EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("JAHR_MONAT DESC");
			
		}

	}
	
	
	
	
	public class LA_SQL_FIELDMAP extends SQLFieldMAP_ext4ReadOnly
	{
		
		public LA_SQL_FIELDMAP() throws myException
		{
			//super(LA_Auswertung_BEWEGUNGEN_AUF_LAGER.cQUERY,"BASIS");
			super(new LA_SQL_ResourceStringLoader("LA_Auswertung_BEWEGUNGEN_AUF_LAGER.SQL").get_cText(),"BASIS");
			
			this.addCompleteBase_Table_FIELDLIST(":ID_BASIS:",false);
			this.add_SQLField(new SQLFieldForPrimaryKey("BASIS","ID_BASIS","ID_BASIS",new MyE2_String("ID-Basisquery"),bibE2.get_CurrSession(),"",true), false);

			
			//
//			this.add_JOIN_Table("JT_BEWEGUNGSVEKTOR", "JT_BEWEGUNGSVEKTOR", SQLFieldMAP.LEFT_OUTER, 
//								":ID_BEWEGUNGSVEKTOR:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
//								"ATOM.ID_BEWEGUNGSVEKTOR=JT_BEWEGUNGSVEKTOR.ID_BEWEGUNGSVEKTOR", "V_", null);
//
//			this.add_JOIN_Table("JT_BEWEGUNG", "JT_BEWEGUNG", SQLFieldMAP.LEFT_OUTER, 
//								":ID_BEWEGUNG:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
//								"JT_BEWEGUNG.ID_BEWEGUNG=JT_BEWEGUNGSVEKTOR.ID_BEWEGUNG", "B_", null);

			this.initFields();
		}

	}
	
	
	
	 



	@Override
	public E2_ComponentMAP build__oComponentMAP() throws myException
	{
		return new ComponentMAP();
	}





	@Override
	public E2_ListSelectorContainer get__oListSelectorContainer() throws myException
	{
		return null;
	}





	@Override
	public MyE2_Grid get__oListBedienPanelWithButtons() throws myException
	{
		MyE2_Grid oGridBedienPanel = new MyE2_Grid(10, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridBedienPanel.add(this.get_oButtonToSelectVisibleColumns());
		oGridBedienPanel.add(this.get_oButtonToShowBaseQuery());
		
		return oGridBedienPanel;
	}





	@Override
	public E2_DataSearch get__oListSearch() throws myException
	{
		return null;
	}





	@Override
	public String get__cMODUL_KENNER_ADDON()
	{
		return LA_CONST.MODUL_KENNER_ADD_ON_BASISLISTE;
	}




	@Override
	public String get__cBaseSQLQuery() throws myException
	{
		return new LA_SQL_ResourceStringLoader("LA_Auswertung_BEWEGUNGEN_AUF_LAGER.SQL").get_cText();
	}


	
	
		
	
	
	
}
