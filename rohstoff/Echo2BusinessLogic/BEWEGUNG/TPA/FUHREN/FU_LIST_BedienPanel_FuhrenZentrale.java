package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG.FUB___LIST_BT_Buchung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KORR.FUK_Bt_StartKorr;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG.FU_PRO_StarteDruckDialog;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_BUTTON_New_fast_and_furious;
import rohstoff.utils.ECHO2.GROUP_COLLECTOR.POPUP_GROUP_COLLECTORS;

/**
 * @author martin
 * 
 */
public class FU_LIST_BedienPanel_FuhrenZentrale extends MyE2_Row
{
	private FU_LIST_ModulContainer 			oModulContainerLIST = 	null;

	private FU_LIST_DataSearch  			oSearchObject = null;
	
	
//	//2014-06-02: neue checkbox, laesst die anzeige der lieferbedingungen zu
//	private MyE2_CheckBox  					oCB_ZeigeLieferbedingungen = new MyE2_CheckBox("Lieferbed.", MyE2_CheckBox.STYLE_SMALL());
	
	/**
	 * @param modulContainerLIST
	 * @throws myException
	 * Bitte beachten !!!
	 */
	public FU_LIST_BedienPanel_FuhrenZentrale(FU_LIST_ModulContainer modulContainerLIST) throws myException
	{
		super();
		this.oModulContainerLIST = modulContainerLIST;
		E2_NavigationList   			oNavList = this.oModulContainerLIST.get_oNavList();
		FU_MASK_ModulContainer			oMaskFuhre = this.oModulContainerLIST.get_oMaskFuhre();
		
//		this.oCB_ZeigeLieferbedingungen.add_oActionAgent(new ownActionAgent());
//		this.oCB_ZeigeLieferbedingungen.setToolTipText(new MyE2_String("Anzeige der Lieferbedingungs-Ermittlung in der Liste ein-/ausschalten ").CTrans());
		
		/*
		 * buttons zur steuerung
		 */
		MyE2_PopUpMenue oPopUp =new MyE2_PopUpMenue(null,null, false);
		
		oPopUp.addButton(new FUB___LIST_BT_Buchung(oNavList,false),true);
		oPopUp.addButton(new FU__LIST_BUTTON_ENTSPERRE_VERBUCHTE_FUHRE(oNavList),true);
		oPopUp.addButton(new FU_LIST_BT_REBUILD_FUHRENSTATUS(oNavList),true);
		
		//2014-07-03: neuer button fuer die aenderung der lieferbedingungen
		oPopUp.addButton(new FUK_Bt_StartKorr(oNavList),true);
		
		//2019-07-09: schleifenfunktion fuer die entfernungsberechnung der fuhren
		oPopUp.addButton(new FU__ListBtBerechneCo2ProfilFuhren(oNavList), true);
		
		
		
		this.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNavList),E2_INSETS.I_2_2_2_2);
		
		this.add(new E2_ComponentGroupHorizontal(0,			new FU_LIST_BT_STORNO_Fuhre(oNavList),
															//new FU_LIST_BT_FUHRE_EXCEL(oNavList),
															new FU_LIST_BT_FUHRE_popup_export(oNavList),
															new FU_LIST_BT_INPUT_INVOICE(oNavList),
															new FU_LIST_BT_PRINT_MAIL_BELEG(oNavList),
															new FU_PRO_StarteDruckDialog(oNavList),
															new FU_LIST_BT_FUHRE_VIEW_MASK(oNavList,oMaskFuhre),
															new FU_LIST_BT_FUHRE_NEW_MASK(oNavList,oMaskFuhre,null,null),
															new FUS_BUTTON_New_fast_and_furious(oNavList,false),
															new FU_LIST_BT_FUHRE_COPY_FUHRE(oNavList, oMaskFuhre),
															new FU_LIST_BT_FUHRE_EDIT_MASK(oNavList, oMaskFuhre),
															new FU_LIST_BT_FUHRE_DELETE(oNavList),
															new E2_ButtonUpDown_NavigationList_to_Archiv(oNavList,E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER),
															new POPUP_GROUP_COLLECTORS(this.oModulContainerLIST.get_MODUL_IDENTIFIER(),oNavList),
															E2_INSETS.I_2_2_2_2),E2_INSETS.I_2_2_2_2);
		

		this.add(oPopUp);
		
		this.add(new FU_LIST_BT_POPUP_DRUCK_MAIL(oNavList),E2_INSETS.I_2_2_2_2);

		this.add(new REP__POPUP_Button(this.oModulContainerLIST.get_MODUL_IDENTIFIER(),oNavList),E2_INSETS.I_2_2_2_2);
		
//		//2014-06-02: neue checkbox fuer die lieferbedingung
//		this.add(this.oCB_ZeigeLieferbedingungen, E2_INSETS.I(2,0,2,0));

		this.add(this.oSearchObject=new FU_LIST_DataSearch(this.oModulContainerLIST, this.oModulContainerLIST.get_MODUL_IDENTIFIER()),E2_INSETS.I_2_2_2_2);
		
	}

	
	public FU_LIST_DataSearch get_oSearchObject() {
		return oSearchObject;
	}


//	public MyE2_CheckBox get_oCB_ZeigeLieferbedingungen() {
//		return this.oCB_ZeigeLieferbedingungen;
//	}
//	
//	private class ownActionAgent extends XX_ActionAgent {
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			FU_LIST_BedienPanel_FuhrenZentrale.this.oModulContainerLIST.get_oNavList()._REBUILD_ACTUAL_SITE(null);
//		}
//	}
	
	
}
