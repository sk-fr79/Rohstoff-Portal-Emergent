/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.AH7.PROFIL.AH7P_MASK_DataObjectCollector;
import rohstoff.Echo2BusinessLogic.AH7.PROFIL.AH7P_MASK_MaskModulContainer;

/**
 * @author martin
 * komponente um die profilnamen und einen edit-button anzuzeigen
 */
public class AH7_LIST_ShowAndEditProfil extends E2_Grid  implements MyE2IF_DB_SimpleComponent, E2_IF_Copy {

	
	public AH7_LIST_ShowAndEditProfil() {
		super();
		this._setSize(16,200);
	}

	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		
		String id_ah7_steuerdatei=AH7_LIST_ShowAndEditProfil.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		
		this.prepare_ContentForNew(false);
		
		Rec21 r_steuerdatei = new Rec21(_TAB.ah7_steuerdatei)._fill_id(id_ah7_steuerdatei);
		Rec21 r_profil 		= r_steuerdatei.get_up_Rec21(AH7_PROFIL.id_ah7_profil);
		if (r_profil!=null) {
			this._a(new BtEditProfil(r_profil.getLongDbValue(AH7_PROFIL.id_ah7_profil)));
			this._a((E2_Button)new E2_Button()
							._t(r_profil.get_fs_dbVal(AH7_PROFIL.bezeichnung))
							._fo_s_plus(-2)._lwn()
							._i()
							._aaa(()-> {AH7_LIST_ShowAndEditProfil.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();}));
		}
		
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			AH7_LIST_ShowAndEditProfil oCheckCopy = new AH7_LIST_ShowAndEditProfil();
			oCheckCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCheckCopy));
			return oCheckCopy;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy("Error copying AH7_LIST_ShowAndEditProfil");
		}
	}

	
	private class BtEditProfil extends E2_Button {

		private Long id_ah7_profil;

		public BtEditProfil(Long id_ah7_profil) {
			super();
			this.id_ah7_profil = id_ah7_profil;
			this.__setImages(E2_ResourceIcon.get_RI("edit_list3.png"), E2_ResourceIcon.get_RI("edit_list3__.png"));
			this._aaa(new OwnAction());
			this._ttt(S.ms("Zugehöriges Profil öffnen und bearbeiten ..."));

	        this.add_GlobalValidator(ENUM_VALIDATION.AH7_PROFIL_EDIT.getValidatorWithoutSupervisorPersilschein());
		}
		
		
		private class OwnAction extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				AH7P_MASK_MaskModulContainer 	container = new AH7P_MASK_MaskModulContainer();
				container.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(new AH7P_MASK_DataObjectCollector(id_ah7_profil.toString(),MASK_STATUS.EDIT));
				E2_Grid g = new E2_Grid()._s(2);

				E2_Button btSaveAndClose = 		new E2_Button()._image("save.png", true)._aaa(new RB_actionStandardSave(container))._aaa(new RB_actionStandardClosePopup(container));
				btSaveAndClose._aaa(
						()->{AH7_LIST_ShowAndEditProfil.this.set_cActual_Formated_DBContent_To_Mask(null,null,null);}
						);
				
				E2_Button btCancelAndClose = 	new E2_Button()._image("cancel.png", true)._aaa(new RB_actionStandardClosePopup(container));
				
				g._a(btSaveAndClose, new RB_gld()._ins(2,2,10,2))._a(btCancelAndClose, new RB_gld()._ins(2,2,10,2));
				container.get_oRowForButtons().add(g);
				
				container.CREATE_AND_SHOW_POPUPWINDOW(S.ms("AH7-Profil bearbeiten ..."));
			}
			
		}
		
		
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(true);
	}
	
	
}
