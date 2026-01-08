
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Adresse;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_USER;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.LH_CONST;

public class LH_P_MASK_ComponentMap extends RB_ComponentMap {
	//zentrale hashmap fuer transport von infos
	private static int lastCountToShow=50;

	private RB_TransportHashMap   m_tpHashMap = null;

	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}

	public LH_P_MASK_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();

		this.m_tpHashMap = p_tpHashMap;        

		RB_selField box_selectField = new RB_selField()._populate(
				new RecList21(_TAB.lager_box)._fill(
						new SEL().FROM(_TAB.lager_box)
						.ORDERDOWN("NVL("+LAGER_BOX.is_default_box.tnfn()+",'N')")
	            		.ORDERUP(LAGER_BOX.id_lager_box.tnfn()).s()),
				LAGER_BOX.boxnummer,
				LAGER_BOX.id_lager_box,
				false);
		box_selectField._setForceEmptyValInFront(false);
		box_selectField._width(200);

		RB_date_selektor datum_verarbeitet_field = new RB_date_selektor(100,true);
		datum_verarbeitet_field.get_oTextField().setAlignment(E2_ALIGN.LEFT_MID);

		RB_selField sf_palettiert_von 	= 	new RB_selField()._populate(get_lager_user_list(true))._populateShadow(get_lager_user_list(false))._width(200);
		RB_selField sf_endkontrolle_von = 	new RB_selField()._populate(get_lager_user_list(true))._populateShadow(get_lager_user_list(false))._width(200);
		RB_selField sf_gepresst_von 	= 	new RB_selField()._populate(get_lager_user_list(true))._populateShadow(get_lager_user_list(false))._width(200);

		this.registerComponent(LAGER_PALETTE.chargennummer,    			new RB_TextField()._width(100));
		this.registerComponent(LAGER_PALETTE.datum_verarbeitet,    		datum_verarbeitet_field);
		this.registerComponent(LAGER_PALETTE.id_artikel_bez,    		new LH_P_MASK_COMP_Palette_SearchArtbez());
		this.registerComponent(LAGER_PALETTE.id_lager_box,    			box_selectField);
		this.registerComponent(LAGER_PALETTE.id_lager_palette,    		new RB_lab());
		this.registerComponent(LAGER_PALETTE.id_vpos_tpa_fuhre_ein,		new LH_P_MASK_Comp_fuhreSearch(100,true));
		this.registerComponent(LAGER_PALETTE.id_vpos_tpa_fuhre_aus,		new LH_P_MASK_Comp_fuhreSearch(100,false));
		this.registerComponent(LAGER_PALETTE.ist_palette,    			new RB_cb());
		this.registerComponent(LAGER_PALETTE.bruttomenge,				new RB_TextField()._width(100));
		this.registerComponent(LAGER_PALETTE.taramenge,    				new RB_TextField()._width(100));
		this.registerComponent(LAGER_PALETTE.nettomenge,    			new RB_TextField()._width(100));
		this.registerComponent(LAGER_PALETTE.gepresst_von,    			sf_gepresst_von);
		this.registerComponent(LAGER_PALETTE.palettiert_von,    		sf_palettiert_von);
		this.registerComponent(LAGER_PALETTE.endkontrolle_von,    		sf_endkontrolle_von);
		this.registerComponent(LAGER_PALETTE.einbuchung_hand,  			new RB_cb());
		this.registerComponent(LAGER_PALETTE.ausbuchung_hand,  			new RB_cb());
		this.registerComponent(LAGER_PALETTE.id_adresse_einbuch_hand,  	new SearchAdresseAutomaticLastXXX());
		this.registerComponent(LAGER_PALETTE.id_adresse_ausbuch_hand,  	new SearchAdresseAutomaticLastXXX());
		
		this.registerComponent(LAGER_PALETTE.buchungsnr_hand,			new RB_TextField()._width(100));
		this.registerComponent(LAGER_PALETTE.material_zusatzinfo, 		new RB_TextField()._width(100));
		this.registerComponent(LAGER_PALETTE.hand_ausbuchung_bemerkung, new RB_TextField(400));
		
		
		this.registerComponent(new RB_KF(LH_CONST.MASK_KEY.LH_MASK_MENGE_HELP.k()), new LH_P_MASK_COMP_Help_Menge(this.m_tpHashMap));
		
		this.registerSetterValidators(LAGER_PALETTE.id_lager_palette,	new LH_P_Set_and_Valid_Palette(this.m_tpHashMap));    
		
		this.registerSetterValidators(LAGER_PALETTE.einbuchung_hand, new LH_P_MASK_Set_handbuchung(this.m_tpHashMap));
		
		this.registerSetterValidators(LAGER_PALETTE.ausbuchung_hand, new LH_P_MASK_Set_handbuchung(this.m_tpHashMap));
		
		//2020-11-17: neue mask-setter fuer die suchfelder bei ein- und ausbuchungen
		this.registerSetterValidators(LAGER_PALETTE.einbuchung_hand, new SetAndValidEinbuchungHand());
		this.registerSetterValidators(LAGER_PALETTE.ausbuchung_hand, new SetAndValidAusbuchungHand());
		
		
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
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
		MyE2_MessageVector mv =  new MyE2_MessageVector();

		preSettingsContainer.rb_get(LAGER_PALETTE.bruttomenge)			.set_MustField(true);
//		preSettingsContainer.rb_get(LAGER_PALETTE.id_vpos_tpa_fuhre_ein).set_MustField(true);
//		preSettingsContainer.rb_set_enabled(LAGER_PALETTE.id_artikel_bez, false);
		preSettingsContainer.rb_set_enabled(LAGER_PALETTE.hand_ausbuchung_bemerkung, false);

		if(status == MASK_STATUS.NEW || status == MASK_STATUS.NEW_COPY) {
			preSettingsContainer.rb_set_defaultMaskValue(LAGER_PALETTE.ist_palette, 	"Y");
			preSettingsContainer.rb_set_defaultMaskValue(LAGER_PALETTE.taramenge, 		"0");
	
			((RB_selField)this.get__Comp(LAGER_PALETTE.id_lager_box)).rb_set_db_value_manual(get_default_box());
		}

		return mv;
	}


	private String[][] get_lager_user_list(boolean isAktiv) throws myException{
		String[][] aRueck = null;

		String userQuery = new SEL()
				.ADDFIELD(USER.name1.tnfn() + "||' '||"+USER.vorname.tnfn())
				.ADDFIELD(LAGER_PALETTE_USER.id_user)
				.FROM(_TAB.lager_palette_user)
				.INNERJOIN(_TAB.user, USER.id_user, LAGER_PALETTE_USER.id_user)

				.s();

		And inaktiveAnd = new And();
		inaktiveAnd = new And(new TermSimple("NOT ("+new vgl(USER.aktiv, "Y").s()+")"));
		
		if(! isAktiv) {
			userQuery += (" AND " + inaktiveAnd.s());
		}
		
		aRueck = bibDB.EinzelAbfrageInArray(userQuery);
		if(aRueck == null || aRueck.length == 0) {
			aRueck = new String[1][2];
			aRueck[0]= new String[]{"-",""};
			
		}else {
		bibARR.add_emtpy_db_value_inFront(aRueck);
		}
		for(String[] t : aRueck) {
			t[1] = bibALL.convertID2FormattedID(t[1]);
		}

		return aRueck;
	}

	/**
	 * <b>WARNING</b> return formated value !</br>
	 * @author sebastien
	 * @date 09.05.2019
	 *
	 * @return
	 * @throws myException
	 */
	private String get_default_box() throws myException{
		RecList21 recList = new RecList21(_TAB.lager_box)._fill(new SEL().FROM(_TAB.lager_box).WHERE(new vgl_YN(LAGER_BOX.is_default_box, true)).s());
		if(recList != null && recList.size()==1) {
			return recList.get(0).getFs(LAGER_BOX.id_lager_box);
		}else if(recList != null && recList.size()==0) {
			return  new RecList21(_TAB.lager_box)._fill(new SEL().FROM(_TAB.lager_box).s()).get(0).getFs(LAGER_BOX.id_lager_box);
		}
		else {
			throw new myException("Mehr als 1 default Box sind definiert!");
		}
	}
	
	
	
	//neuer set-and-valid-agent fuer die neuen felder id_adresse_einbuch_hand / id_adresse_ausbuch_hand, registriert auf die checkbox
	private class SetAndValidEinbuchungHand extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = bibMSG.newMV();
			RB_MaskController mc = new RB_MaskController(LH_P_MASK_ComponentMap.this);
			boolean statusEinbuchungHand = mc.getBooleanValueFromScreen(LH_P_CONST.getLeadingMaskKey(), LAGER_PALETTE.einbuchung_hand);
			
			switch (ActionType) {
			case USE_IN_MASK_KLICK_ACTION:
				mc.get_comp(LAGER_PALETTE.id_adresse_einbuch_hand, mv).set_bEnabled_For_Edit(statusEinbuchungHand&&mc.isEditable());
				if (!statusEinbuchungHand) {
					((RB_HL_Search_V2_Adresse)mc.get_comp(LAGER_PALETTE.id_adresse_einbuch_hand, mv)).rb_set_db_value_manual("");
				} else {
					((LH_P_MASK_Comp_fuhreSearch) 	mc.get_comp(LAGER_PALETTE.id_vpos_tpa_fuhre_ein, mv)).rb_set_db_value_manual("");	
				}
				break;
			case USE_IN_MASK_LOAD_ACTION:
				mc.get_comp(LAGER_PALETTE.id_adresse_einbuch_hand, mv).set_bEnabled_For_Edit(statusEinbuchungHand&&mc.isEditable());
				break;
			case USE_IN_MASK_UNBOUND_KLICK_ACTION:
			case USE_IN_MASK_VALID_ACTION:
			case USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION:
				break;
			}
			return mv;
		}
	}
	
	
	
	//neuer set-and-valid-agent fuer die neuen felder id_adresse_einbuch_hand / id_adresse_ausbuch_hand, registriert auf die checkbox
	private class SetAndValidAusbuchungHand extends RB_Mask_Set_And_Valid {
		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = bibMSG.newMV();
			RB_MaskController mc = new RB_MaskController(LH_P_MASK_ComponentMap.this);
			boolean statusAusbuchungHand = mc.getBooleanValueFromScreen(LH_P_CONST.getLeadingMaskKey(), LAGER_PALETTE.ausbuchung_hand);
			
			switch (ActionType) {
			case USE_IN_MASK_KLICK_ACTION:
				mc.get_comp(LAGER_PALETTE.id_adresse_ausbuch_hand, mv).set_bEnabled_For_Edit(statusAusbuchungHand&&mc.isEditable());
				if (!statusAusbuchungHand) {
					((RB_HL_Search_V2_Adresse)		mc.get_comp(LAGER_PALETTE.id_adresse_ausbuch_hand, mv)).rb_set_db_value_manual("");
				} else {
					((LH_P_MASK_Comp_fuhreSearch) 	mc.get_comp(LAGER_PALETTE.id_vpos_tpa_fuhre_aus, mv)).rb_set_db_value_manual("");	
				}
				break;
			case USE_IN_MASK_LOAD_ACTION:
				mc.get_comp(LAGER_PALETTE.id_adresse_ausbuch_hand, mv).set_bEnabled_For_Edit(statusAusbuchungHand&&mc.isEditable());
				break;
			case USE_IN_MASK_UNBOUND_KLICK_ACTION:
			case USE_IN_MASK_VALID_ACTION:
			case USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION:
				break;
			}
			return mv;
		}
	}
	
	
	
	
	
	private class SearchAdresseAutomaticLastXXX extends RB_HL_Search_V2_Adresse {

		SEL lief = new SEL(new FieldTerm(VPOS_TPA_FUHRE.id_adresse_start,"ID"), new FieldTerm(VPOS_TPA_FUHRE.datum_abholung,"DATUM")).FROM(_TAB.vpos_tpa_fuhre);
		SEL abn =  new SEL(new FieldTerm(VPOS_TPA_FUHRE.id_adresse_ziel,"ID"), new FieldTerm(VPOS_TPA_FUHRE.datum_anlieferung,"DATUM")).FROM(_TAB.vpos_tpa_fuhre);
		
		public SearchAdresseAutomaticLastXXX() throws myException {
			super();
			_setFindOnlyMainAdresses(true);
			_setAllowEmptySearchfield(true);
			
			this.getButtonStartSearch()._ttt(S.ms("Suche nach Firmenhaupt-Adressen (bie leerer Eingabe werden die letzen "+lastCountToShow+" lenutzen Fuhren-Adressen angezeigt!"));
			
			String block1 = "SELECT ID FROM ("+lief.s()+" UNION "+abn.s()+") ORDER BY DATUM DESC";
			String block2 = "SELECT DISTINCT ID FROM ("+block1+")";
			String block3 = "SELECT ID FROM ("+block2+") WHERE ROWNUM<"+lastCountToShow;
			String   where = 	"("+ADRESSE.id_adresse.tnfn()+" IN ("+block3+"))";
			
			getTextFieldSearchInput()._w(100);
			
			this._registerBeforeStartSearchEvent(()-> {
				if (isSearchFieldEmpty()) {
					this.getAndStatementCollectorOneTimeCondition().add(new TermSimple(where));
				}
			});

			
		}
		
	}
	
	
}


