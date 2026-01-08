package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FIBU__LIST_BT_JumpToRG extends MyE2_ButtonInLIST
{

	public FIBU__LIST_BT_JumpToRG() throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass.png"));
		this.setToolTipText(new MyE2_String("Springt ins Rechnungs- oder Gutschriftmodul zu diesem Eintrag").CTrans());
		this.add_oActionAgent(new ownActionJumpToRG());
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			return new FIBU__LIST_BT_JumpToRG();
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("Error Copying FIBU__LIST_BT_JumpToRG");
		}
		
	}
	
	
	
	private class ownActionJumpToRG extends XX_ActionAgentJumpToTargetList
	{
		public ownActionJumpToRG() throws myException 
		{
			super(null, "Rechnung oder Gutschrift");
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
		{
			E2_ComponentMAP  oMAP_FIBU = FIBU__LIST_BT_JumpToRG.this.EXT().get_oComponentMAP();
			RECORD_FIBU  recFibu = new RECORD_FIBU(oMAP_FIBU.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			
			if (recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
			{
				return bibALL.get_Vector(recFibu.get_ID_VKOPF_RG_cUF());
			}
			return new Vector<String>();
		}
		
		//kann ueberschrieben werden wenn innerhalb der aktion noch etwas definiert werden muss ...
		public void OVERRIDE_SETTINGS_BEFORE_ACTION() throws myException
		{
			E2_ComponentMAP  oMAP_FIBU = FIBU__LIST_BT_JumpToRG.this.EXT().get_oComponentMAP();
			if (oMAP_FIBU.get_oInternalSQLResultMAP().get_LActualDBValue(RECORD_FIBU.FIELD__ID_VKOPF_RG, true)!=0)
			{
				RECORD_VKOPF_RG recRG = new RECORD_VKOPF_RG(oMAP_FIBU.get_oInternalSQLResultMAP().get_LActualDBValue(RECORD_FIBU.FIELD__ID_VKOPF_RG, true));
				if (recRG.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG))
				{
					this.set_cModuleName(E2_MODULNAMES.NAME_MODUL_RECHNUNGEN_LIST);
				}
				else
				{
					this.set_cModuleName(E2_MODULNAMES.NAME_MODUL_GUTSCHRIFTEN_LIST);
				}
			}
		}
		
		//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST() throws myException
		{
			MyE2_MessageVector  oMVRueck = new MyE2_MessageVector();
			E2_ComponentMAP  oMAP_FIBU = FIBU__LIST_BT_JumpToRG.this.EXT().get_oComponentMAP();
			if (oMAP_FIBU.get_oInternalSQLResultMAP().get_LActualDBValue(RECORD_FIBU.FIELD__ID_VKOPF_RG, true)==0)
			{
				oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Diese Zeile enthält keinen Rechnungs- oder Gutschrift-Beleg !")));
			}
			return oMVRueck;
		}
		
	}
	
}

	

