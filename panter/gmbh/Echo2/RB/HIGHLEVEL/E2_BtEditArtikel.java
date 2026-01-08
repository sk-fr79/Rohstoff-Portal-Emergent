package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.AS_BasicModuleContainerMASK;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.Break4PopupCheckRCStatus;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;

public abstract class E2_BtEditArtikel extends E2_Button  {

	public abstract Long findIdArtikel() throws myException;
	
	private Vector<XX_ActionAgent>   vActionAfterSave = new VEK<XX_ActionAgent>();
	private Vector<XX_ActionAgent>   vActionAfterCancel = new VEK<XX_ActionAgent>();
	
	public E2_BtEditArtikel() throws myException {
		super();
		this._image(E2_ResourceIcon.get_RI("edit_list3.png"),true)
			._aaa(new ownActionAgent())
			._v(ENUM_VALIDATION.ARTIKEL_EDIT.getValidator());
	}
	

	private class ownActionAgent extends XX_ActionAgent {
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
				
			Long idArtikel = E2_BtEditArtikel.this.findIdArtikel();
				
			if (idArtikel!=null) {
				
				//nachsehen, ob der benutzer editieren darf, wenn nein, dann nur view
				MyE2_MessageVector mvEdit = ENUM_VALIDATION.ARTIKEL_EDIT.getValidator().isValid(E2_BtEditArtikel.this);
				MyE2_MessageVector mvView = ENUM_VALIDATION.ARTIKEL_VIEW.getValidator().isValid(E2_BtEditArtikel.this);
			
				if (mvView.get_bHasAlarms()) {
					bibMSG.MV()._add(mvView);
				} else {
				

					
					
					//zuerst schauen, ob es eine hauptadresse ist
					Rec21_artikel  rae = (Rec21_artikel)new Rec21_artikel()._fill_id(idArtikel);
					
					AS_BasicModuleContainerMASK oMask = new AS_BasicModuleContainerMASK();

					
					String cSTATUS = null;
					String cInfo = null;
							
					oMask.get_oRowForButtons().removeAll();
					
					if (mvEdit.get_bIsOK()) {
						cSTATUS = E2_ComponentMAP.STATUS_EDIT;
						cInfo = "Artikel bearbeiten ...";
						maskButtonSaveMask bt_save = new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null);
						
						for (XX_ActionAgent agent: E2_BtEditArtikel.this.vActionAfterSave) {
							bt_save.add_oActionAgent(agent);
						}
						
						oMask.get_oRowForButtons().add(bt_save);
						
						
						//2020-10-29: RC-ueberwachung
						bt_save.setBreak4PopupController(new E2_Break4PopupController()._registerBreak(new Break4PopupCheckRCStatus(oMask)));		

						
						
					} else {
						cSTATUS = E2_ComponentMAP.STATUS_VIEW;
						cInfo = "Artikel anzeigen";
					}
					MaskButtonCancelMaskSTANDARD bt_cancel = new MaskButtonCancelMaskSTANDARD(oMask);
					for (XX_ActionAgent agent: E2_BtEditArtikel.this.vActionAfterCancel) {
						bt_cancel.add_oActionAgent(agent);
					}
					
					oMask.get_oRowForButtons().add(bt_cancel);
						
					E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMask.get_vCombinedComponentMAPs();
					vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,rae.getIdLong().toString());
					oMask.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String(cInfo));
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Es konnte kein Artikel gefunden werden!")));
			}
		}
	}


	public Vector<XX_ActionAgent> getvActionAfterSave() {
		return vActionAfterSave;
	}


	public Vector<XX_ActionAgent> getvActionAfterCancel() {
		return vActionAfterCancel;
	}
	
	
	public E2_BtEditArtikel _addActionAfterSave(XX_ActionAgent agent) {
		this.vActionAfterSave.add(agent);
		return this;
	}
	
	public E2_BtEditArtikel _addActionAfterCancel(XX_ActionAgent agent) {
		this.vActionAfterCancel.add(agent);
		return this;
	}

	
}
