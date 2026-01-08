package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE extends MyE2_Button
{
	
	private Vector<String> vID_Fuhren = new Vector<String>();

	
	public FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(Vector<String> v_ID_Fuhren, E2_BasicModuleContainer  ContainerToCloseAfterJump, boolean b_jump_activ) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_fuhre.png"));

		this.vID_Fuhren.addAll(v_ID_Fuhren);
		
		if (this.vID_Fuhren.size()==1)
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffende Fuhre in der Fuhrenzentrale an: ID: ",true,this.vID_Fuhren.get(0),false).CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffenden Fuhren aus der Liste in der Fuhrenzentrale an ...").CTrans());
		}

		//2015-12-14: spruenge optional abschalten
		if (b_jump_activ) {
			this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, this.vID_Fuhren, "Fuhrenzentrale", ContainerToCloseAfterJump));
		} else {
			this.add_oActionAgent(new FSI_ActionInfoJumpInactiv());
		}
	}
}

