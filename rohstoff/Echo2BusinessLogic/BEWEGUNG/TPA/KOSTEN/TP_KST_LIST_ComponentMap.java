package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST_LIST_ComponentMap extends E2_ComponentMAP 
{

	public TP_KST_LIST_ComponentMap() throws myException
	{
		super(new TP_KST_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(TP_KST__CONST.MAP_HASHES.NAME_OF_CHECKBOX_IN_LIST.name(),	new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(TP_KST__CONST.MAP_HASHES.NAME_OF_LISTMARKER_IN_LIST.name(),	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder
		//neuer jump-button in fuhre
		this.add_Component(TP_KST__CONST.MAP_HASHES.JUMP_TO_FUHRE.name(),			new TP_KST_LIST_BT_JUMP_to_Fuhren(),new MyE2_String("?"));
		this.add_Component(TP_KST__CONST.MAP_HASHES.JUMP_TO_TPA.name(),				new TP_KST_LIST_BT_JUMP_to_TPA(),new MyE2_String("?"));
		//2015-05-12: normalisierter start/zielort
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(TP_KST__CONST.SQLFIELDMAP_FIELDS.STARTORT_NORM._hash())), new MyE2_String("Startort/Norm"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(TP_KST__CONST.SQLFIELDMAP_FIELDS.ZIELORT_NORM._hash())), new MyE2_String("Zielort/Norm"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ1")), new MyE2_String("Sorte (Spedition)"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANZAHL")), new MyE2_String("Mge"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINHEIT_PREIS_KURZ")), new MyE2_String("Eh."));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINZELPREIS")), new MyE2_String("E.Preis"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS")), new MyE2_String("G.Preis"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("SPEDITION_NAME")), new MyE2_String("Spedition"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(TP_KST__CONST.SQLFIELDMAP_FIELDS.FU_SORTE._hash())), TP_KST__CONST.SQLFIELDMAP_FIELDS.FU_SORTE._descript());
		
		
		this.add_Component(new MyE2_DB_Label(oFM.get_("ZAHLUNGSBEDINGUNGEN")), new MyE2_String("Zahlungsbed."));
		this.add_Component(new MyE2_DB_Label(oFM.get_("LIEFERBEDINGUNGEN")), new MyE2_String("Lieferbed."));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LAND_START_LAENDERNAME")),new MyE2_String("Land"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADR_START_PLZ")), new MyE2_String("PLZ"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADR_START_ORT")), new MyE2_String("Ort Start"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LAND_ZIEL_LAENDERNAME")),new MyE2_String("Land"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADR_ZIEL_PLZ")), new MyE2_String("PLZ"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ADR_ZIEL_ORT")), new MyE2_String("Ort Ziel"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_AUFLADEN")),new MyE2_String("Ladedatum"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_ABLADEN")),new MyE2_String("Abladedatum"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR1")), new MyE2_String("Anr1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR2")), new MyE2_String("Anr2"));
		
		this.set_oSubQueryAgent(new TP_KST_LIST_FORMATING_Agent());
	}

}
