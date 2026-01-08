package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public abstract class E2_BtEditAdress extends E2_Button  {

	public abstract MyLong find_id_adress() throws myException;
	
	private Vector<XX_ActionAgent>   vActionAfterSave = new VEK<XX_ActionAgent>();
	private Vector<XX_ActionAgent>   vActionAfterCancel = new VEK<XX_ActionAgent>();
	
	public E2_BtEditAdress() throws myException {
		super();
		this._image(E2_ResourceIcon.get_RI("edit_list3.png"),true)
			._aaa(new ownActionAgent())
			._v(ENUM_VALIDATION.ADRESSE_EDIT.getValidator());
	}
	
	
	public E2_BtEditAdress(boolean allowViewWhenNotAllowedEdit) throws myException {
		super();
		this._image(E2_ResourceIcon.get_RI("edit_list3.png"),true)
			._aaa(new ownActionAgent());
		
		if (!allowViewWhenNotAllowedEdit) {
			this._v(ENUM_VALIDATION.ADRESSE_EDIT.getValidator());
		}
	}
	
	
	

	private class ownActionAgent extends XX_ActionAgent {
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
				
			MyLong id_adresse = E2_BtEditAdress.this.find_id_adress();
				
			if (id_adresse!=null && id_adresse.isOK()) {
				
				//nachsehen, ob der benutzer editieren darf, wenn nein, dann nur view
				MyE2_MessageVector mv = ENUM_VALIDATION.ADRESSE_EDIT.getValidator().isValid(E2_BtEditAdress.this);
			
				//zuerst schauen, ob es eine hauptadresse ist
				RECORD_ADRESSE_extend  rae = new RECORD_ADRESSE_extend(id_adresse.get_lValue()).get_main_Adress();
				
				
				if (bibDB.EinzelAbfrage("SELECT COUNT(*) FROM " +
						bibE2.cTO()+".JT_ADRESSE WHERE ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO+
						" AND ID_ADRESSE="+rae.ufs(ADRESSE.id_adresse)).equals("1")) 	{
						
					FS_ModulContainer_MASK oMask = new FS_ModulContainer_MASK();
				
					String cSTATUS = null;
					String cInfo = null;
						
					oMask.get_oRowForButtons().removeAll();
				
					if (mv.get_bIsOK()) {
						cSTATUS = E2_ComponentMAP.STATUS_EDIT;
						cInfo = "Adresse bearbeiten ...";
						maskButtonSaveMask bt_save = new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null);
						
						for (XX_ActionAgent agent: E2_BtEditAdress.this.vActionAfterSave) {
							bt_save.add_oActionAgent(agent);
						}
						
						oMask.get_oRowForButtons().add(bt_save);
					} else {
						cSTATUS = E2_ComponentMAP.STATUS_VIEW;
						cInfo = "Adresse anzeigen";
					}
					MaskButtonCancelMaskSTANDARD bt_cancel = new MaskButtonCancelMaskSTANDARD(oMask);
					for (XX_ActionAgent agent: E2_BtEditAdress.this.vActionAfterCancel) {
						bt_cancel.add_oActionAgent(agent);
					}
					
					oMask.get_oRowForButtons().add(bt_cancel);
						
					E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMask.get_vCombinedComponentMAPs();
					vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,rae.ufs(ADRESSE.id_adresse));
					oMask.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String(cInfo));
					
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Adresse mit der ID :").ut(id_adresse.get_cF_LongString()).t(" wurde nicht gefunden / konnte keiner Hauptadresse zugeordnet werden!")));
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Es konnte keine Adresse gefunden werden!")));
			}
		}
	}


	public Vector<XX_ActionAgent> getvActionAfterSave() {
		return vActionAfterSave;
	}


	public Vector<XX_ActionAgent> getvActionAfterCancel() {
		return vActionAfterCancel;
	}
	
	
	public E2_BtEditAdress _addActionAfterSave(XX_ActionAgent agent) {
		this.vActionAfterSave.add(agent);
		return this;
	}
	
	public E2_BtEditAdress _addActionAfterCancel(XX_ActionAgent agent) {
		this.vActionAfterCancel.add(agent);
		return this;
	}

	
}
