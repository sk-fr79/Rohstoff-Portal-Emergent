
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_PALETTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.LH_CONST;

public class LH_P_LIST_ComponentMap extends E2_ComponentMAP_V2  {

	private RB_TransportHashMap   m_tpHashMap = null;

	public LH_P_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap, SQLFieldMAP map) throws myException  {

		super(map==null?new LH_P_LIST_SqlFieldMAP(p_tpHashMap):map);

		this.m_tpHashMap = p_tpHashMap;        

		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();

		this.add_Component(LH_P_CONST.LH_P_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(LH_P_CONST.LH_P_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier optionale spalten fuer direktes loeschen/edit/view
		this.add_Component(LH_P_CONST.LH_P_NAMES.DIRECT_DEL.db_val(),	new LH_P_LIST_bt_DeleteInListRow(this.m_tpHashMap)
				._setGridLayout4List(new RB_gld()._ins(4))
				._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
				S.ms("?"));

		this.add_Component(LH_P_CONST.LH_P_NAMES.DIRECT_EDIT.db_val(),	new LH_P_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
				._setGridLayout4List(new RB_gld()._ins(4))
				._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
				S.ms("?"));

		this.add_Component(LH_P_CONST.LH_P_NAMES.DIRECT_VIEW.db_val(),	new LH_P_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)

				._setGridLayout4List(new RB_gld()._ins(4)._center_top())
				._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
				S.ms("?"));
		if(map==null) {
			this.add_Component(LH_P_CONST.LH_P_NAMES.DIRECT_AUS_EIN_BUCHUNG.db_val(),	new LH_P_LIST_bt_AusbuchungInRow(this.m_tpHashMap)
					._setGridLayout4List(new RB_gld()._ins(4)._center_top())
					._setLongText4ColumnSelection(S.ms("Einbuchung/Ausbuchung"))
					,S.ms("?"));
		}
		LH_P_List_bt_showHistory oBtHistory = new LH_P_List_bt_showHistory();
		oBtHistory._setLDC(new RB_gld()._ins(4)._center_top());
		this.add_Component(LH_P_CONST.LH_P_NAMES.SHOW_HISTORY.db_val(),	oBtHistory
				._setGridLayout4List(new RB_gld()._ins(4)._center_top())
				._setLongText4ColumnSelection(S.ms("Palette Verlauf")),
				S.ms("?"));

		this.add_Component(LH_P_CONST.LH_P_NAMES.ETIKETT_DRUCKEN.db_val(), new LH_P_LIST_bt_DruckEtiketteInRow(m_tpHashMap)
				._setGridLayout4List(new RB_gld()._ins(4)._center_mid())
				._setLongText4ColumnSelection(S.ms("Etikett Drucken")),
				S.ms("?"));

		if(map!=null) {
			LH_P_LIST_COMP_einausFuhreDatum oEINbuchungsDatumcomp = new LH_P_LIST_COMP_einausFuhreDatum(true);
			oEINbuchungsDatumcomp._setLDC(new RB_gld()._ins(1,2,1,1)._center_top());
			this.add_Component(LH_P_CONST.ENUM_PALETTE_LISTKEY.EINBUCHUNGSDATUM_LISTKEY.k(),oEINbuchungsDatumcomp , S.ms("Einbuchungsdatum"));

			LH_P_LIST_COMP_einausFuhreDatum oAUSbuchungsDatumcomp = new LH_P_LIST_COMP_einausFuhreDatum(false);
			oAUSbuchungsDatumcomp._setLDC(new RB_gld()._ins(1,2,1,1)._center_top());
			this.add_Component(LH_P_CONST.ENUM_PALETTE_LISTKEY.AUSBUCHUNGSDATUM_LISTKEY.k(),oAUSbuchungsDatumcomp , S.ms("Ausbuchungsdatum"));
		}

		//hier kommen die Felder  
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.id_lager_palette),true),		S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_lager_palette)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.id_lager_box),true),     	S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_lager_box)));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAGER_PALETTE.ist_palette),true),     			S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.ist_palette)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.chargennummer),true),     	S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.chargennummer)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.datum_verarbeitet),true),    S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.datum_verarbeitet)));
//		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.id_artikel_bez),true),     	S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_artikel_bez)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LH_P_CONST.ENUM_PALETTE_LISTKEY.ARTIKEL_INFO.k()),true), S.ms("Material"));

		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAGER_PALETTE.einbuchung_hand),true),     		S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.einbuchung_hand)));
		this.add_Component(LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fk().get_HASHKEY(), new LH_P_LIST_bt_SprungZuFuhre(true), S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_vpos_tpa_fuhre_ein)));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(VPOS_TPA_FUHRE.ohne_abrechnung),true),         S.ms("WE ohne Abrechnung"));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAGER_PALETTE.ausbuchung_hand),true),     		S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.ausbuchung_hand)));
		this.add_Component(LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fk().get_HASHKEY(), new LH_P_LIST_bt_SprungZuFuhre(false) ,S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_vpos_tpa_fuhre_aus)));

		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.buchungsnr_hand),true),     		S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.buchungsnr_hand)));

		
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.bruttomenge),true),     		S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.bruttomenge)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.taramenge),true),     		S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.taramenge)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.nettomenge),true),     		S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.nettomenge)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.gepresst_von),true),     	S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.gepresst_von)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.palettiert_von),true),     	S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.palettiert_von)));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_PALETTE.endkontrolle_von),true),     S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.endkontrolle_von)));

		//		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("EINBUCHUNG_AM"),true),     S.ms("Einbuchung am"));
		//		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("AUSBUCHUNG_AM"),true),     S.ms("Ausbuchung am"));
		//		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("LAG_BOX_"+ LAGER_PALETTE_BOX.id_lager_box.fn()),true),     S.ms(LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_lager_box)));
		//neu ab 20171025        
		this._setLineWrapListHeader(true 
				,LAGER_PALETTE.bruttomenge.fn()
				,LAGER_PALETTE.chargennummer.fn()
				,LAGER_PALETTE.datum_verarbeitet.fn()
				,LAGER_PALETTE.endkontrolle_von.fn()
				,LAGER_PALETTE.gepresst_von.fn()
//				,LAGER_PALETTE.id_artikel_bez.fn()
				,LH_P_CONST.ENUM_PALETTE_LISTKEY.ARTIKEL_INFO.k()
				,LAGER_PALETTE.id_lager_box.fn()
				,LAGER_PALETTE.id_lager_palette.fn()
				,LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fn()
				
				,LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fn()
				,LAGER_PALETTE.ist_palette.fn()
				,LAGER_PALETTE.nettomenge.fn()
				,LAGER_PALETTE.palettiert_von.fn()
				,LAGER_PALETTE.taramenge.fn()
				,LAGER_PALETTE.einbuchung_hand.fn()
				,LAGER_PALETTE.ausbuchung_hand.fn()
				,LAGER_PALETTE.buchungsnr_hand.fn()
				);


		RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
		this._setLayoutTitles(gldTitelCenter
				,LAGER_PALETTE.chargennummer.fn()
				,LAGER_PALETTE.datum_verarbeitet.fn()
				,LAGER_PALETTE.id_lager_box.fn()
				,LAGER_PALETTE.id_lager_palette.fn()
				,LAGER_PALETTE.einbuchung_hand.fn()
				,LAGER_PALETTE.ausbuchung_hand.fn()
				,LAGER_PALETTE.ist_palette.fn()
				);

		RB_gld gldTitleLeft = new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorDark());
		this._setLayoutTitles(gldTitleLeft
				,LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fn()
				,LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fn()
//				,LAGER_PALETTE.id_artikel_bez.fn()
				,LH_P_CONST.ENUM_PALETTE_LISTKEY.ARTIKEL_INFO.k()
				,LAGER_PALETTE.endkontrolle_von.fn()
				,LAGER_PALETTE.gepresst_von.fn()
				,LAGER_PALETTE.palettiert_von.fn()
				,LAGER_PALETTE.buchungsnr_hand.fn()
				);

		RB_gld gldTitleRight = new RB_gld()._right_top()._ins(2,4,2,2)._col(new E2_ColorDark());
		this._setLayoutTitles(gldTitleRight
				,LAGER_PALETTE.bruttomenge.fn()
				,LAGER_PALETTE.taramenge.fn()
				,LAGER_PALETTE.nettomenge.fn()
				);


		RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
		this._setLayoutElements(gldElementCenter
				,LAGER_PALETTE.chargennummer.fn()
				,LAGER_PALETTE.datum_verarbeitet.fn()
				,LAGER_PALETTE.einbuchung_hand.fn()
				,LAGER_PALETTE.ausbuchung_hand.fn()
				,LAGER_PALETTE.buchungsnr_hand.fn()
				,LAGER_PALETTE.id_lager_box.fn()
				,LAGER_PALETTE.id_lager_palette.fn()
				,LAGER_PALETTE.ist_palette.fn()
				);

		RB_gld gldElementRight = new RB_gld()._right_top()._ins(2,4,2,2)._col(new E2_ColorBase());
		this._setLayoutElements(gldElementRight
				,LAGER_PALETTE.bruttomenge.fn()
				,LAGER_PALETTE.taramenge.fn()
				,LAGER_PALETTE.nettomenge.fn()
				);

		RB_gld gldElementLeft = new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
		this._setLayoutElements(gldElementLeft
//				,LAGER_PALETTE.id_artikel_bez.fn()
				,LH_P_CONST.ENUM_PALETTE_LISTKEY.ARTIKEL_INFO.k()
				,LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fn()
				,LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fn()
				,LAGER_PALETTE.endkontrolle_von.fn()
				,LAGER_PALETTE.gepresst_von.fn()
				,LAGER_PALETTE.palettiert_von.fn()
			
				);

		this._setColExtent(new Extent(25), LH_P_CONST.LH_P_NAMES.DIRECT_EDIT.db_val());
		this._setColExtent(new Extent(25), LH_P_CONST.LH_P_NAMES.DIRECT_VIEW.db_val());
		this._setColExtent(new Extent(25), LH_P_CONST.LH_P_NAMES.DIRECT_DEL.db_val());
		this._setColExtent(new Extent(25), LH_P_CONST.LH_P_NAMES.DIRECT_EDIT.db_val());
		this._setColExtent(new Extent(25), LH_P_CONST.LH_P_NAMES.DIRECT_EDIT.db_val());
		if(map == null) {
			this._setColExtent(new Extent(25), LH_P_CONST.LH_P_NAMES.DIRECT_AUS_EIN_BUCHUNG.db_val());
		}else {
			this._setLayoutElements(gldElementCenter,
					LH_P_CONST.ENUM_PALETTE_LISTKEY.EINBUCHUNGSDATUM_LISTKEY.k()
					,	LH_P_CONST.ENUM_PALETTE_LISTKEY.AUSBUCHUNGSDATUM_LISTKEY.k()
					);
			this._setLayoutTitles(gldTitelCenter,
					LH_P_CONST.ENUM_PALETTE_LISTKEY.EINBUCHUNGSDATUM_LISTKEY.k()
					,	LH_P_CONST.ENUM_PALETTE_LISTKEY.AUSBUCHUNGSDATUM_LISTKEY.k()
					);
			this._setLineWrapListHeader(true ,
					LH_P_CONST.ENUM_PALETTE_LISTKEY.EINBUCHUNGSDATUM_LISTKEY.k()
					,	LH_P_CONST.ENUM_PALETTE_LISTKEY.AUSBUCHUNGSDATUM_LISTKEY.k()
					);
		}

		this._setColExtent(new Extent(25), LH_P_CONST.LH_P_NAMES.SHOW_HISTORY.db_val());

		RB_gld gld_element_icon = new RB_gld()._center_mid()._ins(3,1,3,1)._col(new E2_ColorBase());

		this._setLayoutElements(gld_element_icon, 
				LH_P_CONST.LH_P_NAMES.DIRECT_EDIT.db_val()
				, LH_P_CONST.LH_P_NAMES.DIRECT_VIEW.db_val()
				, LH_P_CONST.LH_P_NAMES.DIRECT_DEL.db_val()
				, LH_P_CONST.LH_P_NAMES.SHOW_HISTORY.db_val()
				);
		if(map==null) {
			this._setLayoutElements(gld_element_icon, LH_P_CONST.LH_P_NAMES.DIRECT_AUS_EIN_BUCHUNG.db_val());
		}

//		this._setColExtent(new Extent(400), LAGER_PALETTE.id_artikel_bez.fn());
		this._setColExtent(new Extent(400), LH_P_CONST.ENUM_PALETTE_LISTKEY.ARTIKEL_INFO.k());
		
		this._setColExtent(new Extent(150),	LAGER_PALETTE.gepresst_von.fn());
		this._setColExtent(new Extent(150), LAGER_PALETTE.palettiert_von.fn());
		this._setColExtent(new Extent(150), LAGER_PALETTE.endkontrolle_von.fn());

		this.set_oSubQueryAgent(new LH_P_LIST_FORMATING_Agent(this.m_tpHashMap));

		this.set_Factory4Records(new factory4Records());
	}

	private class factory4Records extends E2_ComponentMAP_Factory4Records {
		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_LAGER_PALETTE(cID_MAINTABLE);
		}
	}
}


