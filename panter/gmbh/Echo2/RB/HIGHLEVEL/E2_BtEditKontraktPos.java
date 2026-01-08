package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_P_MASK_SAVE_PopupContainer_to_ChangePositionsPreise;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_P_MASK__ModulContainer;

public abstract class E2_BtEditKontraktPos extends E2_Button  {

	public abstract Long findIdVposKon() throws myException;
	
	private Vector<XX_ActionAgent>   vActionAfterSave = new VEK<XX_ActionAgent>();
	private Vector<XX_ActionAgent>   vActionAfterCancel = new VEK<XX_ActionAgent>();
	
	private ENUM_VORGANGSART  		vorgangArt = null;
	private ENUM_VALIDATION  		valEdit = null;
	private ENUM_VALIDATION  		valView = null;
	
	public E2_BtEditKontraktPos(ENUM_VORGANGSART p_vorgangArt) throws myException {
		super();
		
		if (! (p_vorgangArt==ENUM_VORGANGSART.EK_KONTRAKT || p_vorgangArt==ENUM_VORGANGSART.VK_KONTRAKT)) {
			throw new myException("Can only be used with EK_KONTRAKT or VK_KONTRAKT");
		}
		vorgangArt = p_vorgangArt;

		if (p_vorgangArt==ENUM_VORGANGSART.EK_KONTRAKT) {
			valEdit=ENUM_VALIDATION.VPOS_KON_EK_EDIT;
			valView=ENUM_VALIDATION.VPOS_KON_EK_VIEW;
		} else {
			valEdit=ENUM_VALIDATION.VPOS_KON_VK_EDIT;
			valView=ENUM_VALIDATION.VPOS_KON_VK_VIEW;
		}
		
		this._image(E2_ResourceIcon.get_RI("edit_list3.png"),true)
			._aaa(new ownActionAgent());
	}
	

	private class ownActionAgent extends XX_ActionAgent {
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
				
			E2_BtEditKontraktPos oThis = E2_BtEditKontraktPos.this;
			
			Long idVposKon = E2_BtEditKontraktPos.this.findIdVposKon();
				
			boolean ok = false;
			
			if (idVposKon!=null) {
				
				//nachsehen, ob der benutzer editieren darf, wenn nein, dann nur view
				MyE2_MessageVector mv = oThis.valEdit.getValidator().isValid(E2_BtEditKontraktPos.this);
				MyE2_MessageVector mv2 = oThis.valView.getValidator().isValid(E2_BtEditKontraktPos.this);
				
				if (mv2.hasAlarms()) {    //dann darf der benutzer gar nichts
					bibMSG.MV()._add(mv2);
					return;
				}
				
				
				Rec21 recVposKon = new Rec21(_TAB.vpos_kon)._fill_id(idVposKon);
				
				if (recVposKon!=null && recVposKon.is_ExistingRecord()) {
					Rec21 recVkopfKon = recVposKon.get_up_Rec21(VKOPF_KON.id_vkopf_kon);
					Rec21 recVposKonTrakt = recVposKon.get_down_reclist21(VPOS_KON_TRAKT.id_vpos_kon).get(0);
					
					if (recVposKon.is_yes_db_val(VPOS_KON.deleted) || recVkopfKon.is_yes_db_val(VKOPF_KON.deleted)) {
						bibMSG.MV()._addAlarm(S.ms("Der Beleg ist gelöscht worden !!"));
						return;
					} else {
						if (recVkopfKon!=null && recVkopfKon.is_ExistingRecord() && recVposKonTrakt!=null && recVposKonTrakt.is_ExistingRecord()) {
							ok=true;
					
							boolean edit =true;
							if (mv.get_bHasAlarms()) {
								edit=false;
							}
							if (recVposKonTrakt.is_yes_db_val(VPOS_KON_TRAKT.abgeschlossen)) {
								edit = false;
							}
						
							BSK_P_MASK__ModulContainer 	oMaskPositions = new BSK_P_MASK__ModulContainer(new BS__SETTING(oThis.vorgangArt.db_val()));
							oMaskPositions.make_SettingsFrom_Head_to_Position(recVkopfKon.getIdLong().toString());
							oMaskPositions.get_oRowForButtons().removeAll();
							maskButtonSaveMask oMaskButtonSaveMask = new maskButtonSaveMask(	oMaskPositions,
																								new ownSaveMASK(oMaskPositions),	
																								null, 
																								new BSK_P_MASK_SAVE_PopupContainer_to_ChangePositionsPreise(oMaskPositions));
							if (edit) {
								oMaskPositions.get_oRowForButtons().add(oMaskButtonSaveMask);
							}
							oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));
							
							oMaskPositions.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(	edit?E2_ComponentMAP.STATUS_EDIT:E2_ComponentMAP.STATUS_VIEW,
																									recVposKon.getIdLong().toString());
							oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,S.ms("Kontraktposition "+recVposKon.getFs(VPOS_KON.id_vpos_kon)));
							
						}
					}
				}
			} 
			
			if (!ok){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Es konnte keine Kontraktposition gefunden werden!")));
			}
		}
	}


	public Vector<XX_ActionAgent> getvActionAfterSave() {
		return vActionAfterSave;
	}


	public Vector<XX_ActionAgent> getvActionAfterCancel() {
		return vActionAfterCancel;
	}
	
	
	public E2_BtEditKontraktPos _addActionAfterSave(XX_ActionAgent agent) {
		this.vActionAfterSave.add(agent);
		return this;
	}
	
	public E2_BtEditKontraktPos _addActionAfterCancel(XX_ActionAgent agent) {
		this.vActionAfterCancel.add(agent);
		return this;
	}

	
	
	/*
	 * eigener maskSaver, damit die tochterkomponente gleich refresh werden kann 
	 */
	private class ownSaveMASK extends E2_SaveMASK {

		public ownSaveMASK(E2_BasicModuleContainer_MASK maskContainer) {
			super(maskContainer, null);
		}

		public boolean checkMaskBeforeSave(E2_BasicModuleContainer_MASK oMaskContainer, E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps, String cActualMaskStatus)	{
			return true;
		}

		public void actionAfterSaveMask() throws myException {
			E2_BtEditKontraktPos oThis = E2_BtEditKontraktPos.this;
			
			for (XX_ActionAgent a: oThis.vActionAfterSave) {
				a.executeAgentCode(new ExecINFO_OnlyCode());
			}
		}
		
	}

	
}
