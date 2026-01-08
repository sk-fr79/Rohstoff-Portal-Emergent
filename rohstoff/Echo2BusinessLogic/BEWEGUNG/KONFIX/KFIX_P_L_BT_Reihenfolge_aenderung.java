package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PositionSorting;
import rohstoff.utils.VorgangTableNames;

public class KFIX_P_L_BT_Reihenfolge_aenderung extends E2_Button
{
	
	private KFIX_K_M_masklist_position position_mask = null;

	public KFIX_P_L_BT_Reihenfolge_aenderung(KFIX_K_M_masklist_position p_parent, String cID_VPOS, boolean Activ)
	
	{
		super();
		
		this.position_mask = p_parent;
		
		this._image(E2_ResourceIcon.get_RI("moveup.png"));
		this.EXT().set_C_MERKMAL(cID_VPOS);
		this.add_oActionAgent(new ownActionAgentMoveUp());

		this.setToolTipText(new MyE2_String("Reihenfolge verändern ...").CTrans());

		this.add_GlobalValidator(new ownValidator());
	}

	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();

			String cID_VKOPF_KON = position_mask.get_id_vkopf_kon();

			try 
			{
				String cQuery1 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON_EK IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";
				String cQuery2 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON_VK IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";
				String cQuery3 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";				

				int i1 = new Integer(bibDB.EinzelAbfrage(cQuery1)).intValue();
				int i2 = new Integer(bibDB.EinzelAbfrage(cQuery2)).intValue();
				int i3 = new Integer(bibDB.EinzelAbfrage(cQuery3)).intValue();

				int iAlle = i1+i2+i3;

				if (iAlle>0)
				{
					oMV.add(new MyE2_Alarm_Message("Umsortieren ist nur solange möglich, solange keine Fuhre auf den Vertrag gebucht ist!"));
				}

			} 
			catch (NumberFormatException e) 
			{
				e.printStackTrace();
				oMV.add(new MyE2_Alarm_Message("Fehler beim Feststellen der Buchungszahlen !"));
			}

			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}

	}

	private class ownActionAgentMoveUp extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_Button oButtonSource = (E2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			String cID = oButtonSource.EXT().get_C_MERKMAL();
			
			if (bibALL.isEmpty(cID))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Sortieren !"));
			}
			else
			{
				try
				{
					VORGANGSART vorgangsart = ((KFIX_K_M__DataObjectCollector)position_mask.rb_ComponentMap_this_belongsTo().getRbDataObjectActual().rb_get_belongs_to()).getBelegTyp();
					
					new BS_PositionSorting(cID,BS_PositionSorting.DOWN,new VorgangTableNames(vorgangsart.get_DBValue()));
					position_mask._rb_set_db_value_manual(position_mask.get_id_vkopf_kon());
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Reihenfolge wurde geändert"));
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}
}
