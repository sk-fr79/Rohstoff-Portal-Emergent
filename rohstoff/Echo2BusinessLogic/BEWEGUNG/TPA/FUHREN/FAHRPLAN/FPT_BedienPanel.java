package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_NavigationList_to_Archiv;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_DELETE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_EDIT_MASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_NEW_MASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_VIEW_MASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__ALL.CopyTyp;

public class FPT_BedienPanel extends MyE2_Column
{
	private FU_LIST_ModulContainer FU_LISTContainer =null;
	
	// Komponenten bereitstellen
	private E2_DateBrowser 		oDateBrowser = 	new E2_DateBrowser();
	private MyE2_SelectField  	oSelectLKW = 	null; 
	private MyE2_CheckBox  		oCBAutoMatischOeffnen = new MyE2_CheckBox(new MyE2_String("Automatik"));
	
	private FPT_SELECTFIELD_SET_ANHAENGER 		oSetAnhaenger = null;
	private FPT_SELECTFIELD_SET_FAHRER 			oSetFahrer = 	null;
	
	private FPT_BUTTON_OPENFAHRPLAN 			oButOpen = null;

	public FPT_BedienPanel(FU_LIST_ModulContainer modulContainerLIST) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		this.FU_LISTContainer = modulContainerLIST;
		
		E2_NavigationList   				oNaviList = 		modulContainerLIST.get_oNavList();
		FU_MASK_ModulContainer				oMaskFuhre = 		modulContainerLIST.get_oMaskFuhre();
	
		String cQuery = "SELECT   NVL(KFZKENNZEICHEN,'--'),ID_MASCHINEN FROM "+
										bibE2.cTO()+".JT_MASCHINEN,"+bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
										" JT_MASCHINEN.ID_MASCHINENTYP=JT_MASCHINENTYP.ID_MASCHINENTYP AND " +
										" JT_MASCHINENTYP.IST_LKW='Y' AND NVL(JT_MASCHINEN.AKTIV,'N')='Y' ORDER BY NVL(KFZKENNZEICHEN,'--')";		
		this.oSelectLKW = new MyE2_SelectField(cQuery,false,false,false,false);

		//actionagent, der die liste bei neuem datum schliesst
		oSelectLKW.add_oActionAgent(new actionChangeFahrplanRahmenbedingungen());
		oDateBrowser.get_oButtonLeft().add_oActionAgent(new actionChangeFahrplanRahmenbedingungen());
		oDateBrowser.get_oButtonRight().add_oActionAgent(new actionChangeFahrplanRahmenbedingungen());
		
		this.oSetAnhaenger = new FPT_SELECTFIELD_SET_ANHAENGER(modulContainerLIST);
		this.oSetFahrer = 	new FPT_SELECTFIELD_SET_FAHRER(modulContainerLIST);
		
		this.oButOpen = new FPT_BUTTON_OPENFAHRPLAN(modulContainerLIST,oSelectLKW,oDateBrowser,this.oSetAnhaenger,this.oSetFahrer);
		
		FPT_BUTTON_REFRESH					oButRefresh = new FPT_BUTTON_REFRESH(modulContainerLIST);
		FPT_BUTTON_MOVE_BACK_TO_POOL		oButBackToPool = new FPT_BUTTON_MOVE_BACK_TO_POOL(modulContainerLIST);
		FPT_BUTTON_PRINT_MAIL_BELEG	   		oButDruckeAlleBelege = new FPT_BUTTON_PRINT_MAIL_BELEG(modulContainerLIST.get_oNavList());

		
		FPT_BUTTON_PRINT_FAHRPLAN			oButPrint = 				new FPT_BUTTON_PRINT_FAHRPLAN(modulContainerLIST,oSelectLKW,oDateBrowser);
		FPT_BUTTON_PRINT_FAHRPLANUEBERSICHT	oButPrintUebersicht = 		new FPT_BUTTON_PRINT_FAHRPLANUEBERSICHT(oDateBrowser);
		
		

		this.add(new E2_ComponentGroupHorizontal(0,
				new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),
				new FU_LIST_BT_FUHRE_VIEW_MASK(oNaviList,oMaskFuhre),
				new FU_LIST_BT_FUHRE_NEW_MASK(oNaviList,oMaskFuhre,oSelectLKW,oDateBrowser),
				new FU_LIST_BT_FUHRE_EDIT_MASK(oNaviList,oMaskFuhre),
				new FU_LIST_BT_FUHRE_DELETE(oNaviList),
				new E2_ButtonUpDown_NavigationList_to_Archiv(oNaviList,E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER),  //2015-05-28: up-downliad-button
				oDateBrowser,
				oSelectLKW,
				this.oCBAutoMatischOeffnen,
				oButOpen,
				new FPT_BUTTON_OpenCalendar(oDateBrowser,oSelectLKW,oButOpen),
				oButBackToPool,
				oButRefresh,
				oButPrint,
				oButPrintUebersicht,
				oButDruckeAlleBelege,	
				new FPT_btBerechneEntfernung(oNaviList),
				E2_INSETS.I_0_0_5_0), E2_INSETS.I_0_0_0_10);
		
		
		this.add(new E2_ComponentGroupHorizontal(0,
				new MyE2_Label(new MyE2_String("Multiplikator: ")),
				new FP_DROPDOWN_COPYS(oNaviList, CopyTyp.FAHRPLAN_KOPIE),
				new FP_BUTTON_UMBUCHEN(oNaviList),
				new MyE2_Label(new MyE2_String("         ")),
				new MyE2_Label(new MyE2_String("Vorbesetzen:")),
				oSetFahrer,
				oSetAnhaenger,
				new FPT_BUTTON_OpenSortPanel(this.FU_LISTContainer, oSelectLKW,oDateBrowser),
				E2_INSETS.I_0_0_5_0), E2_INSETS.I_0_0_0_10);
		
	}

	
	private class actionChangeFahrplanRahmenbedingungen extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FPT_BedienPanel oThis = FPT_BedienPanel.this;
			
			SQLFieldMAP oMap = oThis.FU_LISTContainer.get_oNavList().get_oComponentMAP__REF().get_oSQLFieldMAP();
			FP__ALL.set_BedingungenForFahrplanSQLFieldMAP_CLOSED(oMap);
			
			if (oThis.oCBAutoMatischOeffnen.isSelected())
			{
				// dann die liste sofort neu oeffnen
				oThis.oButOpen.doActionPassiv();				
			}
			else
			{
				//liste schliessen
				oThis.FU_LISTContainer.get_oNavList().get_vectorSegmentation().removeAllElements();
				oThis.FU_LISTContainer.get_oNavList().get_vActualID_Segment().removeAllElements();
				oThis.FU_LISTContainer.get_oNavList()._REBUILD_ACTUAL_SITE("");
			}
		}
	}
	
	
}
