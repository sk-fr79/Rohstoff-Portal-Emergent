 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
   
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS.RQF_MASK_DaughterListForMotherMask;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS.RQP_MASK_DaughterListForMotherMask;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
  
public class RQ_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;

    private RQ_TextAnzeigeAllSQL  anzeigeAll = new RQ_TextAnzeigeAllSQL(900);

	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public RQ_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
       
         
        this.registerComponent(new RB_KF(REPORTING_QUERY.aktiv),    				new RB_cb());
        this.registerComponent(new RB_KF(REPORTING_QUERY.id_reporting_query),    	new RB_lab());
        this.registerComponent(new RB_KF(REPORTING_QUERY.query1),   			 	new RB_TextArea()._width(400)._rows(5));
        this.registerComponent(new RB_KF(REPORTING_QUERY.query2),    				new RB_TextArea()._width(400)._rows(5));
        this.registerComponent(new RB_KF(REPORTING_QUERY.query3),    				new RB_TextArea()._width(400)._rows(5));
        this.registerComponent(new RB_KF(REPORTING_QUERY.query4),    				new RB_TextArea()._width(400)._rows(5));
        this.registerComponent(new RB_KF(REPORTING_QUERY.realname_temptable),    	new RB_TextField()._width(400));
        this.registerComponent(new RB_KF(REPORTING_QUERY.table_basename),    		new RB_TextField()._width(400));
        this.registerComponent(new RB_KF(REPORTING_QUERY.titel_4_user),    			new RB_TextField()._width(400));
        this.registerComponent(new RB_KF(REPORTING_QUERY.langtextinfo),    			new RB_TextArea()._width(400)._height(100));
        
        this.registerComponent(RQ_TextAnzeigeAllSQL.KEY, 							anzeigeAll._rows(20));
        
        this.registerComponent(RQ_MASK_BtAnalyseQuery.KEY, 						new RQ_MASK_BtAnalyseQuery(m_tpHashMap));
        this.registerComponent(RQ_MASK_BtReadFieldsAndBuildParameters.KEY, 		new RQ_MASK_BtReadFieldsAndBuildParameters(m_tpHashMap));
        this.registerComponent(RQ_MASK_BtSimulateInputDialog.KEY, 				new RQ_MASK_BtSimulateInputDialog(m_tpHashMap));
        this.registerComponent(RQ_MASK_BtMakeQueryActive.KEY, 					new RQ_MASK_BtMakeQueryActive(m_tpHashMap));
        this.registerComponent(RQ_MASK_BtMakeQueryInActiveDropTable.KEY, 		new RQ_MASK_BtMakeQueryInActiveDropTable(m_tpHashMap));
        this.registerComponent(RQ_MASK_BtStartReport.KEY, 						new RQ_MASK_BtStartReport(m_tpHashMap));
        
        
        RQF_MASK_DaughterListForMotherMask daughterfields = new RQF_MASK_DaughterListForMotherMask(this.m_tpHashMap,true);
        RQP_MASK_DaughterListForMotherMask daughterParams = new RQP_MASK_DaughterListForMotherMask(this.m_tpHashMap,true);
        
        ((RQ__TPHM_Zusaetze)this.m_tpHashMap.getPlace4Everything()).daughterfields=daughterfields;
        ((RQ__TPHM_Zusaetze)this.m_tpHashMap.getPlace4Everything()).daughterParams=daughterParams;
        
        //masken-tochter für listen
        this.registerComponent(RQF_MASK_DaughterListForMotherMask.keyForMotherMask, daughterfields);

        //masken-tochter für parameter
        this.registerComponent(RQP_MASK_DaughterListForMotherMask.keyForMotherMask, daughterParams);
        
        
        
        
       //beispiel fuer einen setter-validator 
       this.registerSetterValidators(REPORTING_QUERY.id_reporting_query,new OwnSetterDivideSqlTextTo4Fields());
       this.registerSetterValidators(REPORTING_QUERY.id_reporting_query,new OwnSetterValidatorSetTableName());
       this.registerSetterValidators(REPORTING_QUERY.id_reporting_query,new OwnSetterValidatorDisableMaskFieldsWhenActive());
       this.registerSetterValidators(REPORTING_QUERY.id_reporting_query,new OwnValidatorCheckCriticalSqlWords());
       this.registerSetterValidators(REPORTING_QUERY.id_reporting_query,new OwnValidatorSetDisabledActivateButton());

       
       
       
    }
  
    @Override
    public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
        return null;
    }
    
    @Override
    public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
        return null;
    }
    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
    
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
//		//falls sich alles in einer tochtermaske abspielt, dannn muessen hier die verknuepfung zu presettingscontainer hergestellt werden
//		if (this.m_tpHashMap.getMotherKeyLookupField()!=null && this.m_tpHashMap.getMotherKeyValue().toString()!=null) {
//			preSettingsContainer.rb_set_forcedValueAtSave(this.m_tpHashMap.getMotherKeyLookupField(), this.m_tpHashMap.getMotherKeyValue().toString());
//		}
		

		preSettingsContainer.rb_get(REPORTING_QUERY.realname_temptable).set_Enabled(false);
		preSettingsContainer.rb_get(REPORTING_QUERY.aktiv).set_Enabled(false);
//		
//		if (status.isStatusNew()) {
//			preSettingsContainer.rb_get(REPORTING_QUERY.aktiv).set_rb_Default("N");
//		}
		
		
		return null;
	}
	

	
	private class OwnSetterValidatorSetTableName extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			RB_MaskController c = new RB_MaskController(RQ_MASK_ComponentMap.this);

			String baseName = c.get_liveVal(REPORTING_QUERY.table_basename);

			if (S.isFull(baseName)) {
				//immer grossschreiben
				baseName = S.NN(baseName.toUpperCase());
				baseName = S.NN(S.replaceUmlaute(baseName));
				baseName = S.NN(S.replaceSonderZeichen(baseName, S.grossbuchstaben+S.ziffern, null));   //sauebern
			    if (baseName.length()>15) {
			    	baseName=baseName.substring(0, 15);
			    }
			    c.set_maskVal(REPORTING_QUERY.table_basename, baseName, mv); 
			    
				if (S.isFull(baseName) ) {
					//wenn neuerfassung, dann wird der tablename gebaut, sonst nicht
					if (rbMASK.getRbDataObjectActual().isStatusNew() && ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
				    	//sonst wird er neu gesetzt
				    	String fullNameNew = RQ_CONST.RQ_NAMES.PREFIX_OF_TABLES.db_val()+"_"+baseName+"_"+bibDB.EinzelAbfrage("SELECT SEQ_TR_TEMPTABLES.nextval from DUAL");
				    	c.set_maskVal(REPORTING_QUERY.realname_temptable, fullNameNew, mv);
					}					
				}
			}
			return mv;
		}
		
	}
	
	
	private class OwnSetterValidatorDisableMaskFieldsWhenActive extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			RB_MaskController c = new RB_MaskController(RQ_MASK_ComponentMap.this);

			if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
				c.get_comp(REPORTING_QUERY.table_basename, mv).set_bEnabled_For_Edit(true);
				anzeigeAll.set_bEnabled_For_Edit(true);

				if (c.isYes_LiveVal(REPORTING_QUERY.aktiv)) {
					c.get_comp(REPORTING_QUERY.table_basename, mv).set_bEnabled_For_Edit(false);
					anzeigeAll.set_bEnabled_For_Edit(false);
				}
			}
			return mv;
		}
		
	}

	
	
	
	private class OwnSetterDivideSqlTextTo4Fields extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,		ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION && !rbMASK.getRbDataObjectActual().isStatusNew()) {
				//alle vier textbloecke in einen zusammenfassen
				RB_MaskController mk = new RB_MaskController(rbMASK);
				StringBuffer sbAll = new StringBuffer()	.append(S.NN(mk.get_dbVal(REPORTING_QUERY.query1)))
														.append(S.NN(mk.get_dbVal(REPORTING_QUERY.query2)))
														.append(S.NN(mk.get_dbVal(REPORTING_QUERY.query3)))
														.append(S.NN(mk.get_dbVal(REPORTING_QUERY.query4)));
				
				anzeigeAll.setText(S.NN(sbAll.toString()));
				
			}
			
			if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION ) {
				//alle vier textbloecke in einen zusammenfassen
				RB_MaskController mk = new RB_MaskController(rbMASK);
				String sqlAll = mk.get_liveVal(_TAB.reporting_query,RQ_TextAnzeigeAllSQL.KEY);
				
				if (sqlAll.length()<2000) {
					mk.set_maskVal(REPORTING_QUERY.query1, sqlAll.substring(0), mv);
					mk.set_maskVal(REPORTING_QUERY.query2, "", mv);
					mk.set_maskVal(REPORTING_QUERY.query3, "", mv);
					mk.set_maskVal(REPORTING_QUERY.query4, "", mv);
				} else if (sqlAll.length()<4000) {
					mk.set_maskVal(REPORTING_QUERY.query1, sqlAll.substring(0,2000), mv);
					mk.set_maskVal(REPORTING_QUERY.query2, sqlAll.substring(2000), mv);
					mk.set_maskVal(REPORTING_QUERY.query3, "", mv);
					mk.set_maskVal(REPORTING_QUERY.query4, "", mv);
				} else if (sqlAll.length()<6000) {
					mk.set_maskVal(REPORTING_QUERY.query1, sqlAll.substring(0,2000), mv);
					mk.set_maskVal(REPORTING_QUERY.query2, sqlAll.substring(2000,4000), mv);
					mk.set_maskVal(REPORTING_QUERY.query3, sqlAll.substring(4000), mv);
					mk.set_maskVal(REPORTING_QUERY.query4, "", mv);
				} else if (sqlAll.length()<8000) {
					mk.set_maskVal(REPORTING_QUERY.query1, sqlAll.substring(0,2000), mv);
					mk.set_maskVal(REPORTING_QUERY.query2, sqlAll.substring(2000,4000), mv);
					mk.set_maskVal(REPORTING_QUERY.query3, sqlAll.substring(4000,6000), mv);
					mk.set_maskVal(REPORTING_QUERY.query4, sqlAll.substring(6000), mv);
				} else {
					mv._addAlarm("Der SQL-String kann maximal 7999 Zeichen umfassen !");
				}
			}
			
			
			return mv;
		}
		
	}
	
	
	
	
	private class OwnValidatorCheckCriticalSqlWords extends RB_Mask_Set_And_Valid {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid#make_Interactive_Set_and_Valid(panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap, panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE, panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			RB_MaskController c = new RB_MaskController(rbMASK);
			
		    String s = 	(S.NN(c.get_liveVal(REPORTING_QUERY.query1),"")+" "+
		    			S.NN(c.get_liveVal(REPORTING_QUERY.query2),"")+" "+
		    			S.NN(c.get_liveVal(REPORTING_QUERY.query3),"")+" "+
		    			S.NN(c.get_liveVal(REPORTING_QUERY.query4),"")).toUpperCase();
		    		
		    if (s.contains("DELETE") || s.contains("INSERT") || s.contains("UPDATE")) {
		    	mv._addAlarm(S.ms("Die Schlüsselwort :").ut(" <DELETE>,<INSERT>,<UPDATE> ").t("  sind in diesem Kontext verboten !"));
		    }
			return mv;
		}
		
	}

	
	private class OwnValidatorSetDisabledActivateButton extends RB_Mask_Set_And_Valid {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid#make_Interactive_Set_and_Valid(panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap, panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE, panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			RB_MaskController c = new RB_MaskController(rbMASK);

			if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
				RQ_MASK_BtMakeQueryActive bt = (RQ_MASK_BtMakeQueryActive)RQ_MASK_ComponentMap.this.getRbComponent(RQ_MASK_BtMakeQueryActive.KEY);
				bt.set_bEnabled_For_Edit(!c.getYNDbVal(REPORTING_QUERY.aktiv));
			}
			
			return mv;
		}
		
	}

	
}
 
 
