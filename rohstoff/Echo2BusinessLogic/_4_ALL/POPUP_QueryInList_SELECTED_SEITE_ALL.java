package rohstoff.Echo2BusinessLogic._4_ALL;



import java.util.Vector;

import nextapp.echo2.app.Extent;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

public abstract class POPUP_QueryInList_SELECTED_SEITE_ALL extends  E2_BasicModuleContainer {
	
	private E2_NavigationList  	NAVILIST = null;
	
	private MyE2_String  		TITEL4Popup = null;
	
	private MyE2_CheckBox		oCB_ALL_IDsInList = 				new MyE2_CheckBox();
	private MyE2_CheckBox		oCB_ALL_IDsInVisiblePage = 			new MyE2_CheckBox();
	private MyE2_CheckBox		oCB_ALL_IDsSelected = 				new MyE2_CheckBox();
	
	private MyE2_Button         bt_OK = new MyE2_Button(new MyE2_String("OK"),new MyE2_String("Starten"),new ownActionAgentOK());
	private MyE2_Button         bt_Cancel = new MyE2_Button(new MyE2_String("Abbruch"),new MyE2_String("Abbrechen ohne etwas zu verändern"),new ownActionAgentCancel());
	
	
	public POPUP_QueryInList_SELECTED_SEITE_ALL() {
		super();
	}
	
	public abstract void do_when_ok_is_clicked(Vector<String> vIDs) throws myException;
	
	
	public void ShowPopup(E2_NavigationList oNAVILIST, MyE2_String cTITEL4Popup) throws myException {
		this.NAVILIST = oNAVILIST;
		this.TITEL4Popup = cTITEL4Popup;
		
		// bereichsauswahl definieren
		ActionAgent_RadioFunction_CheckBoxList oRadio1 = new ActionAgent_RadioFunction_CheckBoxList(false);
		oRadio1.add_CheckBox(oCB_ALL_IDsInList);
		oRadio1.add_CheckBox(oCB_ALL_IDsInVisiblePage);
		oRadio1.add_CheckBox(oCB_ALL_IDsSelected);

		if (this.NAVILIST.get_vSelectedIDs_Unformated().size()>0) {
			this.oCB_ALL_IDsSelected.setSelected(true);
		} else {
			this.oCB_ALL_IDsInVisiblePage.setSelected(true);
		}
		
		MyE2_Grid  oGridBasic = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridBasic.add(new MyE2_Label(this.TITEL4Popup,MyE2_Label.STYLE_TITEL_BIG(),true),4,E2_INSETS.I(2,5,2,10));
		
		
		oGridBasic.add(new MyE2_Label(new MyE2_String("Bitte wählen Sie den zu bearbeitenden Listenbereich aus"),MyE2_Label.STYLE_NORMAL_BOLD()),4,E2_INSETS.I(2,5,2,10));
		
		oGridBasic.add(new MyE2_Label(new MyE2_String("Alle Datensätze in der momentanen Liste"),MyE2_Label.STYLE_NORMAL_PLAIN()),1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.NAVILIST.get_vectorSegmentation().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(oCB_ALL_IDsInList,1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(new MyE2_Label(" "),1,E2_INSETS.I(2,5,2,10));
		
		oGridBasic.add(new MyE2_Label(new MyE2_String("Alle Datensätze auf der momentanen Seite"),MyE2_Label.STYLE_NORMAL_PLAIN()),1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.NAVILIST.get_vActualID_Segment().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(oCB_ALL_IDsInVisiblePage,1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(new MyE2_Label(" "),1,E2_INSETS.I(2,5,2,10));

		oGridBasic.add(new MyE2_Label(new MyE2_String("Alle ausgewählten Datensätze"),MyE2_Label.STYLE_NORMAL_PLAIN()),1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.NAVILIST.get_vSelectedIDs_Unformated().size(),false),MyE2_Label.STYLE_NORMAL_PLAIN()),1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(oCB_ALL_IDsSelected,1,E2_INSETS.I(2,5,2,10));
		oGridBasic.add(new MyE2_Label(" "),1,E2_INSETS.I(2,5,2,10));

		oGridBasic.add(new E2_ComponentGroupHorizontal(0, this.bt_OK, this.bt_Cancel, E2_INSETS.I(2,5,10,10)));
		
		this.add(oGridBasic, E2_INSETS.I(2,2,2,2));
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(300), new MyE2_String("Bitte wählen Sie ..."));
		
	}

	private class ownActionAgentOK extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String>  vIDs = new Vector<String>();
			
			POPUP_QueryInList_SELECTED_SEITE_ALL oThis = POPUP_QueryInList_SELECTED_SEITE_ALL.this;
			
			if (oThis.oCB_ALL_IDsSelected.isSelected()) {
				vIDs.addAll(oThis.NAVILIST.get_vSelectedIDs_Unformated());
			} else if (oThis.oCB_ALL_IDsInVisiblePage.isSelected()) {
				vIDs.addAll(oThis.NAVILIST.get_vActualID_Segment());
			} else if (oThis.oCB_ALL_IDsInList.isSelected()) {
				vIDs.addAll(oThis.NAVILIST.get_vectorSegmentation());
			}
			
			oThis.do_when_ok_is_clicked(vIDs);
			
			if (bibMSG.get_bIsOK()) {
				oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			
			
		}
	}

	private class ownActionAgentCancel extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			POPUP_QueryInList_SELECTED_SEITE_ALL.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}
	
	
	
}
