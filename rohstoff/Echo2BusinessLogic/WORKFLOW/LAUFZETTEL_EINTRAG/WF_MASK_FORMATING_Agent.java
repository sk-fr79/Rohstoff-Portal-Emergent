package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.Hashtable;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UB_SelectField_USERS;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class WF_MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7905657594054392152L;

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		// MP: geht nicht!! 	füllen der Inhalte für die Beschreibung des Laufzettels
		SetLaufzettelText(oMAP);

	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		
		// füllen der Inhalte für die Beschreibung des Laufzettels 
		// und den Supervisor
		SetLaufzettelText(oMAP);
		SetConnector(oMAP);
		SetKadenzCB(oMAP);
		
		
	}

	/***
	 * Setzen der Kadenz-Checkboxen 
	 * @param oMAP
	 * @throws myException
	 */
	private void SetKadenzCB(E2_ComponentMAP oMAP) throws myException {
	
		String isKadenzNachFaelligkeit = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("KADENZ_NACH_FAELLIGKEIT","N");
		MyE2_CheckBox oCBKadenzNachAbschluss = (MyE2_CheckBox)oMAP.get__Comp(WF_CONST.HASH_CHECKBOX_KADENZ_NACH_ABSCHLUSS);
		MyE2_CheckBox oCBKadenzNachFaelligkeit = (MyE2_CheckBox)oMAP.get__Comp(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn());
		
		if (isKadenzNachFaelligkeit.equals("Y")){
			((WF_MASK_ComponentMAP)oMAP).getAgentWatchdogKadenz().setCheckbox(oCBKadenzNachFaelligkeit, true);
		} else {
			((WF_MASK_ComponentMAP)oMAP).getAgentWatchdogKadenz().setCheckbox(oCBKadenzNachAbschluss, true);
		}
		
	}

	
	
	/***
	 * Setzen des Connectors 
	 * @param oMAP
	 * @throws myException
	 */
	private void SetConnector(E2_ComponentMAP oMAP) throws myException {
	
		String idLaufzettelEintrag = oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_LAUFZETTEL_EINTRAG");
		
//		MODUL_LINK_Connector m_oModulLinks = new MODUL_LINK_Connector(	E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE, 
//				idLaufzettelEintrag,
//				oMAP.get_oModulContainerMASK_This_BelongsTo());


		MODUL_LINK_Connector o = (MODUL_LINK_Connector)oMAP.get__Comp(WF_CONST.HASH_LIST_CONNECTOR);
		o.initConnector(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE, idLaufzettelEintrag, oMAP.get_oModulContainerMASK_This_BelongsTo());
		o.set_bEnabled_For_Edit(true);
		
	}
	

	
	/***
	 * Setzen des Laufzettel-Textes in der Bearbeitungsmaske
	 * @param oMAP
	 * @throws myException
	 */
	private void SetLaufzettelText(E2_ComponentMAP oMAP) throws myException {
		MyE2_TextArea oBeschreibung = (MyE2_TextArea)oMAP.get__Comp(WF_CONST.HASH_LAUFZETTEL_BESCHREIBUNG);
		UB_SelectField_USERS oSupervisor = (UB_SelectField_USERS)oMAP.get__Comp(WF_CONST.HASH_LAUFZETTEL_SUPERVISOR);
		
		String cText = "-";
		String idSupervisor = null;
		
		Long l = oMAP.get_LActualDBValue("ID_LAUFZETTEL", true, true, null);
		
		SQLFieldMAP oFM = oMAP.get_oSQLFieldMAP();
		SQLField oField = oFM.get_("ID_LAUFZETTEL");
		String cID_LAUFZETTEL_UF = null;
		
		if (oField instanceof SQLFieldForRestrictTableRange){
			cID_LAUFZETTEL_UF=((SQLFieldForRestrictTableRange) oField).get_cRestictionValue_IN_DB_FORMAT();
		}
		
		MyE2_Button obutt = (MyE2_Button)oMAP.get__Comp(WF_CONST.HASH_BUTTON_CREATE_REMINDER);
		MyE2_Button obuttMessage = (MyE2_Button)oMAP.get__Comp(WF_CONST.HASH_BUTTON_CREATE_MESSAGE_TO_GROUP);
		
		if (cID_LAUFZETTEL_UF != null)
		{
			RECORD_LAUFZETTEL oLaufzettel = new RECORD_LAUFZETTEL(cID_LAUFZETTEL_UF);
			if (oLaufzettel != null && !oLaufzettel.isEmpty())
			{
				cText = oLaufzettel.get_TEXT_cUF();
				idSupervisor = oLaufzettel.get_ID_USER_SUPERVISOR_cUF();
				
				// Kennzeichnen ob der Eintrag privat ist.
				String cPrivat =  S.NN(oLaufzettel.get_PRIVAT_cUF());
				if (cPrivat.equalsIgnoreCase("Y"))
				{
					cText = "PRIVAT: " + cText; 
				}
				
			}
			obutt.set_bEnabled_For_Edit(true);
			obuttMessage.set_bEnabled_For_Edit(true);

		} else {
			obutt.set_bEnabled_For_Edit(false);
			obuttMessage.set_bEnabled_For_Edit(false);
		}
		
		
		oBeschreibung.setText(cText);
		
		oSupervisor.set_ActiveValue_OR_FirstValue(idSupervisor);
		
		
		// mögliche Parameter-Übergaben in den Feldern setzen
		
		// Parameter übernehmen (Key muss heissen wie die Komponente)
		Hashtable<String, String> parameters = ((WF_MASK_ComponentMAP)oMAP).getParameters();
		if (parameters.size() > 0){
			for (String key : parameters.keySet()){
				String sValue = parameters.get(key);
				
				// für Textarea und dbTextarea
				Object o = oMAP.get__Comp(key);
				if (o instanceof MyE2_TextArea){
					((MyE2_TextArea)o).setText(sValue);
				} else if (o instanceof MyE2_DB_TextArea){
					((MyE2_DB_TextArea)o).setText(sValue);

				//	für TextField und dbTextField
				} else if (o instanceof MyE2_TextField){
					((MyE2_TextField)o).setText(sValue);
				} else if (o instanceof MyE2_DB_TextField){
					((MyE2_DB_TextField)o).setText(sValue);
				}
			}
		}
		
	}

}
