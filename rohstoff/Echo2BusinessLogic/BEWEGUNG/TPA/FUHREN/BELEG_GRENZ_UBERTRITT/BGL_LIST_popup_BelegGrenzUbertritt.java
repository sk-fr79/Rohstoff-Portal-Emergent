package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import java.util.ArrayList;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_MailAdressSearcher;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST_ENUM.MAILDEF;
import panter.gmbh.basics4project.DB_ENUMS.PROFIL_GRENZUBERTRITT;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel_bez;

public class BGL_LIST_popup_BelegGrenzUbertritt extends E2_BasicModuleContainer {

	private E2_NavigationList naviList = null;

	private VEK<String> vIds_valid = new VEK<>();

	private ownComponentMap compMap = null;

	private MailGrid mail_grid;

	private RB_SelectField profile_selField;

	public BGL_LIST_popup_BelegGrenzUbertritt(E2_NavigationList o_naviList) throws myException {
		super();

		this.compMap = new ownComponentMap();

		this.mail_grid = new MailGrid()._set_list(fill_mail_adresse())._render();
		this.naviList = o_naviList;
		this.vIds_valid._a(this.naviList.get_vSelectedIDs_Unformated());

		E2_Grid param_4_report_grid = 	new E2_Grid()._setSize(330, 470);

		fill_grid(param_4_report_grid);

		E2_Grid command_grid = new E2_Grid()._setSize(150,150,150);

		E2_Button bt_druck = new E2_Button()._t("Druck")._standard_text_button()._fsa(2)._b()._al_center();
		bt_druck._addGlobalValidator(new check_if_ok_to_print_validator(processtype.PRINT));
		bt_druck._aaa(new BGL_LIST_jasperhash_BelegGrenzUbertritt(BGL_LIST_popup_BelegGrenzUbertritt.this, processtype.PRINT));
		bt_druck._aaa(new ownCloseAgent(this));

		E2_Button bt_mail = new E2_Button()._t("Email")._standard_text_button()._fsa(2)._b();
		bt_mail._addGlobalValidator(new check_if_ok_to_print_validator(processtype.MAIL));
		bt_mail._aaa(new BGL_LIST_jasperhash_BelegGrenzUbertritt(BGL_LIST_popup_BelegGrenzUbertritt.this, processtype.MAIL));
		bt_mail._aaa(new ownCloseAgent(this));

		E2_Button bt_cancel = new E2_Button()._t("Abbrechen")._standard_text_button()._al_center()._fsa(2);
		bt_cancel._aaa(()->close_action());

		String profil_abfrage = new SEL(PROFIL_GRENZUBERTRITT.name_profil).ADDFIELD(PROFIL_GRENZUBERTRITT.id_profil_grenzubertritt)
				.FROM(_TAB.profil_grenzubertritt).WHERE(new vgl(PROFIL_GRENZUBERTRITT.id_mandant, bibALL.get_ID_MANDANT()))
				.ORDERUP(PROFIL_GRENZUBERTRITT.std).s();


		profile_selField = new RB_SelectField(PROFIL_GRENZUBERTRITT.id_profil_grenzubertritt, profil_abfrage, false, false, new Extent(402));
		profile_selField._aaa(new ownProfileSelectionAgent());

		E2_Grid profil_grid = new E2_Grid()._setSize(330,405,20,20,20)
				._a("Profil", 								new RB_gld()._ins(1)._left_mid())
				._a(profile_selField, 						new RB_gld()._ins(1)._left_mid())
				._a(new BGL_PROFIL_MASK_bt_new(this), 		new RB_gld()._ins(1)._left_mid())
				._a(new BGL_PROFIL_MASK_bt_edit(this), 		new RB_gld()._ins(2)._left_mid())
				._a(new BGL_PROFIL_MASK_bt_delete(this), 	new RB_gld()._ins(2)._left_mid());

		command_grid
		._a(bt_druck	, new RB_gld()._ins(2)._left_mid())
		._a(bt_mail		, new RB_gld()._ins(2)._center_mid())
		._a(bt_cancel	, new RB_gld()._ins(2)._right_mid());

		this.add(mail_grid,				E2_INSETS.I(6,5,6,5));
		this.add(new Separator(),		E2_INSETS.I(5,5,5,5));
		this.add(profil_grid, 			E2_INSETS.I(5,15,5,20));
		this.add(param_4_report_grid,  	E2_INSETS.I(5));
		this.add(command_grid,  		E2_INSETS.I(5,15,5,5));

		fill_with_std_profile();

	}

	private void close_action() throws myException {
		this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
	}

	private void fill_grid(E2_Grid cGrid) throws myException{
		RB_gld gld = new RB_gld()._ins(1);

		String sorte = "";
		if(vIds_valid.size()>0) {
			Rec20 fuhre_rec = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id(vIds_valid.get(0));
			long artikel_id = 0;
			if(S.isFull(fuhre_rec.get_fs_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_ek))){
				artikel_id = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id(vIds_valid.get(0)).get_myLong_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_ek, new MyLong(0)).get_lValue();				
			}else if(S.isFull(fuhre_rec.get_fs_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_vk))){
				artikel_id = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id(vIds_valid.get(0)).get_myLong_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_vk, new MyLong(0)).get_lValue();
			}
			
			if(artikel_id != 0) {
				sorte = new Rec20_artikel_bez(new Rec20(_TAB.artikel_bez)._fill_id(artikel_id)).__get_ANR1_ANR2_ARTBEZ1();
			}
		}

		cGrid
		._a(BGL_ENUM_REPORT_PARAMETER.ID_ZAHLUNGSBEDINGUNGEN.user_text(), 							gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_ZAHLUNGSBEDINGUNGEN.name()), 				gld._left_mid())
		._a(new E2_Grid()._setSize(105,225,410,20)
				._a(BGL_ENUM_REPORT_PARAMETER.E_PREIS.user_text(), 									new RB_gld()._ins(0)._left_top())
				._a(new RB_lab(sorte)._i()._fsa(-2)._col_fore_dgrey(),								new RB_gld()._ins(0)._left_mid())
				._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.E_PREIS.name() ), 					new RB_gld()._ins(0)._left_mid())
				._a(new BGL_LIST_bt_epreis_durschnitt(BGL_LIST_popup_BelegGrenzUbertritt.this),		new RB_gld()._ins(0)._left_mid())
				, new RB_gld()._ins(1)._left_top()._span(2))

		._a(BGL_ENUM_REPORT_PARAMETER.ID_TAX_RECHNUNG.user_text(), 									gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_TAX_RECHNUNG.name()), 					gld._left_mid())
		._a(BGL_ENUM_REPORT_PARAMETER.ID_TAX_GUTSCHRIFT.user_text(), 								gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_TAX_GUTSCHRIFT.name()), 					gld._left_mid())
		._a(BGL_ENUM_REPORT_PARAMETER.NAME_ANSPRECH_INTERN.user_text(), 							gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.NAME_ANSPRECH_INTERN.name()), 				gld._left_mid())
		._a(BGL_ENUM_REPORT_PARAMETER.TEL_ANSPRECH_INTERN.user_text(), 								gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEL_ANSPRECH_INTERN.name()), 				gld._left_mid())
		._a(BGL_ENUM_REPORT_PARAMETER.FAX_ANSPRECH_INTERN.user_text(),	 							gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FAX_ANSPRECH_INTERN.name()), 				gld._left_mid())
		._a(BGL_ENUM_REPORT_PARAMETER.NAME_BEARBEITER_INTERN.user_text(), 							gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.NAME_BEARBEITER_INTERN.name()), 				gld._left_mid())
		._a(BGL_ENUM_REPORT_PARAMETER.TEL_BEARBEITER_INTERN.user_text(), 							gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEL_BEARBEITER_INTERN.name()), 				gld._left_mid())
		._a(BGL_ENUM_REPORT_PARAMETER.FAX_BEARBEITER_INTERN.user_text(), 							gld._left_mid())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FAX_BEARBEITER_INTERN.name()), 				gld._left_mid())
		._a(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ANFANG.user_text(), 								gld._left_top())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ANFANG.name()), 				gld._left_top())
		._a(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ENDE.user_text(), 								gld._left_top())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ENDE.name()), 					gld._left_top())
		._a(BGL_ENUM_REPORT_PARAMETER.TEXT_AUSLANDSVERTRETUNG.user_text(), 							gld._left_top())
		._a(compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEXT_AUSLANDSVERTRETUNG.name()),				gld._left_top())
		;


	}

	public E2_NavigationList get_naviList() {
		return naviList;
	}

	public ownComponentMap get_componentMap() {
		return compMap;
	}

	public ArrayList<String> get_selected_mail() throws myException{
		ArrayList<String> mailList = new ArrayList<String>();
		mailList.addAll(this.mail_grid.get_selected_items());
		return mailList;
	}

	public String get_selected_profile() throws myException{
		return this.profile_selField.get_ActualWert();
	}

	public void reload_profile_list() throws myException{
		String profil_abfrage = new SEL(PROFIL_GRENZUBERTRITT.name_profil).ADDFIELD(PROFIL_GRENZUBERTRITT.id_profil_grenzubertritt)
				.FROM(_TAB.profil_grenzubertritt).WHERE(new vgl(PROFIL_GRENZUBERTRITT.id_mandant, bibALL.get_ID_MANDANT()))
				.ORDERUP(PROFIL_GRENZUBERTRITT.std).s();

		this.profile_selField.set_ListenInhalt(profil_abfrage, false, true, true, false);

	}

	public VEK<String> get_selected_ids() throws myException{
		return this.vIds_valid;
	}

	/**
	 * 
	 * @return vektor mit mail adresses
	 * @throws myException
	 */
	private VEK<String> fill_mail_adresse() throws myException{
		VectorSingle temps_mail_adresses = new VectorSingle();

		SE_MailAdressSearcher tpaMailSearcher 	= new SE_MailAdressSearcher(new RECORD_ADRESSE(bibALL.get_ID_ADRESS_MANDANT()), MAILDEF.EMAIL_TRANSPORT, true, true, false);
		temps_mail_adresses.addAll(tpaMailSearcher.get_v_MailAdressesFound());

		SE_MailAdressSearcher lagerMailSearcher = new SE_MailAdressSearcher(new RECORD_ADRESSE(bibALL.get_ID_ADRESS_MANDANT()), MAILDEF.EMAIL_LAGER, true, true, false);
		temps_mail_adresses.addAll(lagerMailSearcher.get_v_MailAdressesFound());

		SE_MailAdressSearcher liefMailSearcher 	= new SE_MailAdressSearcher(new RECORD_ADRESSE(bibALL.get_ID_ADRESS_MANDANT()), MAILDEF.EMAIL_LIEFERSCHEIN, true, true, false);
		temps_mail_adresses.addAll(liefMailSearcher.get_v_MailAdressesFound());

		return new VEK<String>()._a(temps_mail_adresses);
	}

	private void fill_with_std_profile() throws myException{

		RecList21 std_records = new RecList21(_TAB.profil_grenzubertritt)._fill(
				new SEL().FROM(_TAB.profil_grenzubertritt)
				.WHERE(new vgl(PROFIL_GRENZUBERTRITT.id_mandant, bibALL.get_ID_MANDANT()))
				.AND(new vgl_YN(PROFIL_GRENZUBERTRITT.std,true)).s());
		if(std_records != null && std_records.size()>0) {
			fill_with_profile(std_records.get(0).get_key_value());

		}else {

		}
	}

	public void fill_with_profile(String id) throws myException {
		if(S.isFull(id)) {
			Rec21 rec = new Rec21(_TAB.profil_grenzubertritt)._fill_id(bibALL.convertID2UnformattedID(id));

			if(rec !=  null) {
				profile_selField.set_ActiveValue(rec.get_fs_dbVal(PROFIL_GRENZUBERTRITT.id_profil_grenzubertritt, ""));

				((RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_ZAHLUNGSBEDINGUNGEN.name())).set_ActiveValue(rec.get_fs_dbVal(PROFIL_GRENZUBERTRITT.id_zahlungsbedingung, ""));
				((RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_TAX_GUTSCHRIFT.name())).set_ActiveValue(rec.get_fs_dbVal(PROFIL_GRENZUBERTRITT.id_tax_gutschrift, ""));
				((RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_TAX_RECHNUNG.name())).set_ActiveValue(rec.get_fs_dbVal(PROFIL_GRENZUBERTRITT.id_tax_rechnung, ""));

				((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.NAME_ANSPRECH_INTERN.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.name_ansprechpartner, ""));
				((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEL_ANSPRECH_INTERN.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.tel_ansprechpartner, ""));
				((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FAX_ANSPRECH_INTERN.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.fax_ansprechpartner, ""));

				((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.NAME_BEARBEITER_INTERN.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.name_bearbeiter, ""));
				((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEL_BEARBEITER_INTERN.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.tel_bearbeiter, ""));
				((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FAX_BEARBEITER_INTERN.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.fax_bearbeiter, ""));

				((RB_TextArea)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ANFANG.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.formulartext_anfang, ""));
				((RB_TextArea)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ENDE.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.formulartext_ende, ""));
				((RB_TextArea)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEXT_AUSLANDSVERTRETUNG.name())).setText(rec.get_ufs_dbVal(PROFIL_GRENZUBERTRITT.auslandsvertretung_text, ""));
			}
		}else {

			((RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_ZAHLUNGSBEDINGUNGEN.name())).set_ActiveValue("");
			((RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_TAX_GUTSCHRIFT.name())).set_ActiveValue("");
			((RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_TAX_RECHNUNG.name())).set_ActiveValue("");

			((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.NAME_ANSPRECH_INTERN.name())).setText("");
			((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEL_ANSPRECH_INTERN.name())).setText("");
			((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FAX_ANSPRECH_INTERN.name())).setText("");

			((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.NAME_BEARBEITER_INTERN.name())).setText("");
			((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEL_BEARBEITER_INTERN.name())).setText("");
			((RB_TextField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FAX_BEARBEITER_INTERN.name())).setText("");

			((RB_TextArea)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ANFANG.name())).setText("");
			((RB_TextArea)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ENDE.name())).setText("");
			((RB_TextArea)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEXT_AUSLANDSVERTRETUNG.name())).setText("");

		}
	}


	private class ownProfileSelectionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String selectedId = ((RB_SelectField)oExecInfo.get_MyActionEvent().getSource()).get_ActualWert();
			fill_with_profile(selectedId);
		}
	}


	private class MailGrid extends E2_Grid {

		private VEK<String> vMail = new VEK<>();
		private VEK<RB_cb> vComp = new VEK<>();
		private E2_Grid grid = new E2_Grid()._s(1);

		public MailGrid() {
			super();
			this._bo_ddd()._setSize(730)._a(new RB_lab("Email")._i()._b(), new RB_gld()._ins(1)._col_back_dd());
			MyE2_ContainerEx contex = new MyE2_ContainerEx(this.grid);
			contex.setHeight(new Extent(60));
			this._a(contex);

		}

		public MailGrid _set_list(VEK<String> vList) throws myException{
			vMail.addAll(vList);
			return this;
		}

		public MailGrid _render() throws myException{
			this.vComp.clear();
			for(String mailAdress:vMail) {
				RB_cb cb = new RB_cb()._t(mailAdress);

				this.vComp._a(cb);
				this.grid._a(cb, new RB_gld()._ins(0,1,0,1));
			}
			return this;
		}

		public VEK<String> get_selected_items() throws myException{
			VEK<String> selected_item = new VEK<>();
			for(RB_cb cmp: this.vComp) {
				if(cmp.isSelected()){
					selected_item._a(((RB_cb) cmp).getText());
				}
			}
			return selected_item;
		}
	}

	private class ownComponentMap extends RB_ComponentMap{
		public ownComponentMap() throws myException {
			super();

			SEL zahlungsbedingung_query = new SEL(ZAHLUNGSBEDINGUNGEN.bezeichnung).ADDFIELD(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen)
					.FROM(_TAB.zahlungsbedingungen).WHERE(new vgl(ZAHLUNGSBEDINGUNGEN.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP("1");	

			SEL  rech_gut_tax_query = new SEL(TAX.dropdown_text).ADDFIELD(TAX.id_tax).FROM(_TAB.tax)
					.WHERE(new vgl_YN(TAX.aktiv, true)).AND(new vgl(TAX.id_mandant, bibALL.get_ID_MANDANT())).ORDERUP("1");	

			RB_SelectField zahlField = 	new RB_SelectField(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, zahlungsbedingung_query.s(), false, false ,new Extent(402));
			zahlField.mark_MustField();

			RB_SelectField rech_tax = 	new RB_SelectField(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, rech_gut_tax_query.s(), false, false ,new Extent(402));
			rech_tax.mark_MustField();

			RB_SelectField gut_tax = 	new RB_SelectField(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, rech_gut_tax_query.s(), false, false ,new Extent(402));
			gut_tax.mark_MustField();

			RB_TextField epreis_feld = new RB_TextField(400);
			epreis_feld.mark_MustField();

			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.ID_VPOS_TPA_FUHRE.key(),		new RB_TextField(400));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.ID_ZAHLUNGSBEDINGUNGEN.key(),	zahlField);
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.E_PREIS.key(),					epreis_feld);
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.ID_TAX_RECHNUNG.key(),			rech_tax);
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.ID_TAX_GUTSCHRIFT.key(),		gut_tax);
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.NAME_ANSPRECH_INTERN.key(),	new RB_TextField(400));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.TEL_ANSPRECH_INTERN.key(),		new RB_TextField(400));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.FAX_ANSPRECH_INTERN.key(),		new RB_TextField(400));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.NAME_BEARBEITER_INTERN.key(),	new RB_TextField(400));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.TEL_BEARBEITER_INTERN.key(),	new RB_TextField(400));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.FAX_BEARBEITER_INTERN.key(),	new RB_TextField(400));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ANFANG.key(),		new RB_TextArea(395,3));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ENDE.key(),		new RB_TextArea(395,3));
			this.registerComponent(BGL_ENUM_REPORT_PARAMETER.TEXT_AUSLANDSVERTRETUNG.key(), new RB_TextArea(395,3));
		}

		@Override
		public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
			return null;
		}

		@Override
		public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
			return null;
		}

		@Override
		public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,
				MASK_STATUS status) throws myException {
			return new MyE2_MessageVector();
		}

		@Override
		public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
		}
	}

	private class check_if_ok_to_print_validator extends XX_ActionValidator_NG{
		private processtype ptyp ;

		public check_if_ok_to_print_validator(processtype eTyp){
			this.ptyp = eTyp;
		}

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();

			RB_TextField field1 = (RB_TextField) compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.E_PREIS.name());
			RB_SelectField field2 = (RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_ZAHLUNGSBEDINGUNGEN.name());
			RB_SelectField field3 = (RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_TAX_GUTSCHRIFT.name());
			RB_SelectField field4 = (RB_SelectField)compMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.ID_TAX_RECHNUNG.name());

			if(S.isEmpty(field1.get__actual_maskstring_in_view())){
				mv._addAlarm("Bitte, füllen Sie das Feld:  <02-Einzelpreis> !");
			}
			if(S.isEmpty(field2.get__actual_maskstring_in_view())){
				mv._addAlarm("Bitte, wählen Sie eine Zahlungsbedingung !");
			}
			if(S.isEmpty(field3.get__actual_maskstring_in_view())){
				mv._addAlarm("Bitte, wählen Sie eine Steuer-Definition für den Rechnungsbeleg !");
			}
			if(S.isEmpty(field4.get__actual_maskstring_in_view())){
				mv._addAlarm("Bitte, wählen Sie eine Steuer-Definition für den Gutschriftsbeleg !");
			}


			if(ptyp == processtype.MAIL){
				if(mail_grid.get_selected_items().size()==0) {
					mv._addAlarm("Bitte wählen Sie eine eMail !");
				}else if(mail_grid.get_selected_items().size()>5) {
					mv._addAlarm("Sie können nur bis 5 eMails wählen.");
				}
			}
			return mv;
		}

	}


	private class ownCloseAgent extends XX_ActionAgentWhenCloseWindow{

		public ownCloseAgent(E2_BasicModuleContainer container) {
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			naviList.Mark_ID_IF_IN_Page(vIds_valid);
			BGL_LIST_popup_BelegGrenzUbertritt.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);

		}

	}
}
