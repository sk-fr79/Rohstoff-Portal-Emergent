package panter.gmbh.Echo2.ListAndMask.List.JUMPER;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class ActionAgentJumpToTargetList_STD extends XX_ActionAgentJumpToTargetList 
{

	private Vector<String>  					v_IDs_in_TargetList = 				new Vector<String>();

	
	public ActionAgentJumpToTargetList_STD(String ModuleName, Vector<String> IDs_Target,	String LesbarerModulName) throws myException 
	{
		super(ModuleName,LesbarerModulName);
		this.v_IDs_in_TargetList.addAll(IDs_Target);
	}

	
	public ActionAgentJumpToTargetList_STD(String ModuleName, Vector<String> IDs_Target,	String LesbarerModulName, E2_BasicModuleContainer ContainerToClose)  throws myException 
	{
		super(ModuleName,LesbarerModulName,ContainerToClose);
		this.v_IDs_in_TargetList.addAll(IDs_Target);
	}

	public ActionAgentJumpToTargetList_STD(String ModuleName, Vector<String> IDs_Target,	String LesbarerModulName, Vector<E2_BasicModuleContainer> ContainerToClose)  throws myException 
	{
		super(ModuleName,LesbarerModulName,ContainerToClose);
		this.v_IDs_in_TargetList.addAll(IDs_Target);
	}



	@Override
	public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
	{
		return this.v_IDs_in_TargetList;
	}

		
		
}


