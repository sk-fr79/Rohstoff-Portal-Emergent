package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K__JUMPER_TO_Fuhre extends MyE2_Button 
{
	public KFIX_K__JUMPER_TO_Fuhre(RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_mini.png"));

		if (recFuhre==null)
		{
			return;
		}
		
		this.setToolTipText(new MyE2_String("Zeigt die betreffende Fuhre an. ID: ",true,recFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),false).CTrans());

		this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(
								E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, 
								bibALL.get_Vector(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF()), 
								"Fuhre"));
	}

}
