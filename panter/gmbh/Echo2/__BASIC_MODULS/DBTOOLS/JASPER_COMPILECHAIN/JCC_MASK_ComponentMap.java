 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;
  
import java.io.File;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.JASPER_COMPILE_CHAIN;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
  
public class JCC_MASK_ComponentMap extends RB_ComponentMap {
    public JCC_MASK_ComponentMap() throws myException {
        super();
        this.registerComponent(new RB_KF(JASPER_COMPILE_CHAIN.id_jasper_compile_chain), new RB_TextField()._width(100));
        
        this.registerComponent(new RB_KF(JASPER_COMPILE_CHAIN.reportname),              new JCC_MASK_comp_selectReportName()._width(300));

        this.registerComponent(new RB_KF(JASPER_COMPILE_CHAIN.compilebasedir),          new JCC_MASK_comp_selectBaseDir()._width(300));
        this.registerComponent(new RB_KF(JASPER_COMPILE_CHAIN.compiletarget),           new JCC_MASK_comp_selectTargets()._width(300));
        
        
        this.registerSetterValidators(JASPER_COMPILE_CHAIN.compilebasedir.fk(), new OwnSetterValidatorReadCompileTargets_From_Compilebasedir());
        
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
	
	
//	private class OwnSetterValidatorReadReportsFrom_basedir extends RB_Mask_Set_And_Valid {
//		@Override
//		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, 	ExecINFO oExecInfo) throws myException {
//			
//			MyE2_MessageVector mv = new MyE2_MessageVector();
//			
//			RB_MaskController  controller = new RB_MaskController(JCC_MASK_ComponentMap.this);
//			
//			if (ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION || ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION ||  ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
//				
//				String actualDir = controller.get_liveVal(JASPER_COMPILE_CHAIN.basedir);
//				if (S.isFull(actualDir)) {
//					JCC_MASK_comp_selectReportName  selReport = (JCC_MASK_comp_selectReportName)controller.get_comp(JASPER_COMPILE_CHAIN.reportname, mv);
//					
//					if (selReport!=null) {
//						
//						String basePath = bibALL.get_ReportPathInFileSystem();
//						if (!basePath.startsWith(File.separator) ) {basePath=File.separator+basePath;}
//						if (!basePath.endsWith(File.separator) ) {basePath=basePath+File.separator;}
//						
//						VEK<String> jrxmls = new VEK<String>();
//						
//						File ofileDir = new File(basePath+actualDir);
//
//						if (ofileDir.exists() && ofileDir.isDirectory()) {
//							String[] cJasperFiles = ofileDir.list();
//							for (String s: cJasperFiles) {
//								File fs = new File(basePath+actualDir+File.separator+s);
//								if (fs.exists() && fs.isFile() && s.toUpperCase().trim().endsWith(".JRXML")) {
//									jrxmls._a(s);
//								}
//							}
//						}
//						
//						jrxmls.sort((s1,s2)->{return s1.toUpperCase().compareTo(s2.toUpperCase());});
//
//						
//						if (ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION ) {
//							//dann die report-komponente befuellen
//							String oldReport = controller.get_liveVal(JASPER_COMPILE_CHAIN.reportname);
//							selReport._clear();
//							selReport._addPair(new PairS("-",""));
//							for (String s: jrxmls) {
//								selReport._addPair(new PairS(s,s));
//							}
//							//dann auf den letzten oder leeren wert stellen
//							//selReport._Set
//							selReport._setActiveOrFirstMaskVal(oldReport);
//						}
//
//						if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
//							String loadedReport = controller.get_dbVal(JASPER_COMPILE_CHAIN.reportname);
//
//							//dann die report-komponente befuellen
//							selReport._clear();
//							selReport._addPair(new PairS("-",""));
//							for (String s: jrxmls) {
//								selReport._addPair(new PairS(s,s));
//							}
//							
//							if (S.isFull(loadedReport)) {
//								if (!jrxmls.contains(loadedReport)) {
//									selReport._putValueToShadow(loadedReport);
//								}
//								
//								selReport._setActiveDBVal(loadedReport);
//							}
//						}
//
//						if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
//							//dann den korrekten wert checken
//							String actualReport = controller.get_liveVal(JASPER_COMPILE_CHAIN.reportname);
//							if (S.isFull(actualReport)) {
//								if (!jrxmls.contains(actualReport)) {
//									mv._addAlarm("Der Report befindet sich nicht im Ordner: "+actualDir);
//								}
//							}
//						}
//					}
//				}
//			}
//			
//			return mv;
//		}
//	}
	
	
	
	
	private class OwnSetterValidatorReadCompileTargets_From_Compilebasedir extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, 	ExecINFO oExecInfo) throws myException {
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			RB_MaskController  controller = new RB_MaskController(JCC_MASK_ComponentMap.this);
			
			if (ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION || ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION ||  ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
				
				String actualDir = controller.get_liveVal(JASPER_COMPILE_CHAIN.compilebasedir);
				
				if (S.isFull(actualDir)) {
					JCC_MASK_comp_selectTargets  selTargets = (JCC_MASK_comp_selectTargets)controller.get_comp(JASPER_COMPILE_CHAIN.compiletarget, mv);
					
					if (selTargets!=null) {
						
						String basePath = bibALL.get_ReportPathInFileSystem();
						if (!basePath.startsWith(File.separator) ) {basePath=File.separator+basePath;}
						if (!basePath.endsWith(File.separator) ) {basePath=basePath+File.separator;}
						
						File ofileDir = new File(basePath+actualDir);

						//zuerst die unterverzeichnisse suchen
						VEK<String> subDirs = new VEK<String>();
						if (ofileDir.exists() && ofileDir.isDirectory()) {
							String[] cJasperFiles = ofileDir.list();
							for (String s: cJasperFiles) {
								File fs = new File(basePath+actualDir+File.separator+s);
								if (fs.exists() && fs.isDirectory()) {
									subDirs._a(s);
								}
							}
						}
						subDirs.sort((s1,s2)->{return s1.toUpperCase().compareTo(s2.toUpperCase());});
						

						//dann die reports im verzeichnis suchen
						VEK<String> subXmls = new VEK<String>();
						if (ofileDir.exists() && ofileDir.isDirectory()) {
							String[] cJasperFiles = ofileDir.list();
							for (String s: cJasperFiles) {
								File fs = new File(basePath+actualDir+File.separator+s);
								if (fs.exists() && (fs.isFile() && fs.getName().toUpperCase().trim().endsWith(".JRXML")  )) {
									subXmls._a(s);
								}
							}
						}
						subXmls.sort((s1,s2)->{return s1.toUpperCase().compareTo(s2.toUpperCase());});

						
						
						if (ActionType==VALID_TYPE.USE_IN_MASK_KLICK_ACTION ) {
							//dann die report-komponente befuellen
							selTargets._clear();
							selTargets._addPair(new PairS("-",""));
							for (String s: subDirs) {
								selTargets._addPair(new PairS(s,s));
							}
							for (String s: subXmls) {
								selTargets._addPair(new PairS(s,s));
							}
							//dann auf den leeren wert stellen
							selTargets._setActiveMaskVal("-");
						}

						if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
							String loadedReport = controller.get_dbVal(JASPER_COMPILE_CHAIN.compiletarget);

							//dann die report-komponente befuellen
							selTargets._clear();
							selTargets._addPair(new PairS("-",""));
							for (String s: subDirs) {
								selTargets._addPair(new PairS(s,s));
							}
							for (String s: subXmls) {
								selTargets._addPair(new PairS(s,s));
							}
							
							if (S.isFull(loadedReport)) {
								if (!subDirs.contains(loadedReport)) {
									selTargets._putValueToShadow(loadedReport);
								}
								
								selTargets._setActiveDBVal(loadedReport);
							}
						}

						if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
							//dann den korrekten wert checken
							String actualTarget = controller.get_liveVal(JASPER_COMPILE_CHAIN.compiletarget);
							if (S.isFull(actualTarget)) {
								if (!subDirs.contains(actualTarget) && !subXmls.contains(actualTarget) ) {
									mv._addAlarm("Das Unterverzeichnis / die Datei befindet sich nicht im Ordner: "+actualDir);
								}
							}
						}
					}
				}
			}
			
			return mv;
		}
	}

}
 
