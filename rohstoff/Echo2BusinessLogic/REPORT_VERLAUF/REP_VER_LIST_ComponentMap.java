package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;
  
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
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_LOG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class REP_VER_LIST_ComponentMap extends E2_ComponentMAP  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public REP_VER_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new REP_VER_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(REP_VER_CONST.REP_VER_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(REP_VER_CONST.REP_VER_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view

        this.add_Component(REP_VER_CONST.REP_VER_NAMES.DIRECT_VIEW.db_val(),   	new REP_VER_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																					new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_LOG.id_report_log),true),     	S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.id_report_log)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_LOG.report_datei_name),true),   S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_datei_name)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_LOG.report_druck_am),true),    	S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_druck_am)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_LOG.report_druck_von),true),    S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_druck_von)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_LOG.report_jasperfile),true),   S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_jasperfile)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_LOG.report_titel),true),    	S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_titel)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORT_LOG.report_weg),true),    		S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_weg)));

		this.add_Component(new REP_VER_LIST_groupByUuid(oFM.get_(REPORT_LOG.report_uuid)),S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_uuid)));
        
		this._setLineWrapListHeader(true 
                                  ,REPORT_LOG.id_report_log		.fn()
                                  ,REPORT_LOG.report_datei_name	.fn()
                                  ,REPORT_LOG.report_druck_am	.fn()
                                  ,REPORT_LOG.report_druck_von	.fn()
                                  ,REPORT_LOG.report_jasperfile	.fn()
                                  ,REPORT_LOG.report_uuid		.fn()
                                  ,REPORT_LOG.report_weg		.fn()
        );
        
        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
                                 ,REPORT_LOG.id_report_log.fn()
      	);
      	
        RB_gld gldTitelCenter= 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
                                 ,REPORT_LOG.id_report_log.fn()
      	);
       	
       	RB_gld gldElementLeft = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementLeft
                                 ,REPORT_LOG.report_datei_name	.fn()
                                 ,REPORT_LOG.report_druck_am	.fn()
                                 ,REPORT_LOG.report_druck_von	.fn()
                                 ,REPORT_LOG.report_jasperfile	.fn()
                                 ,REPORT_LOG.report_uuid		.fn()
                                 ,REPORT_LOG.report_weg			.fn()
      	);
       	
      	RB_gld gldTitelLeft = 	new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelLeft
                                 ,REPORT_LOG.report_datei_name	.fn()
                                 ,REPORT_LOG.report_druck_am	.fn()
                                 ,REPORT_LOG.report_druck_von	.fn()
                                 ,REPORT_LOG.report_jasperfile	.fn()
                                 ,REPORT_LOG.report_uuid		.fn()
                                 ,REPORT_LOG.report_weg			.fn()
      	);
  
        this._setColExtent(new Extent(50), 	REPORT_LOG.id_report_log	.fn());
        this._setColExtent(new Extent(400), REPORT_LOG.report_datei_name.fn());
        this._setColExtent(new Extent(150), REPORT_LOG.report_druck_am	.fn());
        this._setColExtent(new Extent(100), REPORT_LOG.report_druck_von	.fn());
        this._setColExtent(new Extent(400), REPORT_LOG.report_jasperfile.fn());
        this._setColExtent(new Extent(400), REPORT_LOG.report_uuid		.fn());
        this._setColExtent(new Extent(90), 	REPORT_LOG.report_weg		.fn());
      	
        this.set_oSubQueryAgent(new REP_VER_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_REPORT_LOG(cID_MAINTABLE);
        }
    }
    
  
    
}
 
 
