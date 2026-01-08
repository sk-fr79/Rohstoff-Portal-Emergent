package rohstoff.businesslogic.bewegung2.lager_liste;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextArea_INROW;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class B2_LALI_LIST_ComponentMap extends E2_ComponentMAP 
{

	B2_LALI_LIST_BTN_NAVIGATOR__JUMP_TO_FUHRE oNavigatorFuhrenliste  = null;
	
	
	public B2_LALI_LIST_ComponentMap() throws myException
	{
		super(new B2_LALI_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(B2_LALI_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(B2_LALI_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_BG_AUSWERT")), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("EW_FW")), new MyE2_String("EW"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ABRECHENBAR_BESITZ")), new MyE2_String("Abr."));
		
		MyE2_DB_TextArea o_LagerInfo  =new MyE2_DB_TextArea(oFM.get_("LAGER_INFO"),350,3);
		o_LagerInfo.__setStyleFactory(new StyleFactory_TextArea_INROW());
		this.add_Component(o_LagerInfo, new MyE2_String("Lager"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL")), new MyE2_String("ID Artikel"));
		
		MyE2_DB_MultiComponentColumn oColArtikel_Bemerkung = new MyE2_DB_MultiComponentColumn();
		oColArtikel_Bemerkung.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")),new MyE2_String("Sorte"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ2")),new MyE2_String("ArtBez2"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		oColArtikel_Bemerkung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNG")),new MyE2_String("Bemerkung"),null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
		this.add_Component(B2_LALI_CONST.HASH_COLUMN_SORTE_BEMERKUNG,oColArtikel_Bemerkung, new MyE2_String("Sorte/Bemerkung"));
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_AUSFUEHRUNG")), new MyE2_String("Datum"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_WE_BRUTTO")), new MyE2_String("WE brutto"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_WE")), new MyE2_String("WE netto"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_WA_BRUTTO")), new MyE2_String("WA brutto"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_WA")), new MyE2_String("WA netto"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_BRUTTO")), new MyE2_String("Menge Brutto"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_NETTO")), new MyE2_String("Menge Netto"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ABZUG_MENGE")), new MyE2_String("Mengenabzug"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("E_PREIS_BASISWAEHRUNG")), new MyE2_String("Einzelpreis"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("E_PREIS_RES_NETTO_MGE_BASIS")), new MyE2_String("Result. EPreis"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADR_KUNDE_ID_ADRESSE")), new MyE2_String("ID Kunde"));
		MyE2_DB_TextArea o_KundeInfo =new MyE2_DB_TextArea(oFM.get_("NAME_GEGEN"),350,3);
		o_KundeInfo.__setStyleFactory(new StyleFactory_TextArea_INROW());
		this.add_Component( o_KundeInfo, new MyE2_String("Lieferadresse / (Kunde)"));
		
		o_KundeInfo.__setStyleFactory(new StyleFactory_TextArea_INROW());
		
		
		// leere spalte einbauen, die dann vom  gefüllt wird
		MyE2_DB_MultiComponentColumn oColBewegungIDS = new MyE2_DB_MultiComponentColumn();
		
		oColBewegungIDS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_BG_VEKTOR")), new MyE2_String("Vektor"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColBewegungIDS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_BG_ATOM")), new MyE2_String("Atom"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oColBewegungIDS.add_Component(new MyE2_DB_Label_INROW(oFM.get_("K_ID_VPOS_TPA_FUHRE")), new MyE2_String("Fuhre"), null, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		
		// letzte Zeile: ein Button zum Popup der Fuhre, ein Button zum Sprung in die Fuhrenzentrale
		MyE2_Row_EveryTimeEnabled oRowFuhrenNavigator = new MyE2_Row_EveryTimeEnabled();
		oColBewegungIDS.add_Component(oRowFuhrenNavigator, new MyE2_String("Sprünge"), B2_LALI_CONST.HASH_BUTTON_SHOW_FUHRE, LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		oNavigatorFuhrenliste = new B2_LALI_LIST_BTN_NAVIGATOR__JUMP_TO_FUHRE(this, null);
		oRowFuhrenNavigator.EXT().set_oCompTitleInList(oNavigatorFuhrenliste);
		oRowFuhrenNavigator.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		this.add_Component(B2_LALI_CONST.HASH_COLUMN_FUHRE_FUHRENORT,oColBewegungIDS, new MyE2_String("Bewegung-IDs"));
		

		this.set_oSubQueryAgent(new B2_LALI_LIST_FORMATING_Agent());
	}

}
