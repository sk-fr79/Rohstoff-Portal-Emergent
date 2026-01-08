package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Column_IMMER_ENABLED;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_BEW_LIST_ComponentMap extends E2_ComponentMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1822091948870665968L;

	public ATOM_LAG_BEW_LIST_ComponentMap(boolean bPreiseMitKosten ) throws myException
	{
		super(new ATOM_LAG_BEW_LIST_SqlFieldMAP(bPreiseMitKosten));
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(ATOM_LAG_BEW_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(ATOM_LAG_BEW_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_BEWEGUNG_ATOM")), new MyE2_String("Atom-ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LEISTUNGSDATUM")), new MyE2_String("Buchungsdatum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADDRESS_INFO")), new MyE2_String("Lager"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ART_INFO")), new MyE2_String("Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL")), new MyE2_String("ID Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGE_NETTO")), new MyE2_String("WE-Menge"));
		
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("E_PREIS")), new MyE2_String("EK-Preis"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("PREIS")), new MyE2_String("Preis"));	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("VERBUCHT")), new MyE2_String("Abgebuchte Menge"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("OFFEN")), new MyE2_String("Restmenge"));
		
//		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_KOMPLETT")), new MyE2_String("Komplett verbucht"));
		
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE")), new MyE2_String("IdFuhre"));
		// leere spalte einbauen, die dann vom FormattingAgent gefüllt wird
		this.add_Component(ATOM_LAG_BEW_CONST.HASH_BUTTON_SHOW_FUHRE, new MyE2_Column_IMMER_ENABLED(),new MyE2_String("WE-Fuhre"));

//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA_FUHRE_ORT")), new MyE2_String("WE-Fuhrenort"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME_GEGEN")), new MyE2_String("Lieferant"));
//		this.add_Component(LAG_BEW_LIST_BasicModuleContainer.NAME_OF_FUHRE_INFO_IN_LIST, new MyE2_Label(), new MyE2_String("Lieferant"));

	
		
		
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SALDO")), new MyE2_String("SALDO"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSTYP")), new MyE2_String("BUCHUNGSTYP"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE_LAGER")), new MyE2_String("ID_ADRESSE_LAGER"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL_SORTE")), new MyE2_String("ID_ARTIKEL_SORTE"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_LAGER_KONTO_PARENT")), new MyE2_String("ID_LAGER_KONTO_PARENT"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_RG")), new MyE2_String("ID_VPOS_RG"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STORNO")), new MyE2_String("STORNO"));
		
		// Spalten verstecken
		this.get__Comp("ID_BEWEGUNG_ATOM").EXT().set_bIsVisibleInList(false);
		this.get__Comp(ATOM_LAG_BEW_CONST.HASH_BUTTON_SHOW_FUHRE).EXT().set_bIsVisibleInList(false);
//		this.get__Comp("ID_VPOS_TPA_FUHRE_ORT").EXT().set_bIsVisibleInList(false);
//		this.get__Comp("IST_KOMPLETT").EXT().set_bIsVisibleInList(false);
		
	}

}
