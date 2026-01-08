 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;
  
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
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_FIELD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORTING_QUERY_FIELD;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class RQF_LIST_ComponentMap extends E2_ComponentMAP  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public RQF_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new RQF_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(RQF_CONST.RQF_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(RQF_CONST.RQF_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(RQF_CONST.RQF_NAMES.DIRECT_DEL.db_val(),    	new RQF_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(RQF_CONST.RQF_NAMES.DIRECT_EDIT.db_val(),   	new RQF_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(RQF_CONST.RQF_NAMES.DIRECT_VIEW.db_val(),   	new RQF_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(REPORTING_QUERY_FIELD.aktiv),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.aktiv)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_FIELD.sortierfolge),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.sortierfolge)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_FIELD.fieldname),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.fieldname)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_FIELD.fieldname_4_user),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.fieldname_4_user)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_FIELD.alignment),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.alignment)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_FIELD.breite_liste_px),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.breite_liste_px)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(REPORTING_QUERY_FIELD.is_searchfield),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.is_searchfield)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(REPORTING_QUERY_FIELD.is_selectorfield),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.is_selectorfield)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_FIELD.id_reporting_query),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.id_reporting_query)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY_FIELD.id_reporting_query_field),true),     new MyE2_String(RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.id_reporting_query_field)));
        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,REPORTING_QUERY_FIELD.aktiv.fn()
                                  ,REPORTING_QUERY_FIELD.alignment.fn()
                                  ,REPORTING_QUERY_FIELD.breite_liste_px.fn()
                                  ,REPORTING_QUERY_FIELD.fieldname.fn()
                                  ,REPORTING_QUERY_FIELD.fieldname_4_user.fn()
                                  ,REPORTING_QUERY_FIELD.id_reporting_query.fn()
                                  ,REPORTING_QUERY_FIELD.id_reporting_query_field.fn()
                                  ,REPORTING_QUERY_FIELD.is_searchfield.fn()
                                  ,REPORTING_QUERY_FIELD.is_selectorfield.fn()
        );
        
        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
                                 ,REPORTING_QUERY_FIELD.aktiv.fn()
                                 ,REPORTING_QUERY_FIELD.alignment.fn()
                                 ,REPORTING_QUERY_FIELD.breite_liste_px.fn()
                                 ,REPORTING_QUERY_FIELD.fieldname.fn()
                                 ,REPORTING_QUERY_FIELD.fieldname_4_user.fn()
                                 ,REPORTING_QUERY_FIELD.id_reporting_query.fn()
                                 ,REPORTING_QUERY_FIELD.id_reporting_query_field.fn()
                                 ,REPORTING_QUERY_FIELD.is_searchfield.fn()
                                 ,REPORTING_QUERY_FIELD.is_selectorfield.fn()
      	);
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelCenter
                                 ,REPORTING_QUERY_FIELD.aktiv.fn()
                                 ,REPORTING_QUERY_FIELD.alignment.fn()
                                 ,REPORTING_QUERY_FIELD.breite_liste_px.fn()
                                 ,REPORTING_QUERY_FIELD.fieldname.fn()
                                 ,REPORTING_QUERY_FIELD.fieldname_4_user.fn()
                                 ,REPORTING_QUERY_FIELD.id_reporting_query.fn()
                                 ,REPORTING_QUERY_FIELD.id_reporting_query_field.fn()
                                 ,REPORTING_QUERY_FIELD.is_searchfield.fn()
                                 ,REPORTING_QUERY_FIELD.is_selectorfield.fn()
      	);
  
  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.aktiv.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.aktiv)), REPORTING_QUERY_FIELD.aktiv.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.aktiv))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.aktiv.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.aktiv))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.aktiv.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.alignment.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.alignment)), REPORTING_QUERY_FIELD.alignment.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.alignment))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.alignment.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.alignment))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.alignment.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.breite_liste_px.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.breite_liste_px)), REPORTING_QUERY_FIELD.breite_liste_px.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.breite_liste_px))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.breite_liste_px.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.breite_liste_px))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.breite_liste_px.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.fieldname.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.fieldname)), REPORTING_QUERY_FIELD.fieldname.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.fieldname))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.fieldname.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.fieldname))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.fieldname.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.fieldname_4_user.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.fieldname_4_user)), REPORTING_QUERY_FIELD.fieldname_4_user.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.fieldname_4_user))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.fieldname_4_user.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.fieldname_4_user))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.fieldname_4_user.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.id_reporting_query.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.id_reporting_query)), REPORTING_QUERY_FIELD.id_reporting_query.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.id_reporting_query))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.id_reporting_query.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.id_reporting_query))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.id_reporting_query.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.id_reporting_query_field.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.id_reporting_query_field)), REPORTING_QUERY_FIELD.id_reporting_query_field.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.id_reporting_query_field))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.id_reporting_query_field.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.id_reporting_query_field))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.id_reporting_query_field.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.is_searchfield.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.is_searchfield)), REPORTING_QUERY_FIELD.is_searchfield.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.is_searchfield))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.is_searchfield.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.is_searchfield))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.is_searchfield.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY_FIELD.is_selectorfield.fn()
        this._setColExtent(     new Extent(RQF_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY_FIELD.is_selectorfield)), REPORTING_QUERY_FIELD.is_selectorfield.fn());
        this._setLayoutElements(new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.is_selectorfield))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.is_selectorfield.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQF_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY_FIELD.is_selectorfield))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY_FIELD.is_selectorfield.fn());
        // ----
        //
      	
        this.set_oSubQueryAgent(new RQF_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_REPORTING_QUERY_FIELD(cID_MAINTABLE);
        }
    }
    
  
    
}
 
 
