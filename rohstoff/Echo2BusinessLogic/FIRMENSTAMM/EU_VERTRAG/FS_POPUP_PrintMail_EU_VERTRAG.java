package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import java.util.ArrayList;
import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_ListWithSelector_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.myCONST_ENUM.MAILDEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class FS_POPUP_PrintMail_EU_VERTRAG extends E2_BasicModuleContainer {

	public E2_NavigationList getNavilist() {
		return navilist;
	}

	private SE_ListWithSelector_Component 				faxContactComp;
	private SE_ListWithSelector_Component 				emailContactComp;
	private FS_COMP_Checkbox_List						lagerList;
	private FS_COMP_Veranlasser_Empfaenger_chkBox_grid	chooser;

	private FS_BT_EU_Vertrag_DruckMail_button  	bt_print;
	private FS_BT_EU_Vertrag_DruckMail_button 	bt_preview; 
	private FS_BT_EU_Vertrag_DruckMail_button  	bt_mail;

	private MyE2_TextField_DatePOPUP_OWN 	tf_vertragsDatum;
	private FS_COMP_User_Select_and_Textfeld cb_verantwortlichPerson;

	private E2_NavigationList navilist = null;
	private E2_Grid mainGrid;

	private String selected_id = "";

	private E2_Button bt_select_all_eu_adresses;


	public FS_POPUP_PrintMail_EU_VERTRAG(E2_NavigationList p_navilist) throws myException {
		super();

		this.navilist = p_navilist;

		this.selected_id = this.navilist.get_vSelectedIDs_Unformated_Select_the_one_and_only().get(0);

		this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());

		_init_components();

		buildContactPanel();
		addEmptyLine();
		buildPanel();
		addEmptyLine();
		buildLagerPanel();
		addEmptyLine();
		buildVertragsDatumPanel();

		this.add(mainGrid);

		this.add(buildButtonPanel(), E2_INSETS.I(5,25,5,0));


		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(900), new Extent(768), S.ms("Drucken/Mailen/Vorschau - EU Vertrag"));
	}

	private void _init_components() throws myException {
		myDateHelper heutigeDatum = new myDateHelper(bibALL.get_cDateNOW());

		this.mainGrid = new E2_Grid()._setSize(new Extent(50, Extent.PC),new Extent(50, Extent.PC));//GLOBAL_POPUP_WIDTH/2, GLOBAL_POPUP_WIDTH/2)._bo_b();

		this.bt_print = new FS_BT_EU_Vertrag_DruckMail_button(this, 	processtype.PRINT);
		this.bt_print._al_center();
		this.bt_print.setInsets(E2_INSETS.I(2,4,2,4));

		this.bt_preview = new FS_BT_EU_Vertrag_DruckMail_button(this, processtype.PREVIEW);
		this.bt_preview._al_center(); 
		this.bt_preview.setInsets(E2_INSETS.I(2,4,2,4));

		this.bt_mail = new FS_BT_EU_Vertrag_DruckMail_button(this, 	processtype.MAIL);
		this.bt_mail._al_center();
		this.bt_mail.set_bEnabled_For_Edit(false);
		this.bt_mail.setInsets(E2_INSETS.I(2,4,2,4));
		
		this.tf_vertragsDatum = new MyE2_TextField_DatePOPUP_OWN(heutigeDatum.get_cDateFormatForMask(),120);
		this.tf_vertragsDatum.set_oLastDateKlicked(heutigeDatum);

		this.cb_verantwortlichPerson = new FS_COMP_User_Select_and_Textfeld();

		this.faxContactComp = new SE_ListWithSelector_Component(null, bt_mail, null, processtype.PRINT);
		this.faxContactComp.preloadContactList(selected_id,null, true, false, false);
		this.faxContactComp.setWidth(new Extent(25, Extent.PC));

		this.emailContactComp = new SE_ListWithSelector_Component(null, bt_mail, null, processtype.MAIL);
		this.emailContactComp.setOnly_five_contact(false);
		this.emailContactComp.setFixedSize(490, 75);
		this.emailContactComp.preloadContactList(selected_id,MAILDEF.EMAIL_ALLTYPES, true, false, false);

		this.lagerList = new FS_COMP_Checkbox_List(this.getNavilist());

		this.bt_select_all_eu_adresses = new E2_Button()
				._t("Lager in EU-Ländern auswählen")
				._ttt(S.ms("Alle Läger in einem EU-Land vorselektieren"))
				._bord_black()._backDark()
				._aaa(new own_select_all_eu_land())
				._al_center()._insets(5)
				._width(268);
	}

	private E2_Grid buildContactPanel() throws myException{	
		mainGrid
		._a(new RB_lab()._t("Kontakt")		._fsa(2)._b()	, 	new RB_gld()._ins(10,5,5,2)._span(2)	._col_back_d())
		._a(new RB_lab()._t("Fax Nummer")	._bi() 			, 	new RB_gld()._ins(10,2,5,2)._span(1)	._col_back_d())
		._a(new RB_lab()._t("Email")		._bi() 			, 	new RB_gld()._ins(10,2,5,2)._span(1)	._col_back_d())
		._a(faxContactComp, 									new RB_gld()._ins(10,5,5,25))
		._a(emailContactComp, 									new RB_gld()._ins(10,5,5,25));

		return mainGrid;
	}


	private void buildPanel() throws myException{	
		this.chooser = new FS_COMP_Veranlasser_Empfaenger_chkBox_grid(this.navilist,this.mainGrid);
	}


	private E2_Grid buildLagerPanel() throws myException{
		mainGrid
		._a(new RB_lab()._t("Aktive Lageradressen bei dieser Firma")._fsa(2)._b(), 	new RB_gld()._ins(10,5,5,5)._span(2)._col_back_d())
		._a(bt_select_all_eu_adresses, 												new RB_gld()._ins(10,5,5,5)._span(2))
		._a(lagerList,																new RB_gld()._ins(0,5,5,25)._span(2));

		return mainGrid;
	}

	private E2_Grid buildButtonPanel() throws myException{
//		int[] i_button_breite = {GLOBAL_POPUP_WIDTH/3,GLOBAL_POPUP_WIDTH/3,GLOBAL_POPUP_WIDTH/3};
		E2_Grid buttonGrid = new E2_Grid()._setSize(new Extent(50, Extent.PC), new Extent(50, Extent.PC), new Extent(50, Extent.PC))
				._a(bt_print,	new RB_gld()._ins(5))
				._a(bt_mail, 	new RB_gld()._ins(5))
				._a(bt_preview, new RB_gld()._ins(5))
				;
		return buttonGrid;//._setSize(i_button_breite);
	}

	private E2_Grid buildVertragsDatumPanel() throws myException{
		mainGrid
		._a(new RB_lab()._t("Vertragsdatum")._fsa(2)._b(), 				new RB_gld()._ins(10,5,5,5)._col_back_d())
		._a(new RB_lab()._t("Verantwortliche Person")._fsa(2)._b(), 	new RB_gld()._ins(10,5,5,5)._col_back_d())
		._a(this.tf_vertragsDatum, 										new RB_gld()._ins(10,5,5,5)._col(new E2_ColorBase()))
		._a(this.cb_verantwortlichPerson, 								new RB_gld()._ins(10,5,5,5)._col(new E2_ColorBase()));
		return mainGrid;
	}

	private void addEmptyLine(){
		mainGrid._a("", new RB_gld()._span(2)._ins(10,5,5,5));
	}

	public ArrayList<String> getSelectedEmails(){
		return emailContactComp.getSelectedKontakt();
	}

	public VEK<String> getSelectedLager(){
		return lagerList.getSelectedIds();
	}

	public boolean isLagerListEmpty(){
		return (lagerList.getComponentMap().size()==0);
	}

	public boolean isSelectedLagerListEmpty(){
		return( (getSelectedLager().size()==0));
	}

	public String getSelectedFaxNummer(){
		return faxContactComp.getFaxNummer();
	}

	public HashMap<String, String> getEmpfaengerVeranlasserGrid(){
		return chooser.getSelection();
	}

	public String getVertragsDatum() throws myException{
		return bibALL.BaueDatumSQL(this.tf_vertragsDatum.get_oFormatedDateFromTextField(), 
				"DD.MM.YYYY");
	}

	public MyE2_TextField_DatePOPUP_OWN getVertragsDatumFeld() {
		return tf_vertragsDatum;
	}

	public String getVerantworlichPerson() throws myException{
		return cb_verantwortlichPerson.getVerantwortlichePerson();
	}

	public void getLagerListInputStatus(boolean status_ok) {
		lagerList.show_InputStatus(status_ok);
	}

	public void getVerantworlichePersonInuptStatus(boolean status_ok){
		cb_verantwortlichPerson.show_InputStatus(status_ok);
	}

	private class own_select_all_eu_land extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(! (lagerList == null)) {
				lagerList.auto_select_intrastat_land();
			}
		}
	}
}
