package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

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

public class AS_LIST_BT_JUMP_to_FuhrenMitSorte extends MyE2_ButtonInLIST
{

	public AS_LIST_BT_JUMP_to_FuhrenMitSorte() throws myException
	{
		super(	E2_ResourceIcon.get_RI("kompass_fuhre.png"), 
				new MyE2_String("Springe zu den Fuhren, die diesen Artikel enthalten"));
		
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
			AS_LIST_BT_JUMP_to_FuhrenMitSorte oThis = AS_LIST_BT_JUMP_to_FuhrenMitSorte.this;
			String cID_ARTIKEL = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			VectorSingle  vID_ARTIKEL = new VectorSingle();

			vID_ARTIKEL.addAll(bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray("SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU  WHERE FU.ID_ARTIKEL="+cID_ARTIKEL)));
			vID_ARTIKEL.addAll(bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray("SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU INNER JOIN JT_ARTIKEL_BEZ AB ON(FU.ID_ARTIKEL_BEZ_EK=AB.ID_ARTIKEL_BEZ) WHERE AB.ID_ARTIKEL="+cID_ARTIKEL)));
			vID_ARTIKEL.addAll(bibVECTOR.get_VectorFromArray(bibDB.EinzelAbfrageInArray("SELECT FU.ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU INNER JOIN JT_ARTIKEL_BEZ AB ON(FU.ID_ARTIKEL_BEZ_EK=AB.ID_ARTIKEL_BEZ) WHERE AB.ID_ARTIKEL="+cID_ARTIKEL)));
			
			return vID_ARTIKEL;
		}
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			AS_LIST_BT_JUMP_to_FuhrenMitSorte oButton = new AS_LIST_BT_JUMP_to_FuhrenMitSorte();
			return oButton;
		}
		catch (myException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
		
	}
	
}
