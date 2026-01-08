package panter.gmbh.Echo2.ListAndMask.List.JUMPER;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_ActionAgentJumpToTargetList extends XX_ActionAgent 
{
	private String 								cModuleName = 						null;


	private Vector<String>  					v_IDs_in_TargetList = 				new Vector<String>();
	private String              				cLesbarerModulName = 				null;
	private Vector<E2_BasicModuleContainer>   	vContainers =   					new Vector<E2_BasicModuleContainer>();

	//2011-06-07: wahlweise den sprung mit einer fehlermeldung abweisen, wenn keine passende id gefunden wurde
	private boolean                             bRefuseJumpWhenNoIDs = false;


	//2011-12-05: der methode get_vID_Target() wird ExecINFO oExecInfo vom zugehoerigen actionAgent uebergeben
	//            zur besseren aufloesung von listeninfos
	public abstract Vector<String>  get_vID_Target(ExecINFO oExecInfo) throws myException;
	
	public XX_ActionAgentJumpToTargetList(String ModuleName, String LesbarerModulName) throws myException 
	{
		super();
		this.cModuleName = 			ModuleName;
		this.cLesbarerModulName = 	LesbarerModulName;
	}

	
	public XX_ActionAgentJumpToTargetList(String ModuleName, String LesbarerModulName, E2_BasicModuleContainer ContainerToClose)  throws myException 
	{
		super();
		this.cModuleName = 			ModuleName;
		this.cLesbarerModulName = 	LesbarerModulName;
		if (ContainerToClose!=null)
		{
			this.vContainers.add(ContainerToClose);
		}
	}

	public XX_ActionAgentJumpToTargetList(String ModuleName, String LesbarerModulName, Vector<E2_BasicModuleContainer> ContainerToClose)  throws myException 
	{
		super();
		this.cModuleName = 			ModuleName;
		this.cLesbarerModulName = 	LesbarerModulName;
		if (ContainerToClose!=null)
		{
			this.vContainers.addAll(ContainerToClose);
		}
	}

	
	public String get_cModuleName() 
	{
		return cModuleName;
	}

	public void set_cModuleName(String cModuleName) 
	{
		this.cModuleName = cModuleName;
	}


	public boolean get_bRefuseJumpWhenNoIDs()
	{
		return bRefuseJumpWhenNoIDs;
	}

	public void set_bRefuseJumpWhenNoIDs(boolean bRefuseJumpWhenNoIDs)
	{
		this.bRefuseJumpWhenNoIDs = bRefuseJumpWhenNoIDs;
	}

	/**
	 * Löschen der Zielliste für abgeleitete Objekte erlauben
	 * 
	 * @author manfred
	 */
	protected void clearTargetList(){
		this.v_IDs_in_TargetList.clear();
	}
	
	
	//kann ueberschrieben werden wenn innerhalb der aktion noch etwas definiert werden muss ...
	public void OVERRIDE_SETTINGS_BEFORE_ACTION() throws myException
	{
	}
	
	//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
	public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST() throws myException
	{
		return new MyE2_MessageVector();
	}

	//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
	public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
	{
		return new MyE2_MessageVector();
	}

	
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException 
	{
		
		this.OVERRIDE_SETTINGS_BEFORE_ACTION();
		
		MyE2_MessageVector oMV = this.OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST();
		if (oMV.get_bHasAlarms())
		{
			bibMSG.add_MESSAGE(oMV);
			return;
		}
		
		if (S.isEmpty(this.cModuleName))
		{
			throw new myException(this,"Cannot call without target-Module !!");
		}
		
		this.v_IDs_in_TargetList.removeAllElements();
		
		this.v_IDs_in_TargetList.addAll(this.get_vID_Target(oExecInfo));

		MyE2_MessageVector oMV2 = this.OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(this.v_IDs_in_TargetList);
		if (oMV2.get_bHasAlarms())
		{
			bibMSG.add_MESSAGE(oMV2);
			return;
		}
		
		
		
		new BaseJumper(this.cModuleName, this.cLesbarerModulName, this.v_IDs_in_TargetList, this.bRefuseJumpWhenNoIDs, this.vContainers);
		
	}

	public String get_cLesbarerModulName()
	{
		return cLesbarerModulName;
	}

}
