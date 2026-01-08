package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import java.util.HashMap;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SELECTFIELD_WAEHRUNG_FREMD;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__CONST;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_BUTTON_FIRMA_WITH_INFO;

public class BSFP_LIST_ComponentMAP extends E2_ComponentMAP_V2
{

	/**
	 * @param oContentPane
	 * @param cID_VPOS_TPA_FUHRE (wenn == null, dann standalone-modul-betrieb)
	 * @throws myException
	 */
	public BSFP_LIST_ComponentMAP(String cID_VPOS_TPA_FUHRE) throws myException
	{
		super(new BSFP_LIST_SQLFieldMAP(cID_VPOS_TPA_FUHRE));
		
		SQLFieldMAP   oFM = this.get_oSQLFieldMAP();
		
		this.add_Component("CHECK_BOX",new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG,	new MyE2_Grid_InLIST(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100()),new MyE2_String("?"));
		this.add_Component(BSRG__CONST.COMPONENTKEY_ANZEIGE_HAT_ABZUEGE,	new MyE2_Label(BSRG__CONST.ICON_LEERKLEIN),new MyE2_String("-"));
		this.add_Component(BSRG__CONST.COMPONENTKEY_ANZEIGE_IST_STORNO_POS,	new MyE2_Label(BSRG__CONST.ICON_LEERKLEIN),new MyE2_String("S"));
		
		this.add_Component(BSRG__CONST.COMPONENTKEY_ANZEIGE_JUMP_TO_FUHRE,	new MyE2_Grid_InLIST(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100()),new MyE2_String("F"));
		
		//2018-06-12: waehrungsanzeigen
		this.add_Component(BSRG__CONST.HASHKEY_COLUMN_CURRENCYS,			new BSFP_LIST_WaehrungsAnzeigeWrapper(),new MyE2_String("Währ."));
		
		//2019-06-19: spalte mit fakturierungsfrist
		this.add_Component(new BSFP_ListAnzeigeAblaufFakturierungsFrist());
		
		
//		ColumnLayoutData  oColLayLeft = LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0);
		GridLayoutData  oColLayRight = LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_2_1_2_1);
		GridLayoutData  oColLayRightTitel = LayoutDataFactory.get_GridLayoutGridRight_DARK_TOP(E2_INSETS.I_2_1_2_1);
//		ColumnLayoutData  oColLayLeftTopSpace = LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_2_2_0);
//		oColLayLeft.setInsets(E2_INSETS.I_2_0_2_0);
//		oColLayRight.setInsets(E2_INSETS.I_2_0_2_0);
//		oColLayLeftTopSpace.setInsets(E2_INSETS.I_2_2_2_0);
		
		MyE2_DB_MultiComponentColumn	 	oCol_Liefbed_Zahlbed = new MyE2_DB_MultiComponentColumn();
		oCol_Liefbed_Zahlbed.add_Component(new MyE2_DB_Label(oFM.get_("LIEFERBEDINGUNGEN"),MyE2_Label.STYLE_SMALL_ITALIC()),			new MyE2_String("Lieferbed."),null);
		oCol_Liefbed_Zahlbed.add_Component(new MyE2_DB_Label(oFM.get_("ZAHLUNGSBEDINGUNGEN"),MyE2_Label.STYLE_SMALL_ITALIC()),		new MyE2_String("Zahlungsbed."),null);

		//einige Tooltips
		MyE2_String cTipMenge = new MyE2_String("Menge ohne Abzüge");
		MyE2_String cTipMgAbzug = new MyE2_String("Mengenabzug (absolut und prozentual)");
		MyE2_String cGesamtbetrag = new MyE2_String("Gesamtbetrag ohne Abzüge");
		MyE2_String cGesamtAbzug = new MyE2_String("Abzugsbetrag basierend auf allen Abzügen (Menge/Preis absolut und prozentual)");
		MyE2_String cPauschAbzug = new MyE2_String("Abzugsanteil pauschaler Abzugsbetrag");
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR1_ANR2")),				new MyE2_String("Anr 1-2"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ1")),				new MyE2_String("Artikelbezeichnung 1"));
//		this.add_Component(new BSFP_LIST_BT_ED_IN_LIST_ADRESSE(oFM.get_("ADRESSE")),	new MyE2_String("Adresse"));
		
		this.add_Component(	new DB_BUTTON_FIRMA_WITH_INFO(oFM.get_("INFO_ID_ADRESSE"),null,"A_NAME1","JT_ADRESSE.NAME1 ASC","JT_ADRESSE.NAME1 DESC",200,100), 
				new MyE2_String("Adresse"),	true,true, new MyE2_String("Adresse"),null,null);

		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("C_ANZAHL")),				new MyE2_String("Menge"),	true,true,cTipMenge,oColLayRightTitel,oColLayRight);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("C_ANZAHL_ABZUG")),			new MyE2_String("M.Abzug"),	true,true,cTipMgAbzug,oColLayRightTitel,oColLayRight);              //aenderung 2010-11-15: abzuege anzeigen
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINHEITKURZ")),			new MyE2_String("EH"));
		this.add_Component(new BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM(oFM.get_("EINZELPREIS")), new MyE2_String("E-Preis "+bibE2.get_cBASISWAEHRUNG_SYMBOL()));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("C_ABZUG_PAUSCHAL")),		new MyE2_String("(-) Psch"),true,true,cPauschAbzug, oColLayRightTitel,oColLayRight);              //aenderung 2010-11-15: abzuege anzeigen
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS")),			new MyE2_String("G-Preis "+bibE2.get_cBASISWAEHRUNG_SYMBOL()),	true,true,cGesamtbetrag,oColLayRightTitel,oColLayRight);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS_ABZUG")),		new MyE2_String("(-) "+bibE2.get_cBASISWAEHRUNG_SYMBOL()),	true,true,cGesamtAbzug,oColLayRightTitel,oColLayRight);              //aenderung 2010-11-15: abzuege anzeigen
		
		//fremdwaehrungsblock
		this.add_Component(new BS_SELECTFIELD_WAEHRUNG_FREMD(oFM, true), new MyE2_String("Fremdwährung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WAEHRUNGSKURS"),MyE2_Label.STYLE_NORMAL_PLAIN(),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2)),	new MyE2_String("Währungskurs"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINZELPREIS_FW")),	new MyE2_String("E-Preis-FW"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS_FW")),	new MyE2_String("G-Preis-FW"),	true,true,cGesamtbetrag,oColLayRightTitel,oColLayRight);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS_ABZUG_FW")),	new MyE2_String("(-) FW"),	true,true,cGesamtAbzug,oColLayRightTitel,oColLayRight);            //aenderung 2010-11-15: abzuege anzeigen


		this.add_Component(new BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM(oFM.get_("STEUERSATZ")),new MyE2_String("MWSt"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINH_PREIS")),new MyE2_String("Pr.Einh."));
		this.add_Component("GRUPPENFELD_4",oCol_Liefbed_Zahlbed,new MyE2_String("Zahl./Lieferbed."));

		this.add_Component(new BSFP_LIST_BT_ED_IN_LIST_PREIS_MWST_LEISTUNGSDATUM(oFM.get_("AUSFUEHRUNGSDATUM")),new MyE2_String("Leist.Dat."),true,true,oColLayRightTitel,oColLayRight);
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ARTIKEL")),new MyE2_String("ID-Art."));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_RG")),new MyE2_String("ID-VPos"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_ADRESSE")),new MyE2_String("ID-Adresse"));
		this.add_Component(new BSFP_LIST_BT_ZEIGE_ALL_ZU_KONTRAKT(oFM.get_("ID_VPOS_KON_ZUGEORD")), new MyE2_String("ID-Kontr.Pos"),true,true,oColLayRightTitel,oColLayRight);
		this.add_Component(new BSFP_LIST_BT_ZEIGE_ALL_ZU_FUHRE(oFM.get_("ID_VPOS_TPA_FUHRE_ZUGEORD")), new MyE2_String("ID-Fuhre"),true,true,oColLayRightTitel,oColLayRight);
		this.add_Component(new BSFP_LIST_BT_ZEIGE_ALL_ZU_FUHRE_ORT(oFM.get_("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD")), new MyE2_String("ID-F.Ort"),true,true,oColLayRightTitel,oColLayRight);
		
		//2011-10-13: neue felder in liste: geaendert von und erzeugt von
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ERZEUGT")),new MyE2_String("Angelegt"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GEAENDERT")),new MyE2_String("Geändert"));

		
		
		// sichtbarkeit
		this.get__Comp("GRUPPENFELD_4").EXT().set_bIsVisibleInList(false);
		
		this.get__Comp("ID_WAEHRUNG_FREMD").EXT().set_bIsVisibleInList(false);
		this.get__Comp("WAEHRUNGSKURS").EXT().set_bIsVisibleInList(false);
		this.get__Comp("EINZELPREIS_FW").EXT().set_bIsVisibleInList(false);
		this.get__Comp("GESAMTPREIS_FW").EXT().set_bIsVisibleInList(false);
		
		/*
		 * spaltenbreiten
		 */
		HashMap<String, MyE2IF__Component> oHMReal = this.get_REAL_ComponentHashMap();
//		((MyE2IF__Component)oHMReal.get("ANZAHL")).EXT().set_oLayout_ListElement();
//		((MyE2IF__Component)oHMReal.get("ANZAHL")).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridRight_DARK_TOP(null));

//		((MyE2IF__Component)oHMReal.get("AUSFUEHRUNGSDATUM")).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridRight_DARK_TOP(null));
//		((MyE2IF__Component)oHMReal.get("ID_VPOS_TPA_FUHRE_ZUGEORD")).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridRight_DARK_TOP(null));

		
		//2011-06-07: in den header der jump-buttons einen multi-jump-butten
		oHMReal.get(BSRG__CONST.COMPONENTKEY_ANZEIGE_JUMP_TO_FUHRE).EXT().set_oCompTitleInList(new BSFP_LIST_Button_Jump_To_Fuhre_HEAD(this));
		
		BSFP_LIST_Button_Jump_ToRechGut_HEAD oMultiJumpRG = new BSFP_LIST_Button_Jump_ToRechGut_HEAD(this);
		oHMReal.get(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG).EXT().set_oCompTitleInList(oMultiJumpRG);
		oHMReal.get(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG).EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_1_1_1_1, new E2_ColorDark()));
		
		
		// geloeschte werden durchgestrichten
		this.set_oSubQueryAgent(new BSFP_LIST_MarkerSubQueryAgent());
		
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());
		
	}

	
	
}
