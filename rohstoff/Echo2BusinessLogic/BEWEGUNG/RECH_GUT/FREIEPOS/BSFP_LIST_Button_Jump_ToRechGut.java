package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_LIST_Button_Jump_ToRechGut extends MyE2_Button 
{
	
	public BSFP_LIST_Button_Jump_ToRechGut(Vector<String> vID_RG_KOPF, String VORGANG_TYP) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_rg.png"));

		boolean bGutschrift  = VORGANG_TYP.equals(myCONST.VORGANGSART_GUTSCHRIFT);
		String  cBelegtyp =    bGutschrift?"Gutschrift":"Rechnung";
		
		if (vID_RG_KOPF.size()==1)
		{
			this.setToolTipText(new MyE2_String("Zeigt den betreffenden Beleg vom Typ <"+cBelegtyp+"> im Modul "+(bGutschrift?" <Gutschriften> ":" <Rechnungen> ")+" an: ID: ",true,vID_RG_KOPF.get(0),false).CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffenden Belege vom Typ <"+cBelegtyp+"> aus der Liste an ...").CTrans());
		}
		this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(
				bGutschrift?E2_MODULNAMES.NAME_MODUL_GUTSCHRIFTEN_LIST:E2_MODULNAMES.NAME_MODUL_RECHNUNGEN_LIST, 
				vID_RG_KOPF, 
				bGutschrift?"Gutschriften":"Rechnungen"));
	}
}
