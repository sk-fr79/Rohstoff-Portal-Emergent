package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;




public class ZOL_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
    
	ZOL_LIST_ComponentMap _oZolListCompMap;
	
	public ZOL_LIST_FORMATING_Agent(ZOL_LIST_ComponentMap oMap) {
		super();
		_oZolListCompMap = oMap;
	}
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException 
    {
    }
    
    
    
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
    {
    	boolean bChanged = false;
    	boolean bDeleted = false;
		
    	boolean bImport = _oZolListCompMap.isImportMode();
    	
    	if (!bImport){
    		return;
    	}
    	String sID = oUsedResultMAP.get_UnFormatedValue("ID_ZOLLTARIFNUMMER");
    	String changed = oUsedResultMAP.get_UnFormatedValue("IS_CHANGED");
		String deleted = oUsedResultMAP.get_UnFormatedValue("IS_DELETED");

		if (changed != null && changed.equalsIgnoreCase("Y")){
    		bChanged = true;
    	}
    	
		if (deleted != null && deleted.equalsIgnoreCase("Y")){
    		bDeleted = true;
    	}
    	
		MyE2_DB_MultiComponentColumn oColImp = (MyE2_DB_MultiComponentColumn) oMAP.get_Comp(ZOL_CONST.COLUMN_IMPORT);
		
		if (!bChanged && !bDeleted){
			oColImp.removeAll();
			oColImp.add(new MyE2_Label(E2_ResourceIcon.get_RI("ok.png")));
		} else if (bChanged && !bDeleted){	
			String text1 = oUsedResultMAP.get_UnFormatedValue("TEXT1");
			String text2 = oUsedResultMAP.get_UnFormatedValue("TEXT2");
			String text3 = oUsedResultMAP.get_UnFormatedValue("TEXT3");
			
			String textImport1 = oUsedResultMAP.get_UnFormatedValue("IMP_" + ZOLLTARIFNUMMER_IMPORT.text1.fn().toUpperCase());
			String textImport2 = oUsedResultMAP.get_UnFormatedValue("IMP_" + ZOLLTARIFNUMMER_IMPORT.text2.fn().toUpperCase());
			String textImport3 = oUsedResultMAP.get_UnFormatedValue("IMP_" + ZOLLTARIFNUMMER_IMPORT.text3.fn().toUpperCase());

			MyE2_DB_Label_INGRID txtImp1 =  (MyE2_DB_Label_INGRID) oMAP.get_hmRealComponents().get("IMP_" + ZOLLTARIFNUMMER_IMPORT.text1.fn().toUpperCase());
			MyE2_DB_Label_INGRID txtImp2 =  (MyE2_DB_Label_INGRID) oMAP.get_hmRealComponents().get("IMP_" + ZOLLTARIFNUMMER_IMPORT.text2.fn().toUpperCase());
			MyE2_DB_Label_INGRID txtImp3 =  (MyE2_DB_Label_INGRID) oMAP.get_hmRealComponents().get("IMP_" + ZOLLTARIFNUMMER_IMPORT.text3.fn().toUpperCase());

			if (!text1.equalsIgnoreCase(textImport1)){
				txtImp1.setForeColorActive(Color.RED);
			}
			if (!text2.equalsIgnoreCase(textImport2)){
				txtImp2.setForeColorActive(Color.RED);
			}
			if (!text3.equalsIgnoreCase(textImport3)){
				txtImp3.setForeColorActive(Color.RED);
			}
			
			MyE2_DB_CheckBox cbChanged = (MyE2_DB_CheckBox) oMAP.get_hmRealComponents().get("IS_CHANGED");
			MyE2_DB_CheckBox cbDeleted = (MyE2_DB_CheckBox) oMAP.get_hmRealComponents().get("IS_DELETED");

			oColImp.remove(cbChanged);
			oColImp.remove(cbDeleted);
			
			oColImp.add(new ButtonChange(sID));
			
		} else if (bDeleted){
			oColImp.removeAll();
			
			String sAktiv = oUsedResultMAP.get_UnFormatedValue("AKTIV", "N");
			if (sAktiv.equalsIgnoreCase("N")){
				oColImp.add(new MyE2_Label(E2_ResourceIcon.get_RI("ok.png")));
			} else {
				oColImp.add(new ButtonDelete(sID));
			}
		}
    }
    
    
    

    private class ButtonChange  extends MyE2_ButtonInLIST{
    	String id_zolltarifnummer;
		
    	public ButtonChange(String id) {
    		super("Ändern");
    		
			id_zolltarifnummer = id;
			
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					RECORD_ZOLLTARIFNUMMER rec = new RECORD_ZOLLTARIFNUMMER(id_zolltarifnummer);
					IMPORT_Zolltarifnummer_Handler oHandler = new IMPORT_Zolltarifnummer_Handler();
					oHandler.updateEntry(rec);
					_oZolListCompMap.get_oNavigationList_This_Belongs_to()._REBUILD_ACTUAL_SITE(null);;
				}
			});
		}
    }
    

    private class ButtonDelete  extends MyE2_ButtonInLIST{
    	String id_zolltarifnummer;
		
    	public ButtonDelete(String id) {
    		super("Inaktiv setzen");
    		
			id_zolltarifnummer = id;
			
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					RECORD_ZOLLTARIFNUMMER rec = new RECORD_ZOLLTARIFNUMMER(id_zolltarifnummer);
					IMPORT_Zolltarifnummer_Handler oHandler = new IMPORT_Zolltarifnummer_Handler();
					oHandler.setEntryInactive(rec);
					_oZolListCompMap.get_oNavigationList_This_Belongs_to()._REBUILD_ACTUAL_SITE(null);;
				}
			});
		}
    	
    	
		
    }
    
    
    
    
    
}
 
