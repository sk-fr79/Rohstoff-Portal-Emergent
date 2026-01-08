package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.exceptions.myException;

public class FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_KONTRAKT_NG extends MyE2_Button
{
	
	private Vector<String> 	vID_Kontrakte = new Vector<String>();
	private String      	cEK_VK = null;
	
	public FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_KONTRAKT_NG(Vector<String> v_ID_kontrakte, E2_BasicModuleContainer  ContainerToCloseAfterJump, String EK_VK,  boolean b_jump_activ) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_kontrakt.png"));

		this.vID_Kontrakte.addAll(v_ID_kontrakte);
		this.cEK_VK = EK_VK;
		
		
		/* @author sebastien, 2017-06-02: wenn neue kontrakt schalter is Aktiv, dann sprung im neuer Kontrakte*/
		String modulName = "";
		if(ENUM_MANDANT_DECISION.USE_NEW_KONTRAK_TYP.is_YES()){
			modulName = this.cEK_VK.equals("EK")?E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST_NG:E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST_NG; 
		}else{
			modulName = this.cEK_VK.equals("EK")?E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST:E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST; 
		}
		
		
		if (this.vID_Kontrakte.size()==1)
		{
			this.setToolTipText(new MyE2_String("Zeigt den Kontrakt im Modul "+(this.cEK_VK.equals("EK")?"EK-Kontrakte":"VK-Kontrakte")+" an: ID: ",true,this.vID_Kontrakte.get(0),false).CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Zeigt die Kontrakte aus der Liste an ...").CTrans());
		}
		
		//2015-12-14: spruenge optional abschalten
		if (b_jump_activ) {
			this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(
					modulName, 
					this.vID_Kontrakte, 
					this.cEK_VK.equals("EK")?"EK-Kontrakte":"VK-Kontrakte", ContainerToCloseAfterJump)); 
		} else {
			this.add_oActionAgent(new FSI_ActionInfoJumpInactiv_NG());
		}

	}
}
