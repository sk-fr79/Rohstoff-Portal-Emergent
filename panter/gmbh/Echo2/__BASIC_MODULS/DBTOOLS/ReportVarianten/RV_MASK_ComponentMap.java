 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten;
   
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.BasicInterfaces.Service.PdServiceFindAllJRXMLMainfiles;
import panter.gmbh.BasicInterfaces.Service.PdServiceJrxmlParameterReader;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperReportPathFinder;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_SelFieldV3;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.Params.RVP_MASK_DaughterListForMotherMask;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN_PARAM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
  
public class RV_MASK_ComponentMap extends RB_ComponentMap {
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
     
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
	
	public RV_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
       super();
    
       this.m_tpHashMap = p_tpHashMap;        
         
       this.registerComponent(new RB_KF(REP_VARIANTEN.id_rep_varianten),    new RB_lab());
       this.registerComponent(new RB_KF(REP_VARIANTEN.aktiv),    			new RB_cb());
       this.registerComponent(new RB_KF(REP_VARIANTEN.rep_file_name),       new RB_SelFieldV3()
    		     														._populate(new PdServiceFindAllJRXMLMainfiles().getAllJrxmlMainFilesHMAP(true), null)
    		     														._setSizes(400, 500, 300)
    		     														._render()
    		     														._aaa(()->{delegateParamtersFromReport();})
    		     														);
       this.registerComponent(new RB_KF(REP_VARIANTEN.rep_file_name_trans),  new RB_SelFieldV3()
																		._populate(new PdServiceFindAllJRXMLMainfiles().getAllJrxmlMainFilesHMAP(true), null)
																		._setSizes(400, 500, 300)
																		._render()
																		);

       
       this.registerComponent(RVP_MASK_DaughterListForMotherMask.keyForMotherMask, new RVP_MASK_DaughterListForMotherMask(this.m_tpHashMap));

       this.registerSetterValidators(new RB_KF(REP_VARIANTEN.id_rep_varianten), new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
					RB_MaskController con = new RB_MaskController(RV_MASK_ComponentMap.this);
					if (!con.isNew() && con.getYNMaskVal(REP_VARIANTEN.aktiv)) {
						RVP_MASK_DaughterListForMotherMask daughter = (RVP_MASK_DaughterListForMotherMask)RV_MASK_ComponentMap.this.getRbComponent(RVP_MASK_DaughterListForMotherMask.keyForMotherMask);
						if (daughter.getTransportHashMap().getNavigationList().get_vComponentMAPS().size()==0) {
							mv._addAlarm(S.ms("Bitte geben Sie mindestens einen Parameter an oder machen Sie die Regel inaktiv!"));
						}
					}
				}
				if (ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
					RB_MaskController con = new RB_MaskController(RV_MASK_ComponentMap.this);
					if (con.isNew()) {
						con._setEnabledForEdit(false, RV_CONST.getLeadingMaskKey(), REP_VARIANTEN.aktiv);
					} else {
						delegateParamtersFromReport();
					}
				}
				return mv;
			}
		});
       
       this.registerSetterValidators(new RB_KF(REP_VARIANTEN.aktiv), new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (ActionType == VALID_TYPE.USE_IN_MASK_KLICK_ACTION) {
					RB_MaskController con = new RB_MaskController(RV_MASK_ComponentMap.this);
					RVP_MASK_DaughterListForMotherMask daughter = (RVP_MASK_DaughterListForMotherMask)RV_MASK_ComponentMap.this.getRbComponent(RVP_MASK_DaughterListForMotherMask.keyForMotherMask);
					if (daughter.getTransportHashMap().getNavigationList().get_vComponentMAPS().size()==0) {
						if (con.getYNMaskVal(REP_VARIANTEN.aktiv)) {
							mv._addAlarm(S.ms("Bitte geben Sie mindestens einen Parameter an !"));
							con.set_maskVal(REP_VARIANTEN.aktiv, "N", mv);
						}
					}
				}
				return mv;
			}
		});
       
       //validieren, ob die angegebenen parameter im report-file enthalten sind
       this.registerSetterValidators(new RB_KF(REP_VARIANTEN.id_rep_varianten), new RB_Mask_Set_And_Valid() {
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				RB_MaskController con = new RB_MaskController(RV_MASK_ComponentMap.this);

				if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
					HashMap<String,String> parameters  = (HashMap<String,String>) m_tpHashMap.getSB("PARAMETERLISTE");
					if (parameters!=null && parameters.size()>0 && !con.isNew()) {	
						Long id_rep_varianten = con.getLongDBVal(REP_VARIANTEN.id_rep_varianten);
						
						Rec21 rv = new Rec21(_TAB.rep_varianten)._fill_id(id_rep_varianten);
						
						RecList21 rlParams = rv.get_down_reclist21(REP_VARIANTEN_PARAM.id_rep_varianten);
						
						for (Rec21 param: rlParams) {
							if (!parameters.keySet().contains(param.getUfs(REP_VARIANTEN_PARAM.param_name))) {
								mv._addWarn(S.ms("Unbekannter Parameter: ").ut(param.getUfs(REP_VARIANTEN_PARAM.param_name)));
							}
						}
					}
				}
				return mv;
			}
		});

       
       
    }
  
	
	
	private void delegateParamtersFromReport() {
		try {
			RB_MaskController con = new RB_MaskController(RV_MASK_ComponentMap.this);
			String reportFile = con.get_liveVal(REP_VARIANTEN.rep_file_name);
			if (S.isFull(reportFile)) {
				String reportFileBase = reportFile.substring(0,reportFile.toUpperCase().indexOf(".JRXML"));
				E2_JasperReportPathFinder finder = new E2_JasperReportPathFinder(reportFileBase);
				String completePath = finder.getFoundPath()+reportFile; 
				HashMap<String,String> ret = new PdServiceJrxmlParameterReader()._init(completePath).readParameter();
				m_tpHashMap._putSB("PARAMETERLISTE", ret);
			} else {
				m_tpHashMap._putSB("PARAMETERLISTE", new HashMap<String,String>() );
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} 
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
        return null;
     }
	
	
	private class ActionReadParametersFromJrxml extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			delegateParamtersFromReport();
		}
		
	}
	
	
}
 
 
