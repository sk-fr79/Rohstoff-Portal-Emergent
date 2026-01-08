package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_LIST_Button_Jump_To_Fuhre extends MyE2_Button 
{
	public BSFP_LIST_Button_Jump_To_Fuhre(Vector<String> vID_FUHRE) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_fuhre.png"));

		if (vID_FUHRE.size()==1)
		{
			this.setToolTipText(new MyE2_String("Zeigt die der Position zugrunde liegende Fuhre an").CTrans());
			this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(
					E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, 
								vID_FUHRE, 
								"Fuhre"));
		}
		else
		{
			this.setToolTipText(new MyE2_String("Fuhre nicht gefunden ???").CTrans());
		}
	}
}
