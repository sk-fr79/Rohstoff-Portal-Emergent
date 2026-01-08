package panter.gmbh.basics4project.SANKTION;

import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceHandleSanktionsChecks;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class SANKTION_Adresse_scan_popUp extends E2_BasicModuleContainer {

	private String  			TITEL4Popup = null;

	private MyE2_CheckBox		oCB_ALL_IDsInList = 				new MyE2_CheckBox();
	private MyE2_CheckBox		oCB_ALL_IDsSelected = 				new MyE2_CheckBox();

	private E2_Button        	bt_OK = 	new E2_Button()._standard_text_button();
	private E2_Button			bt_Cancel = new E2_Button()._standard_text_button();

	private VEK<String>  		addressIds = new VEK<String>();
	private E2_Grid  			anzeigeGrid = new E2_Grid();
	private  E2_NavigationList 	naviList;

	public SANKTION_Adresse_scan_popUp(E2_NavigationList oNavilist, String cTITEL4Popup) throws myException {
		super();
		this.bt_OK._t("Starten")._ttt("Starten");
		this.bt_OK._aaa(new ownActionAgentOK());

		this.bt_Cancel._t("Abbrechen")._ttt("Abbrechen ohne etwas zu verändern");
		this.bt_Cancel._aaa(new ownActionAgentCancel());

		this.naviList = oNavilist;
		this.TITEL4Popup = cTITEL4Popup;

		this._setLeftPos(500)._setTopPos(500);
		generatePopUpContainer();

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(300), S.ms("Bitte wählen Sie ..."));
	}

	public void generatePopUpContainer() throws myException {


		// bereichsauswahl definieren
		ActionAgent_RadioFunction_CheckBoxList oRadio1 = new ActionAgent_RadioFunction_CheckBoxList(false);
		oRadio1.add_CheckBox(oCB_ALL_IDsInList);
		oRadio1.add_CheckBox(oCB_ALL_IDsSelected);

		if (naviList.get_vSelectedIDs_Unformated().size()>0) {
			oCB_ALL_IDsSelected.setSelected(true);
		} else {
			oCB_ALL_IDsInList.setSelected(true);
		}

		E2_Grid  oGridBasic = new E2_Grid()._bo_no()._s(4);

		oGridBasic
		._a(new RB_lab(TITEL4Popup)._set_style(MyE2_Label.STYLE_TITEL_BIG())			, new RB_gld()._span(4)._ins(2,5,2,10))
		._a(new RB_lab("Bitte wählen Sie den zu bearbeitenden Listenbereich aus")._b()	, new RB_gld()._span(4)._ins(2,5,2,10))

		._a(new RB_lab("Alle Datensätze in der momentanen Liste")._fo_plain()			, new RB_gld()._span(1)._ins(2,5,2,10))
		._a(new RB_lab(""+naviList.get_vectorSegmentation().size())._fo_plain()			, new RB_gld()._span(1)._ins(2,5,2,10))
		._a(oCB_ALL_IDsInList															, new RB_gld()._span(1)._ins(2,5,2,10))
		._a(""																			, new RB_gld()._span(1)._ins(2,5,2,10))

		._a(new RB_lab("Alle ausgewählten Datensätze")._fo_plain()						, new RB_gld()._span(1)._ins(2,5,2,10))
		._a(new RB_lab(""+naviList.get_vSelectedIDs_Unformated().size())._fo_plain()	, new RB_gld()._span(1)._ins(2,5,2,10))
		._a(oCB_ALL_IDsSelected															, new RB_gld()._span(1)._ins(2,5,2,10))
		._a(""																			, new RB_gld()._span(1)._ins(2,5,2,10));

		oGridBasic._a(new E2_ComponentGroupHorizontal(0, bt_OK, bt_Cancel, E2_INSETS.I(2,5,10,10)));

		this.add(oGridBasic, E2_INSETS.I(2,2,2,2));

	}

	private class ownActionAgentOK extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			VEK<String>  vIDs = new VEK<String>();

			SANKTION_Adresse_scan_popUp oThis = SANKTION_Adresse_scan_popUp.this;

			if (oThis.oCB_ALL_IDsSelected.isSelected()) {
				vIDs.addAll(oThis.naviList.get_vSelectedIDs_Unformated());
			}  else if (oThis.oCB_ALL_IDsInList.isSelected()) {
				vIDs.addAll(oThis.naviList.get_vectorSegmentation());
			}

			do_when_ok_is_clicked(vIDs);

			if (bibMSG.get_bIsOK()) {
				oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
	}


	private class ownActionAgentCancel extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SANKTION_Adresse_scan_popUp.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}


	private void do_when_ok_is_clicked(VEK<String> ids) throws myException{
		addressIds.removeAllElements();
		addressIds.addAll(ids);

		anzeigeGrid._clear();


		if (addressIds.size()<=30) {
			
			PdServiceHandleSanktionsChecks checkservice = new PdServiceHandleSanktionsChecks()._send_meldung(false);
			
			this.update(checkservice, addressIds, null);

//			SANKTION_ErgebnisseVektor vErg = new SANKTION_ErgebnisseVektor();
			
//			vErg._a(checkservice.get_check_result().values());
			
			new SANKTION_ResultScreen(true, checkservice.render_ergebnis());
			
		} else {

			new E2_ServerPushMessageContainer_STD(new Extent(640), new Extent(250), S.ms("Gefahr Prüfung"),false,true,2000,anzeigeGrid,E2_INSETS.I(10,10,10,10)) {

				@Override
				public void Run_Loop() throws myException {
					PdServiceHandleSanktionsChecks checkservice = new PdServiceHandleSanktionsChecks()._send_meldung(false);
					
					SANKTION_Adresse_scan_popUp.this.update(checkservice, addressIds, this);

					this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					
//					SANKTION_ErgebnisseVektor vErg = new SANKTION_ErgebnisseVektor();
//					
//					vErg._a(checkservice.get_check_result().values());
					
					new SANKTION_ResultScreen(true, checkservice.render_ergebnis());
				}
				
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
					this.get_oWindowPane().setWidth(new Extent(510));

					this.get_oWindowPane().setHeight(new Extent(560));
				}
			};
			
		}
		


	}


	private void update(
			PdServiceHandleSanktionsChecks checkservice, 
			VEK<String> viDs
			,E2_ServerPushMessageContainer_STD container) throws myException {

		long zaehler = 0;

		boolean first = true;

		for (String id:viDs) {

			zaehler++;

			MyLong l = new MyLong(id);

			if (l.isOK()) {
				checkservice.initWithAdresses(new VEK<String>()._a(id));
			}

			first = this.refreshPopUp(zaehler, viDs.size(),first);

			if (container!=null && E2_ServerPushMessageContainer_STD.get_LoopStopped()) {
				break;
			}
			
		}
		
	}


	private boolean refreshPopUp(long zaehler, int idAdressBaseSize, boolean first) {
		if (first || (zaehler)%10==0) {
			this.anzeigeGrid._clear();
			this.anzeigeGrid._setSize(50,20,50)._bo_dd();

			RB_gld gl = new RB_gld()._center_mid()._ins(5);
			RB_gld glt = new RB_gld()._center_mid()._ins(5)._col(new E2_ColorDark());

			this.anzeigeGrid._a("Firmen",glt)._a(new RB_lab()._tr("von"),glt)._a("Firmen/Ges.",glt);
			this.anzeigeGrid._a(new RB_lab()._t(""+zaehler)._b(),gl)._a(new RB_lab()._tr("von")._b(),gl)._a(new RB_lab()._t(""+idAdressBaseSize)._b(),gl);
		}

		return false;
	}
}
