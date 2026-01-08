package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MASK_VALID;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_Adress_Check;

public class FS_VALID_PRIVAT_GESCHAEFTSKUNDEN extends XX_MAP_Set_And_Valid {


	
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this._pruefung(oMAP, ActionType, oExecInfo, oInputMap);
	}

	
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this._pruefung(oMAP, ActionType, oExecInfo, oInputMap);
	}

	
	
	
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this._pruefung(oMAP, ActionType, oExecInfo, oInputMap);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector  oMV_Rueck = new MyE2_MessageVector();
		return oMV_Rueck;
	}


	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		MyE2_MessageVector  oMV_Rueck = new MyE2_MessageVector();
		return oMV_Rueck;
	}


	
	
	private MyE2_MessageVector  _pruefung(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		
		__FS_Adress_Check oAdressCheck = new __FS_Adress_Check(oMAP);
		
		MyE2_MessageVector  oMV_Rueck = new MyE2_MessageVector();
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION && S.isFull(oExecInfo.get_HASHKEY_of_KLICK_COMPONENT())) {
			
			//hier die beiden schalter firma und privat gegensaetzlich steuern
			if (oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(_DB.FIRMENINFO$PRIVAT)) {
				if (((MyE2_DB_CheckBox)oAdressCheck.get_oMAP_FIRMENINFO().get__Comp(_DB.FIRMENINFO$PRIVAT)).isSelected()) {
					((MyE2_DB_CheckBox)oAdressCheck.get_oMAP_FIRMENINFO().get__Comp(_DB.FIRMENINFO$FIRMA)).setSelected(false);
					// neu einlesen, da veraendert
					oAdressCheck = new __FS_Adress_Check(oMAP);
				}
			}
			if (oExecInfo.get_HASHKEY_of_KLICK_COMPONENT().equals(_DB.FIRMENINFO$FIRMA)) {
				if (((MyE2_DB_CheckBox)oAdressCheck.get_oMAP_FIRMENINFO().get__Comp(_DB.FIRMENINFO$FIRMA)).isSelected()) {
					((MyE2_DB_CheckBox)oAdressCheck.get_oMAP_FIRMENINFO().get__Comp(_DB.FIRMENINFO$PRIVAT)).setSelected(false);
					// neu einlesen, da veraendert
					oAdressCheck = new __FS_Adress_Check(oMAP);
				}
			}
			oMV_Rueck.add_MESSAGE(oAdressCheck.get_Messages(oExecInfo.get_HASHKEY_of_KLICK_COMPONENT()));
		} else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION ) {
			oMV_Rueck.add_MESSAGE(oAdressCheck.get_MessagesOnSave());
		} else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION ) {
			oMV_Rueck.add_MESSAGE(oAdressCheck.get_MessagesOnLoad());	
		}
		return oMV_Rueck;
	}
	
	
	
	
}
