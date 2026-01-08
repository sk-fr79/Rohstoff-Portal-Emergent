package panter.gmbh.Echo2.__BASIC_MODULS.DRUCK_PIPELINE;

import java.util.ArrayList;
import java.util.HashMap;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class DP_ListExpander_Pipeline_Positions extends XX_List_EXPANDER_4_ComponentMAP  
{
	
	private E2_NavigationList   		oNaviListPipeline = null;
	private E2_BasicModuleContainer   	oBasicContainerPipeline = null;
	private DP_P_LIST_BasicModule_Inlay  oListInlay = null;
	
	
	public DP_ListExpander_Pipeline_Positions(E2_NavigationList NavigationList, E2_BasicModuleContainer  BasicContainerPipeline) throws myException 
	{
		super(NavigationList);
		
		this.oNaviListPipeline = 		NavigationList;
		this.oBasicContainerPipeline = 	BasicContainerPipeline;
		
		this.set_iLeftColumnOffset(3);
		
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy 
	{
		try 
		{
			DP_ListExpander_Pipeline_Positions oPosExtender = new DP_ListExpander_Pipeline_Positions(this.oNaviListPipeline, this.oBasicContainerPipeline);
			return oPosExtender;
		} 
		catch (myException e) 
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.get_ErrorMessage().get_cMessage().COrig());
		}

	}

	@Override
	public Component get_ComponentDaughter(String cID_ROW_Unformated)	throws myException 
	{
		if (this.oListInlay==null)
		{
			this.oListInlay = new DP_P_LIST_BasicModule_Inlay(this.oBasicContainerPipeline);
		}
		
		this.oListInlay.set_ID_From_Calling_Record(cID_ROW_Unformated);
		this.oListInlay.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");
		return this.oListInlay;
	}

	@Override
	public ArrayList<HashMap<String, Component>> get_ComponentListDaughter(String cID_ROW_Unformated) throws myException 
	{
		return null;
	}

	@Override
	public void refreshDaughterComponent() throws myException 
	{
		this.oListInlay.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");
	}

}
