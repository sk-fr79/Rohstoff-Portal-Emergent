package panter.gmbh.basics4project.SANKTION_FREIGABE;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask_V2;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public class ADR_FREIGABE_LIST_bt_ListToMask extends RB_bt_List2Mask_V2 {
	private E2_NavigationList  naviList = null;
	
	public ADR_FREIGABE_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList) {
		super(bEdit,p_naviList);
		this.naviList = p_naviList;

		this.add_GlobalValidator(new XX_ActionValidator_NG() {
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				
				
				if (naviList.get_vSelectedIDs_Unformated().size()==0) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie müssen mindestens eine Datenzeile auswählen !")));
				}
				return mv;
			}
		});
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new ADR_FREIGABE_MASK_MaskModulContainer();
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
		Vector<String> v_ids = new Vector<String>();
		v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());

		RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
		RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;

		for (String id: v_ids) {
			boolean is_aktiv = new Rec21(_TAB.sanktion_pruefung)._fill_id(id).is_yes_db_val(SANKTION_PRUEFUNG.aktiv);
			if(is_aktiv) {
				collector.put(id, new ADR_FREIGABE_MASK_DataObjectCollector(id,aktuellerStatus));
			}else {
				collector.put(id, new ADR_FREIGABE_MASK_DataObjectCollector(id,RB__CONST.MASK_STATUS.VIEW));
			}
		}
		return collector;
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return S.ms(this.is_UsedToEdit()?"Bearbeiten von Adress-Sperren wegen Sanktionen":"Bearbeiten von Adress-Sperren wegen Sanktionen");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return S.ms("Sperreinstellungen wurden gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
		Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();

		v_rueck.add(new XX_ActionAgentWhenCloseWindow(rb_ModulContainerMask) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ADR_FREIGABE_LIST_bt_ListToMask.this.get_NaviList()._REBUILD_ACTUAL_SITE("");
			}
		});
		return v_rueck;
	}

	
}

