package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class BSKC_LIST_BT_JUMP_to_FuhrenMitKontrakt extends MyE2_ButtonInLIST
{

	private boolean bEK = true;
	
	public BSKC_LIST_BT_JUMP_to_FuhrenMitKontrakt(String 	cEK_VK) throws myException
	{
		super(	E2_ResourceIcon.get_RI("kompass_fuhre.png"), 
				new MyE2_String("Springe zu den Fuhren, die diesen "+(cEK_VK.equals("EK")?"Einkaufskontrakt":"Verkaufskontrakt")+" enthalten"));
		
		this.bEK =(cEK_VK.equals("EK"));
		
		this.add_oActionAgent(new ownActionJump());
	}

	
	private class ownActionJump extends XX_ActionAgentJumpToTargetList
	{

		public ownActionJump() throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"Fuhre");
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			BSKC_LIST_BT_JUMP_to_FuhrenMitKontrakt oThis = BSKC_LIST_BT_JUMP_to_FuhrenMitKontrakt.this;
			String cID_VPOS_KON = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			VectorSingle  vID_ID_VPOS_TPA_FUHRE = new VectorSingle();

			///Fuhren
			vID_ID_VPOS_TPA_FUHRE.addAll(
					bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray(
							"SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU  WHERE NVL(FU.DELETED,'N')='N' AND FU.ID_VPOS_KON_EK="+cID_VPOS_KON+" OR FU.ID_VPOS_KON_VK="+cID_VPOS_KON), new Vector<String>()));
			
			//Fuhren-Orte
			vID_ID_VPOS_TPA_FUHRE.addAll(
					bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray(
							"SELECT FUO.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO  WHERE NVL(FUO.DELETED,'N')='N' AND FUO.ID_VPOS_KON="+cID_VPOS_KON), new Vector<String>()));
			
			return vID_ID_VPOS_TPA_FUHRE;
		}
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			BSKC_LIST_BT_JUMP_to_FuhrenMitKontrakt oButton = new BSKC_LIST_BT_JUMP_to_FuhrenMitKontrakt(this.bEK?"EK":"VK");
			return oButton;
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
		
	}
	
}
