 
package rohstoff.Echo2BusinessLogic._TAX.RATE_V2;
  
  
import java.util.Collection;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V22;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.E2_DBLabelTranslateIdToText;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.FIBU_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.HMAP;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
  
public class TX_LIST_ComponentMap extends E2_ComponentMAP_V22  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public TX_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new TX_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(TX_CONST.TX_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(TX_CONST.TX_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        this.add_Component(TX_CONST.TX_NAMES.DIRECT_DEL.db_val(),    	new TX_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(TX_CONST.TX_NAMES.DIRECT_EDIT.db_val(),   	new TX_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(TX_CONST.TX_NAMES.DIRECT_VIEW.db_val(),   	new TX_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(TX_CONST.TX_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(TX_VALIDATORS.ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX.id_tax),true),     		new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.id_tax)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TAX.aktiv),true),    				new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.aktiv)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TAX.historisch),true),     		new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.historisch)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX.dropdown_text),true),     	new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.dropdown_text)));
        this.add_Component(new LandFromId(oFM.get_(TAX.id_land)),    				 		new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.id_land)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX.steuersatz),true),     	new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.steuersatz)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX.steuervermerk),true),     	new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.steuervermerk)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX.info_text_user),true),     new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.info_text_user)));
        this.add_Component(new TX_LIST_UebersichtAusnahmen());
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX.wechseldatum),true),     	new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.wechseldatum)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX.steuersatz_neu),true),     new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.steuersatz_neu)));
        this.add_Component(new MyE2_DB_SelectField(oFM.get_("TAXTYP"), TAX_CONST.TAXTYP_DD_ARRAY, true),  new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.taxtyp)));
        this.add_Component(new TaxGroupFromId(oFM.get_(TAX.id_tax_group)),     				new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.id_tax_group)));
        this.add_Component(new FibuKontoFromId(oFM.get_(TAX.id_fibu_konto_gs)),   			new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.id_fibu_konto_gs)));
        this.add_Component(new FibuKontoFromId(oFM.get_(TAX.id_fibu_konto_re)),   			new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.id_fibu_konto_re)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TAX.leervermerk),true),     		new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.leervermerk)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX.sort),true),    			 new MyE2_String(TX_READABLE_FIELD_NAME.getReadableForList(TAX.sort)));

        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,TAX.aktiv.fn()
                                  ,TAX.dropdown_text.fn()
                                  ,TAX.historisch.fn()
                                  ,TAX.id_fibu_konto_gs.fn()
                                  ,TAX.id_fibu_konto_re.fn()
                                  ,TAX.id_land.fn()
                                  ,TAX.id_tax.fn()
                                  ,TAX.id_tax_group.fn()
                                  ,TAX.info_text_user.fn()
                                  ,TAX.leervermerk.fn()
                                  ,TAX.sort.fn()
                                  ,TAX.steuersatz.fn()
                                  ,TAX.steuersatz_neu.fn()
                                  ,TAX.steuervermerk.fn()
                                  ,TAX.taxtyp.fn()
                                  ,TAX.wechseldatum.fn()
        );
        
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  TAX.aktiv.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.aktiv)), TAX.aktiv.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.aktiv))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.aktiv.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.aktiv))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.aktiv.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.dropdown_text.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.dropdown_text)), TAX.dropdown_text.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.dropdown_text))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.dropdown_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.dropdown_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.dropdown_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.historisch.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.historisch)), TAX.historisch.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.historisch))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.historisch.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.historisch))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.historisch.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.id_fibu_konto_gs.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.id_fibu_konto_gs)), TAX.id_fibu_konto_gs.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_fibu_konto_gs))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.id_fibu_konto_gs.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_fibu_konto_gs))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.id_fibu_konto_gs.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.id_fibu_konto_re.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.id_fibu_konto_re)), TAX.id_fibu_konto_re.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_fibu_konto_re))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.id_fibu_konto_re.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_fibu_konto_re))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.id_fibu_konto_re.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.id_land.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.id_land)), TAX.id_land.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_land))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.id_land.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_land))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.id_land.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.id_tax.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.id_tax)), TAX.id_tax.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_tax))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.id_tax.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_tax))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.id_tax.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.id_tax_group.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.id_tax_group)), TAX.id_tax_group.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_tax_group))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.id_tax_group.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.id_tax_group))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.id_tax_group.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.info_text_user.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.info_text_user)), TAX.info_text_user.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.info_text_user))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.info_text_user.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.info_text_user))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.info_text_user.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.leervermerk.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.leervermerk)), TAX.leervermerk.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.leervermerk))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.leervermerk.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.leervermerk))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.leervermerk.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.sort.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.sort)), TAX.sort.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.sort))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.sort.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.sort))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.sort.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.steuersatz.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.steuersatz)), TAX.steuersatz.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.steuersatz))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.steuersatz.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.steuersatz))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.steuersatz.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.steuersatz_neu.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.steuersatz_neu)), TAX.steuersatz_neu.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.steuersatz_neu))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.steuersatz_neu.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.steuersatz_neu))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.steuersatz_neu.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.steuervermerk.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.steuervermerk)), TAX.steuervermerk.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.steuervermerk))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.steuervermerk.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.steuervermerk))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.steuervermerk.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.taxtyp.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.taxtyp)), TAX.taxtyp.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.taxtyp))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.taxtyp.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.taxtyp))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.taxtyp.fn());
        // ----
        //
        // spaltenlayout fuer:  TAX.wechseldatum.fn()
        this._setColExtent(     new Extent(TX_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TAX.wechseldatum)), TAX.wechseldatum.fn());
        this._setLayoutElements(new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.wechseldatum))._ins(3,1,3,1)._col(new E2_ColorBase()), TAX.wechseldatum.fn());
        this._setLayoutTitles(  new RB_gld()._al(TX_READABLE_FIELD_NAME.getAlignment(TAX.wechseldatum))._ins(1,2,1,1)._col(new E2_ColorDark()), TAX.wechseldatum.fn());
        // ----
        //
        
        // spaltenlayout fuer:  uebersicht ueber die ausnahmen
        this._setColExtent(     new Extent(120), TX_LIST_UebersichtAusnahmen.KeyName);
        this._setLayoutElements(new RB_gld()._al(E2_ALIGN.CENTER_TOP())._ins(3,10,3,1)._col(new E2_ColorBase()), TX_LIST_UebersichtAusnahmen.KeyName);
        this._setLayoutTitles(  new RB_gld()._al(E2_ALIGN.CENTER_TOP())._ins(1,2,1,1)._col(new E2_ColorDark()), TX_LIST_UebersichtAusnahmen.KeyName);
        // ----
        //
      	
        this.set_oSubQueryAgent(new TX_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.setComponentMapMarker(new TX_LIST_ComponentMapMapMarker(this));
        
    }
    

    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V22 oRueck = new E2_ComponentMAP_V22(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new TX_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V22 mit marker-option
     * korrespontiert mit  
     */
    public class TX_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public TX_LIST_ComponentMapMapMarker(E2_ComponentMAP_V22 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V22 map = (E2_ComponentMAP_V22)this.getMap();
    		
            try {
                Rec22 recTX = map.getRec22();
                if (recTX.getUfs(TAX.aktiv, "N").equals("N")) {
                	this.setTextColorInMap(v,Color.DARKGRAY);
                }
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
    private class LandFromId extends E2_DBLabelTranslateIdToText {

    	public LandFromId(SQLField osqlField) throws myException {
			super(osqlField);
			this._setTab(_TAB.land)._addFieldsToShow(new HMAP<IF_Field,String>()._put(LAND.laendername, "<name>")._put(LAND.laendercode, "-"));
		}

		@Override
		public void set_cActualMaskValue(String s_id) throws myException {
			
			Long id = null;
			
			if (S.isFull(s_id)) {
				MyLong l = new MyLong(s_id); 
				if (l.isOK()) {
					id=l.getLong();
				}
			}
				
			if (id!=null) {
				Rec21 r = new Rec21(_TAB.land)._fill_id(id);
				if (r.is_ExistingRecord()) {
					this.setText(r.getFs(LAND.laendername,r.getFs(LAND.laendercode)));
					
				} else {
					this.setText("-");
				}
			} else {
				this.setText("-");
			}
			
		}

		@Override
		public Object get_Copy(Object ob) throws myExceptionCopy {
			try {
				LandFromId cop = new LandFromId(this.EXT_DB().get_oSQLField());
				cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
				return cop;

			} catch (myException e) {
				e.printStackTrace();
				return new MyE2_Label("-");
			}
		}
    }


    
    private class TaxGroupFromId extends E2_DBLabelTranslateIdToText {

    	public TaxGroupFromId(SQLField osqlField) throws myException {
			super(osqlField);
			this._setTab(_TAB.tax_group)._addFieldsToShow(new HMAP<IF_Field,String>()._put(TAX_GROUP.kurztext, "<kurz>"));
		}


		@Override
		public Object get_Copy(Object ob) throws myExceptionCopy {
			try {
				TaxGroupFromId cop = new TaxGroupFromId(this.EXT_DB().get_oSQLField());
				cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
				return cop;

			} catch (myException e) {
				e.printStackTrace();
				return new MyE2_Label("-");
			}
		}
    }

    
    
    private class FibuKontoFromId extends E2_DBLabelTranslateIdToText {

    	public FibuKontoFromId(SQLField osqlField) throws myException {
			super(osqlField);
			this._setTab(_TAB.fibu_konto)._addFieldsToShow(new HMAP<IF_Field,String>()._put(FIBU_KONTO.beschreibung, "<Bescheibung>")._put(FIBU_KONTO.konto, "<Konto>"));
			this._setSeparator(": ")._lw();
			this._setAlignment(Alignment.ALIGN_LEFT);
		}


		@Override
		public Object get_Copy(Object ob) throws myExceptionCopy {
			try {
				FibuKontoFromId cop = new FibuKontoFromId(this.EXT_DB().get_oSQLField());
				cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
				return cop;

			} catch (myException e) {
				e.printStackTrace();
				return new MyE2_Label("-");
			}
		}
    }

    
}
 
 
