 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
  
  
import java.util.Collection;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V22;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  
public class WK_RB_CHILD_ABZUG_GEB_LIST_ComponentMap extends E2_ComponentMAP_V2  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WK_RB_CHILD_ABZUG_GEB_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new WK_RB_CHILD_ABZUG_GEB_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
//        this.add_Component(WK_RB_CHILD__CONST.WK_RB_CHILD__NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
//        this.add_Component(WK_RB_CHILD__CONST.WK_RB_CHILD__NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        
        this.add_Component(WK_RB_CHILD_ABZUG_GEB_CONST.WK_RB_CHILD__NAMES.DIRECT_EDIT.db_val(),   	new WK_RB_CHILD_ABZUG_GEB_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        		._setGridLayout4List(new RB_gld()._ins(4)._center_mid())
        		._setLongText4ColumnSelection(S.ms("Bearbeiten")),
        		new MyE2_String("?"));
        
        this.add_Component(WK_RB_CHILD_ABZUG_GEB_CONST.WK_RB_CHILD__NAMES.DIRECT_VIEW.db_val(),   	new WK_RB_CHILD_ABZUG_GEB_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
        		._setGridLayout4List(new RB_gld()._ins(4)._center_mid())
        		._setLongText4ColumnSelection(S.ms("Anzeigen")),
        		new MyE2_String("?"));
        
        this.add_Component(WK_RB_CHILD_ABZUG_GEB_CONST.WK_RB_CHILD__NAMES.DIRECT_DEL.db_val(),    	new WK_RB_CHILD_ABZUG_GEB_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        		._setGridLayout4List(new RB_gld()._ins(4)._center_mid())
        		._setLongText4ColumnSelection(S.ms("Löschen")),	
        		new MyE2_String("?"));
        


        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE_ABZUG_GEB.gebinde),true),     new MyE2_String(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.gebinde)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE_ABZUG_GEB.gewicht_einzel),true),     new MyE2_String(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.gewicht_einzel)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE_ABZUG_GEB.menge),true),     new MyE2_String(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.menge)));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE_ABZUG_GEB.id_wiegekarte),true),     new MyE2_String(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.id_wiegekarte)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb),true),     new MyE2_String(WK_RB_CHILD__READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde),true),     new MyE2_String(WK_RB_CHILD__READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde)));

  
  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  WIEGEKARTE_ABZUG_GEB.gebinde.fn()
        this._setColExtent(     new Extent(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE_ABZUG_GEB.gebinde)), WIEGEKARTE_ABZUG_GEB.gebinde.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE_ABZUG_GEB.gebinde))._ins(3,1,3,1)._col(new E2_ColorBase())._left_mid(), WIEGEKARTE_ABZUG_GEB.gebinde.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE_ABZUG_GEB.gebinde))._ins(1,2,1,1)._col(new E2_ColorDark())._left_mid(), WIEGEKARTE_ABZUG_GEB.gebinde.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE_ABZUG_GEB.gewicht_einzel.fn()
        this._setColExtent(     new Extent(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE_ABZUG_GEB.gewicht_einzel)), WIEGEKARTE_ABZUG_GEB.gewicht_einzel.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE_ABZUG_GEB.gewicht_einzel))._ins(3,1,3,1)._col(new E2_ColorBase())._right_mid(), WIEGEKARTE_ABZUG_GEB.gewicht_einzel.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE_ABZUG_GEB.gewicht_einzel))._ins(1,2,1,1)._col(new E2_ColorDark())._right_mid(), WIEGEKARTE_ABZUG_GEB.gewicht_einzel.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE_ABZUG_GEB.id_wiegekarte.fn()
        this._setColExtent(     new Extent(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE_ABZUG_GEB.id_wiegekarte)), WIEGEKARTE_ABZUG_GEB.id_wiegekarte.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE_ABZUG_GEB.id_wiegekarte))._ins(3,1,3,1)._col(new E2_ColorBase())._right_mid(), WIEGEKARTE_ABZUG_GEB.id_wiegekarte.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE_ABZUG_GEB.id_wiegekarte))._ins(1,2,1,1)._col(new E2_ColorDark())._right_mid(), WIEGEKARTE_ABZUG_GEB.id_wiegekarte.fn());
        this.get__Comp(WIEGEKARTE_ABZUG_GEB.id_wiegekarte).EXT().set_bIsVisibleInList(false);
        
        //
        // spaltenlayout fuer:  WIEGEKARTE_ABZUG_GEB.menge.fn()
        this._setColExtent(     new Extent(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE_ABZUG_GEB.menge)), WIEGEKARTE_ABZUG_GEB.menge.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE_ABZUG_GEB.menge))._ins(3,1,3,1)._col(new E2_ColorBase())._right_mid(), WIEGEKARTE_ABZUG_GEB.menge.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE_ABZUG_GEB.menge))._ins(1,2,1,1)._col(new E2_ColorDark())._right_mid(), WIEGEKARTE_ABZUG_GEB.menge.fn());
        // ----
        //

      	
        this.set_oSubQueryAgent(new WK_RB_CHILD_ABZUG_GEB_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
//        this.set_Factory4Records(new factory4Records());
    }
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_WIEGEKARTE_ABZUG_GEB(cID_MAINTABLE);
        }
    }
    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V2 oRueck = new E2_ComponentMAP_V2(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new WK_RB_CHILD__LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V2 mit marker-option
     * korrespontiert mit  
     */
    public class WK_RB_CHILD__LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public WK_RB_CHILD__LIST_ComponentMapMapMarker(E2_ComponentMAP_V2 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V22 map = (E2_ComponentMAP_V22)this.getMap();
    		
            try {
                Rec22 recWK_RB_CHILD_ = map.getRec22();
                //hier koennen formatierungsoptionen hinterlegt werden
                
//                if (recWK_RB_CHILD_.is_no_db_val(WIEGEKARTE_ABZUG_GEB.aktiv)) {
//                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
//                    this.setTextColorInMap(v,Color.DARKGRAY);
//                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
    
    
}
 
 
