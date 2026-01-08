package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL_ListBedienPanel extends MyE2_Grid 
{

	private BSAAL__ModulContainerLIST	oModulContainerList = null;

	private BSAAL_ButtonEditList 			ButtonEditList = null;
	private BSAAL_ButtonDelete 				ButtonDelete = null;
	private BSAAL_ButtonCancelEdit 			ButtonCancelEdit = null;
	private BSAAL_ButtonSaveEdit 			ButtonSaveEdit = null;
	private BSAAL_ButtonCalcVormonat_ 		ButtonCalcVormonat = null;
	private BSAAL_ButtonBaueAngebote_ 		ButtonBaueAngebote = null;
	private BSAAL_ButtonReloadList			ButtonReloadListe = null;
	private BSAAL_ButtonCopy				ButtonCopyAngebot = null;
	private BSAAL_ButtonBauePDFs_			ButtonSendAngebote = null;
	private BSAAL_ButtonUnlock				ButtonUnlock = null;
	private BSAAL_ButtonChangeGueltigkeit	ButtonChangeGueltig = null;
	
	public BSAAL_ListBedienPanel(BSAAL__ModulContainerLIST modulContainerList) throws myException
	{
		super(14,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oModulContainerList = modulContainerList;
		
		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.oModulContainerList.get_oNaviList()),1,E2_INSETS.I_10_2_10_2);
		
		this.ButtonEditList = 		new BSAAL_ButtonEditList(this.oModulContainerList);
		this.ButtonDelete = 		new BSAAL_ButtonDelete(this.oModulContainerList);
		this.ButtonCancelEdit = 	new BSAAL_ButtonCancelEdit(this.oModulContainerList);
		this.ButtonSaveEdit = 		new BSAAL_ButtonSaveEdit(this.oModulContainerList);
		this.ButtonReloadListe	=	new BSAAL_ButtonReloadList(this.oModulContainerList);
		this.ButtonCopyAngebot	= 	new BSAAL_ButtonCopy(this.oModulContainerList);
		this.ButtonCalcVormonat = 	new BSAAL_ButtonCalcVormonat_(this.oModulContainerList);
		this.ButtonBaueAngebote = 	new BSAAL_ButtonBaueAngebote_(this.oModulContainerList);
		this.ButtonSendAngebote = 	new BSAAL_ButtonBauePDFs_(this.oModulContainerList);
		this.ButtonUnlock = 		new BSAAL_ButtonUnlock(this.oModulContainerList);
		this.ButtonChangeGueltig =  new BSAAL_ButtonChangeGueltigkeit(this.oModulContainerList);
		
		
		this.add(this.ButtonEditList,1,E2_INSETS.I_2_2_10_2);
		this.add(this.ButtonSaveEdit,1,E2_INSETS.I_2_2_10_2);
		this.add(this.ButtonCancelEdit,1,E2_INSETS.I_2_2_10_2);
		this.add(this.ButtonDelete,1,E2_INSETS.I_2_2_10_2);
		this.add(this.ButtonUnlock,1,E2_INSETS.I_2_2_10_2);
		this.add(this.ButtonCopyAngebot,1,E2_INSETS.I_2_2_10_2);
		this.add(this.ButtonChangeGueltig,1,E2_INSETS.I_2_2_10_2);
		this.add(this.ButtonReloadListe,1,E2_INSETS.I_10_2_10_2);
		this.add(this.ButtonCalcVormonat,1,E2_INSETS.I_10_2_10_2);
		this.add(this.ButtonBaueAngebote,1,E2_INSETS.I_10_2_10_2);
		this.add(this.ButtonSendAngebote,1,E2_INSETS.I_10_2_10_2);
		
		
		/*
		 * ein reportbutton einfuegen
		 */
		this.add(new REP__POPUP_Button(	this.oModulContainerList.get_MODUL_IDENTIFIER(),
										this.oModulContainerList.get_oNaviList()),1,E2_INSETS.I_10_2_10_2);
		
		
		
		
	}

	
	public void set_Status_ViewMode()
	{
		try
		{
			this.ButtonEditList.set_bEnabled_For_Edit(true);
			this.ButtonSaveEdit.set_bEnabled_For_Edit(false);
			this.ButtonCancelEdit.set_bEnabled_For_Edit(false);
			this.ButtonDelete.set_bEnabled_For_Edit(true);
			this.ButtonCalcVormonat.set_bEnabled_For_Edit(true);
			this.ButtonBaueAngebote.set_bEnabled_For_Edit(true);
			this.ButtonReloadListe.set_bEnabled_For_Edit(true);
			this.ButtonCopyAngebot.set_bEnabled_For_Edit(true);
			this.ButtonChangeGueltig.set_bEnabled_For_Edit(true);
			this.ButtonSendAngebote.set_bEnabled_For_Edit(true);
			this.ButtonUnlock.set_bEnabled_For_Edit(true);
			this.oModulContainerList.get_oNaviList().set_EnabledNavigationElements(true);
			
			E2_NavigationList oList = this.oModulContainerList.get_oNaviList();
			
			// die listen-knoepfe zur aenderung der zeiten deaktivieren
			for (int i=0;i<oList.get_vComponentMAPS().size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)oList.get_vComponentMAPS().get(i);
				((BSAAL_ButtonChangeGueltigkeit_IN_LIST)oMap.get("G_DATUMSBEREICH")).setEnabled(true);
			}
			
			
			
		}
		catch (myException ex) {}
	}
	

//	public void set_Status_EditMode()
//	{
//		try
//		{
//			this.ButtonEditList.set_bEnabled_For_Edit(false);
//			this.ButtonSaveEdit.set_bEnabled_For_Edit(true);
//			this.ButtonCancelEdit.set_bEnabled_For_Edit(true);
//			this.ButtonDelete.set_bEnabled_For_Edit(false);
//			this.ButtonCalcVormonat.set_bEnabled_For_Edit(false);
//			this.ButtonBaueAngebote.set_bEnabled_For_Edit(false);
//			this.ButtonReloadListe.set_bEnabled_For_Edit(false);
//			this.ButtonCopyAngebot.set_bEnabled_For_Edit(false);
//			this.ButtonChangeGueltig.set_bEnabled_For_Edit(false);
//			this.ButtonSendAngebote.set_bEnabled_For_Edit(false);
//			this.ButtonUnlock.set_bEnabled_For_Edit(false);
//			this.oModulContainerList.get_oNaviList().set_EnabledNavigationElements(false);
//
//			E2_NavigationList oList = this.oModulContainerList.get_oNaviList();
//			
//			// die listen-knoepfe zur aenderung der zeiten deaktivieren
//			for (int i=0;i<oList.get_vComponentMAPS().size();i++)
//			{
//				E2_ComponentMAP oMap = (E2_ComponentMAP)oList.get_vComponentMAPS().get(i);
//				((BSAAL_ButtonChangeGueltigkeit_IN_LIST)oMap.get("G_DATUMSBEREICH")).setEnabled(false);
//			}
//			
//			
//		}
//		catch (myException ex) {}
//	}
//	

	
	
}
