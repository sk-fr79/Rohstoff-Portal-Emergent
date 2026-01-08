package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_ANGEBOT extends MyE2_Button 
{
	private Vector<String> 	vID_Kontrakte = new Vector<String>();
	private String      	cEK_VK = null;
	
	public FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_ANGEBOT(Vector<String> v_ID_Angebote, E2_BasicModuleContainer  ContainerToCloseAfterJump, String EK_VK, boolean b_jump_activ) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_angebot.png"));

		this.vID_Kontrakte.addAll(v_ID_Angebote);
		this.cEK_VK = EK_VK;
		
		if (this.vID_Kontrakte.size()==1)
		{
			this.setToolTipText(new MyE2_String("Zeigt das betreffende Angebot im Modul "+(this.cEK_VK.equals("EK")?"Abnahme-Angebote/Preisinfos":"Verkaufs-Angebote")+" an: ID: ",true,this.vID_Kontrakte.get(0),false).CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffenden Angebote aus der Liste an ...").CTrans());
		}
		if (b_jump_activ) {
			this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(
					this.cEK_VK.equals("EK")?E2_MODULNAMES.NAME_MODUL_ABNAHMEANGEBOT_LIST:E2_MODULNAMES.NAME_MODUL_VERKAUFSANGEBOT_LIST, 
					this.vID_Kontrakte, 
					this.cEK_VK.equals("EK")?"Abnahme-Angebote/Preisinfos":"Verkaufsangebote", ContainerToCloseAfterJump));
		} else {
			this.add_oActionAgent(new FSI_ActionInfoJumpInactiv());
		}
	}

}
