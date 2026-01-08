package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class BSRG_K_LIST_BT_JUMPER extends MyE2_PopUpMenue
{
	
	public BSRG_K_LIST_BT_JUMPER() throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_popup.png"), E2_ResourceIcon.get_RI("kompass_popup.png"),false);
		
		this.addButton(new ownJumpButtonToFibu(), true);
		this.addButton(new ownJumpButtonToFuhren(), true);
		
		this.setToolTipText(new MyE2_String("Sprungfunktionen in verschiedene Module ...").CTrans());
	}

	
	public void set_bEnabled_For_Edit(boolean bbEnabled) throws myException
	{
		//inaktiv
	}


	
	private class ownJumpButtonToFibu extends MyE2_Button
	{
		public ownJumpButtonToFibu() throws myException
		{
			super(new MyE2_String("Sprungziel: Fibu / Rechnung-/Gutschrift-Ausgänge"));
			this.add_oActionAgent(new ownActionJumpToFibu());
		}
	}

	
	private class ownActionJumpToFibu extends XX_ActionAgentJumpToTargetList
	{

		public ownActionJumpToFibu()	throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FIBU_LIST, "Fibu-Eintrag");
			this.set_bRefuseJumpWhenNoIDs(true);
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			BSRG_K_LIST_BT_JUMPER oThis = BSRG_K_LIST_BT_JUMPER.this;
			
			String cID_VKOPF_RG = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			return bibVECTOR.get_VectorFromSet(new RECORD_VKOPF_RG(cID_VKOPF_RG).get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg().get_ID_FIBU_hmString_UnFormated("").keySet());
		}
		
	}
	
	
	

	private class ownJumpButtonToFuhren extends MyE2_Button
	{
		public ownJumpButtonToFuhren() throws myException
		{
			super(new MyE2_String("Sprungziel: Fuhrenzentrale"));
			this.add_oActionAgent(new ownActionJumpToFuhren());
		}
	}

	
	private class ownActionJumpToFuhren extends XX_ActionAgentJumpToTargetList
	{

		public ownActionJumpToFuhren()	throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentrale");
			this.set_bRefuseJumpWhenNoIDs(true);
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			BSRG_K_LIST_BT_JUMPER oThis = BSRG_K_LIST_BT_JUMPER.this;
			
			String cID_VKOPF_RG = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			VectorSingle vIDFuhren = new VectorSingle();
			
			RECLIST_VPOS_RG  reclistRGs = new RECORD_VKOPF_RG(cID_VKOPF_RG).get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg();
			
			for(int i=0;i<reclistRGs.get_vKeyValues().size();i++)
			{
				if (S.isFull(reclistRGs.get(i).get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF()))
				{
					vIDFuhren.add(reclistRGs.get(i).get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF_NN("-1"));
				}
			}
			return vIDFuhren;
		}
		
	}
	

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		BSRG_K_LIST_BT_JUMPER oPopRueck;
		try 
		{
			oPopRueck = new BSRG_K_LIST_BT_JUMPER();
			return oPopRueck;
		} 
		catch (myException e) 
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.get_ErrorMessage().get_cMessage().COrig());
		}
	}

	
}
