 
package rohstoff.Echo2BusinessLogic._TaxOld.Changes;
  
  
import java.util.Collection;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  

public class TOC_LIST_ComponentMap extends E2_ComponentMAP_V2  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public TOC_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new TOC_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(TOC_CONST.TOC_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(TOC_CONST.TOC_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(TOC_CONST.TOC_NAMES.DIRECT_DEL.db_val(),    	new TOC_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(TOC_CONST.TOC_NAMES.DIRECT_EDIT.db_val(),   	new TOC_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(TOC_CONST.TOC_NAMES.DIRECT_VIEW.db_val(),   	new TOC_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(TOC_CONST.TOC_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(TOC_VALIDATORS.ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL_AENDERUNGEN.beschreibung),true),     				new MyE2_String(TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.beschreibung)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz),true),     					new MyE2_String(TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von),true),     				new MyE2_String(TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis),true),     				new MyE2_String(TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen),true),   new MyE2_String(TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel),true),     			new MyE2_String(TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel)));

  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  MWSTSCHLUESSEL_AENDERUNGEN.beschreibung.fn()
        this._setColExtent(     new Extent(TOC_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL_AENDERUNGEN.beschreibung)), MWSTSCHLUESSEL_AENDERUNGEN.beschreibung.fn());
        this._setLayoutElements(new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.beschreibung))._ins(3,1,3,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.beschreibung.fn());
        this._setLayoutTitles(  new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.beschreibung))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.beschreibung.fn());
        // ----
        //
        // spaltenlayout fuer:  MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis.fn()
        this._setColExtent(     new Extent(TOC_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis)), MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis.fn());
        this._setLayoutElements(new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis))._ins(3,1,3,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis.fn());
        this._setLayoutTitles(  new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis.fn());
        // ----
        //
        // spaltenlayout fuer:  MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von.fn()
        this._setColExtent(     new Extent(TOC_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von)), MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von.fn());
        this._setLayoutElements(new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von))._ins(3,1,3,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von.fn());
        this._setLayoutTitles(  new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von.fn());
        // ----
        //
        //
        // spaltenlayout fuer:  MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen.fn()
        this._setColExtent(     new Extent(TOC_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen)), MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen.fn());
        this._setLayoutElements(new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen))._ins(3,1,3,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen.fn());
        this._setLayoutTitles(  new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen.fn());
        // ----
        this._setColExtent(     new Extent(TOC_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel)), MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel.fn());
        this._setLayoutElements(new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel))._ins(3,1,3,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel.fn());
        this._setLayoutTitles(  new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel.fn());
       //
        // spaltenlayout fuer:  MWSTSCHLUESSEL_AENDERUNGEN.steuersatz.fn()
        this._setColExtent(     new Extent(TOC_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz)), MWSTSCHLUESSEL_AENDERUNGEN.steuersatz.fn());
        this._setLayoutElements(new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz))._ins(3,1,3,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.steuersatz.fn());
        this._setLayoutTitles(  new RB_gld()._al(TOC_READABLE_FIELD_NAME.getAlignment(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz))._ins(1,2,1,1)._col(new E2_ColorDark()), MWSTSCHLUESSEL_AENDERUNGEN.steuersatz.fn());
        // ----
      	
        this.set_oSubQueryAgent(new TOC_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
    }
    

    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V2 oRueck = new E2_ComponentMAP_V2(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new TOC_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V2 mit marker-option
     * korrespontiert mit  
     */
    public class TOC_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public TOC_LIST_ComponentMapMapMarker(E2_ComponentMAP_V2 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2)this.getMap();
    		
            try {
                Rec21 recTOC = map.getRec21();
                //hier koennen formatierungsoptionen hinterlegt werden
                
//                if (recTOC.is_no_db_val(MWSTSCHLUESSEL_AENDERUNGEN.aktiv)) {
//                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
//                    this.setTextColorInMap(v,Color.DARKGRAY);
//                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
}
 
 
