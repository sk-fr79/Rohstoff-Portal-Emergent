package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Auswertungen.REPORT_MODULE_CONTAINER;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_MaskInfo;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_MASK_BUTTON_SaveAndTest extends MyE2_Button 
{

	public QUERY_MASK_BUTTON_SaveAndTest() 
	{
		super(new MyE2_String("Speichern und Testen"));
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_RecursiveSearch_MaskInfo oMaskSearch = new E2_RecursiveSearch_MaskInfo(bibE2.get_LAST_ACTIONEVENT());
			
			E2_BasicModuleContainer_MASK oMask = oMaskSearch.get_oMotherContainerMASK();
			if (oMask == null || oMask.get_vBasicContainerHierarchie().size()==0)
				throw new myException(this,"Error 1 ...");
			
			
			// der vorige in der hirarchie ist der listencontainer
			E2_BasicModuleContainer oContainerWithList = 
				(E2_BasicModuleContainer)oMask.get_vBasicContainerHierarchie().get(oMask.get_vBasicContainerHierarchie().size()-1);
			
			
			E2_RecursiveSearch_Component oSearchNavilist = new E2_RecursiveSearch_Component(
									oContainerWithList,bibALL.get_Vector(E2_NavigationList.class.getName()),null);
			
			if (oSearchNavilist.get_vAllComponents().size()!=1)
				throw new myException(this,"Error 2 ...");
			
			E2_SaveMaskStandard oSave = new E2_SaveMaskStandard(oMask,(E2_NavigationList)oSearchNavilist.get_vAllComponents().get(0));
			
			
			oSave.doSaveMask(true);
			
			if (bibMSG.get_bIsOK())
			{
				// dann beginnt der test
				String cReportID = 			oSave.get_cActualMaskID_Unformated();
				REPORT_MODULE_CONTAINER 	oReport = new REPORT_MODULE_CONTAINER(cReportID);
				String cReportName = 		bibDB.EinzelAbfrage("SELECT NVL(NAME,'-') FROM "+bibE2.cTO()+".JT_QUERY WHERE ID_QUERY="+cReportID);
				
				MyE2_String cReportTitel = new MyE2_String("Testbetrieb Report: ");
				cReportTitel.addUnTranslated(cReportName);
				
				oReport.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), cReportTitel);
			}
		}
	}
	
	
	
}
