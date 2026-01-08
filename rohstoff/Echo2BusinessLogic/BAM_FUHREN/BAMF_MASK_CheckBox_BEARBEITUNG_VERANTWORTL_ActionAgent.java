/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;

public class BAMF_MASK_CheckBox_BEARBEITUNG_VERANTWORTL_ActionAgent  extends XX_ActionAgent {
	private E2_ComponentMAP oComponentMAP = null;
	
	public BAMF_MASK_CheckBox_BEARBEITUNG_VERANTWORTL_ActionAgent(E2_ComponentMAP componentMAP)	{
		super();
		this.oComponentMAP = componentMAP;
	}


	public void executeAgentCode(ExecINFO oExecInfo)	{
		/*
		 * die action setzt den benutzer auf die eigene ID und sperrt die user-auswahl
		 * kontrolle darf erst angeklickt werden, wenn behebung angeklickt war
		 */
		MyE2_DB_CheckBox    	checkBox = 		(MyE2_DB_CheckBox) this.oComponentMAP.get(FBAM.bearbeitung.fn());
		MyE2_DB_SelectField 	selectUSER = 	(MyE2_DB_SelectField) this.oComponentMAP.get(FBAM.id_user_bearbeitung.fn());
		MyE2_DB_TextField		datum  =   		(MyE2_DB_TextField) this.oComponentMAP.get(FBAM.bearbeitung_datum.fn());
		
		try	{
			if (checkBox.isSelected())	{
				String cUser_ID = bibALL.get_ID_USER_FORMATTED();
				selectUSER.set_ActiveValue(cUser_ID);
				selectUSER.EXT().set_bDisabledFromInteractive(true);
				selectUSER.set_bEnabled_For_Edit(true);
				
				String cSTATUS_MASKE = S.NN(oComponentMAP.get_STATUS_LAST_FILL(),E2_ComponentMAP.STATUS_NEW_EMPTY);

				if (bibALL.isEmpty(datum.getText())) {
					datum.set_cActual_Formated_DBContent_To_Mask(bibALL.get_cDateNOW(), cSTATUS_MASKE, null);
				}
			} else	{
				selectUSER.EXT().set_bDisabledFromInteractive(false);
				selectUSER.set_bEnabled_For_Edit(true);
			}
		} catch (Exception ex)	{
			selectUSER.setSelectedIndex(0);   // leer
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("BAM_ActionAgents:ActionAgent_CheckBox:doAction:Error Selection own USER-ID !",false)));
		}
		
		
	}
	
}