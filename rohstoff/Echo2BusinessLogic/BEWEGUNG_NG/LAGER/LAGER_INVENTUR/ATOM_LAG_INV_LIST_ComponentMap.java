package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_INVENTUR;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_INV_LIST_ComponentMap extends E2_ComponentMAP 
{
	// den übergeordneten Container merken
	private boolean m_bNurNullbuchungen = false;

	public ATOM_LAG_INV_LIST_ComponentMap(boolean bNurNullbuchungen) throws myException
	{
		super(new ATOM_LAG_INV_LIST_SqlFieldMAP());
		this.m_bNurNullbuchungen = bNurNullbuchungen;
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(ATOM_LAG_INV_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(ATOM_LAG_INV_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_LAGER_INVENTUR")), new MyE2_String("InventurID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO")), new MyE2_String("Lager"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")), new MyE2_String("Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL_SORTE")), new MyE2_String("ID Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSDATUM")), new MyE2_String("Datum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSZEIT")), new MyE2_String("Zeit"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE")), new MyE2_String("Menge"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("PREIS")), new MyE2_String("Preis"));
		
		this.add_Component(ATOM_LAG_INV_CONST.HASH_COL_ATOM_LAGERSALDO_OK, new MyE2_ButtonInLIST("OK"),new MyE2_String("OK"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LAGERBESTAND")), new MyE2_String("Saldo tatsächlich"));
		
//		this.add_Component(LAG_INV_CONST.HASH_COL_LAGERSALDO_MENGE, new MyE2_Label(),new MyE2_String("Saldo tatsächlich"));
		this.add_Component(ATOM_LAG_INV_CONST.HASH_COL_ATOM_LAGERSALDO_DIFFERENZ, new MyE2_Label(),new MyE2_String("Saldo Differenz"));
		
		this.add_Component(ATOM_LAG_INV_CONST.HASH_COL_ATOM_SETZKASTEN_NEU_AUFBAUEN, new MyE2_ButtonInLIST("Neuaufbau Setzkasten"),new MyE2_String("Neuaufbau Setzkasten"));
		

		
		
		// Gridlayout für den Header
		GridLayoutData  GLHead = new GridLayoutData();
		GLHead.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
		GLHead.setBackground(new E2_ColorDark());
		
		// Gridlayout für die Liste
		GridLayoutData  GLList = new GridLayoutData();
		GLList.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));

		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_LAGERSALDO_OK).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_LAGERSALDO_OK).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_LAGERSALDO_OK).EXT().set_ToolTipStringForListHeader(new MyE2_String("Zeigt an, ob der tatsächliche Saldo mit dem Inventuereintrag übereinstimmt"));
		
		this.get("LAGERBESTAND").EXT().set_oLayout_ListTitelElement(GLHead);
		this.get("LAGERBESTAND").EXT().set_oLayout_ListElement(GLList);
		this.get("LAGERBESTAND").EXT().set_ToolTipStringForListHeader(new MyE2_String("Die tatsächliche Lagersaldo-Menge zum Zeitpunkt des Inventureintrags. "));

		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_LAGERSALDO_DIFFERENZ).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_LAGERSALDO_DIFFERENZ).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_LAGERSALDO_DIFFERENZ).EXT().set_ToolTipStringForListHeader(new MyE2_String("Die Differenz zwischen der Inventurmenge und dem Lagersaldo"));

		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_SETZKASTEN_NEU_AUFBAUEN).EXT().set_oLayout_ListTitelElement(GLHead);
		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_SETZKASTEN_NEU_AUFBAUEN).EXT().set_oLayout_ListElement(GLList);
		this.get(ATOM_LAG_INV_CONST.HASH_COL_ATOM_SETZKASTEN_NEU_AUFBAUEN).EXT().set_ToolTipStringForListHeader(new MyE2_String("Verbucht den Setzkasten für Lager/Sorte komplett neu auf und schreibt die Historiendaten neu"));

		
		this.set_oSubQueryAgent(new ATOM_LAG_INV_LIST_FORMATING_Agent());
	}

}
