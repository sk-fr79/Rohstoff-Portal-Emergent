package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSK___JUMPER_TO_RechGut extends MyE2_Button 
{
	public BSK___JUMPER_TO_RechGut(RECORD_VPOS_RG recRG) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_mini.png"));

		if (recRG==null || recRG.get_ID_VKOPF_RG_cUF_NN("").equals(""))
		{
			return;
		}
		
		boolean bGutschrift  = recRG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_VORGANG_TYP_cF_NN("").equals(myCONST.VORGANGSART_GUTSCHRIFT);
		String  cBelegtyp =    bGutschrift?"Gutschrift":"Rechnung";
		
		this.setToolTipText(new MyE2_String("Zeigt den betreffenden Beleg vom Typ <"+cBelegtyp+"> im Modul "+(bGutschrift?" <Gutschriften> ":" <Rechnungen> ")+" an: ID: ",true,recRG.get_ID_VKOPF_RG_cF_NN(""),false).CTrans());

		this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(
				bGutschrift?E2_MODULNAMES.NAME_MODUL_GUTSCHRIFTEN_LIST:E2_MODULNAMES.NAME_MODUL_RECHNUNGEN_LIST, 
				bibALL.get_Vector(recRG.get_ID_VKOPF_RG_cUF_NN("")), 
				bGutschrift?"Gutschriften":"Rechnungen"));
	}

}
