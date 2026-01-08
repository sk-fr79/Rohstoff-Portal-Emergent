package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_K_M_BT_List2Mask extends RB_bt_List2Mask {

	private E2_NavigationList 	naviList		= null;

	private VORGANGSART 		belegTyp		= null;

	private Vector<String>  	vLastUsedIDs 	= new Vector<String>();

	private boolean 			bKontraktsType = false;

	public KFIX_K_M_BT_List2Mask(boolean bEdit, E2_NavigationList p_naviList) throws myException {

		super(bEdit, p_naviList);

		this.naviList = p_naviList;

		String oModul = p_naviList.get_oContainer_NaviList_BelongsTo().get_MODUL_IDENTIFIER();

		if(oModul.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_EK_KONTRAKT_LIST_NG.get_callKey())){
			this.belegTyp = VORGANGSART.EK_KONTRAKT;
		}else{
			this.belegTyp = VORGANGSART.VK_KONTRAKT;
		}

		if(this.belegTyp == VORGANGSART.EK_KONTRAKT){
			this.add_GlobalValidator(new KFIX_K_M_Selection_Validator(this.naviList));
			if(bEdit){
				this.add_GlobalValidator( ENUM_VALIDATION.VKOPF_KON_EK_EDIT.getValidator());
			}else{
				this.add_GlobalValidator( ENUM_VALIDATION.VKOPF_KON_EK_VIEW.getValidator());
			}
		}else{
			this.add_GlobalValidator(new KFIX_K_M_Selection_Validator(this.naviList));
			if(bEdit){
				this.add_GlobalValidator( ENUM_VALIDATION.VKOPF_KON_VK_EDIT.getValidator());
			}else{
				this.add_GlobalValidator( ENUM_VALIDATION.VKOPF_KON_EK_VIEW.getValidator());
			}
		}
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		if(this.naviList.get_vSelectedIDs_Unformated().size()>0){
			this.bKontraktsType = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(this.naviList.get_vSelectedIDs_Unformated().get(0)).is_fixierungsKontrakte();
		}
		return new KFIX_K_M_MaskModulContainer(this.bKontraktsType, this.belegTyp, this.naviList);
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler)
			throws myException {


		this.vLastUsedIDs.clear();
		this.vLastUsedIDs.addAll(naviList.get_vSelectedIDs_Unformated());



		if (this.vLastUsedIDs.size()==0) {
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens eine Kontrakt auswählen!")));

			return null;
		} else {
			RB_hm_multi_DataobjectsCollector hmDataContainer = new RB_hm_multi_DataobjectsCollector();

			for (String id: this.vLastUsedIDs) {

				boolean isAbgeschlossen = new Rec20(_TAB.vkopf_kon)._fill_id(id).is_yes_db_val(VKOPF_KON.abgeschlossen);
				
				boolean isDeleted = new Rec20(_TAB.vkopf_kon)._fill_id(id).is_yes_db_val(VKOPF_KON.deleted);
				
				if(isAbgeschlossen || isDeleted){
					hmDataContainer.put(id, new KFIX_K_M__DataObjectCollector(id,RB__CONST.MASK_STATUS.VIEW,this.belegTyp));
				}else{
					hmDataContainer.put(id, new KFIX_K_M__DataObjectCollector(id,this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW,this.belegTyp));
				}
			}
			return hmDataContainer;
		}
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String(this.is_UsedToEdit()?"Bearbeiten von Fixierungskontrakte":"Anzeige von Fixierungskontrakte");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String("Fixierungskontrakte wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(
			RB_ModuleContainerMASK rb_ModulContainerMask, E2_NavigationList navilist) throws myException {
		Vector<XX_ActionAgentWhenCloseWindow> oVekt = new Vector<XX_ActionAgentWhenCloseWindow>();
		oVekt.add(new ownActionRefreshNavilist(rb_ModulContainerMask));
		return oVekt;
	}

	private class ownActionRefreshNavilist extends XX_ActionAgentWhenCloseWindow {


		public ownActionRefreshNavilist(RB_ModuleContainerMASK p_maskPopup) {
			super(p_maskPopup);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			E2_NavigationList f_naviList = KFIX_K_M_BT_List2Mask.this.get_NaviList();

			f_naviList._REBUILD_ACTUAL_SITE(true, true, vLastUsedIDs);
		}
	}
}
