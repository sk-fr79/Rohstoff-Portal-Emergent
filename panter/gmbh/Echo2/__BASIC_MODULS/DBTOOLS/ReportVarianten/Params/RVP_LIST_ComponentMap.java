 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.Params;
  
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
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN_PARAM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  
public class RVP_LIST_ComponentMap extends E2_ComponentMAP_V2  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public RVP_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new RVP_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(RVP_CONST.RVP_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(RVP_CONST.RVP_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(RVP_CONST.RVP_NAMES.DIRECT_DEL.db_val(),    	new RVP_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(RVP_CONST.RVP_NAMES.DIRECT_EDIT.db_val(),   	new RVP_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(RVP_CONST.RVP_NAMES.DIRECT_VIEW.db_val(),   	new RVP_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REP_VARIANTEN_PARAM.id_rep_varianten),true),     new MyE2_String(RVP_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN_PARAM.id_rep_varianten)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REP_VARIANTEN_PARAM.id_rep_varianten_param),true),     new MyE2_String(RVP_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN_PARAM.id_rep_varianten_param)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REP_VARIANTEN_PARAM.param_name),true),     new MyE2_String(RVP_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN_PARAM.param_name)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REP_VARIANTEN_PARAM.param_value),true),     new MyE2_String(RVP_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN_PARAM.param_value)));

        //neu ab 20171025        
        this._setLineWrapListHeader(true 
//                                  ,REP_VARIANTEN_PARAM.id_rep_varianten.fn()
                                  ,REP_VARIANTEN_PARAM.id_rep_varianten_param.fn()
                                  ,REP_VARIANTEN_PARAM.param_name.fn()
                                  ,REP_VARIANTEN_PARAM.param_value.fn()
        );
        
        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
//                                 ,REP_VARIANTEN_PARAM.id_rep_varianten.fn()
                                 ,REP_VARIANTEN_PARAM.id_rep_varianten_param.fn()
                                 ,REP_VARIANTEN_PARAM.param_name.fn()
                                 ,REP_VARIANTEN_PARAM.param_value.fn()
      	);
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelCenter
//                                 ,REP_VARIANTEN_PARAM.id_rep_varianten.fn()
                                 ,REP_VARIANTEN_PARAM.id_rep_varianten_param.fn()
                                 ,REP_VARIANTEN_PARAM.param_name.fn()
                                 ,REP_VARIANTEN_PARAM.param_value.fn()
      	);
  
  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  REP_VARIANTEN_PARAM.id_rep_varianten.fn()
//        this._setColExtent(     new Extent(RVP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REP_VARIANTEN_PARAM.id_rep_varianten)), REP_VARIANTEN_PARAM.id_rep_varianten.fn());
//        this._setLayoutElements(new RB_gld()._al(RVP_READABLE_FIELD_NAME.getAlignment(REP_VARIANTEN_PARAM.id_rep_varianten))._ins(3,1,3,1)._col(new E2_ColorDark()), REP_VARIANTEN_PARAM.id_rep_varianten.fn());
//        this._setLayoutTitles(  new RB_gld()._al(RVP_READABLE_FIELD_NAME.getAlignment(REP_VARIANTEN_PARAM.id_rep_varianten))._ins(1,2,1,1)._col(new E2_ColorDark()), REP_VARIANTEN_PARAM.id_rep_varianten.fn());
        // ----
        //
        // spaltenlayout fuer:  REP_VARIANTEN_PARAM.id_rep_varianten_param.fn()
        this._setColExtent(     new Extent(RVP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REP_VARIANTEN_PARAM.id_rep_varianten_param)), REP_VARIANTEN_PARAM.id_rep_varianten_param.fn());
        this._setLayoutElements(new RB_gld()._al(RVP_READABLE_FIELD_NAME.getAlignment(REP_VARIANTEN_PARAM.id_rep_varianten_param))._ins(3,1,3,1)._col(new E2_ColorDark()), REP_VARIANTEN_PARAM.id_rep_varianten_param.fn());
        this._setLayoutTitles(  new RB_gld()._al(RVP_READABLE_FIELD_NAME.getAlignment(REP_VARIANTEN_PARAM.id_rep_varianten_param))._ins(1,2,1,1)._col(new E2_ColorDark()), REP_VARIANTEN_PARAM.id_rep_varianten_param.fn());
        // ----
        //
        // spaltenlayout fuer:  REP_VARIANTEN_PARAM.param_name.fn()
        this._setColExtent(     new Extent(RVP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REP_VARIANTEN_PARAM.param_name)), REP_VARIANTEN_PARAM.param_name.fn());
        this._setLayoutElements(new RB_gld()._al(RVP_READABLE_FIELD_NAME.getAlignment(REP_VARIANTEN_PARAM.param_name))._ins(3,1,3,1)._col(new E2_ColorDark()), REP_VARIANTEN_PARAM.param_name.fn());
        this._setLayoutTitles(  new RB_gld()._al(RVP_READABLE_FIELD_NAME.getAlignment(REP_VARIANTEN_PARAM.param_name))._ins(1,2,1,1)._col(new E2_ColorDark()), REP_VARIANTEN_PARAM.param_name.fn());
        // ----
        //
        // spaltenlayout fuer:  REP_VARIANTEN_PARAM.param_value.fn()
        this._setColExtent(     new Extent(RVP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REP_VARIANTEN_PARAM.param_value)), REP_VARIANTEN_PARAM.param_value.fn());
        this._setLayoutElements(new RB_gld()._al(RVP_READABLE_FIELD_NAME.getAlignment(REP_VARIANTEN_PARAM.param_value))._ins(3,1,3,1)._col(new E2_ColorDark()), REP_VARIANTEN_PARAM.param_value.fn());
        this._setLayoutTitles(  new RB_gld()._al(RVP_READABLE_FIELD_NAME.getAlignment(REP_VARIANTEN_PARAM.param_value))._ins(1,2,1,1)._col(new E2_ColorDark()), REP_VARIANTEN_PARAM.param_value.fn());
        // ----
        //
      	
        this.set_oSubQueryAgent(new RVP_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
    }
    
    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V2 oRueck = new E2_ComponentMAP_V2(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new RVP_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V2 mit marker-option
     * korrespontiert mit  
     */
    public class RVP_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public RVP_LIST_ComponentMapMapMarker(E2_ComponentMAP_V2 p_map) {
            super(p_map);
        }

        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2)this.getMap();
    		
            try {
                Rec21 recRVP = map.getRec21();
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
}
 
 
