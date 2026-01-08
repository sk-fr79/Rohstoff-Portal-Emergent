package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic._4_ALL.BT_OpenMaskByID;

public class TR__LIST_BT_DirektEditWithFuhrenView extends BT_OpenMaskByID {
 
	private TR__MASK_BasicModuleContainer_4_direktClick  oMaskContainer = null;
	
	
	public TR__LIST_BT_DirektEditWithFuhrenView() {
		super(E2_ResourceIcon.get_RI("edit.png"),E2_ResourceIcon.get_RI("leer.png"));
		this.set_bShowSaveAndReload(false);
		//2018-07-12: neue validierung:  this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("BEARBEITE_TR_"));
		this.add_GlobalValidator(ENUM_VALIDATION.HANDELSDEFINITIONEN_EDIT.getValidatorWithoutSupervisorPersilschein());
		this.setToolTipText(new MyE2_String("Editieren der Handelsdefinition mit sichtbarer Beispielfuhre").CTrans());
		
		this.set_cTitel4Popup(new MyE2_String("Bearbeiten einer Handelsdefinition"));
	}

	//immer aktiv
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		TR__LIST_BT_DirektEditWithFuhrenView btRueck = new TR__LIST_BT_DirektEditWithFuhrenView();
		btRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(btRueck));
		return btRueck;
	}

	

	@Override
	public MyE2_MessageVector do_AfterCreatedAndFilledMaskObject(E2_BasicModuleContainer_MASK oMaskContainer, String cID_MainTable_UF, String cEDITSTATUS) throws myException {
		
		//TestCode
		E2_BasicModuleContainer_MASK oContainer = this.get_BasicModuleContainer_MASK();
		TR__MASK tr_MASK = (TR__MASK)oContainer.get_oComponentWithMaskElements();
		
		//tr_MASK.get_oGridWithMaskContent().setSize(4);
		//jetzt eine fuhrenid suchen 
		RECORD_HANDELSDEF  recHandelsdef = new RECORD_HANDELSDEF(this.get_cID_BASICTABLE_UF());
		
		TR___CreateStatement_to_find_Fuhre oSQLCreater = new TR___CreateStatement_to_find_Fuhre(recHandelsdef, 1);
		oSQLCreater.createSqlStatementHandelsDef();
		
		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(oSQLCreater.get_cSQL_FAST());
		
	//	DEBUG.System_println("<##@@@@searchhelp>"+ oSQLCreater.get_cSQL_FAST());
		
		String cID_VPOS_TPA_FUHRE = null;
		
		if (cErgebnis != null && cErgebnis.length>0) {
			cID_VPOS_TPA_FUHRE = cErgebnis[0][0];
		} else {
			cErgebnis = bibDB.EinzelAbfrageInArray(oSQLCreater.get_cSQL_SLOW());
			if (cErgebnis != null && cErgebnis.length>0) { 
				cID_VPOS_TPA_FUHRE = cErgebnis[0][0];
			}
		}
		
		tr_MASK.get_oGridAddOnContent().removeAll();
		if (S.isFull(cID_VPOS_TPA_FUHRE)) {
			//blendet die fuhrenmaske in eine spalte neben der eigentlicht maske
			tr_MASK.activate_AddonContent();
			tr_MASK.get_oGridAddOnContent().add(this.create_and_fill_FuMaskContainer(cID_VPOS_TPA_FUHRE));
		}
		
		
		
		return new MyE2_MessageVector();
	}


	@Override
	public E2_BasicModuleContainer_MASK get_BasicModuleContainer_MASK() throws myException {
		//zuerst die ID beschaffen

		this.set_cID_BASICTABLE_UF(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		this.set_cSTATUS_MASKE(E2_ComponentMAP.STATUS_EDIT);
		
		E2_NavigationList naviList = this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		
		if (this.oMaskContainer == null) {
			this.oMaskContainer = new TR__MASK_BasicModuleContainer_4_direktClick(naviList);
		}
		
		return this.oMaskContainer;
	}


	@Override
	public MyE2_MessageVector do_AfterPopupMask(E2_BasicModuleContainer_MASK oMaskContainer, String cID_MainTable_UF, String cEDITSTATUS) throws myException {
		return new MyE2_MessageVector();
	}


	@Override
	public E2_ButtonAUTHValidator getValdiatorEdit() {
		return null;
	}


	@Override
	public E2_ButtonAUTHValidator getValdiatorView() {
		return null;
	}
	
	
	private FU_MASK_ModulContainer create_and_fill_FuMaskContainer(String cID_VPOS_TPA_FUHRE) throws myException {
		
		FU_MASK_ModulContainer  fuMask = new FU_MASK_ModulContainer(null, null, false, false);
		fuMask.set_bVisible_Row_For_Messages(false);

		fuMask.get_oRowForButtons().removeAll();
		fuMask.get_oRowForButtons().setVisible(false);
		
		E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = fuMask.get_vCombinedComponentMAPs();
		vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,cID_VPOS_TPA_FUHRE);
		
		return fuMask;
	}
	
	
}
