 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
  
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORTING_QUERY;
import panter.gmbh.indep.F;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  
public class RQ_LIST_ComponentMap extends E2_ComponentMAP  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public RQ_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new RQ_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(RQ_CONST.RQ_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(RQ_CONST.RQ_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        
        this.add_Component(RQ_CONST.RQ_NAMES.LISTBUTTON_START_REPORT.db_val(),  new BtStartReport(),new MyE2_String("?"));
        
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(RQ_CONST.RQ_NAMES.DIRECT_DEL.db_val(),    	new RQ_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(RQ_CONST.RQ_NAMES.DIRECT_EDIT.db_val(),   	new RQ_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(RQ_CONST.RQ_NAMES.DIRECT_VIEW.db_val(),   	new RQ_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(REPORTING_QUERY.aktiv),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.aktiv)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY.id_reporting_query),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.id_reporting_query)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY.titel_4_user),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.titel_4_user)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY.table_basename),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.table_basename)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY.realname_temptable),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.realname_temptable)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY.query1),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.query1)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY.query2),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.query2)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY.query3),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.query3)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REPORTING_QUERY.query4),true),     new MyE2_String(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.query4)));

//        //neu ab 20171025        
//        this._setLineWrapListHeader(true 
//                                  ,REPORTING_QUERY.aktiv.fn()
//                                  ,REPORTING_QUERY.id_reporting_query.fn()
//                                  ,REPORTING_QUERY.query1.fn()
//                                  ,REPORTING_QUERY.query2.fn()
//                                  ,REPORTING_QUERY.query3.fn()
//                                  ,REPORTING_QUERY.query4.fn()
//                                  ,REPORTING_QUERY.realname_temptable.fn()
//                                  ,REPORTING_QUERY.table_basename.fn()
//                                  ,REPORTING_QUERY.titel_4_user.fn()
//        );
//        
//        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
//       	this._setLayoutElements(gldElementCenter
//                                 ,REPORTING_QUERY.aktiv.fn()
//                                 ,REPORTING_QUERY.id_reporting_query.fn()
//                                 ,REPORTING_QUERY.query1.fn()
//                                 ,REPORTING_QUERY.query2.fn()
//                                 ,REPORTING_QUERY.query3.fn()
//                                 ,REPORTING_QUERY.query4.fn()
//                                 ,REPORTING_QUERY.realname_temptable.fn()
//                                 ,REPORTING_QUERY.table_basename.fn()
//                                 ,REPORTING_QUERY.titel_4_user.fn()
//      	);
//      	
//      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
//       	this._setLayoutTitles(gldTitelCenter
//                                 ,REPORTING_QUERY.aktiv.fn()
//                                 ,REPORTING_QUERY.id_reporting_query.fn()
//                                 ,REPORTING_QUERY.query1.fn()
//                                 ,REPORTING_QUERY.query2.fn()
//                                 ,REPORTING_QUERY.query3.fn()
//                                 ,REPORTING_QUERY.query4.fn()
//                                 ,REPORTING_QUERY.realname_temptable.fn()
//                                 ,REPORTING_QUERY.table_basename.fn()
//                                 ,REPORTING_QUERY.titel_4_user.fn()
//      	);
  
  
        
        this._setLayoutElements(new RB_gld()._left_top()._ins(3,3,3,1)._col(new E2_ColorBase()), 
        						RQ_CONST.RQ_NAMES.CHECKBOX_LISTE.db_val()
        						,RQ_CONST.RQ_NAMES.MARKER_LISTE.db_val()
        						,RQ_CONST.RQ_NAMES.LISTBUTTON_START_REPORT.db_val()
        						);

        
        
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  REPORTING_QUERY.aktiv.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.aktiv)), REPORTING_QUERY.aktiv.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.aktiv))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.aktiv.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.aktiv))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.aktiv.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY.id_reporting_query.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.id_reporting_query)), REPORTING_QUERY.id_reporting_query.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.id_reporting_query))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.id_reporting_query.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.id_reporting_query))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.id_reporting_query.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY.query1.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.query1)), REPORTING_QUERY.query1.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.query1))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.query1.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.query1))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.query1.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY.query2.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.query2)), REPORTING_QUERY.query2.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.query2))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.query2.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.query2))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.query2.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY.query3.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.query3)), REPORTING_QUERY.query3.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.query3))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.query3.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.query3))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.query3.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY.query4.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.query4)), REPORTING_QUERY.query4.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.query4))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.query4.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.query4))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.query4.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY.realname_temptable.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.realname_temptable)), REPORTING_QUERY.realname_temptable.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.realname_temptable))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.realname_temptable.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.realname_temptable))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.realname_temptable.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY.table_basename.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.table_basename)), REPORTING_QUERY.table_basename.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.table_basename))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.table_basename.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.table_basename))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.table_basename.fn());
        // ----
        //
        // spaltenlayout fuer:  REPORTING_QUERY.titel_4_user.fn()
        this._setColExtent(     new Extent(RQ_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(REPORTING_QUERY.titel_4_user)), REPORTING_QUERY.titel_4_user.fn());
        this._setLayoutElements(new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.titel_4_user))._ins(3,1,3,1)._col(new E2_ColorDark()), REPORTING_QUERY.titel_4_user.fn());
        this._setLayoutTitles(  new RB_gld()._al(RQ_READABLE_FIELD_NAME.getAlignment(REPORTING_QUERY.titel_4_user))._ins(1,2,1,1)._col(new E2_ColorDark()), REPORTING_QUERY.titel_4_user.fn());
        // ----
        //
      	
        this.set_oSubQueryAgent(new RQ_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_REPORTING_QUERY(cID_MAINTABLE);
        }
    }
    
    
    
    public static class  BtStartReport extends E2_Button {
    	
    	private boolean aktiv = true;
    	
    	public BtStartReport() throws myException {
			super();
			this._image("led_green.png");
			this._aaa(new OwnAction());
			try {
				this._ttt("Report starten");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this._addGlobalValidator(new XX_ActionValidator_NG() {
				@Override
				public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
					return F.MV()._addWhenNN((!aktiv?new MyE2_Alarm_Message(S.ms("Der Report ist inaktiv!")):null));
				}
			});
			
			
		}

		@Override
    	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
    	}
		
		private class OwnAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				Rec21 recReport = new Rec21(_TAB.reporting_query)._fill_id(
						BtStartReport.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().getUfs(REPORTING_QUERY.id_reporting_query));
				
				RB__EXECUTION_Container container = new RB__EXECUTION_Container(recReport);
				if (container.isToBeShown()) {
					container.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Paramter-Eingabe für Report:").ut(recReport.getUfs(REPORTING_QUERY.titel_4_user)));
				} else {
					bibMSG.add_MESSAGE(container.createIfNeededAndFillTempTable());
					
					if (bibMSG.get_bIsOK()) {
						new RB__ResultContainer(recReport).CREATE_AND_SHOW_POPUPWINDOW(S.msUt(recReport.getUfs(REPORTING_QUERY.titel_4_user)));
					}
				}
			}
		}

		@Override
		public Object get_Copy(Object objHelp) throws myExceptionCopy {
			try {
				BtStartReport ret = new BtStartReport();
				ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
				return ret;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public boolean isAktiv() {
			return aktiv;
		}

		public BtStartReport _setAktiv(boolean aktiv) {
			this.aktiv = aktiv;
			if (aktiv) {
				this._image("led_green.png");
			} else {
				this._image("led_red.png");
			}

			return this;
		}
    }
  
    
}
 
 
