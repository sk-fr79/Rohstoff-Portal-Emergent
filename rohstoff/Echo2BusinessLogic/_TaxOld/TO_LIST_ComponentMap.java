 
package rohstoff.Echo2BusinessLogic._TaxOld;
  
  
import java.util.Collection;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey;
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
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.HMAP;
  
public class TO_LIST_ComponentMap extends E2_ComponentMAP_V2  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public TO_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new TO_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(TO_CONST.TO_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(TO_CONST.TO_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(TO_CONST.TO_NAMES.DIRECT_DEL.db_val(),    	new TO_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(TO_CONST.TO_NAMES.DIRECT_EDIT.db_val(),   	new TO_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(TO_CONST.TO_NAMES.DIRECT_VIEW.db_val(),   	new TO_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(TO_CONST.TO_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(TO_VALIDATORS.ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL.bezeichnung),true),     new MyE2_String(TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.bezeichnung)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL.kurzbezeichnung),true),     new MyE2_String(TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.kurzbezeichnung)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL.id_land),true),     new MyE2_String(TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.id_land)));
        
        this.add_Component(new LandFromId(oFM.get_(MWSTSCHLUESSEL.id_land)),S.ms("Land"));
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL.steuersatz),true),     new MyE2_String(TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.steuersatz)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(MWSTSCHLUESSEL.ist_standard),true),     new MyE2_String(TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.ist_standard)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL.id_mwstschluessel),true),     new MyE2_String(TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.id_mwstschluessel)));

        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  MWSTSCHLUESSEL.bezeichnung.fn()
        this._setColExtent(     new Extent(TO_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL.bezeichnung)), MWSTSCHLUESSEL.bezeichnung.fn());
        this._setLayoutElements(new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.bezeichnung))._ins(3,1,3,1)._col(new E2_ColorBase()), MWSTSCHLUESSEL.bezeichnung.fn());
        this._setLayoutTitles(  new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.bezeichnung))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL.bezeichnung.fn());
        // ----
        //
        // spaltenlayout fuer:  MWSTSCHLUESSEL.id_land.fn()
        this._setColExtent(     new Extent(TO_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL.id_land)), MWSTSCHLUESSEL.id_land.fn());
        this._setLayoutElements(new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.id_land))._ins(3,1,3,1)._col(new E2_ColorBase()), MWSTSCHLUESSEL.id_land.fn());
        this._setLayoutTitles(  new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.id_land))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL.id_land.fn());
        // ----
        //
        // spaltenlayout fuer:  MWSTSCHLUESSEL.id_mwstschluessel.fn()
        this._setColExtent(     new Extent(TO_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL.id_mwstschluessel)), MWSTSCHLUESSEL.id_mwstschluessel.fn());
        this._setLayoutElements(new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.id_mwstschluessel))._ins(3,1,3,1)._col(new E2_ColorBase()), MWSTSCHLUESSEL.id_mwstschluessel.fn());
        this._setLayoutTitles(  new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.id_mwstschluessel))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL.id_mwstschluessel.fn());
        // ----
        //
        // spaltenlayout fuer:  MWSTSCHLUESSEL.ist_standard.fn()
        this._setColExtent(     new Extent(TO_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL.ist_standard)), MWSTSCHLUESSEL.ist_standard.fn());
        this._setLayoutElements(new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.ist_standard))._ins(3,1,3,1)._col(new E2_ColorBase()), MWSTSCHLUESSEL.ist_standard.fn());
        this._setLayoutTitles(  new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.ist_standard))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL.ist_standard.fn());
        // ----
        //
        // spaltenlayout fuer:  MWSTSCHLUESSEL.kurzbezeichnung.fn()
        this._setColExtent(     new Extent(TO_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL.kurzbezeichnung)), MWSTSCHLUESSEL.kurzbezeichnung.fn());
        this._setLayoutElements(new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.kurzbezeichnung))._ins(3,1,3,1)._col(new E2_ColorBase()), MWSTSCHLUESSEL.kurzbezeichnung.fn());
        this._setLayoutTitles(  new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.kurzbezeichnung))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL.kurzbezeichnung.fn());
        // ----
        //
        // spaltenlayout fuer:  MWSTSCHLUESSEL.steuersatz.fn()
        this._setColExtent(     new Extent(TO_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL.steuersatz)), MWSTSCHLUESSEL.steuersatz.fn());
        this._setLayoutElements(new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.steuersatz))._ins(3,1,3,1)._col(new E2_ColorBase()), MWSTSCHLUESSEL.steuersatz.fn());
        this._setLayoutTitles(  new RB_gld()._al(TO_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL.steuersatz))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL.steuersatz.fn());
        // ----
        //
      	
        this.set_oSubQueryAgent(new TO_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
    }
    
    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V2 oRueck = new E2_ComponentMAP_V2(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new TO_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V2 mit marker-option
     * korrespontiert mit  
     */
    public class TO_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public TO_LIST_ComponentMapMapMarker(E2_ComponentMAP_V2 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2)this.getMap();
    		
            try {
                Rec21 recTO = map.getRec21();
                //hier koennen formatierungsoptionen hinterlegt werden
                
//                if (recTO.is_no_db_val(MWSTSCHLUESSEL.aktiv)) {
//                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
//                    this.setTextColorInMap(v,Color.DARKGRAY);
//                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
    
    private class LandFromId extends E2_DBLabelTranslateIdToText {
		/**
		 * @author martin
		 * @date 25.06.2020
		 *
		 * @param osqlField
		 * @throws myException
		 */
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
    
    
}
 
 
