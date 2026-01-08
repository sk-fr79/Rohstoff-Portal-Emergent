 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORTING_QUERY_PARAM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class RQP_LIST_ComponentMap extends E2_ComponentMAP  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public RQP_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new RQP_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(RQP_CONST.RQP_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(RQP_CONST.RQP_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(RQP_CONST.RQP_NAMES.DIRECT_DEL.db_val(),    	new RQP_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(RQP_CONST.RQP_NAMES.DIRECT_EDIT.db_val(),   	new RQP_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(RQP_CONST.RQP_NAMES.DIRECT_VIEW.db_val(),   	new RQP_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_PARAM.paramkey),true),     				new MyE2_String(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramkey)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_PARAM.paramname_4_user),true),     		new MyE2_String(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramname_4_user)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_PARAM.paramdefault),true),     			new MyE2_String(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramdefault)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_PARAM.typ),true),     						new MyE2_String(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.typ)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_PARAM.id_reporting_query_param),true),     new MyE2_String(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.id_reporting_query_param)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_PARAM.id_reporting_query),true),     		new MyE2_String(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.id_reporting_query)));

        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,REPORTING_QUERY_PARAM.id_reporting_query.fn()
                                  ,REPORTING_QUERY_PARAM.id_reporting_query_param.fn()
                                  ,REPORTING_QUERY_PARAM.paramdefault.fn()
                                  ,REPORTING_QUERY_PARAM.paramkey.fn()
                                  ,REPORTING_QUERY_PARAM.paramname_4_user.fn()
                                  ,REPORTING_QUERY_PARAM.typ.fn()
        );
        
//        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
//       	this._setLayoutElements(gldElementCenter
//                                 ,REPORTING_QUERY_PARAM.id_reporting_query.fn()
//                                 ,REPORTING_QUERY_PARAM.id_reporting_query_param.fn()
//                                 ,REPORTING_QUERY_PARAM.paramdefault.fn()
//                                 ,REPORTING_QUERY_PARAM.paramkey.fn()
//                                 ,REPORTING_QUERY_PARAM.paramname_4_user.fn()
//                                 ,REPORTING_QUERY_PARAM.typ.fn()
//      	);
//      	
//      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
//       	this._setLayoutTitles(gldTitelCenter
//                                 ,REPORTING_QUERY_PARAM.id_reporting_query.fn()
//                                 ,REPORTING_QUERY_PARAM.id_reporting_query_param.fn()
//                                 ,REPORTING_QUERY_PARAM.paramdefault.fn()
//                                 ,REPORTING_QUERY_PARAM.paramkey.fn()
//                                 ,REPORTING_QUERY_PARAM.paramname_4_user.fn()
//                                 ,REPORTING_QUERY_PARAM.typ.fn()
//      	);
  
  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  REPORTING_QUERY_PARAM.id_reporting_query.fn()
        this._setColExtent(     new Extent(RQP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_PARAM.id_reporting_query)), REPORTING_QUERY_PARAM.id_reporting_query.fn());
        this._setLayoutElements(new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.id_reporting_query))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.id_reporting_query.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.id_reporting_query))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.id_reporting_query.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_PARAM.id_reporting_query_param.fn()
        this._setColExtent(     new Extent(RQP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_PARAM.id_reporting_query_param)), REPORTING_QUERY_PARAM.id_reporting_query_param.fn());
        this._setLayoutElements(new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.id_reporting_query_param))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.id_reporting_query_param.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.id_reporting_query_param))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.id_reporting_query_param.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_PARAM.paramdefault.fn()
        this._setColExtent(     new Extent(RQP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_PARAM.paramdefault)), REPORTING_QUERY_PARAM.paramdefault.fn());
        this._setLayoutElements(new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.paramdefault))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.paramdefault.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.paramdefault))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.paramdefault.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_PARAM.paramkey.fn()
        this._setColExtent(     new Extent(RQP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_PARAM.paramkey)), REPORTING_QUERY_PARAM.paramkey.fn());
        this._setLayoutElements(new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.paramkey))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.paramkey.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.paramkey))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.paramkey.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_PARAM.paramname_4_user.fn()
        this._setColExtent(     new Extent(RQP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_PARAM.paramname_4_user)), REPORTING_QUERY_PARAM.paramname_4_user.fn());
        this._setLayoutElements(new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.paramname_4_user))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.paramname_4_user.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.paramname_4_user))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.paramname_4_user.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_PARAM.typ.fn()
        this._setColExtent(     new Extent(RQP_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_PARAM.typ)), REPORTING_QUERY_PARAM.typ.fn());
        this._setLayoutElements(new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.typ))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.typ.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQP_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_PARAM.typ))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_PARAM.typ.fn());
        // ----
        //
      	
        this.set_oSubQueryAgent(new RQP_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_REPORTING_QUERY_PARAM(cID_MAINTABLE);
        }
    }
    
  
    
}
 
 
