package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;

import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Editor;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;

public class AI_LIST_btSendAdhocMessage extends MyE2_Button
{

	public AI_LIST_btSendAdhocMessage()
	{
		super(new MyE2_String("Direkt-Nachricht"));
		this.setFont(new E2_FontPlain(-2));
		this.add_oActionAgent(new ownActionAgent());
	}

	
	//hier umdrehen: im status bearbeiten inaktiv und umgekehrt
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(!enabled);
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		AI_LIST_btSendAdhocMessage oButton = new AI_LIST_btSendAdhocMessage();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		return oButton;
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			//die vordefinierten Empfaenger sammeln
			VectorSingle vIDsEmpfaenger = new VectorSingle();
			
			E2_ComponentMAP  oMap = AI_LIST_btSendAdhocMessage.this.EXT().get_oComponentMAP();
			RECORD_ADRESSE_INFO  	recInfo =		new RECORD_ADRESSE_INFO(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			RECORD_ADRESSE  		recAdresse = 	recInfo.get_UP_RECORD_ADRESSE_id_adresse();


			if (oMap.get_bdActualDBValue("ID_USER", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				vIDsEmpfaenger.add(""+oMap.get_cActualDBValueFormated("ID_USER", "-1"));
			}
			if (oMap.get_bdActualDBValue("ID_USER_ERSATZ", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				vIDsEmpfaenger.add(""+oMap.get_cActualDBValueFormated("ID_USER_ERSATZ", "-1"));
			}
			if (oMap.get_bdActualDBValue("ID_USER_SACHBEARBEITER", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				vIDsEmpfaenger.add(""+oMap.get_cActualDBValueFormated("ID_USER_SACHBEARBEITER", "-1"));
			}
			
			
			String cBetreff="Infotext zu Adresse:  "+recAdresse.get_NAME1_cUF_NN("")+" "+recAdresse.get_ORT_cF_NN("");
			String cNachricht = recInfo.get_TEXT_cUF_NN("<Textinhalt>");
			
//			MESSAGE_Editor oMSG_EDIT = new MESSAGE_Editor();
//			oMSG_EDIT.__get_tfTitel().setText(cBetreff);
//			oMSG_EDIT.__get_tfNachricht().setText(cNachricht);
//			oMSG_EDIT.__add_Empfaenger(vIDsEmpfaenger);
//			oMSG_EDIT.__get_cbDirektanzeige().setSelected(true);

			
			MESSAGE_Entry_Target target = new MESSAGE_Entry_Target(
					recInfo.get_ID_ADRESSE_INFO_cUF(),
					E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK_INFOLISTE,
					null,
					null);
			
			MESSAGE_Entry entry = new MESSAGE_Entry()
									.set_Titel(cBetreff)
									.set_Nachricht(cNachricht)
									.set_vIdEmpfaenger(vIDsEmpfaenger)
									.set_Kategorie(MESSAGE_CONST.REMINDER_Kennung.MESSAGE_FIRMENSTAMM_INFO.toString())
									.add_Target(target)
									.set_Sofortanzeige(true)
									;
			
			MESSAGE_Editor oMSG_EDIT = new MESSAGE_Editor(entry);
			

			
			
		}
	}
	
}
