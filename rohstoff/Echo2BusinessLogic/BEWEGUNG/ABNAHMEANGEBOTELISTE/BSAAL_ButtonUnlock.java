package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;


import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * unlock gesperrte angebote
 */
public class BSAAL_ButtonUnlock extends MyE2_Button 
{
	private BSAAL__ModulContainerLIST	oModulContainerList = 	null;
	private MyDBToolBox					oDB = 					bibALL.get_myDBToolBox();
	
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}
	
	public BSAAL_ButtonUnlock(BSAAL__ModulContainerLIST	oModulContainer)
	{

		super(E2_ResourceIcon.get_RI("unlocked.png"), true);
		
		this.oModulContainerList = oModulContainer;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Entsperren von Abnahme-Angeboten ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_UNLOCK_POSITION));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));

	}

	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			BSAAL_ButtonUnlock oThis = BSAAL_ButtonUnlock.this; 
			
			Vector<String> vSelectedIDs = BSAAL_ButtonUnlock.this.oModulContainerList.get_oNaviList().get_vSelectedIDs_Unformated();
			
			if (vSelectedIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens eine Position auswählen !"));
				return;
			}
			
			bibMSG.add_MESSAGE(oThis.valid_IDValidation(vSelectedIDs));
			
			if (bibMSG.get_bIsOK())
			{

				Vector<String> vID_KOPF_ToUnlock = new Vector<String>();
				
				// jetzt den vector mit den betroffenen koepfen fuellen
				String cSQL_KOPF = "SELECT DISTINCT JT_VKOPF_STD.ID_VKOPF_STD,ABGESCHLOSSEN FROM "+bibE2.cTO()+".JT_VPOS_STD,"+bibE2.cTO()+".JT_VKOPF_STD WHERE  " +
						" JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD AND "+
						" JT_VPOS_STD.ID_VPOS_STD IN ("+bibALL.ConcatenateWithoutException(vSelectedIDs,",", null)+")";
				
				String[][] cID_Koepfe = BSAAL_ButtonUnlock.this.oDB.EinzelAbfrageInArray(cSQL_KOPF);
				
				vID_KOPF_ToUnlock.removeAllElements();
				
				if (cID_Koepfe == null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Abfrage ..."));
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(cSQL_KOPF));
				}

				if (bibMSG.get_bIsOK())
				{
					if (cID_Koepfe.length==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Sie haben nichts markiert ..."));
					}
					if (bibMSG.get_bIsOK())
					{
						/*
						 * checken, ob nur gelockte angklickt wurden
						 */
						for (int i=0;i<cID_Koepfe.length;i++)
						{
							if (!cID_Koepfe[i][1].equals("Y"))
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte nur gesperrte auswählen !"));
								break;
							}
						}
						
						if (bibMSG.get_bIsOK())
						{
							Vector<String> vSQL = new Vector<String>();

							for (int i=0;i<cID_Koepfe.length;i++)
							{
								vSQL.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_STD SET ABGESCHLOSSEN='N' WHERE ID_VKOPF_STD="+cID_Koepfe[i][0]);
							}
							
							try
							{
								bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
								BSAAL_ButtonUnlock.this.oModulContainerList.get_oNaviList()._REBUILD_ACTUAL_SITE(null);
							}
							catch (myException ex)
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error executing update-statement !!!"));
							}
						}
					}
				}
			}
		}

	}
	
	
}
