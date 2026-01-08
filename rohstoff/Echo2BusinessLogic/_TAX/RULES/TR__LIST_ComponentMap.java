package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.E2_QUERYAGENT_MarkiereInaktiveInNaviList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_StyleBT;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_LAND_DROPDOWN_LabelInList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField_LabelInList;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_Auswahl_Y_N_EGAL;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_STEUERDEF;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_VERANTWORTUNG;

public class TR__LIST_ComponentMap extends E2_ComponentMAP {

	public TR__LIST_ComponentMap(String cWhereBlock) throws myException	{
		
		super(new TR__LIST_SqlFieldMAP(cWhereBlock));
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		 
		//20190221: neuer marker
		this.setComponentMapMarker(new TR__LIST_ComponentMapMarker(this));

		
		//einstellung, ob mit oder ohne unterscheuidung der Mandantenvermerke gearbeitet wird
		boolean bBeruecksichtigeMandantenVermerke = bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_NO();
		
		
		this.add_Component(TR__LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(TR__LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));


		//2014-04-23: sprung-button in fuhren einfuegen
		TR__LIST_Grid_formatable   oGridButtons = new TR__LIST_Grid_formatable(2,60);
		oGridButtons.add_Component(new TR__LIST_CB_AktivAnAus(oFM.get_(_DB.HANDELSDEF$AKTIV)), 	new MyE2_String("Aktiv"),null)									._setWidth(60,60);
		oGridButtons.add_Component(new MyE2_Label(""), 											new MyE2_String(""),	TR__CONST.KEY_BT_EMPTY_BLOCK)			._setWidth(60,60);
		oGridButtons.add_Component(new TR__LIST_BT_JumpToFuhren(new Integer(10)),				new MyE2_String("Sprünge"),TR__CONST.KEY_BT_JUMP_TO_FUHREN10)	._setWidth(60,60);
		oGridButtons.add_Component(new TR__LIST_BT_JumpToFuhren(null), 							new MyE2_String(""),TR__CONST.KEY_BT_JUMP_TO_FUHREN)			._setWidth(60,60);
		oGridButtons.add_Component(new TR__LIST_BT_DirektEditWithFuhrenView(), 					new MyE2_String("Sprung"),TR__CONST.KEY_BT_EDIT_MASK_DIREKT)	._setWidth(60,60);
		this.add_Component(TR__CONST.KEY_BLOCK_BUTTONS,oGridButtons,new MyE2_String("Buttons"))																	._setWidth(60, 60);

		//2019-02-18: info und schalter zu mehrfachstatus
		this.add_Component((TR__LIST_BtShowSameMatchDatasets)new TR__LIST_BtShowSameMatchDatasets()._setLongText4ColumnSelection(S.ms("M: Anzeige des Mehrfachstatus"))
									._setGridLayout4List(new RB_gld()._center_top()._ins(2)))																	._setWidth(60,60);
		
		
		//linke seite
		MyE2_DB_MultiComponentGrid   oGridLaenderQUELL = new MyE2_DB_MultiComponentGrid(1,40);
		oGridLaenderQUELL.add_Component(new DB_Component_LAND_DROPDOWN_LabelInList(oFM.get_(HANDELSDEF.id_land_quelle_jur),50,true,true), 	new MyE2_String("Quelle JUR"),null)._setWidth(50,50);
		oGridLaenderQUELL.add_Component(new DB_Component_LAND_DROPDOWN_LabelInList(oFM.get_(HANDELSDEF.id_land_quelle_geo),50,true,true), 	new MyE2_String("Quelle GEO"),null)._setWidth(50,50);
		//oGridLaenderQUELL.EXT().set_oColExtent(new Extent(50));
		this.add_Component(TR__CONST.KEY_BLOCK_LAND_QUELLE,oGridLaenderQUELL,new MyE2_String("Länder Quellseite"))																._setWidth(50,50);
		


		if (bBeruecksichtigeMandantenVermerke) {
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$QUELLE_IST_MANDANT)), 					new MyE2_String("Quelle: Mand."))							._setWidth(50,50)
																																												._setGridLayout4List(new RB_gld()._center_top()._ins(0, 2, 0, 2));
		}
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_RC_QUELLE),70,true), 			new MyE2_String("Quelle: RC"))								._setWidth(50,50);

		
		MyE2_DB_MultiComponentGrid   oGridSorteQUELL = new MyE2_DB_MultiComponentGrid(1,40);
		oGridSorteQUELL.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_PRODUKT_QUELLE),70,true), 		new MyE2_String("Quelle:Prod."),null)			._setWidth(70,70);
		oGridSorteQUELL.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_EOW_QUELLE),70,true), 			new MyE2_String("EoW"),null)					._setWidth(70,70);
		oGridSorteQUELL.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_DIENSTLEIST_QUELLE),70,true), 	new MyE2_String("Dienstl."),null)				._setWidth(70,70);
//		oGridSorteQUELL.EXT().set_oColExtent(new Extent(50));
		this.add_Component(TR__CONST.KEY_BLOCK_SORTE_QUELLE,oGridSorteQUELL,new MyE2_String("Sortenmerkmale Quellseite"))														._setWidth(70,70);

		
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$UST_TEILNEHMER_QUELLE),70,true),	new MyE2_String("Quelle steuerl. Unterneh."))				._setWidth(60,60);
		this.add_Component(new TAX__DD_STEUERDEF(oFM.get_(_DB.HANDELSDEF$ID_TAX_QUELLE),80,true), 					new MyE2_String("Steuer Quelle"))							._setWidth(200,200);
		this.add_Component(new TAX__DD_STEUERDEF(oFM.get_(_DB.HANDELSDEF$ID_TAX_NEGATIV_QUELLE),80,true), 			new MyE2_String("Steuer Quelle (neg.Preis)"))				._setWidth(200,200);
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$INTRASTAT_MELD_IN)), 						new MyE2_String("Intrastat Einfuhr"))						._setWidth(60,60)._setGridLayout4List(new RB_gld()._center_top()._ins(0, 2, 0, 2));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$TRANSIT_EK)), 								new MyE2_String("Trans.EK"))								._setWidth(60,60)._setGridLayout4List(new RB_gld()._center_top()._ins(0, 2, 0, 2));
		
		
		//mitte
		this.add_Component(new TAX__DD_VERANTWORTUNG(oFM.get_(_DB.HANDELSDEF$TP_VERANTWORTUNG),120,true,true), 		new MyE2_String("TPA-Verantwort."))						._setWidth(120,120);
	
		
		//rechte seite
		MyE2_DB_MultiComponentGrid   oGridLaenderZIEL = new MyE2_DB_MultiComponentGrid(1);
		oGridLaenderZIEL.add_Component(new DB_Component_LAND_DROPDOWN_LabelInList(oFM.get_(HANDELSDEF.id_land_ziel_jur),50,true,true), 	new MyE2_String("Ziel JUR"),null)._setWidth(50,50);
		oGridLaenderZIEL.add_Component(new DB_Component_LAND_DROPDOWN_LabelInList(oFM.get_(HANDELSDEF.id_land_ziel_geo),50,true,true), 	new MyE2_String("Ziel GEO"),null)._setWidth(50,50);
		this.add_Component(TR__CONST.KEY_BLOCK_LAND_ZIEL,oGridLaenderZIEL,new MyE2_String("Länder Zielseite"))._setWidth(50,50);
		
		if (bBeruecksichtigeMandantenVermerke) {
			this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$ZIEL_IST_MANDANT)), 					new MyE2_String("Ziel: Mand."))						._setWidth(50,50)._setGridLayout4List(new RB_gld()._center_top()._ins(0, 2, 0, 2));
		}
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_RC_ZIEL), 70,true), 			new MyE2_String("Ziel: RC"))						._setWidth(50,50);
		
		MyE2_DB_MultiComponentGrid   oGridSorteZIEL = new MyE2_DB_MultiComponentGrid(1,40);
		oGridSorteZIEL.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_PRODUKT_ZIEL),70,true), 		new MyE2_String("Ziel:Prod."),null)		._setWidth(70,70);
		oGridSorteZIEL.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_EOW_ZIEL),70,true), 			new MyE2_String("EoW"),null)			._setWidth(70,70);
		oGridSorteZIEL.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_DIENSTLEIST_ZIEL),70,true), 	new MyE2_String("Dienstl."),null)		._setWidth(70,70);
//		oGridSorteZIEL.EXT().set_oColExtent(new Extent(50));
		this.add_Component(TR__CONST.KEY_BLOCK_SORTE_ZIEL,oGridSorteZIEL,new MyE2_String("Sortenmerkmale Zielseite"))													._setWidth(70,70);

		
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$UST_TEILNEHMER_ZIEL),70,true), 		new MyE2_String("Ziel steu. Untern."))				._setWidth(60,60);
		this.add_Component(new TAX__DD_STEUERDEF(oFM.get_(_DB.HANDELSDEF$ID_TAX_ZIEL),80,true), 					new MyE2_String("Steuer Ziel"))						._setWidth(200,200);
		this.add_Component(new TAX__DD_STEUERDEF(oFM.get_(_DB.HANDELSDEF$ID_TAX_NEGATIV_ZIEL),80,true), 			new MyE2_String("Steuer Ziel (neg.Preis)"))			._setWidth(200,200);
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$INTRASTAT_MELD_OUT)), 						new MyE2_String("Intrastat Ausfuhr"))				._setWidth(60,60)._setGridLayout4List(new RB_gld()._center_top()._ins(0, 2, 0, 2));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$TRANSIT_VK)), 								new MyE2_String("Trans.VK"))						._setWidth(60,60)._setGridLayout4List(new RB_gld()._center_top()._ins(0, 2, 0, 2));

		
		//Anhang 
		this.add_Component(new MyE2_DB_SelectField_LabelInList(oFM.get_(_DB.HANDELSDEF$TYP_MELDUNG),	TAX_CONST.MELDUNG_DD_ARRAY, true, new Extent(200),true), new MyE2_String("Info-Typ"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.HANDELSDEF$MELDUNG_FUER_USER),true), 				new MyE2_String("Benutzer-Info"))					._setWidth(200,200);
		
		RB_StyleBT styleButton = new RB_StyleBT()._defaultText()._alRight();
		
		//styleButton.setProperty(Button.PROPERTY_ALIGNMENT, new Alignment(Alignment.CENTER, Alignment.CENTER));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.HANDELSDEF$ID_HANDELSDEF)), 						new MyE2_String("ID"))								._setWidth(50,50)
																																._setGridLayout4List(new RB_gld()._right_top()._ins(0, 2, 0, 2))
																																._setGridLayout4ListTitle(new RB_gld()._right_top()._ins(0, 2, 0, 2)._col_back_d())
																																._setStyleForSortButton(styleButton);	
		
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_LAND_QUELLE_JUR).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("LQJ.LAENDERCODE");
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_LAND_QUELLE_JUR).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("LQJ.LAENDERCODE DESC");
		
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_LAND_QUELLE_GEO).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("LQG.LAENDERCODE");
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_LAND_QUELLE_GEO).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("LQG.LAENDERCODE DESC");
		
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("LZJ.LAENDERCODE");
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("LZJ.LAENDERCODE DESC");
		
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("LZG.LAENDERCODE");
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("LZG.LAENDERCODE DESC");
		
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_TAX_QUELLE).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("TQ.DROPDOWN_TEXT");
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_TAX_QUELLE).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("TQ.DROPDOWN_TEXT DESC");
		
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_TAX_ZIEL).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("TZ.DROPDOWN_TEXT");
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$ID_TAX_ZIEL).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN("TZ.DROPDOWN_TEXT DESC");
		
		
		
		
		

		//2018-07-18: sortierbarkeit der TP-Verantwortung
		//felder, die auf static-const-texten basieren, duerfen nicht sortierbar sein, da die sortierung evtl. irritiert
		//this.get_hmRealDBComponents().get(_DB.HANDELSDEF$TP_VERANTWORTUNG).EXT_DB().set_bIsSortable(false);
		
		//sortierung der HANDELSDEF$TP_VERANTWORTUNG steuern
		String sortString = "("+
				"CASE "+
				"  WHEN JT_HANDELSDEF.TP_VERANTWORTUNG=CSCONVERT('MANDANT','NCHAR_CS') THEN (SELECT NVL(JD_MANDANT.NAME1,'-')||' '||NVL(JD_MANDANT.NAME2,'-') FROM JD_MANDANT WHERE JD_MANDANT.ID_MANDANT=JT_HANDELSDEF.ID_MANDANT) "+
				"  WHEN JT_HANDELSDEF.TP_VERANTWORTUNG=CSCONVERT('QUELLE','NCHAR_CS') THEN CSCONVERT('Lieferant','NCHAR_CS') "+
				"  WHEN JT_HANDELSDEF.TP_VERANTWORTUNG=CSCONVERT('ZIEL','NCHAR_CS') THEN CSCONVERT('Abnehmer','NCHAR_CS') "+
				"  ELSE CSCONVERT('-','NCHAR_CS') "+
				" END "+
				") "
				;
		
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$TP_VERANTWORTUNG).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN(sortString+" DESC ");
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$TP_VERANTWORTUNG).EXT_DB().set_cSortAusdruckFuerSortbuttonUP(sortString+" ASC ");
		// -- 2018-07-18 --fertig
		
//		this.get__DBComp(_DB.HANDELSDEF$ART_GESCHAEFT).EXT_DB().set_bIsSortable(false);
		this.get_hmRealDBComponents().get(_DB.HANDELSDEF$TYP_MELDUNG).EXT_DB().set_bIsSortable(false);
		
		
		//zeilenumbruch fuer listenkopf einstellen
		for (MyE2IF__Component oComp: this.get_hmRealComponents().values())
		{
			oComp.EXT().set_bLineWrapListHeader(true);
		}
		
		
		//this.add_oSubQueryAgent(new E2_QUERYAGENT_MarkiereInaktiveInNaviList("AKTIV",Color.DARKGRAY,Color.BLACK));

	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		
		E2_ComponentMAP oRueck = new E2_ComponentMAP(this.get_oSQLFieldMAP());
		E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);

		oRueck.setComponentMapMarker(new TR__LIST_ComponentMapMarker(oRueck));
		
		return oRueck;

	}

	
//	public void mark_if_activ() throws myException {
//		boolean b_is_checked=false;
//		
//		if (this.get_oInternalSQLResultMAP()!=null && this.get_oInternalSQLResultMAP().get_booleanActualValue(HANDELSDEF.aktiv.fn())) {
//			b_is_checked=true;
//		}
//
//		Color  colHighlight = new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT();
//		E2_ComponentMAP_TOOLS.SetBackgroundColorInList(this, b_is_checked?colHighlight:null);
//	}
//	

	
	
	
	
}
