package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class USER_MASK_ComponentMAP_SetAndValid_LockFieldsNonAdmin extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ErrorWithEdit(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ErrorWithEdit(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_MaskLockWithUserEdit(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ErrorWithView(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.set_ErrorWithEdit(oMAP, ActionType, oExecInfo, oInputMap);
	}

	
	
	private MyE2_MessageVector set_MaskLockWithUserEdit(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		BASIC_RECORD_USER  oUserLoggedIn = bibALL.get_RECORD_USER();
		if (oUserLoggedIn.is_IST_SUPERVISOR_YES() || oUserLoggedIn.is_GESCHAEFTSFUEHRER_YES()) {
			return oMV;           //darf alles editieren
		}
		
		if (oMAP.get_oInternalSQLResultMAP().get_LActualDBValue(_DB.USER$ID_USER, true).intValue()==oUserLoggedIn.get_ID_USER_lValue(new Long(-1)).intValue()) {
			
			//hier werden einige Felder gesperrt
			Vector<String> vSperrFelder = new Vector<String>();
			vSperrFelder.add(_DB.USER$ID_MANDANT);
			vSperrFelder.add(_DB.USER$NAME);
			vSperrFelder.add(_DB.USER$VORNAME);
			vSperrFelder.add(_DB.USER$ID_SPRACHE);
			vSperrFelder.add(_DB.USER$KUERZEL);
			vSperrFelder.add(_DB.USER$AKTIV);
			vSperrFelder.add(_DB.USER$TODO_SUPERVISOR);
			vSperrFelder.add(_DB.USER$EMAIL);
			vSperrFelder.add(_DB.USER$TEXTCOLLECTOR);
			vSperrFelder.add(_DB.USER$IST_SUPERVISOR);
			vSperrFelder.add(_DB.USER$ID_USERGROUP);
			vSperrFelder.add(_DB.USER$TELEFON);
			vSperrFelder.add(_DB.USER$TELEFAX);
			vSperrFelder.add(_DB.USER$NAME1);
			vSperrFelder.add(_DB.USER$NAME2);
			vSperrFelder.add(_DB.USER$NAME3);
			vSperrFelder.add(_DB.USER$IST_FAHRER);
			//vSperrFelder.add(_DB.USER$MAIL_SIGNATUR);
			vSperrFelder.add(_DB.USER$HAT_FAHRPLAN_BUTTON);
			vSperrFelder.add(_DB.USER$FENSTER_MIT_SCHATTEN);
			vSperrFelder.add(_DB.USER$VERTICAL_OFFSET_MASKTABS);
			vSperrFelder.add(_DB.USER$SONDERRECH_ZEIGE_OPLISTE_SALDO);
			vSperrFelder.add(_DB.USER$IST_VERWALTUNG);
			vSperrFelder.add(_DB.USER$ANREDE);
			vSperrFelder.add(_DB.USER$GESCHAEFTSFUEHRER);
			vSperrFelder.add(_DB.USER$MENGENABSCHLUSS_FUHRE_OK);
			vSperrFelder.add(_DB.USER$PREISABSCHLUSS_FUHRE_OK);
			
			//die Signatur-Funktion
			vSperrFelder.add("ID_USER_SIGNATUR");
			
			oMAP.set_ActiveADHOC(vSperrFelder, false, false);
			
			
		} else {
			//sicherheitshalber ERROR-Message, verhinder oeffnen der Maske
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Öffnen und Bearbeiten anderer Benutzer ist nur ADMINS gestattet !")));
		}
			
				
		
		return oMV;
	}

	
	private MyE2_MessageVector set_ErrorWithEdit(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		BASIC_RECORD_USER  oUserLoggedIn = bibALL.get_RECORD_USER();
		if (oUserLoggedIn.is_IST_SUPERVISOR_YES() || oUserLoggedIn.is_GESCHAEFTSFUEHRER_YES()) {
			return oMV;           //darf alles editieren
		}
		
		// bei allen anderen erfolgt ein fehler
		oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Öffnen und Bearbeiten anderer Benutzer ist nur ADMINS gestattet !")));
		
		return oMV;
	}
	
	
	
	private MyE2_MessageVector set_ErrorWithView(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		BASIC_RECORD_USER  oUserLoggedIn = bibALL.get_RECORD_USER();
		if (oUserLoggedIn.is_IST_SUPERVISOR_YES() || oUserLoggedIn.is_GESCHAEFTSFUEHRER_YES()) {
			return oMV;           //darf alles editieren
		}

		if (oMAP.get_oInternalSQLResultMAP().get_LActualDBValue(_DB.USER$ID_USER, true).intValue()!=oUserLoggedIn.get_ID_USER_lValue(new Long(-1)).intValue()) {
			// bei allen anderen erfolgt ein fehler
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Öffnen und Bearbeiten anderer Benutzer ist nur ADMINS gestattet !")));
		}
		
		return oMV;
	}
	
	
	
	
	
	
}
